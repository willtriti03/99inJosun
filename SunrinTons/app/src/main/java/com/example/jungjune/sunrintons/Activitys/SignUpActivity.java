package com.example.jungjune.sunrintons.Activitys;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jungjune.sunrintons.Network.ApiClient;
import com.example.jungjune.sunrintons.R;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by jungjune on 2016-07-22.
 */
public class SignUpActivity extends ActionBarActivity {
    protected TextView mSignUp;
    protected EditText mId;
    protected EditText mPw;
    protected EditText mPwCheck;
    protected RadioGroup mRg;
    protected RadioButton 천민, 상민, 중인, 양반;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        mId = (EditText) findViewById(R.id.id_signup);
        mPw = (EditText) findViewById(R.id.pw_signup);
        mPwCheck = (EditText) findViewById(R.id.check_pw_signup);
        mRg = (RadioGroup) findViewById(R.id.rg);
        천민 = (RadioButton) findViewById(R.id.radio0);
        상민 = (RadioButton) findViewById(R.id.radio1);
        중인 = (RadioButton) findViewById(R.id.radio2);
        양반 = (RadioButton) findViewById(R.id.radio3);
        mSignUp = (TextView) findViewById(R.id.sign_btn);

        mId.getBackground().setColorFilter(Color.parseColor("#A7A3A0"), PorterDuff.Mode.SRC_IN);
        mPw.getBackground().setColorFilter(Color.parseColor("#A7A3A0"), PorterDuff.Mode.SRC_IN);
        mPwCheck.getBackground().setColorFilter(Color.parseColor("#A7A3A0"), PorterDuff.Mode.SRC_IN);

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = mId.getText().toString();
                String pw = mPw.getText().toString();
                String pwCheck=mPwCheck.getText().toString();
                String grade = "";

                if (id.trim().equals("")) {
                    Toast.makeText(SignUpActivity.this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if (pw.trim().equals("")) {
                    Toast.makeText(SignUpActivity.this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if (pwCheck.trim().equals("") || !pw.equals(pwCheck)) {
                    Toast.makeText(SignUpActivity.this, "비밀번호 확인을 제대로 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if (!천민.isChecked() && !상민.isChecked() && !중인.isChecked() && !양반.isChecked()) {
                    Toast.makeText(SignUpActivity.this, "신분을 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (천민.isChecked())
                        grade = "천민";
                    else if (상민.isChecked())
                        grade = "상민";
                    else if (중인.isChecked())
                        grade = "중인";
                    else if (양반.isChecked())
                        grade = "양반";

                    RequestParams params = new RequestParams();
                    params.put("id", id);
                    params.put("password", pw);
                    params.put("rank", grade);
                    ApiClient.post("/users/signup", params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        //  Log.e("asdf", new String(responseBody) + "a");
                            switch (statusCode){
                                case 200:
                                    Toast.makeText(getApplicationContext(),"가입되었습니다.",Toast.LENGTH_LONG).show();
                                    finish();
                                    break;
                                case 304:
                                    Toast.makeText(getApplicationContext(),"중복된 아이디입니다.",Toast.LENGTH_LONG).show();
                                    mId.clearFocus();
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

