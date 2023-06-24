package com.example.mobile_robot_term_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Vector;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;

class Table_info{
    int price;
    String orderlist= "";
    boolean used = false;

    }
public class Menu_Activity extends AppCompatActivity {
    static public Table_info table_infos[] = new Table_info[4];

    Button carb_btn,rose_btn,ss_btn,CD_btn,churros_btn,Jbtn,Cbtn,Tbtn,Mbtn,ordbtn,backbtn;
    TextView bill;
    CheckBox table1_cb , table2_cb , table3_cb , table4_cb ;
    CheckBox CheckBoxList[] = new CheckBox[4];
    private Robot robot;
    private TtsRequest ttsRequest;
    public int sum = 0 ;
    public String ordlist = "" ;


    public void unCheck(int cur){
        for(int i = 0 ; i < 4 ; i++){
            if(i != cur){
                if(CheckBoxList[i].isChecked()) CheckBoxList[i].setChecked(false);
            }
        }
    }
    public int current_checkbox(){

        int status = -1;
        if (table1_cb.isChecked()) status = 0;
        if (table2_cb.isChecked()) status = 1;
        if (table3_cb.isChecked()) status = 2;
        if (table4_cb.isChecked()) status = 3;

        return status;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);

        carb_btn = findViewById(R.id.carbo_btn);
        rose_btn = findViewById(R.id.rose_btn);
        ss_btn = findViewById(R.id.seafood_btn);
        CD_btn = findViewById(R.id.CD_btn);
        churros_btn = findViewById(R.id.churros_btn);
        Jbtn = findViewById(R.id.juice_btn);
        Cbtn = findViewById(R.id.cocktail_btn);
        Tbtn = findViewById(R.id.tequila_btn);
        Mbtn = findViewById(R.id.mojito_btn);
        bill = findViewById(R.id.bill);
        backbtn = findViewById(R.id.backbtn);

        robot = Robot.getInstance();


        ordbtn = findViewById(R.id.orderbtn);

        table1_cb = findViewById(R.id.checkBox1);
        CheckBoxList[0] = table1_cb;
        table2_cb = findViewById(R.id.checkBox2);
        CheckBoxList[1] = table2_cb;
        table3_cb = findViewById(R.id.checkBox3);
        CheckBoxList[2] = table3_cb;
        table4_cb = findViewById(R.id.checkBox4);
        CheckBoxList[3] = table4_cb;

        ttsRequest = TtsRequest.create("주문이 완료되었습니다. 감사합니다!",false);

        backbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent mintent = new Intent(getApplicationContext() , MainActivity.class);
                startActivity(mintent);
            }
        });
        table1_cb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                unCheck(0);
            }
        });
        table2_cb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                unCheck(1);
            }
        });
        table3_cb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                unCheck(2);
            }
        });
        table4_cb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                unCheck(3);
            }
        });


        ordbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AlertDialog.Builder ad = new AlertDialog.Builder(Menu_Activity.this);
                ad.setIcon(android.R.drawable.ic_menu_info_details);
                ad.setTitle("주문서");
                if(sum > 0 && current_checkbox() != -1) {
                    ad.setMessage("금액 : " + sum +"원\n"+"\n"+" 주문 하시겠습니까? ");
                    ad.setPositiveButton("주문", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
//                        String result = et.getText().toString();
                            int cur_stat = current_checkbox();
                            for (int s = 0 ; s < 4 ; s ++) {
                                if (s == cur_stat){
                                table_infos[cur_stat] = new Table_info();
                                table_infos[cur_stat].price = sum;
                                table_infos[cur_stat].orderlist += ordlist;
                                table_infos[cur_stat].used = true;
                                }
                                else{
                                    try{
                                        if(table_infos[s].used){
                                            table_infos[s].used = true;
                                        }

                                    }
                                    catch (Exception e)
                                    {
                                        table_infos[s] = new Table_info();
                                        table_infos[s].used = false;
                                    }
                                }
                            }

                            robot.speak(ttsRequest);
                            for (String location : robot.getLocations()) {
                                if (location.equals(("home base").trim())) {
                                    robot.goTo(location);
                                }
                            }
                            Intent menu_intent = new Intent(getApplicationContext() , MainActivity.class);
                            startActivity(menu_intent);
                            sum = 0;
                            ordlist = "" ;
                            dialogInterface.dismiss();
                        }
                    });
                    ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();

                        }
                    });

                    ad.show();
                }
                else if (sum == 0 || current_checkbox() == -1 ) {
                    ad.setMessage("메뉴와 테이블을 제대로 선택해주시길 바랍니다.");
                    ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
//                        String result = et.getText().toString();
                            dialogInterface.dismiss();
                        }

                    });
                    ad.show();
                }

                ad.setPositiveButton("주문", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String result = et.getText().toString();
                        Intent menu_intent = new Intent(getApplicationContext() , MainActivity.class);
                        startActivity(menu_intent);
                        dialogInterface.dismiss();
                    }
                });

            }
        });

        carb_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder ad = new AlertDialog.Builder(Menu_Activity.this);
                ad.setIcon(R.drawable.menu_1);
                ad.setTitle("까르보나라 파스타");
                ad.setMessage("까르보나라 파스타(Carbonara Pasta)는 이탈리아 요리 중 하나로, 고소하고 부드러운 크림 소스와 베이컨, 파마산 치즈가 들어간 파스타입니다. ");
                ad.setPositiveButton("주문", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String result = et.getText().toString();
                        bill.append("까르보나라 : 11000원\n");
                        sum += 11000;
                        ordlist += "까르보나라 : 11000원 \n";
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
        rose_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder ad = new AlertDialog.Builder(Menu_Activity.this);
                ad.setIcon(android.R.drawable.btn_star_big_on);
                ad.setTitle("로제 파스타");
                ad.setMessage("로제 파스타(Rosé Pasta)는 이탈리아 요리 중 하나로, 토마토 소스와 크림 소스를 혼합하여 만든 파스타입니다. 로제 소스는 토마토의 신선한 맛과 크림 소스의 부드러움이 조화로운 중간 톤의 소스로, 부드럽고 풍부한 맛을 가지고 있습니다. ");
                ad.setPositiveButton("주문", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String result = et.getText().toString();
                        bill.append("로제 : 11000원\n");
                        sum += 11000;
                        ordlist += "로제 : 11000원 \n";

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
        ss_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder ad = new AlertDialog.Builder(Menu_Activity.this);
                ad.setIcon(android.R.drawable.btn_star_big_on);
                ad.setTitle("해물 파스타");
                ad.setMessage("해물 파스타는 해산물과 파스타를 조합한 이탈리아 요리입니다. 다양한 해산물을 사용하여 만들어질 수 있으며, 주로 새우 ,문어, 오징어, 꽃게 등을 사용하여 요리합니다.");
                ad.setPositiveButton("주문", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String result = et.getText().toString();
                        bill.append("해물 파스타 : 12000원 \n");
                        sum += 12000;
                        ordlist += "해물 파스타 : 12000원 \n";

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

        CD_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder ad = new AlertDialog.Builder(Menu_Activity.this);
                ad.setIcon(android.R.drawable.btn_star_big_on);
                ad.setTitle("Chips and dip");
                ad.setMessage("Chips and dips는 과자 칩과 딥소스를 함께 즐기는 간단하면서도 맛있는 스낵 조합입니다.");
                ad.setPositiveButton("주문", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String result = et.getText().toString();
                        bill.append("Chips and dip : 11000원 \n");
                        sum += 11000;
                        ordlist += "Chips and dip : 11000원 \n";

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
        churros_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder ad = new AlertDialog.Builder(Menu_Activity.this);
                ad.setIcon(android.R.drawable.btn_star_big_on);
                ad.setTitle("Churros");
                ad.setMessage("츄러스(Churros)는 스페인에서 유래한 달콤하고 바삭한 디저트로 주로 설탕이나 시나몬과 함께 제공됩니다.");
                ad.setPositiveButton("주문", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String result = et.getText().toString();
                        bill.append("Churros : 8000원\n");
                        sum += 8000;
                        ordlist += "Churros : 8000원 \n";

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
        Jbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {

                AlertDialog.Builder ad = new AlertDialog.Builder(Menu_Activity.this);
                ad.setIcon(android.R.drawable.btn_star_big_on);
                ad.setTitle("Juice");
                ad.setMessage("주스(Juice)는 과일이나 채소를 압착하거나 블렌더로 갈아서 추출한 음료입니다. 주스는 신선한 과일이나 채소의 맛과 영양소를 높은 농도로 즐길 수 있는 방법 중 하나입니다.");
                ad.setPositiveButton("주문", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String result = et.getText().toString();
                        bill.append("Juice : 9000원\n");
                        sum += 9000;
                        ordlist += "Juice : 9000원 \n";

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
        Cbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {

                AlertDialog.Builder ad = new AlertDialog.Builder(Menu_Activity.this);
                ad.setIcon(android.R.drawable.btn_star_big_on);
                ad.setTitle("Cocktail");
                ad.setMessage("칵테일(Cocktail)은 주로 알코올 음료를 기반으로 만들어지는 혼합 음료로, 다양한 맛과 색상을 가지고 있습니다. 칵테일은 술과 주스, 시럽, 소다, 과일 등 다양한 재료를 조합하여 만들어지며, 섬세한 조화와 특별한 장식으로 유명합니다.");
                ad.setPositiveButton("주문", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String result = et.getText().toString();
                        bill.append("Cocktail : 11000원 \n");
                        sum += 11000;
                        ordlist += "Cocktail : 11000원 \n";

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
        Tbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder ad = new AlertDialog.Builder(Menu_Activity.this);
                ad.setIcon(android.R.drawable.btn_star_big_on);
                ad.setTitle("Tequila");
                ad.setMessage("테킬라(Tequila)는 멕시코에서 생산되는 대표적인 독특한 술입니다. 테킬라는 파란 아가베 식물을 원료로 하며, 대부분 멕시코 헤안도주(Highlands)와 할리스코주(Lowlands) 지역에서 생산됩니다..");
                ad.setPositiveButton("주문", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String result = et.getText().toString();
                        bill.append("Tequila : 8000원 \n");
                        sum += 8000;
                        ordlist += "Tequila : 8000원 \n";

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
        Mbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder ad = new AlertDialog.Builder(Menu_Activity.this);
                ad.setIcon(android.R.drawable.btn_star_big_on);
                ad.setTitle("Mojito");
                ad.setMessage("모히토(Mojito)는 상쾌하고 시원한 맛을 가진 대표적인 칵테일 중 하나입니다. 주로 명란(라임)과 민트, 설탕, 세이다(탄산음료), 화이트 럼을 사용하여 만들어집니다.");
                ad.setPositiveButton("주문", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String result = et.getText().toString();
                        bill.append("Mojito : 8000원 \n");
                        sum += 8000;
                        ordlist += "Mojito : 8000원 \n";

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
