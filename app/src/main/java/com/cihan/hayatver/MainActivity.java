package com.cihan.hayatver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ExampleLogInDialog.ExampleLoginDialogListener {

    private Button btnKanVer;
    private Button mbtnGiris;
    private String mPhone;


    public void init(){

        btnKanVer = (Button) findViewById(R.id.btnKanVer);
        mbtnGiris = (Button) findViewById(R.id.btnGiris);
    }

    @Override
    public void applytext(String phone) {
        mPhone = phone;
    }

    public void openLogInDialog(){
      ExampleLogInDialog exampleLogInDialog = new ExampleLogInDialog();
      exampleLogInDialog.show(getSupportFragmentManager(), "phone login");


    }

     public void openDialog(){
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(),"information");
     }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
          openDialog();

        btnKanVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity1.class);
                startActivity(intent);
            }
        });

        mbtnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogInDialog();
                }
        });



    }
}