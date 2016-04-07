package com.gennaroallocca.myproject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfigImpl;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
public class ProfileActivity extends Activity {

    SharedPreferences pref;
    String token, grav, oldpasstxt, newpasstxt;
    WebView web;
    Button chgpass,chgpassfr,cancel, logout;
    Dialog dig;
    EditText oldpass, newpass;
    List<NameValuePair> params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        chgpass = (Button) findViewById(R.id.chgbtn);
        logout = (Button) findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor edit = pref.edit();
                //Storing Data using SharedPreferences
                edit.putString("token", "");
                edit.commit();
                Intent loginactivity = new Intent(ProfileActivity.this, LoginActivity.class);

                startActivity(loginactivity);
                finish();
            }
        });

        pref = getSharedPreferences("AppPref", MODE_PRIVATE);
        token = pref.getString("token", "");
        grav = pref.getString("grav", "");


        chgpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dig = new Dialog(ProfileActivity.this);

                dig.setContentView(R.layout.chgpassword_frag);
                dig.setTitle("Change Password");
                chgpassfr = (Button) dig.findViewById(R.id.chgbtn);

                chgpassfr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        oldpass = (EditText) dig.findViewById(R.id.oldpass);
                        newpass = (EditText) dig.findViewById(R.id.newpass);
                        oldpasstxt = oldpass.getText().toString();
                        newpasstxt = newpass.getText().toString();

                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("oldpass", oldpasstxt));
                        params.add(new BasicNameValuePair("newpass", newpasstxt));
                        params.add(new BasicNameValuePair("id", token));

                        ServerRequest sr = new ServerRequest();
                        JSONObject json = sr.getJSON("http://10.160.13.42:5000/api/chgpass", params);

                        if (json != null) {
                            try {
                                String jsonstr = json.getString("response");

                                if (json.getBoolean("res")) {
                                    dig.dismiss();
                                    Toast.makeText(getApplication(), jsonstr, Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(getApplication(), jsonstr, Toast.LENGTH_LONG).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                });

                cancel = (Button) dig.findViewById(R.id.cancelbtn);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dig.dismiss();
                    }
                });

                dig.show();
            }
        });

    }
}
