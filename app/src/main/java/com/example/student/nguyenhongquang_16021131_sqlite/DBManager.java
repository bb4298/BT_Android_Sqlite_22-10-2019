package com.example.student.nguyenhongquang_16021131_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="student.sqlite";
    private static final String TABLE_NAME ="student";
    private static final String ID ="id";
    private static final String NAME ="name";

    private Context context;
    public DBManager(Context context) {
        super(context, DATABASE_NAME,null, 1);
       // Log.d("DBManager", "DBManager: ");
       // this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE "+TABLE_NAME +" (" +
                ID +" integer primary key, "+
                NAME + " TEXT)";

        db.execSQL(sqlQuery);
        Toast.makeText(context, "Create successfylly", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
      onCreate(db);
        Toast.makeText(context, "Drop successfylly", Toast.LENGTH_SHORT).show();
    }



    //Add new a student
    public void addStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, student.getName());


        //Neu de null thi khi value bang null thi loi

        db.insert(TABLE_NAME,null,values);

        db.close();
    }

    /*
    Select a student by ID
     */

    public Student getSdtudentById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] { ID,
                        NAME }, ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Student student = new Student(cursor.getString(1));
        cursor.close();
        db.close();
        return student;
    }

    /*
    Update name of student
     */

    public int Update(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME,student.getName());

        return db.update(TABLE_NAME,values,ID +"=?",new String[] { String.valueOf(student.getId())});


    }

    /*
     Getting All Student
      */

    public ArrayList<Student> getAllStudent() {
        ArrayList<Student> listStudent = new ArrayList<Student>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                listStudent.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listStudent;
    }
    /*
    Delete a student by ID
     */
    public void deleteStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID + " = ?",
                new String[] { String.valueOf(student.getId()) });
        db.close();
    }
    /*
    Get Count Student in Table Student
     */
    public int getStudentsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        // return count
        return cursor.getCount();
    }

}
