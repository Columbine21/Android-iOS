package chapter.android.aweme.ss.com.homework;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ChattingRoom extends AppCompatActivity implements View.OnClickListener{

    private String title;
    private String contacter;

    private TextView titleWithName;
    private TextView chatBox;
    private EditText messageBox;
    private ImageView sendButton;

    private Handler handler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        titleWithName = findViewById(R.id.tv_with_name);
        chatBox = findViewById(R.id.tv_content_info);
        messageBox = findViewById(R.id.ed_say);
        sendButton = findViewById(R.id.btn_send_info);

        handler = new Handler();

        Bundle bundle = getIntent().getExtras();
        String history = bundle.getString("history");
        contacter = bundle.getString("contacter");
        showMessage(history, contacter);
        title = "去和" + contacter + "聊天";
        titleWithName.setText(title);
        sendButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d("YUANZIQI", "click something");
        if(v.getId() == R.id.btn_send_info) {
            Log.d("YUANZIQI", "send button click");
            showMessage(messageBox.getText().toString(), "我");
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showMessage(messageBox.getText().toString(), contacter);
                }
            }, 1500);
        }
    }

    private void showMessage(String message, String speaker){
        StringBuilder builder = new StringBuilder();
        builder.append(speaker);
        builder.append(": ");
        builder.append(message);
        builder.append("\n");
        chatBox.append(builder.toString());
    }

}
