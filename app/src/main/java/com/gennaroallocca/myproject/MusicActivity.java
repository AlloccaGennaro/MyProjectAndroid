package com.gennaroallocca.myproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Created by Gennaro Allocca on 17/03/2016.
 */
public class MusicActivity extends Activity {
    EditText name,lastname,city,age;
    Button forward,back;
    String email,response,emailTxt,nameTxt,lastnameTxt,cityTxt,ageTxt,jsonemail;
    List<NameValuePair> params;
    ServerRequest sr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        back = (Button)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regactivity = new Intent(MusicActivity.this, AboutActivity.class);
                startActivity(regactivity);
                finish();
            }
        });
    }
}
