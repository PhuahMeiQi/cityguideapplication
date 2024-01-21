package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Penang_Forum_Write extends AppCompatActivity
{
    EditText edName,edTitle,edCon;
    User user;
    Button btn_submit,btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penang__forum__write);
        initView();

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUserInput();
                finish();
            }
        });

    }

    private void registerUserInput()
    {
        String Name,Title,Content;
        Name = edName.getText().toString();
        Title = edTitle.getText().toString();
        Content = edCon.getText().toString();
        //error checking here
        user = new User(Name,Title,Content);
        insertData();
        //registerUserDialog();
    }

    private void insertData(){
        class RegisterUser extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Penang_Forum_Write.this,
                        "Submit", "Thanks for submit !", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("Name", user.Name);
                hashMap.put("Title", user.Title);
                hashMap.put("Content", user.Content);
                Request_Handler rh = new Request_Handler();
                String s = rh.sendPostRequest
                        ("http://tommyworkspace.com/testing/blog.php", hashMap);
                return s;
            }

            protected void onPostExecute(String s)
            {
                super.onPostExecute(s);
                loading.dismiss();
                if (s.equalsIgnoreCase("success")) {
                    Toast.makeText(Penang_Forum_Write.this, "Submit Success", Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    //RegisterActivity.this.finish();
                    //startActivity(intent);
                }

                else if (s.equalsIgnoreCase("nodata"))
                {
                    Toast.makeText(Penang_Forum_Write.this, "Please fill in data first", Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(Penang_Forum_Write.this, "Submit Failed", Toast.LENGTH_SHORT).show();
                }
            }

        }
        RegisterUser registerUser = new RegisterUser();
        registerUser.execute();
    }

    private void initView()
    {
        edName = findViewById(R.id.ed_name);
        edTitle = findViewById(R.id.ed_title);
        edCon = findViewById(R.id.ed_content);
        btn_submit = findViewById(R.id.btn_submit);
    }
}
