package com.example.sotobi_motor;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PW_check_Request extends StringRequest {
    private static final String MOTORPWCHECK_REQUEST_URL = "http://jwu8615.dothome.co.kr/motorPWCheck.php";
    private Map<String, String> params;

    public PW_check_Request(String bnum, String password, Response.Listener<String> listener) {
        super(Method.POST, MOTORPWCHECK_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("bnum", bnum);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
