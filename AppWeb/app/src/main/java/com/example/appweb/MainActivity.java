package com.example.appweb;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Map;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.util.Log;


import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button guardar;
    EditText txtUser;
    EditText txtPasword;

    RequestQueue requestQueue;
    String url = "http://localhost:8000/login?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guardar = findViewById(R.id.btnGuardar);
        txtUser = findViewById(R.id.txtUser);
        txtPasword = findViewById(R.id.txtPasword);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener los valores de usuario y contraseña
                String user = txtUser.getText().toString();
                String pass = txtPasword.getText().toString();

                requestQueue = Volley.newRequestQueue(MainActivity.this);

                url += "user="+user+"&pass="+pass;
                GetApiLogin(url);

            }
        });
    }

    private void GetApiLogin(String _url){
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, _url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int x = 0;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                int x = 0;
            }
        });
        requestQueue.add(request);
    }
}