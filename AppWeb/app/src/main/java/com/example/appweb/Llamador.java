package com.example.appweb;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import android.util.Log;
import android.widget.TextView;

public class Llamador extends AsyncTask<URL, Void, String> {
    String TAG = getClass().getSimpleName();

    HttpURLConnection con;
    TextView texvieww;
    //constructor de la clase
    public Llamador(TextView TextViewReference) {
        this.texvieww = TextViewReference;
    }

    //metodo que es llamado antes de ejecutar la accion generada en doInBackground
    protected void onPreExecute() {
        super.onPreExecute();
        texvieww.setText("Antes de ejecutar......");
        Log.d(TAG + " PreExceute","Antes de ejecutar......");
    }

    @Override
    protected String doInBackground(URL... urls) {
        String retorno="";
        try {
            // Establecer la conexión
            con = (HttpURLConnection)urls[0].openConnection();
            // Obtener el estado del recurso
            int statusCode = con.getResponseCode();
            if(statusCode!=200) {
                retorno = "El recurso no está disponible";
            }
            else{

                //Es posible Parsear el flujo con formato JSON a una lista de Strings
                InputStream in = new BufferedInputStream(con.getInputStream());
                InputStreamReader isReader = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(isReader);
                StringBuffer sb = new StringBuffer();
                String str;
                while((str = reader.readLine())!= null){
                    sb.append(str);
                }
                retorno = sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            con.disconnect();
        }
        return retorno;
    }

    //metodo invocada luego de finalizar
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.d(TAG + " onPostExecute", "" + result);
        texvieww.setText(result);
    }
}

