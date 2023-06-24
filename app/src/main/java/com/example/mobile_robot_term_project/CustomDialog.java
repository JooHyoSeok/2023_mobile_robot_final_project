package com.example.mobile_robot_term_project;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;


public class CustomDialog extends Dialog {
    private TextView txt_contents;
    private Button shutdownClick;
    private ImageView image;

    public CustomDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.activity_custom_dialog);
        //txt_contents = findViewById(R.id.txt_contents);

        image = findViewById(R.id.image_);
        //txt_contents.setText(contents);
        shutdownClick = findViewById(R.id.btn_shutdown);

        shutdownClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dismiss();
            }
        });

    }
}