package com.example.myapplication5;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {
    private View view;
    private EditText etUserId, etAccount, etUserName, etGender, etHeight, etWeight;
    private Button btnSaveChanges;

    private DatabaseHelper databaseHelper;
    private User currentUser;

    private ImageView ivProfileImage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        databaseHelper = new DatabaseHelper(getContext());

        etUserId = view.findViewById(R.id.etUserId);
        etAccount = view.findViewById(R.id.etAccount);
        etUserName = view.findViewById(R.id.etUserName);
        etGender = view.findViewById(R.id.etGender);
        etHeight = view.findViewById(R.id.etHeight);
        etWeight = view.findViewById(R.id.etWeight);
        btnSaveChanges = view.findViewById(R.id.btnSaveChanges);




        // 获取当前用户信息
        int userId = getCurrentUserId(); // 调用获取当前用户ID的方法
        currentUser = databaseHelper.getUserData(userId);

        // 显示用户信息
        displayUserInfo();

        // 设置保存修改的按钮点击事件
        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });

        return view;
    }

    private int getCurrentUserId() {
        // ... (根据实际逻辑获取当前用户ID)
        return 1; // 假设默认返回用户ID为1
    }

    private void displayUserInfo() {
        etUserId.setText(String.valueOf(currentUser.getUserId()));
        etAccount.setText(currentUser.getAccount());
        etUserName.setText(currentUser.getUserName());
        etGender.setText(currentUser.getGender());
        etHeight.setText(String.valueOf(currentUser.getHeight()));
        etWeight.setText(String.valueOf(currentUser.getWeight()));
    }




    private void saveChanges() {
        // 获取修改后的用户信息
        String newUserName = etUserName.getText().toString().trim();
        String newGender = etGender.getText().toString().trim();
        double newHeight = Double.parseDouble(etHeight.getText().toString().trim());
        double newWeight = Double.parseDouble(etWeight.getText().toString().trim());

        // 更新数据库中的用户信息
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_name", newUserName);
        values.put("gender", newGender);
        values.put("height", newHeight);
        values.put("weight", newWeight);

        String selection = "user_id=?";
        String[] selectionArgs = {String.valueOf(currentUser.getUserId())};

        int count = db.update("users", values, selection, selectionArgs);

        if (count > 0) {
            // 保存成功
            Toast.makeText(getContext(), "保存成功", Toast.LENGTH_SHORT).show();
        } else {
            // 保存失败
            Toast.makeText(getContext(), "保存失败，请重试", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }
}
