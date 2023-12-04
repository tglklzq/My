package com.example.myapplication5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class WardrobeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wardrobe, container, false);

        NavigationView navigationView = view.findViewById(R.id.wardrobeNavigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // 处理导航栏菜单项的点击事件
                int itemId = item.getItemId();

                if (itemId == R.id.menu_hat) {
                    // 切换到帽子页面
                    replaceFragment(new HatFragment());
                    return true;
                } else if (itemId == R.id.menu_upper) {
                    // 切换到上衣页面
                    replaceFragment(new ShirtFragment());
                    return true;
                } else if (itemId == R.id.menu_lower) {
                    // 切换到下衣页面
                    replaceFragment(new PantsFragment());
                    return true;
                } else if (itemId == R.id.menu_shoes) {
                    // 切换到鞋子页面
                    replaceFragment(new ShoesFragment());
                    return true;
                }

                return false;
            }
        });

        // 初始时显示帽子页面
        replaceFragment(new HatFragment());

        return view;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.wardrobeFragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
