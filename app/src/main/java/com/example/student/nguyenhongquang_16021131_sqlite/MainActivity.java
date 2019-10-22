package com.example.student.nguyenhongquang_16021131_sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DBManager db = new DBManager(this);
    private ArrayAdapter<String> adapter;
    private ArrayList<Student> list;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db.addStudent(new Student(1,"Quang"));
        db.addStudent(new Student(2,"Hoa"));
        db.addStudent(new Student(3,"Tien"));
        db.addStudent(new Student(4,"Trang"));


        Toast.makeText(this,"Them thanh cong",Toast.LENGTH_SHORT).show();

        listView = (ListView) findViewById(R.id.lv_main);
        list = db.getAllStudent();

        ArrayList<String> listshow = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listshow);
        listView.setAdapter(adapter);

        for (int i= 0;i<db.getStudentsCount();i++)
        {
            String show = "ID: " + Integer.toString(list.get(i).getId()) + "--" + "Ten: " + list.get(i).getName();
            listshow.add(show);
        }




    }

}
