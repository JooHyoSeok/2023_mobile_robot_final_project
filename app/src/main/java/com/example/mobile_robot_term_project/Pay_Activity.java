package com.example.mobile_robot_term_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import androidx.appcompat.app.AppCompatActivity;

public class Pay_Activity extends AppCompatActivity {
    private TextView ordTxt ;
    private Button paybtn_,backbtn_;
    private CheckBox table1_cb , table2_cb , table3_cb , table4_cb ;
    private CheckBox CheckBoxList[] = new CheckBox[4];
    private CustomDialog customDialog;
    private androidx.appcompat.app.AppCompatActivity AppCompatActivity;
    private Robot robot ;
    private TtsRequest ttsRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_main);

        robot = Robot.getInstance();
        ttsRequest = TtsRequest.create("결제가 완료 되었습니다. 감사합니다!",false);

        table1_cb = findViewById(R.id.checkBox1);
        CheckBoxList[0] = table1_cb;

        table2_cb = findViewById(R.id.checkBox2);
        CheckBoxList[1] = table2_cb;

        table3_cb = findViewById(R.id.checkBox3);
        CheckBoxList[2] = table3_cb;

        table4_cb = findViewById(R.id.checkBox4);
        CheckBoxList[3] = table4_cb;

        ordTxt = findViewById(R.id.ordlstTxt);

        paybtn_ = findViewById(R.id.paybtn);
        backbtn_ = findViewById(R.id.backbtn);

        paybtn_.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                customDialog = new CustomDialog(Pay_Activity.this);
                customDialog.show();
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //딜레이 후 시작할 코드 작성
                        customDialog.dismiss();
                        robot.speak(ttsRequest);
                        Intent sintent = new Intent(getApplicationContext() , MainActivity.class);
                        startActivity(sintent);
                    }

                }, 5000);

                int cur = current_checkbox();
                Menu_Activity.table_infos[cur].orderlist = " ";
                Menu_Activity.table_infos[cur].price = 0;
                Menu_Activity.table_infos[cur].used = false;

            }
        });

        backbtn_.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent main_intent = new Intent(getApplicationContext() , MainActivity.class);
                startActivity(main_intent);

            }
        });

        table1_cb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                unCheck(0);
                //ordTxt.setText(Menu_Activity.info[0].orderlist);

                ordTxt.setText(Menu_Activity.table_infos[0].orderlist);
                ordTxt.append("\n주문 금액 : " + Integer.toString(Menu_Activity.table_infos[0].price));


            }
            });

        table2_cb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                unCheck(1);
                ordTxt.setText(Menu_Activity.table_infos[1].orderlist);
                ordTxt.append("\n주문 금액 : " + Integer.toString(Menu_Activity.table_infos[1].price));


            }
        });

        table3_cb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                unCheck(2);
                ordTxt.setText(Menu_Activity.table_infos[2].orderlist);
                ordTxt.append("\n주문 금액 : " + Integer.toString(Menu_Activity.table_infos[2].price));


            }
        });

        table4_cb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                unCheck(3);
                ordTxt.setText(Menu_Activity.table_infos[3].orderlist);
                ordTxt.append("\n주문 금액 : " + Integer.toString(Menu_Activity.table_infos[3].price));


            }
        });

    }


    public void unCheck(int cur){
        for(int i = 0 ; i < 4 ; i++){
            if(i != cur){
                if(CheckBoxList[i].isChecked()) CheckBoxList[i].setChecked(false);
            }
        }
    }
    public int current_checkbox(){
        int status = 0;
        if (table1_cb.isChecked()) status = 0;
        if (table2_cb.isChecked()) status = 1;
        if (table3_cb.isChecked()) status = 2;
        if (table4_cb.isChecked()) status = 3;
        return status;
    }
}
