package com.byted.camp.todolist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.byted.camp.todolist.beans.Note;
import com.byted.camp.todolist.beans.Priority;
import com.byted.camp.todolist.beans.State;
import com.byted.camp.todolist.db.TodoContract;
import com.byted.camp.todolist.db.TodoDbHelper;
import com.byted.camp.todolist.operation.activity.DatabaseActivity;
import com.byted.camp.todolist.operation.activity.DebugActivity;
import com.byted.camp.todolist.operation.activity.SettingActivity;
import com.byted.camp.todolist.ui.NoteListAdapter;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD = 1002;

    private static final String TAG = "TodoListDB";

    private RecyclerView recyclerView;
    private NoteListAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(
                        new Intent(MainActivity.this, NoteActivity.class),
                        REQUEST_CODE_ADD);
            }
        });

        recyclerView = findViewById(R.id.list_todo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        notesAdapter = new NoteListAdapter(new NoteOperator() {
            @Override
            public void deleteNote(Note note) {
                MainActivity.this.deleteNote(note);
            }

            @Override
            public void updateNote(Note note) {
                MainActivity.this.updateNode(note);
            }
        });
        recyclerView.setAdapter(notesAdapter);

        notesAdapter.refresh(loadNotesFromDatabase());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingActivity.class));
                return true;
            case R.id.action_debug:
                startActivity(new Intent(this, DebugActivity.class));
                return true;
            case R.id.action_database:
                startActivity(new Intent(this, DatabaseActivity.class));
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD
                && resultCode == Activity.RESULT_OK) {
            notesAdapter.refresh(loadNotesFromDatabase());
        }
    }

    private List<Note> loadNotesFromDatabase() {
        // TODO 从数据库中查询数据，并转换成 JavaBeans
        List<Note> curTodoList = new ArrayList<>();

        TodoDbHelper dbHelper = new TodoDbHelper(this);
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getReadableDatabase();;

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                TodoContract.TodoItem.COLUMN_NAME_PRIORITY + " ASC";

        Cursor cursor = db.query(
                TodoContract.TodoItem.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                sortOrder
        );

        Log.i(TAG, "perfrom query data:");
        if(cursor != null) {
            while (cursor.moveToNext()) {

                Note todoItemTmp = new Note(cursor.getLong(
                        cursor.getColumnIndexOrThrow(TodoContract.TodoItem._ID)));
                long DateTmp =cursor.getLong(
                        cursor.getColumnIndexOrThrow(TodoContract.TodoItem.COLUMN_NAME_DATE));
                int state = cursor.getInt(
                        cursor.getColumnIndexOrThrow(TodoContract.TodoItem.COLUMN_NAME_STATE));
                int priority = cursor.getInt(
                        cursor.getColumnIndexOrThrow(TodoContract.TodoItem.COLUMN_NAME_PRIORITY));
                String content = cursor.getString(
                        cursor.getColumnIndexOrThrow(TodoContract.TodoItem.COLUMN_NAME_CONTENT));
                todoItemTmp.setContent(content);
                todoItemTmp.setPriority(Priority.from(priority));
                todoItemTmp.setState(State.from(state));
                todoItemTmp.setDate(new Date(DateTmp));
                curTodoList.add(todoItemTmp);
            }
        }
        cursor.close();
        return curTodoList;
    }

    private void deleteNote(Note note) {
        // TODO 删除数据
        TodoDbHelper dbHelper = new TodoDbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Define 'where' part of query.
        String selection = TodoContract.TodoItem._ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = new String[]{String.valueOf(note.id)};

        // Issue SQL statement.
        if(db.delete(TodoContract.TodoItem.TABLE_NAME, selection, selectionArgs) != -1)
        {
            Log.i(TAG, "perform delete data");
            notesAdapter.refresh(loadNotesFromDatabase());
        }
        db.close();
    }

    private void updateNode(Note note) {
        // 更新数据
        TodoDbHelper dbHelper = new TodoDbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TodoContract.TodoItem.COLUMN_NAME_STATE, note.getState().intValue);

        // Define 'where' part of query.
        String selection = TodoContract.TodoItem._ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = new String[]{String.valueOf(note.id)};

        if(db.update(TodoContract.TodoItem.TABLE_NAME, values, selection, selectionArgs) != -1) {
            Log.i(TAG, "perform update data");
            notesAdapter.refresh(loadNotesFromDatabase());
        }
        db.close();
    }
}
