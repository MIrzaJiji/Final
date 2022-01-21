package com.example.afinal

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.afinal.fragments.Home


class DataBaseHandler(val context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "MyDB"
        val TABLE_NAME = "TodoList"
        val COL_TITLE = "title"
        val COL_DESCRIPTION = "description"
        val COL_ID = "id"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TITLE + " VARCHAR(256)," +
                COL_DESCRIPTION + " VARCHAR(256))";
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)

    }

    fun insertData(todolist: TodoList) {
        val values = ContentValues()
        values.put(COL_TITLE, todolist.title)
        values.put(COL_DESCRIPTION, todolist.description)
        val db = this.writableDatabase
        var result = db.insert(TABLE_NAME, null, values)
        db.close()
        if (result == -1.toLong()) {
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
        }
    }

    fun getTitle(): MutableList<TodoList> {
        var list: MutableList<TodoList> = ArrayList()

        val db = this.readableDatabase
        val query = "Select title  from " + TABLE_NAME
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {
                var todolist = TodoList()
                todolist.title = result.getString(result.getColumnIndex(COL_TITLE)).toString()
                list.add(todolist)
            } while (result.moveToNext())


        }
        return list
    }

    fun getDescription(): MutableList<TodoList> {
        var list: MutableList<TodoList> = ArrayList()

        val db = this.readableDatabase
        val query = "Select description  from " + TABLE_NAME
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {
                var todolist = TodoList()
                todolist.title = result.getString(result.getColumnIndex(COL_DESCRIPTION)).toString()
                list.add(todolist)
            } while (result.moveToNext())


        }
        return list
    }

    fun readData(): ArrayList<String> {
        var list: ArrayList<String> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {
                var todolist = TodoList()
                todolist.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                todolist.title = result.getString(result.getColumnIndex(COL_TITLE)).toString()
                todolist.description =
                    result.getString(result.getColumnIndex(COL_DESCRIPTION)).toString()

                list.add(todolist.toString())
            } while (result.moveToNext())


        }
        return list
    }

    fun getAllData(): ArrayList<String> {
        val list = ArrayList<String>()

        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        do {
            var todolist = TodoList()
            todolist.id = cursor.getString(cursor.getColumnIndex(COL_ID)).toInt()
            todolist.title = cursor.getString(cursor.getColumnIndex(COL_TITLE)).toString()
            todolist.description =
                cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)).toString()

            list.add(todolist.toString())
        } while (cursor.moveToNext())

        cursor.close()
        return list
    }
}
