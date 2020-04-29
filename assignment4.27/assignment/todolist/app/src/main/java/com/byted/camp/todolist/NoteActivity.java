package com.byted.camp.todolist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.byted.camp.todolist.beans.Priority;
import com.byted.camp.todolist.beans.State;
import com.byted.camp.todolist.db.TodoContract;
import com.byted.camp.todolist.db.TodoDbHelper;

public class NoteActivity extends AppCompatActivity {

    private EditText editText;
    private Button addBtn;
    private RadioGroup priorityGroup;
    private Priority prioritySelect;

    private HandlerThread mWorkThread;
    private WorkHander mWorkHander;

    static private boolean executeState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        setTitle(R.string.take_a_note);

        editText = findViewById(R.id.edit_text);
        editText.setFocusable(true);
        editText.requestFocus();
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.showSoftInput(editText, 0);
        }

        addBtn = findViewById(R.id.btn_add);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence content = editText.getText();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(NoteActivity.this,
                            "No content to add", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean succeed = saveNote2Database(content.toString().trim());
                if (succeed) {
                    Toast.makeText(NoteActivity.this,
                            "Note added", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_OK);
                } else {
                    Toast.makeText(NoteActivity.this,
                            "Error", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
        priorityGroup = findViewById(R.id.radiogroup);
        priorityGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                RadioButton radioButton = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
                int choice = 0;

                switch (radioButton.getText().toString()){
                    case "High":
                        choice = 0;
                        break;
                    case "Medium":
                        choice = 1;
                        break;
                    case "Low":
                        choice = 2;
                        break;
                }
                prioritySelect = Priority.from(choice);

                Log.d("Radio Change:", String.valueOf(i) + String.valueOf(prioritySelect));
            }
        });

        mWorkThread = new HandlerThread("operation_database");
        mWorkThread.start();
        mWorkHander = new WorkHander(mWorkThread.getLooper());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWorkThread.quitSafely();
    }

    private boolean saveNote2Database(String content) {

        Message message = new Message();
        message.what = WorkHander.MSG_ADD_DATA;
        message.obj = content;
        mWorkHander.sendMessage(message);
        return true;
    }

    private boolean addData(String content) {
        TodoDbHelper dbHelper = new TodoDbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TodoContract.TodoItem.COLUMN_NAME_CONTENT, content);
        values.put(TodoContract.TodoItem.COLUMN_NAME_DATE, System.currentTimeMillis());
        values.put(TodoContract.TodoItem.COLUMN_NAME_PRIORITY, prioritySelect.intValue);
        values.put(TodoContract.TodoItem.COLUMN_NAME_STATE, State.TODO.intValue);
        return (db.insert(TodoContract.TodoItem.TABLE_NAME, null, values) != -1);

    }
    private class WorkHander extends Handler {

        public static final int MSG_ADD_DATA = 1;

        public WorkHander(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message message){
            super.handleMessage(message);
            switch (message.what){
                case MSG_ADD_DATA:
                    String content = (String) message.obj;
                    executeState = addData(content);
                    Log.d("HandleMessage ", String.valueOf(executeState));
                    Log.d("HandleMessage ", content);
                    break;
                default:
                    break;
            }
        }
    }
}
