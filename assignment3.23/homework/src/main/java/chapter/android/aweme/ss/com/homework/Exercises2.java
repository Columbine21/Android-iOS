package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Queue;


/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
public class Exercises2 extends AppCompatActivity {

    TextView CountView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exercise2);
        CountView = (TextView) findViewById(R.id.tv_center);
        int counter = getAllChildViewCount((View) this.findViewById(android.R.id.content));
        CountView.setText(String.valueOf(counter));
    }

    public int getAllChildViewCount(View view) {
        if(view == null) return 0;
        int viewCount = 0;
        Queue<View> queue = new LinkedList<View>();
        queue.offer(view);
        while(!queue.isEmpty()) {
            View curView = queue.poll();
            if(curView instanceof ViewGroup) {
                for(int i = 0; i < ((ViewGroup) curView).getChildCount(); ++i) {
                    queue.offer(((ViewGroup) curView).getChildAt(i));
                }
            }else {
                viewCount += 1;
            }
        }
        return viewCount;
    }
}
