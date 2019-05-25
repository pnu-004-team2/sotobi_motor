package com.example.sotobi_motor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Timer;
import java.util.TimerTask;


import static com.example.sotobi_motor.PW_Check.BNum;

public class using_and_stop  extends AppCompatActivity {
    Timer mTimer;
    long time;
    long currtime;
    long resulttime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_and_stop);

        ImageView stop = (ImageView)findViewById(R.id.stop_Btn);

        final TextView left_time = (TextView)findViewById(R.id.remain_time);

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(using_and_stop.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        String timestamp = jsonResponse.getString("time"); //받아와서

                        time = Long.parseLong(timestamp); //바꾸고
                        currtime = System.currentTimeMillis();

                        resulttime = time - currtime;

                        String stringtimeH = Long.toString(resulttime/1000/60/60);
                        String stringtimeM = Long.toString(resulttime/1000/60%60);

                        left_time.setText(stringtimeH +"시 "+ stringtimeM+"분");
                    } else {
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        time_check_request timecheckrequest = new time_check_request(BNum, responseListener);
        RequestQueue queue = Volley.newRequestQueue(using_and_stop.this);
        queue.add(timecheckrequest);

        TimerTask mTask = new TimerTask() {
            @Override
            public void run() {
                currtime = System.currentTimeMillis();

                resulttime = time - currtime;

                String stringtimeH = Long.toString(resulttime/1000/60/60);
                String stringtimeM = Long.toString(resulttime/1000/60%60);

                left_time.setText(stringtimeH +"시 "+ stringtimeM+"분");

            }
        };
        mTimer = new Timer();
        mTimer.schedule(mTask,0,10000);
    }

}
/*

 */