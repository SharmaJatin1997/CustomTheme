package com.app.mytheme;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.turkialkhateeb.materialcolorpicker.ColorChooserDialog;
import com.turkialkhateeb.materialcolorpicker.ColorListener;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences, app_preferences;
    SharedPreferences.Editor editor;
    Button button;
    Methods methods;

    int appTheme;
    int themeColor;
    int appColor;
    Constant constant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
        appColor = app_preferences.getInt("color", 0);
        appTheme = app_preferences.getInt("theme", 0);
        themeColor = appColor;
        constant.color = appColor;

        if (themeColor == 0) {
            setTheme(Constant.theme);
        } else if (appTheme == 0) {
            setTheme(Constant.theme);
        } else {
            setTheme(appTheme);
        }
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = findViewById(R.id.toolbar_setting);
        toolbar.setTitle("Settings");
        toolbar.setBackgroundColor(Constant.color);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        methods = new Methods();
        button = (Button) findViewById(R.id.button_color);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        colorize();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorChooserDialog dialog = new ColorChooserDialog(MainActivity.this);
                dialog.setTitle("Select");
                dialog.setColorListener(new ColorListener() {
                    @Override
                    public void OnColorClick(View v, int color) {
                        colorize();
                        Constant.color = color;

                        methods.setColorTheme();
                        editor.putInt("color", color);
                        editor.putInt("theme", Constant.theme);
                        editor.commit();

                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                });

                dialog.show();
            }
        });
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void colorize() {
        ShapeDrawable d = new ShapeDrawable(new OvalShape());
        d.setBounds(60, 60, 60, 60);
        d.getPaint().setStyle(Paint.Style.FILL);
        d.getPaint().setColor(Constant.color);
        button.setBackground(d);
    }
}