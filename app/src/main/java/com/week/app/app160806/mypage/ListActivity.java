package com.week.app.app160806.mypage;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v7.app.AlertDialog;

import com.week.app.app160806.R;
import com.week.app.app160806.member.LoginActivity;
import com.week.app.app160806.member.MemberBean;
import com.week.app.app160806.member.MemberService;
import com.week.app.app160806.member.MemberServiceImpl;

import java.util.ArrayList;

public class ListActivity extends Activity implements View.OnClickListener{
    EditText et_search;
    Button bt_mypage, bt_findByName, bt_findById, bt_add;
    MemberService service;
    ListView lv_memberlist;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        service = new MemberServiceImpl(getApplicationContext());
        ArrayList<MemberBean> list = service.list();

        et_search = (EditText) findViewById(R.id.et_search);
        bt_mypage = (Button) findViewById(R.id.bt_mypage);
        bt_findByName = (Button) findViewById(R.id.bt_findByName);
        bt_findById = (Button) findViewById(R.id.bt_findById);
        bt_add = (Button) findViewById(R.id.bt_add);
        lv_memberlist = (ListView) findViewById(R.id.lv_memberlist);
        lv_memberlist.setAdapter(new MemberAdapter(this, list));
        lv_memberlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int i, long id) {
                Object o = lv_memberlist.getItemAtPosition(i);
                //Log.d("long type check: ",o.toString());
                MemberBean member = (MemberBean) o;
                Log.d("ListA/선택한 이름", o.toString());
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                intent.putExtra("id", member.getId()); //collection(map) like #
                startActivity(intent);
            }
        });

        lv_memberlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View v, int i, long id) {
                position = i;
                new AlertDialog.Builder(ListActivity.this)
                        .setTitle("Delete ?")
                        .setMessage("정말 삭제 하시겠습니까?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                Object o = lv_memberlist.getItemAtPosition(position);
                                MemberBean member = (MemberBean) o;
                                service.delete(member.getId());
                                //service.delete("");
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return true;
            }
        });

        bt_mypage.setOnClickListener(this);
        bt_findByName.setOnClickListener(this);
        bt_findById.setOnClickListener(this);
        bt_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String keyword = et_search.getText().toString();
        switch (v.getId()) {
            case R.id.bt_mypage :
                //String id = getIntent().getStringExtra("id");
                //Log.d("ListA/id from LoginAct:", id);

                SharedPreferences sharedpreferences;
                sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                //key String: The name of the preference to retrieve.
                //defValue String:Value to return if this preference does not exist.

                String shared_id = sharedpreferences.getString("id", "WRONG");
                Log.d("id from sharedprefer: ", shared_id);

                Intent intent = new Intent(this.getApplicationContext(), MyPageActivity.class);
                //intent.putExtra("id", id);
                intent.putExtra("id", shared_id);
                startActivity(intent);
                break;
            case R.id.bt_findByName :
                String name = et_search.getText().toString();
                if (name.equals("")){
                    Toast.makeText(ListActivity.this, "검색어를 입력하세요", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(ListActivity.this, "검색어: " + name, Toast.LENGTH_LONG).show();
                    //List<MemberBean> list = service.findByName(keyword);
                    Intent intent3 = new Intent(this.getApplicationContext(), SearchActivity.class);
                    Log.d("ListA/ id to DetailA: ", keyword);
                    intent3.putExtra("keyword", keyword);
                    startActivity(intent3);
                }
                break;
            case R.id.bt_findById :
                String search_id = et_search.getText().toString();
                if (search_id.equals("")){
                    Toast.makeText(ListActivity.this, "검색어를 입력하세요", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(ListActivity.this, "검색어: " + search_id, Toast.LENGTH_LONG).show();
                    MemberBean member = service.findById(keyword);
                    if(member.getId().equals("NONE")){
                        Toast.makeText(ListActivity.this, "해당 ID가 존재하지 않습니다", Toast.LENGTH_LONG).show();
                    }else{
                        Intent intent2 = new Intent(this.getApplicationContext(), DetailActivity.class);
                        Log.d("ListA/ id to DetailA: ", member.getId());
                        intent2.putExtra("id", member.getId());
                        startActivity(intent2);
                    }
                }
                break;
            case R.id.bt_add :
                startActivity(new Intent(this.getApplicationContext(), AddActivity.class));
                break;
        }

    }
}
