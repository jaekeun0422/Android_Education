package com.week.app.app160806.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.week.app.app160806.member.MemberServiceImpl;
import com.week.app.app160806.R;
import com.week.app.app160806.member.MemberBean;
import com.week.app.app160806.member.MemberService;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    Button bt_list;
    MemberService service;
    ListView lv_memberlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        service = new MemberServiceImpl(getApplicationContext());
        ArrayList<MemberBean> list = service.findByName(getIntent().getStringExtra("keyword"));

        bt_list = (Button) findViewById(R.id.bt_list);
        lv_memberlist = (ListView) findViewById(R.id.lv_memberlist);
        lv_memberlist.setAdapter(new MemberAdapter(this, list));
        lv_memberlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int i, long id) {
                Object o = lv_memberlist.getItemAtPosition(i);
                //Log.d("long type check: ",o.toString());
                MemberBean member = (MemberBean) o;
                Log.d("ListA/선택한 이름", o.toString());
                Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
                intent.putExtra("id", member.getId()); //collection(map) like #
                startActivity(intent);
            }
        });

        bt_list.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(SearchActivity.this, ListActivity.class));
    }
}
