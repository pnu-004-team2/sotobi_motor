package com.example.sotobi_motor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class number_check extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_check);

        final EditText Number = (EditText)findViewById(R.id.number);

        Button numberChk = (Button)findViewById(R.id.numberChk);
        numberChk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String number = Number.getText().toString();

                if(number.length()<1){
                    Toast.makeText(getApplicationContext(), "차량번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                }else {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    Intent intent = new Intent(number_check.this, PW_Check.class);
                                    intent.putExtra("number",number);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "실패! 존재하지 않는 차량입니다.", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    number_check_Request numbercheckrequest = new number_check_Request(number, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(number_check.this);
                    queue.add(numbercheckrequest);
                }
                /*
                Intent intent = new Intent(number_check.this, PW_Check.class);
                startActivity(intent);
                finish();
                */
            }
        });
    }
}
