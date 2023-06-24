package com.example.mobile_robot_term_project;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.robotemi.sdk.navigation.model.Position;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;


public class Serving_Activity extends AppCompatActivity {
    private ImageButton tbutton1,tbutton2,tbutton3,tbutton4;
    private Button backptn_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serving_main);

        tbutton1 = findViewById(R.id.tbtn1);
        tbutton2 = findViewById(R.id.tbtn2);
        tbutton3 = findViewById(R.id.tbtn3);
        tbutton4 = findViewById(R.id.tbtn4);
        backptn_ = findViewById(R.id.backbtn);

        backptn_.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent mintent = new Intent(getApplicationContext() , MainActivity.class);
                startActivity(mintent);
            }

        });


        tbutton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder ad = new AlertDialog.Builder(Serving_Activity.this);
                ad.setIcon(android.R.drawable.btn_star_big_on);
                ad.setTitle("Serving");
                ad.setMessage("1번 테이블로 서빙하시겠습니까?");
//                final EditText et = new EditText(Serving_Activity.this);
//                ad.setView(et);
                ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String result = et.getText().toString();
                        Intent intent1 = new Intent(getApplicationContext(), Moving_Activity.class);
                        intent1.putExtra("table_number","1");
                        startActivity(intent1);
                        dialogInterface.dismiss();
                    }
                });
                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String result = et.getText().toString();
                        dialogInterface.dismiss();
                    }
                });
                ad.show();

            }
        });

        tbutton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder ad = new AlertDialog.Builder(Serving_Activity.this);
                ad.setIcon(android.R.drawable.btn_star_big_on);
                ad.setTitle("Serving");
                ad.setMessage("2번 테이블로 서빙하시겠습니까?");
//                final EditText et = new EditText(Serving_Activity.this);
//                ad.setView(et);
                ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String result = et.getText().toString();
                        Intent intent1 = new Intent(getApplicationContext(), Moving_Activity.class);
                        intent1.putExtra("table_number","2");
                        startActivity(intent1);
                        dialogInterface.dismiss();

                    }
                });
                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String result = et.getText().toString();
                        dialogInterface.dismiss();
                    }
                });
                ad.show();

            }
        });

        tbutton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder ad = new AlertDialog.Builder(Serving_Activity.this);
                ad.setIcon(android.R.drawable.btn_star_big_on);
                ad.setTitle("Serving");
                ad.setMessage("3번 테이블로 서빙하시겠습니까?");
//                final EditText et = new EditText(Serving_Activity.this);
//                ad.setView(et);
                ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String result = et.getText().toString();
                        Intent intent1 = new Intent(getApplicationContext(), Moving_Activity.class);
                        intent1.putExtra("table_number","3");
                        startActivity(intent1);
                        dialogInterface.dismiss();
                    }
                });
                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String result = et.getText().toString();
                        dialogInterface.dismiss();
                    }
                });
                ad.show();

            }
        });

        tbutton4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder ad = new AlertDialog.Builder(Serving_Activity.this);
                ad.setIcon(android.R.drawable.star_big_on);
                ad.setTitle("Serving");
                ad.setMessage("4번 테이블로 서빙하시겠습니까?");
//                final EditText et = new EditText(Serving_Activity.this);
//                ad.setView(et);
                ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String result = et.getText().toString();
                        Intent intent1 = new Intent(getApplicationContext(), Moving_Activity.class);
                        intent1.putExtra("table_number","4");
                        startActivity(intent1);
                        dialogInterface.dismiss();
                    }
                });
                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String result = et.getText().toString();
                        dialogInterface.dismiss();
                    }
                });
                ad.show();

            }
        });



    }


}
