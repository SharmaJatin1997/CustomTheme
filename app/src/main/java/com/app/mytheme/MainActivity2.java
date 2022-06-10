package com.app.mytheme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {

    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(Constant.theme);
        setContentView(R.layout.activity_main2);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setBackgroundColor(Constant.color);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intent);
    }
}