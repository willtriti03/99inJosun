package com.example.jungjune.sunrintons.Activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;

import com.example.jungjune.sunrintons.Network.ApiClient;
import com.example.jungjune.sunrintons.R;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class GonggoActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<GongooSchema> gongooSchemas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gonggo);

        RequestParams params = new RequestParams();
        ApiClient.post("/notices/lastest", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                switch (statusCode) {
                    case 200:
                        try {
                            JSONArray jsonArray = new JSONArray(new String(responseBody));
                            System.out.println(jsonArray.length() + "");
                            for (int i = 0; i < jsonArray.length(); ++i) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);// jsonObject.getString("title")
                                gongooSchemas.add(new GongooSchema(jsonObject.getString("name")));
                            }
                            Log.e("adsfasdf", gongooSchemas.get(0).getName());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;

                    case 304:
                    case 403:

                        break;

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });


        gridView = (GridView) findViewById(R.id.gridview_gonggo);

        GonggoAdapter.items = gongooSchemas;
        gridView.setAdapter(new GonggoAdapter());
    }
}
