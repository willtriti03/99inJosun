package com.example.jungjune.sunrintons.Activitys;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jungjune.sunrintons.R;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    protected String id;
    protected String pw;
    protected String rank;

    protected EditText mSearch;

    protected ImageView mNotice;
    protected ImageView mSupport;
    protected ImageView mPlay;
    protected ImageView mHome;
    protected ImageView mAdd;
    protected ImageView mSetting;

    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(), UserInfo.id+"/"+UserInfo.pw+"/"+ UserInfo.rank,Toast.LENGTH_SHORT).show();
        handleIntent(getIntent());

        mSearch=(EditText)findViewById(R.id.search);

        mNotice =(ImageView)findViewById(R.id.notice);
        mSupport=(ImageView)findViewById(R.id.support);
        mPlay=(ImageView)findViewById(R.id.play);
        mHome=(ImageView)findViewById(R.id.home);
        mAdd=(ImageView)findViewById(R.id.add);
        mSetting=(ImageView)findViewById(R.id.setting);
        viewPager = (ViewPager) findViewById(R.id.lastest_4);

        mNotice.setOnClickListener(this);
        mSupport.setOnClickListener(this);
        mSetting.setOnClickListener(this);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
        }
    }

    public void setInform(){
        this.id=(String)getIntent().getExtras().getSerializable("id");
        this.pw=(String)getIntent().getExtras().getSerializable("pw");
        this.rank=(String)getIntent().getExtras().getSerializable("rank");
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.setting:
                intent= new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
                break;

            case R.id.notice:
                intent = new Intent(getApplicationContext(), GonggoRegisterActivity.class);
                startActivity(intent);
                break;

            case R.id.support:
                intent = new Intent(getApplicationContext(), ZiownRegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
