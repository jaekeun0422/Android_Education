package com.week.app.app160806.util;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.week.app.app160806.R;

public class CalcActivity extends Activity implements View.OnClickListener{
    EditText et_num1, et_num2;
    Button bt_minus, bt_plus, bt_multi, bt_divide, bt_remainder, bt_equal;
    TextView tv_result;
    int result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        et_num1 = (EditText) findViewById(R.id.et_num1);
        et_num2 = (EditText) findViewById(R.id.et_num2);
        bt_plus = (Button) findViewById(R.id.bt_plus);
        bt_minus = (Button) findViewById(R.id.bt_minus);
        bt_multi = (Button) findViewById(R.id.bt_multi);
        bt_divide = (Button) findViewById(R.id.bt_divide);
        bt_remainder = (Button) findViewById(R.id.bt_remainder);
        bt_equal = (Button) findViewById(R.id.bt_equal);
        tv_result = (TextView) findViewById(R.id.tv_result);

        bt_plus.setOnClickListener(this);
        bt_minus.setOnClickListener(this);
        bt_divide.setOnClickListener(this);
        bt_multi.setOnClickListener(this);
        bt_remainder.setOnClickListener(this);
        bt_equal.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int num1 = Integer.parseInt(et_num1.getText().toString());
        int num2 = Integer.parseInt(et_num2.getText().toString());
        switch (v.getId()) {
            case R.id.bt_plus:
//String a = et_num1.getText().toString();
                //int b = Integer.parseInt(a);
                result = num1 + num2;
                break;
            case R.id.bt_minus:
                result = num1 - num2;
                break;
            case R.id.bt_multi:
                result = num1 * num2;
                break;
            case R.id.bt_divide:
                result = num1 / num2;
                break;
            case R.id.bt_remainder:
                result = num1 % num2;
                break;
            case R.id.bt_equal:
                tv_result.setText("계산 결과 : " + result);
                break;
        }
    }
}
