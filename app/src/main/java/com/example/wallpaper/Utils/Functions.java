package com.example.wallpaper.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.example.wallpaper.R;

/**
 * Created by admin on 2018/7/28.
 */

public class Functions {
    public static void changeMainFragment(FragmentActivity fragmentActivity, Fragment fragment) {
        fragmentActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, fragment)
                .addToBackStack(null)
                .commit();

    }
}
