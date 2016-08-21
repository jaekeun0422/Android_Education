package com.week.app.app160806.member;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.week.app.app160806.R;

public class JoinActivity extends Activity implements View.OnClickListener {
    EditText et_id, et_pw, et_name, et_phone, et_email, et_addr;
    Button bt_join;
    MemberService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        service = new MemberServiceImpl(this.getApplicationContext());//context가 전달됨
        et_id = (EditText) findViewById(R.id.et_id);
        et_pw = (EditText) findViewById(R.id.et_pw);
        et_name = (EditText) findViewById(R.id.et_name);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_email = (EditText) findViewById(R.id.et_email);
        et_addr = (EditText) findViewById(R.id.et_addr);
        bt_join = (Button) findViewById(R.id.bt_join);

        bt_join.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MemberBean member = new MemberBean();
        member.setId(et_id.getText().toString());
        member.setPw(et_pw.getText().toString());
        member.setName(et_name.getText().toString());
        member.setPhone(et_phone.getText().toString());
        member.setEmail(et_email.getText().toString());
        member.setAddr(et_addr.getText().toString());

        service.join(member);
        startActivity(new Intent(this.getApplicationContext(), LoginActivity.class));

    }
}
