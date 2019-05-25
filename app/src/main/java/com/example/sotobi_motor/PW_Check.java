package com.example.sotobi_motor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class PW_Check extends AppCompatActivity {
    public static String BNum;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw_check);
        final EditText PW = (EditText) findViewById(R.id.PW);

        Intent number= getIntent();
        BNum = number.getStringExtra("number");
        Toast.makeText(getApplicationContext(), " Number : " + BNum, Toast.LENGTH_SHORT).show();

        /*
        ImageView start = (ImageView)findViewById(R.id.start_Btn);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PW_Check.this, using_and_stop.class);
                startActivity(intent);
                finish();
            }
        });
        */
        ImageView startBtn = (ImageView) findViewById(R.id.start_Btn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String pw = PW.getText().toString();
                if (pw.length() < 1) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success) {
                                    Intent intent = new Intent(PW_Check.this, using_and_stop.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "실패! 비밀번호를 확인하세요", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    PW_check_Request pwcheckrequest = new PW_check_Request(BNum, pw, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(PW_Check.this);
                    queue.add(pwcheckrequest);
                    }
            }
        });
    }
}


