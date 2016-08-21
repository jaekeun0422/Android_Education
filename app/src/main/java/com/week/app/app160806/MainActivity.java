package com.week.app.app160806;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.week.app.app160806.member.LoginActivity;
import com.week.app.app160806.util.CalcActivity;

public class MainActivity extends Activity implements View.OnClickListener {
    Button bt_calc, bt_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_calc = (Button) findViewById(R.id.bt_calc);
        bt_calc.setOnClickListener(this);

        bt_login = (Button) findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_calc :
                Log.d("계산기 버튼", "진 입");
                startActivity(new Intent(this.getApplicationContext(), CalcActivity.class));
                break;
            case R.id.bt_login :
                startActivity(new Intent(this.getApplicationContext(), LoginActivity.class));
                break;
        }

    }
}
