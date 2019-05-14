package com.example.sotobi_motor;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class number_check_Request extends StringRequest {
    private static final String NUMBERCHECK_REQUEST_URL = "http://jwu8615.dothome.co.kr/motorNumCheck.php";
    private Map<String, String> params;

    public number_check_Request(String bnum, Response.Listener<String> listener) {
        super(Method.POST, NUMBERCHECK_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("bnum", bnum);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}