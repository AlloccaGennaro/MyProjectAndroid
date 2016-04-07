package com.gennaroallocca.myproject;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AboutActivity extends Activity {
    EditText name,lastname,city,age;
    Button forward,back;
    String emailRegister,passwordRegister,emailTxt,passwordTxt,nameTxt,lastnameTxt,cityTxt,ageTxt;
    List<NameValuePair> params;
    ServerRequest sr;
    App app = (App) getApplication();
    String email = app.getEmail();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        name = (EditText)findViewById(R.id.name_user);
        lastname = (EditText)findViewById(R.id.lastname_user);
        city = (EditText)findViewById(R.id.city_user);
        age = (EditText)findViewById(R.id.age_user);
        forward = (Button)findViewById(R.id.forwardbtn);
        back = (Button)findViewById(R.id.back);

        Bundle datipassati = getIntent().getExtras();
        emailRegister = datipassati.getString("email");
        passwordRegister = datipassati.getString("password");

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailTxt = emailRegister.toString();
                passwordTxt = passwordRegister.toString();
                nameTxt = name.getText().toString();
                lastnameTxt = lastname.getText().toString();
                cityTxt = city.getText().toString();
                ageTxt = age.getText().toString();

           /*     sr = new ServerRequest();
                params = new ArrayList<NameValuePair>();

                params.add(new BasicNameValuePair("email", emailTxt));
                params.add(new BasicNameValuePair("name", nameTxt));
                params.add(new BasicNameValuePair("lastname", lastnameTxt));
                params.add(new BasicNameValuePair("city", cityTxt));
                params.add(new BasicNameValuePair("age", ageTxt));

                sr = new ServerRequest();

                JSONObject json = sr.getJSON("http://10.160.13.42:5000/about", params);*/

               /* if (json != null) {
                    try {
                        String jsonresponse = json.getString("response");

                        Intent musicactivity = new Intent(AboutActivity.this, MusicActivity.class);

                        if (!(json.isNull("email"))) {
                            jsonemail = json.getString("email");
                            musicactivity.putExtra("email", jsonemail);
                            musicactivity.putExtra("response", jsonresponse);
                            startActivity(musicactivity);
                            finish();
                        } else {
                            Toast.makeText(getApplication(), jsonresponse, Toast.LENGTH_LONG).show();
                        }
                        Log.d("register", jsonresponse);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }*/
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regactivity = new Intent(AboutActivity.this, RegisterActivity.class);
                startActivity(regactivity);
                finish();
            }
        });

    }


}
