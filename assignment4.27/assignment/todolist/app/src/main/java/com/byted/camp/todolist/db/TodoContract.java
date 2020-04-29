package com.byted.camp.todolist.db;

import android.database.Cursor;
import android.provider.BaseColumns;

/**
 * Created on 2019/1/22.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */
public final class TodoContract {

    // TODO 定义表结构和 SQL 语句常量
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TodoItem.TABLE_NAME + " (" +
                    TodoItem._ID + " INTEGER PRIMARY KEY," +
                    TodoItem.COLUMN_NAME_PRIORITY+ " INTEGER," +
                    TodoItem.COLUMN_NAME_DATE+ " DATETIME," +
                    TodoItem.COLUMN_NAME_STATE+ " INTEGER," +
                    TodoItem.COLUMN_NAME_CONTENT + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TodoItem.TABLE_NAME;

    private TodoContract() {
    }

    public static class TodoItem implements BaseColumns {

        public static final String TABLE_NAME = "itemList";

        public static final String COLUMN_NAME_PRIORITY = "priority";

        public static final String COLUMN_NAME_DATE = "date";

        public static final String COLUMN_NAME_STATE = "state";

        public static final String COLUMN_NAME_CONTENT = "content";
    }
}
