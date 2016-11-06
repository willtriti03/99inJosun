package com.example.jungjune.sunrintons.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jungjune.sunrintons.Network.ApiClient;
import com.example.jungjune.sunrintons.R;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


/**
 * Created by jungjune on 2016-07-22.
 */
public class LoginActivity extends AppCompatActivity {

    protected EditText mId;
    protected EditText mPw;
    protected TextView mLogin;
    protected TextView mSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mId = (EditText) findViewById(R.id.id_login);
        mPw = (EditText) findViewById(R.id.pw_login);
        mLogin = (TextView) findViewById(R.id.login_btn);
        mSignIn = (TextView) findViewById(R.id.signup_btn);


        mId.getBackground().setColorFilter(Color.parseColor("#A7A3A0"), PorterDuff.Mode.SRC_IN);
        mPw.getBackground().setColorFilter(Color.parseColor("#A7A3A0"), PorterDuff.Mode.SRC_IN);

        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = mId.getText().toString();
                final String pw = mPw.getText().toString();
                if (id.trim().equals("")) {
                    Toast.makeText(LoginActivity.this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if (pw.trim().equals("")) {
                    Toast.makeText(LoginActivity.this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    RequestParams params = new RequestParams();
                    params.put("id", id);
                    params.put("password", pw);
                    ApiClient.post("/users/login", params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            switch (statusCode) {
                                case 200:
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    UserInfo.id = id;
                                    UserInfo.pw = pw;

                                    startActivity(intent);
                                    finish();
                                    try {
                                        JSONObject jsonObject = new JSONObject(new String (responseBody));// jsonObject.getString("title")

                                        String rank = jsonObject.getString("rank");

                                        UserInfo.rank = rank;
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    break;

                                case 304:
                                case 403:
                                        Toast.makeText(getApplicationContext(), "아이디 혹은 비밀번호가 잘못 되었습니다.", Toast.LENGTH_LONG).show();
                                    break;

                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Log.e("asdf", new String(responseBody) + "a");
                        }
                    });
                }
            }
        });
    }
}
