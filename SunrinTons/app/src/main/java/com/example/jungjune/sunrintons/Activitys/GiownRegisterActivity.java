package com.example.jungjune.sunrintons.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jungjune.sunrintons.Network.ApiClient;
import com.example.jungjune.sunrintons.R;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by jungjune on 2016-07-23.
 */
public class GiownRegisterActivity extends AppCompatActivity {
    protected ImageButton mBackBtn;
    protected ImageButton mCheckBtn;
    protected EditText mNameEdit;
    protected EditText mLocationEdit;
    protected EditText mBillEdit;
    protected EditText mInfoEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ziown_register);

        mBackBtn= (ImageButton)findViewById(R.id.arrow);
        mCheckBtn=(ImageButton)findViewById(R.id.check);

        mNameEdit=(EditText)findViewById(R.id.name_giown_register);
        mLocationEdit=(EditText)findViewById(R.id.location_giown_register);
        mBillEdit=(EditText)findViewById(R.id.money_giown_register);
        mInfoEdit=(EditText)findViewById(R.id.info_giown_register);

        mCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams params = new RequestParams();
                params.put("id", UserInfo.id);
                params.put("title","");
                params.put("image","");
                params.put("name", mNameEdit.getText().toString());
                params.put("location", mLocationEdit.getText().toString());
                params.put("money", mBillEdit.getText().toString());
                params.put("info", mInfoEdit.getText().toString());
                params.put("complete", false);

                ApiClient.post("/notices/posting", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        switch (statusCode) {
                            case 200:
                                Toast.makeText(getApplicationContext(), "등록 성공", Toast.LENGTH_LONG).show();
                                finish();
                                break;
                            case 304:
                            case 403:
                                Toast.makeText(getApplicationContext(), "글 등록 실패", Toast.LENGTH_LONG).show();
                                break;

                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(getApplicationContext(), "글 등록 실패", Toast.LENGTH_LONG).show();
                    }
                });

                mBackBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

            }
        });
    }
}
