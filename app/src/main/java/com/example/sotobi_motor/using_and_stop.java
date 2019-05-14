package com.example.sotobi_motor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class using_and_stop  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_and_stop);

        ImageView stop = (ImageView)findViewById(R.id.stop_Btn);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(using_and_stop.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
