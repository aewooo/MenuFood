package com.wannaporn.phoso.menufoodthai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.squareup.okhttp.internal.framed.FrameReader;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void toMenu(View view){
        startActivity(new Intent(MainActivity.this,MainMenu.class));
        finish();
    }

}
