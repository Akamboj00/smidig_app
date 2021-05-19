package com.example.report_app.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        const val DATABASE_NAME = "brightApp.db"
        const val DATABASE_VERSION = 40
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DatabaseInfo.SQL_CREATE_TABLE_PROGRESS_QUERY)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DatabaseInfo.SQL_DELETE_TABLE_PROGRESS_QUERY)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }

    fun setProgress(){
        if(getProgress.created == 1) return
        val db = this.writableDatabase
        val values = ContentValues()
        val progress =  ProgressTemplate()
        progress.id = "progressID"
        progress.progress1 = 0.00f
        progress.progress2 = 0.00f
        progress.progress3 = 0.00f
        progress.progress4 = 0.00f
        progress.progress5 = 0.00f
        progress.progress6 = 0.00f
        values.put(DatabaseInfo.TableProgress.COLUMN_ID, progress.id)
        values.put(DatabaseInfo.TableProgress.COLUMN_CREATED, 1)
        values.put(DatabaseInfo.TableProgress.COLUMN_PROGRESS_1, progress.progress1)
        values.put(DatabaseInfo.TableProgress.COLUMN_PROGRESS_1, progress.progress1)
        values.put(DatabaseInfo.TableProgress.COLUMN_PROGRESS_2, progress.progress2)
        values.put(DatabaseInfo.TableProgress.COLUMN_PROGRESS_3, progress.progress3)
        values.put(DatabaseInfo.TableProgress.COLUMN_PROGRESS_4, progress.progress4)
        values.put(DatabaseInfo.TableProgress.COLUMN_PROGRESS_5, progress.progress5)
        values.put(DatabaseInfo.TableProgress.COLUMN_PROGRESS_6, progress.progress6)
        db.insert(DatabaseInfo.TableProgress.TABLE_NAME, null, values)
        db.close()
    }

    val getProgress:ProgressTemplate
    @SuppressLint("Recycle")
    get(){
        var progressData = ProgressTemplate()
        val selectQuery = "SELECT * FROM " + DatabaseInfo.TableProgress.TABLE_NAME
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if(cursor.moveToFirst())
        {
            do{
                val progress = ProgressTemplate()
                progress.id = cursor.getString(cursor.getColumnIndex(DatabaseInfo.TableProgress.COLUMN_ID))
                progress.created = cursor.getInt(cursor.getColumnIndex(DatabaseInfo.TableProgress.COLUMN_CREATED))
                progress.progress1 = cursor.getFloat(cursor.getColumnIndex(DatabaseInfo.TableProgress.COLUMN_PROGRESS_1))
                progress.progress2 = cursor.getFloat(cursor.getColumnIndex(DatabaseInfo.TableProgress.COLUMN_PROGRESS_2))
                progress.progress3 = cursor.getFloat(cursor.getColumnIndex(DatabaseInfo.TableProgress.COLUMN_PROGRESS_3))
                progress.progress4 = cursor.getFloat(cursor.getColumnIndex(DatabaseInfo.TableProgress.COLUMN_PROGRESS_4))
                progress.progress5 = cursor.getFloat(cursor.getColumnIndex(DatabaseInfo.TableProgress.COLUMN_PROGRESS_5))
                progress.progress6 = cursor.getFloat(cursor.getColumnIndex(DatabaseInfo.TableProgress.COLUMN_PROGRESS_6))
                progressData = progress
            } while (cursor.moveToNext())
        }
        db.close()
        return progressData
    }

    fun updateProgress(progressData:ProgressTemplate): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(DatabaseInfo.TableProgress.COLUMN_PROGRESS_1, progressData.progress1)
        values.put(DatabaseInfo.TableProgress.COLUMN_PROGRESS_2, progressData.progress2)
        values.put(DatabaseInfo.TableProgress.COLUMN_PROGRESS_3, progressData.progress3)
        values.put(DatabaseInfo.TableProgress.COLUMN_PROGRESS_4, progressData.progress4)
        values.put(DatabaseInfo.TableProgress.COLUMN_PROGRESS_5, progressData.progress5)
        values.put(DatabaseInfo.TableProgress.COLUMN_PROGRESS_6, progressData.progress6)
        return db.update(DatabaseInfo.TableProgress.TABLE_NAME, values, "${progressData.id} = ?", arrayOf(progressData.id.toString()))
    }

}

class ProgressTemplate{
    var id: String ?= null
    var created: Int ?= null
    var progress1: Float ?= null
    var progress2: Float ?= null
    var progress3: Float ?= null
    var progress4: Float ?= null
    var progress5: Float ?= null
    var progress6: Float ?= null

    constructor(){}

    constructor(id:String, created:Int, progress1:Float, progress2:Float, progress3:Float, progress4:Float, progress5:Float, progress6:Float)
    {
        this.id = id
        this.created = created
        this.progress1 = progress1
        this.progress2 = progress2
        this.progress3 = progress3
        this.progress4 = progress4
        this.progress5 = progress5
        this.progress6 = progress6
    }
}