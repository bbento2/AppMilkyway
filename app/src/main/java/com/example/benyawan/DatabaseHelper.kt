package com.example.benyawan

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "user_db" //ชื่อdatabase
        private const val DATABASE_VERSION = 1
        private const val TABLE_USERS = "users"//ชื่อตารางเก็บข้อมูลผู้ใช้ ชื่อ users

        // Table columns
        private const val COLUMN_ID = "id"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_AGE = "age"
        private const val COLUMN_BIRTHDAY = "birthday"
        private const val COLUMN_GENDER = "gender"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
    }

    //ชนิดของข้อมูลที่จะกรอกลงไปในแต่ละ column โดยให้ column id เป็น integer primary keyที่เพิ่มค่าโดยอัตโนมัติ
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_USERS_TABLE = """
            CREATE TABLE $TABLE_USERS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_EMAIL TEXT,
                $COLUMN_NAME TEXT,
                $COLUMN_AGE INTEGER,
                $COLUMN_BIRTHDAY TEXT,
                $COLUMN_GENDER TEXT,
                $COLUMN_USERNAME TEXT,
                $COLUMN_PASSWORD TEXT
            );
        """
        db?.execSQL(CREATE_USERS_TABLE)
    }

    //จะอัพข้อมูลเมื่อมีการเพิ่มข้อมูลลงในฐานข้อมูลในทุกๆครั้ง
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    // เพิ่มข้อมูลผู้ใช้ลงในตาราง
    fun addUser(email: String, name: String, age: Int, birthday: String, gender: String, username: String, password: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_EMAIL, email)
            put(COLUMN_NAME, name)
            put(COLUMN_AGE, age)
            put(COLUMN_BIRTHDAY, birthday)
            put(COLUMN_GENDER, gender)
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, password)
        }
        try { //เพิ่มข้อมูลเข้าไปในตาราง
            val result = db.insert(TABLE_USERS, null, values)
            if (result == -1L) { //ถ้าเพิ่มข้อมูลลงในตารางไม่สำเร็จจะคืนค่า -1
                Log.e("DatabaseHelper", "Failed to insert user")
            } else {//ถ้าเพิ่มข้อมูลสำเร็จ แถว ID เพิ่มขึ้นมา 1
                Log.d("DatabaseHelper", "User inserted successfully")
            }
        } catch (e: Exception) {
            Log.e("DatabaseHelper", "Error inserting user: ${e.message}")
        } finally {
            db.close()
        }
    }

    


    // ดึงข้อมูลของ users ทั้งหมด แล้วข้อมูลจะโชว์ในรูปแบบตารางใน sqlite
    fun getAllUsers(): List<String> {
        val users = mutableListOf<String>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_USERS", null)
        if (cursor.moveToFirst()) {
            do {
                val user = "Email: ${cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))}, Name: ${cursor.getString(cursor.getColumnIndex(COLUMN_NAME))}"
                users.add(user)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return users
    }
}
