package com.example.room;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.room.database.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText txtNhap;
    Button btnAdd,btnRemove;
    private ArrayList<User> userList;
    private  ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    View updateview ;// above oncreate method
    String nameSelect="";
    ListView lstDanhSach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNhap = findViewById(R.id.txtNhap);
        btnAdd = findViewById(R.id.btnAdd);
        btnRemove = findViewById(R.id.btnRemove);
        lstDanhSach = findViewById(R.id.lstDanhSach);
        userList = new ArrayList<>();
        arrayList = new ArrayList<>();
        userList =(ArrayList<User>) UserDatabase.getInstance(MainActivity.this).userDAO().getListUser();
        for (User u : userList) {
            arrayList.add(u.getName());
        }
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , arrayList);
        lstDanhSach.setAdapter(arrayAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
                Toast.makeText(MainActivity.this, "Add ok", Toast.LENGTH_SHORT).show();
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove();
                Toast.makeText(MainActivity.this, "Remove OK", Toast.LENGTH_SHORT).show();
            }
        });

        lstDanhSach.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lstDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (updateview != null)
                    updateview.setBackgroundColor(Color.TRANSPARENT);
                updateview = view;
                nameSelect = lstDanhSach.getItemAtPosition(position).toString();
                view.setBackgroundColor(Color.YELLOW);
            }
        });
    }
    void add(){
        String strName = txtNhap.getText().toString().trim();
        User user = new User(strName);
        UserDatabase.getInstance(MainActivity.this).userDAO().insertUser(user);
        arrayAdapter.add(user.getName());

    }
    void remove(){
        User user = new User(nameSelect);
        UserDatabase.getInstance(MainActivity.this).userDAO().deleteUser(nameSelect);
        arrayAdapter.remove(user.getName());
    }

}