package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 作业1：
 * Logcat在屏幕旋转的时候 #onStop() #onDestroy()会展示出来
 * 但我们的 mLifecycleDisplay 由于生命周期的原因(Tips:执行#onStop()之后，UI界面我们是看不到的)并没有展示
 * 在原有@see Exercises1 基础上如何补全它，让其跟logcat的展示一样?
 * <p>
 * Tips：思考用比Activity的生命周期要长的来存储？  （比如：application、static变量）
 */
public class Exercises1 extends AppCompatActivity {

    private TextView LifecycleDisplay;

    private static final String TAG = "yuanziqi";
    private static final String ON_CREATE = "onCreate";
    private static final String ON_START = "onStart";
    private static final String ON_RESUME = "onResume";
    private static final String ON_PAUSE = "onPause";
    private static final String ON_STOP = "onStop";
    private static final String ON_RESTART = "onRestart";
    private static final String ON_DESTROY = "onDestroy";
    private static final String ON_SAVE_INSTANCE_STATE = "onSaveInstanceState";
    private static final String ON_RESTORE_INSTANCE_STATE = "onRestoreInstanceState";
    private static final String CALLBACK = "Lifecycle callbacks:";

    private static final ArrayList<String> liveEventRecord = new ArrayList<String>();

    public void resetLifecycleDisplay(View view) {
        StringBuilder builder = new StringBuilder();
        builder.append(CALLBACK);
        builder.append("\n");
        LifecycleDisplay.setText(builder.toString());
        Log.d(TAG, "resetLifecycleDisplay\n");
        liveEventRecord.clear();
        liveEventRecord.add(builder.toString());
    }

    private void logAndAppend(String event){
        StringBuilder builder = new StringBuilder();
        builder.append(event);
        builder.append("\n");
        LifecycleDisplay.append(builder.toString());
        Log.d(TAG, builder.toString());
        liveEventRecord.add(builder.toString());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise1);
        LifecycleDisplay = (TextView) findViewById(R.id.tv_loglifecycle);
        if(savedInstanceState != null) {
            for(String record: liveEventRecord) {
                LifecycleDisplay.append(record);
            }
        }else {
            liveEventRecord.clear();
        }
        logAndAppend(ON_CREATE);
    }

    @Override
    protected void onStart(){
        super.onStart();
        logAndAppend(ON_START);
    }

    @Override
    protected void onResume(){
        super.onResume();
        logAndAppend(ON_RESUME);
    }

    @Override
    protected void onPause(){
        super.onPause();
        logAndAppend(ON_PAUSE);
    }

    @Override
    protected void onStop(){
        super.onStop();
        logAndAppend(ON_STOP);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        logAndAppend(ON_RESTART);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        logAndAppend(ON_DESTROY);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        logAndAppend(ON_SAVE_INSTANCE_STATE);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        logAndAppend(ON_RESTORE_INSTANCE_STATE);
    }

}
