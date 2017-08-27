package com.kodluyoruz.volleysample.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.kodluyoruz.volleysample.R;
import com.kodluyoruz.volleysample.network.AppController;

import org.json.JSONException;
import org.json.JSONObject;

public class VolleyActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {

    private String apiUrl = "https://api.myjson.com/bins/ecemt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, apiUrl, null, this, this);
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest();
//        StringRequest stringRequest = new StringRequest();
        AppController.getInstance().addToRequestQueue(jsonObjectRequest, "Request1");

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            Toast.makeText(this, response.getJSONObject("GlossDiv").getString("title"), Toast.LENGTH_SHORT).show();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
}
