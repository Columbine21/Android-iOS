package com.byted.camp.todolist.beans;

import android.graphics.Color;

/**
 * Created on 2020/4/28.
 *
 * @author Jason Yuan(1564123490@qq.com)
 */

public enum Priority {
    LOW(2), MEDIUM(1), HIGH(0);

    public final int intValue;

    Priority(int intValue){
        this.intValue = intValue;
    }

    public static Priority from(int intValue) {
        for(Priority priority : Priority.values()) {
            if(priority.intValue == intValue)
                return priority;
        }
        return LOW;
    }

    public static int getColor(Priority priority) {
        switch (priority){
            case MEDIUM:
                return Color.GREEN;
            case HIGH:
                return Color.RED;
            default:
                return Color.WHITE;
        }
    }
}
