package com.example.mobile_robot_term_project;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import com.robotemi.sdk.Robot;
import androidx.appcompat.app.AppCompatActivity;
import com.robotemi.sdk.navigation.model.Position;

import com.robotemi.sdk.TtsRequest;
import java.util.ArrayList;
import java.util.List;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;


public class Guide_Activity extends AppCompatActivity {
    private  ImageButton tbtn , bbtn ,tablebtn_ , backbtn_;
    private Robot robot;
    private List<String> locations;
    private String target = "home base";
    private String toilet = "toilet";

    private String table1 = "table1";
    private String table2 = "table2";
    private String table3 = "table3";
    private String table4 = "table4";

    private String table = "table";

    @RequiresApi(api = Build.VERSION_CODES.O)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_main);
        robot = Robot.getInstance();

        bbtn = findViewById(R.id.batterybtn);
        tbtn = findViewById(R.id.toiletbtn);
        backbtn_ = findViewById(R.id.backbtn);
        tablebtn_ = findViewById(R.id.tablebtn);

        tablebtn_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //딜레이 후 시작할 코드 작성
                        Intent intent1 = new Intent(getApplicationContext(), table_Activity.class);
                        startActivity(intent1);
                    }
                }, 500);// 1.0초 정도 딜레이를 준 후 시작
            }
        });

        backbtn_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //딜레이 후 시작할 코드 작성
                        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent1);
                    }
                }, 500);// 1.0초 정도 딜레이를 준 후 시작
            }
        });




        bbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (String location : robot.getLocations()) {
                    if (location.equals(target.trim())) {
                        robot.goTo(location);
                        //hideKeyboard();
                    }
                }
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //딜레이 후 시작할 코드 작성
                        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent1);
                    }
                }, 5000);// 1.0초 정도 딜레이를 준 후 시작
            }
        });

        tbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (String location : robot.getLocations()) {
                    if (location.equals(toilet.trim())) {
                        robot.goTo(location);
                        //hideKeyboard();
                    }
                }
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //딜레이 후 시작할 코드 작성
                        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent1);
                    }
                }, 5000);// 1.0초 정도 딜레이를 준 후 시작
            }
    });
    }
}