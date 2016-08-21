package com.week.app.app160806.mypage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.week.app.app160806.R;
import com.week.app.app160806.member.MemberBean;
import com.week.app.app160806.member.MemberService;
import com.week.app.app160806.member.MemberServiceImpl;

public class UpdateActivity extends Activity implements View.OnClickListener{

    Button bt_confirm,bt_cancel;
    EditText et_pass,et_email,et_addr;
    MemberService service;
    MemberBean member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        service = new MemberServiceImpl(this.getApplicationContext());
        Log.d("마이페이지 엑티비티에 전달된 ID",getIntent().getStringExtra("id"));
        member = service.findById(getIntent().getStringExtra("id"));
        Log.d("DB에서 추출된 이름 :",member.getName());
        et_pass = (EditText) findViewById(R.id.et_pass);
        et_email = (EditText) findViewById(R.id.et_email);
        et_addr = (EditText) findViewById(R.id.et_addr);
        bt_confirm = (Button) findViewById(R.id.bt_confirm);
        bt_cancel = (Button) findViewById(R.id.bt_cancel);

        bt_confirm.setOnClickListener(this);
        bt_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_confirm :
                Log.d("===수정 확인버튼", " 클릭");
               /* if(et_email.getText().toString().equals("")){
                    break;
                }else{
                   member.setEmail(et_email.getText().toString());
                }
                if(et_addr.getText().toString().equals("")){
                    break;
                }else{
                    member.setAddr(et_addr.getText().toString());
                }
                if(et_pass.getText().toString().equals("")){
                    break;
                }else{
                    member.setPw(et_pass.getText().toString());
                }*/
                member.setPw(et_pass.getText().toString());
                service.update(member);
                MemberBean temp = service.findById(member.getId());
                Log.d("수정된 정보",temp.toString());
                Intent intent = new Intent(UpdateActivity.this,MyPageActivity.class);
                intent.putExtra("id",member.getId());
                startActivity(intent);

                break;
            case R.id.bt_cancel :
                Intent intent2 = new Intent(UpdateActivity.this,MyPageActivity.class);
                intent2.putExtra("id",member.getId());
                startActivity(intent2);
                break;
        }

    }
}
