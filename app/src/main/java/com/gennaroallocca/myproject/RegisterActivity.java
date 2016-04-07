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

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends Activity {

    EditText email,password;
    Button login,register;
    String emailtxt,passwordtxt;
    List<NameValuePair> params;
    String jsonemail;
    Pattern pattern;
    Matcher matcher;
    App app = (App) getApplication();
    String emailApp;
    public final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    public boolean validateEmail(String emailStr) {
        matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }


    public boolean validatePssword(final String password){
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        register = (Button)findViewById(R.id.registerbtn);
        login = (Button)findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regactivity = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(regactivity);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                emailtxt = email.getText().toString();
                passwordtxt = password.getText().toString();
                params = new ArrayList<NameValuePair>();

                boolean textEmail = validateEmail(emailtxt);
                boolean textPassword = validatePssword(passwordtxt);

                app.setEmail(emailtxt);

                if(textEmail){
                    if(textPassword) {
                        Intent aboutactivity = new Intent(RegisterActivity.this, AboutActivity.class);
                        aboutactivity.putExtra("password", passwordtxt);
                        aboutactivity.putExtra("email", emailtxt);
                        startActivity(aboutactivity);
                        finish();
                    } else {
                        Toast.makeText(getApplication(), "password sbagliata", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplication(), "email sbagliata", Toast.LENGTH_LONG).show();
                }


                /*params.add(new BasicNameValuePair("email", emailtxt));
                params.add(new BasicNameValuePair("password", passwordtxt));

                ServerRequest sr = new ServerRequest();
                JSONObject json = sr.getJSON("http://10.160.13.42:5000/register",params);

                if(json != null) {
                    try {
                        String response = json.getString("response");


                        if(!(json.isNull("email"))) {
                                     jsonemail = json.getString("email");

                        } else {
                            Toast.makeText(getApplication(), response, Toast.LENGTH_LONG).show();
                        }
                        Log.d("register", response);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }*/
            }
        });
    }
}
