package com.example.sotobi_motor;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class time_check_request extends StringRequest {
    private static final String REQUEST_URL = "http://jwu8615.dothome.co.kr/motorTimeCheck.php";
    private Map<String, String> params;

    public time_check_request(String bnum, Response.Listener<String> listener) {
        super(Method.POST, REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("bnum", bnum);
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
