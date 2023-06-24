package com.example.mobile_robot_term_project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class table_Activity extends AppCompatActivity {
    private ImageView t1, t2 ,t3 ,t4;
    private TextView table1_, table2_, table3_, table4_;
    private Button backbtn_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_main);

        t1 = findViewById(R.id.tbtn1);
        t2 = findViewById(R.id.tbtn2);
        t3 = findViewById(R.id.tbtn3);
        t4 = findViewById(R.id.tbtn4);

        table1_ = findViewById(R.id.table1);
        table2_ = findViewById(R.id.table2);
        table3_ = findViewById(R.id.table3);
        table4_ = findViewById(R.id.table4);

        backbtn_ = findViewById(R.id.backbtn);
    try {


        for (int i = 0; i < 4; i++) {
            if (Menu_Activity.table_infos[i].used) {
                if (i == 0) table1_.setText("테이블 1 : 사용 중");
                if (i == 1) table2_.setText("테이블 2 : 사용 중");
                if (i == 2) table3_.setText("테이블 3 : 사용 중");
                if (i == 3) table4_.setText("테이블 4 : 사용 중");

            } else {
                if (i == 0) table1_.setText("테이블 1 : 사용 가능");
                if (i == 1) table2_.setText("테이블 2 : 사용 가능");
                if (i == 2) table3_.setText("테이블 3 : 사용 가능");
                if (i == 3) table4_.setText("테이블 4 : 사용 가능");
            }
        }
    }
    catch (Exception e){

    }

        backbtn_.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent mintent = new Intent(getApplicationContext() , MainActivity.class);
                startActivity(mintent);
            }

        });

    }




}
