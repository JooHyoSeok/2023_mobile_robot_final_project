package com.example.mobile_robot_term_project;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;
import com.robotemi.sdk.navigation.model.Position;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import java.util.ArrayList;
import java.util.List;

public class Moving_Activity extends AppCompatActivity {
    TextView textView;
    Robot robot;
    Position position;
    List<String> locations;
    String target = "home base";
    String table1 = "table1";
    String table2 = "table2";
    String table3 = "table3";
    String table4 = "table4";
    String table = "table";
    @RequiresApi(api = Build.VERSION_CODES.O)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moving_disp_main);
        textView = findViewById(R.id.txtDisp);
        Intent intent = getIntent();
        String table_num = intent.getStringExtra("table_number");
        robot = Robot.getInstance();
//        position.setX();
//        position.setY();
//        position.getYaw();
//        robot.goToPosition(Position);
        textView.setText(table_num + "번 테이블로 이동중");

            for (String location : robot.getLocations()) {
                if (location.equals((table + table_num).trim())) {
                    robot.goTo(location);
                }
            }
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                //딜레이 후 시작할 코드 작성
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent1);
            }
        }, 1000);// 1.0초 정도 딜레이를 준 후 시작
    }
}
