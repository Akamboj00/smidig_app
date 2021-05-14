package com.example.report_app.database

import android.provider.BaseColumns

object DatabaseInfo {
    const val SQL_CREATE_TABLE_PROGRESS_QUERY =
        "CREATE TABLE ${TableProgress.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${TableProgress.COLUMN_ID} TEXT DEFAULT id," +
                "${TableProgress.COLUMN_CREATED} INTEGER DEFAULT 0," +
                "${TableProgress.COLUMN_PROGRESS_1} REAL DEFAULT 0.00," +
                "${TableProgress.COLUMN_PROGRESS_2} REAL DEFAULT 0.00," +
                "${TableProgress.COLUMN_PROGRESS_3} REAL DEFAULT 0.00," +
                "${TableProgress.COLUMN_PROGRESS_4} REAL DEFAULT 0.00," +
                "${TableProgress.COLUMN_PROGRESS_5} REAL DEFAULT 0.00," +
                "${TableProgress.COLUMN_PROGRESS_6} REAL DEFAULT 0.00)"

    const val SQL_DELETE_TABLE_PROGRESS_QUERY = "DROP TABLE IF EXISTS ${TableProgress.TABLE_NAME}"
    object TableProgress: BaseColumns{
        const val TABLE_NAME = "progressData"
        const val COLUMN_ID = "progressID"
        const val COLUMN_CREATED = "created"
        const val COLUMN_PROGRESS_1 = "progress1"
        const val COLUMN_PROGRESS_2 = "progress2"
        const val COLUMN_PROGRESS_3 = "progress3"
        const val COLUMN_PROGRESS_4 = "progress4"
        const val COLUMN_PROGRESS_5 = "progress5"
        const val COLUMN_PROGRESS_6 = "progress6"
    }
}