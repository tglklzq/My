package com.example.myapplication5;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText etAccount, etPassword;
    private Button btnLogin, btnRegister;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);

        etAccount = findViewById(R.id.etAccount);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegisterButtonClick();
            }
        });
    }

    private void loginUser() {
        String account = etAccount.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (account.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "请填写账号和密码", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String selection = "account=? AND password=?";
        String[] selectionArgs = {account, password};

        Cursor cursor = db.query("users", null, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            // 登录成功
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();

            // 获取用户信息
            int userId = cursor.getInt(cursor.getColumnIndex("user_id"));
            String userName = cursor.getString(cursor.getColumnIndex("user_name"));
            String gender = cursor.getString(cursor.getColumnIndex("gender"));
            double height = cursor.getDouble(cursor.getColumnIndex("height"));
            double weight = cursor.getDouble(cursor.getColumnIndex("weight"));
            String userImage = cursor.getString(cursor.getColumnIndex("user_image"));

            // 创建 User 对象
            User loggedInUser = new User(userId, account, password, userName, gender, height, weight, userImage);

            // 跳转到主界面，并传递用户信息
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("user", loggedInUser);
            startActivity(intent);
            finish(); // 关闭当前登录界面，防止用户按返回键返回到登录界面
        } else {
            // 登录失败
            Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
        db.close();
    }

    private void onRegisterButtonClick() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
