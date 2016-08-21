package com.week.app.app160806.mypage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.week.app.app160806.member.MemberServiceImpl;
import com.week.app.app160806.R;
import com.week.app.app160806.member.MemberBean;
import com.week.app.app160806.member.MemberService;


public class AddActivity extends Activity implements View.OnClickListener{
    EditText et_name, et_phone, et_email, et_addr;
    Button bt_add;
    MemberService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        service = new MemberServiceImpl(this.getApplicationContext());

        et_name = (EditText) findViewById(R.id.et_name);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_email = (EditText) findViewById(R.id.et_email);
        et_addr = (EditText) findViewById(R.id.et_addr);
        bt_add = (Button) findViewById(R.id.bt_add);

        bt_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MemberBean member = new MemberBean();
        //String random_num = String.valueOf((int) (Math.random() * 9999) + 1000);
        member.setId(String.valueOf((int) (Math.random() * 9999) + 1000));
        //Log.d("random", String.valueOf((int) (Math.random() * 9999) + 1000));
        member.setAddr(et_addr.getText().toString());
        member.setEmail(et_email.getText().toString());
        member.setPhone(et_phone.getText().toString());
        member.setName(et_name.getText().toString());
        service.join(member);

        startActivity(new Intent(AddActivity.this, ListActivity.class));
    }
}
