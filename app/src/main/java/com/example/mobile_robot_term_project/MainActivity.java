package com.example.mobile_robot_term_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.annotation.RequiresApi;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;

public class MainActivity extends AppCompatActivity {

    private ImageButton button_serving;
    private ImageButton button_pay;
    private ImageButton button_guide;
    private ImageButton button_menu;
    private Robot robot;
    private TtsRequest ttsRequest ,ttsRequest_toilet;

    protected void goToafter(String dst){
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                for (String location : robot.getLocations()) {
                    if (location.equals((dst).trim())) {
                        robot.goTo(location);
                    }
                }
            }

        }, 10000);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_serving = findViewById(R.id.button0);
        button_pay = findViewById(R.id.button1);
        button_guide = findViewById(R.id.button2);
        button_menu = findViewById(R.id.button3);
        robot = Robot.getInstance();
        String servingDonemsg = "음식을 픽업 해주세요!";
        String toiletArrived = "화장실에 도착했습니다.";

        ttsRequest = TtsRequest.create(servingDonemsg.trim(),true);
        ttsRequest_toilet =TtsRequest.create(toiletArrived.trim(),false);

        robot.addOnGoToLocationStatusChangedListener(new OnGoToLocationStatusChangedListener() {
            @Override
            public void onGoToLocationStatusChanged(@NonNull String s, @NonNull String s1, int i, @NonNull String s2) {
                System.out.println(s + " " + s1);
                if (s.equals("table1") && s1.equals("complete")){
                    System.out.println("1번 도착");
                    robot.speak(ttsRequest);
                    goToafter("home base");
                }
                else if (s.equals("table2") && s1.equals("complete")){
                    System.out.println("2번 도착");
                    robot.speak(ttsRequest);
                    goToafter("home base");
                }
                else if (s.equals("table3") && s1.equals("complete")){
                    System.out.println("3번 도착");
                    robot.speak(ttsRequest);
                    goToafter("home base");
                }
                else if (s.equals("table4") && s1.equals("complete")) {
                    System.out.println("4번 도착");
                    robot.speak(ttsRequest);
                    goToafter("home base");
                }
                else if (s.equals("toilet") && s1.equals("complete")) {
                    System.out.println("화장실 도착");
                    robot.speak(ttsRequest_toilet);
                    goToafter("home base");
                }
            }
        });

        button_serving.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent sintent = new Intent(getApplicationContext() , Serving_Activity.class);
                startActivity(sintent);
            }
        });

        button_pay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent pintent = new Intent(getApplicationContext() , Pay_Activity.class);
                startActivity(pintent);
            }
        });

        button_guide.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent gintent = new Intent(getApplicationContext() , Guide_Activity.class);
                startActivity(gintent);
            }
        });

        button_menu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent mintent = new Intent(getApplicationContext() , Menu_Activity.class);
                startActivity(mintent);
            }
        });


    }
}