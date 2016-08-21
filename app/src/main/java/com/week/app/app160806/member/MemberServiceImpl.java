package com.week.app.app160806.member;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 1027 on 2016-08-06.
 */
public class MemberServiceImpl implements MemberService {
    MemberDAO dao;

    public MemberServiceImpl(Context context) {
        this.dao = new MemberDAO(context);
    }

    @Override
    public MemberBean login(MemberBean member) {
        Log.d("서비스:Login ID 체크", member.getId());
        return dao.login(member);
    }

    @Override
    public MemberBean findById(String id) {
        Log.d("서비스:Join ID 체크", id);
        return dao.findById(id);
    }

    @Override
    public int count() {
        Log.d("서비스:Join ID 체크", "진입");
        return dao.count();
    }

    @Override
    public ArrayList<MemberBean> list() {
        Log.d("서비스:Join ID 체크", "진입");
        return dao.list();
    }

    @Override
    public ArrayList<MemberBean> findByName(String name) {
        Log.d("서비스:FIND_BY_NAME","진입");
        return dao.findByName(name);
    }

    @Override
    public void update(MemberBean member) {
        Log.d("서비스:UPDATE - ID 체크",member.getId());
        dao.update(member);
    }

    @Override
    public void delete(String id) {
        Log.d("서비스:DELETE - ID 체크",id);
        dao.delete(id);
    }

    @Override
    public void join(MemberBean member) {
        Log.d("서비스:Join ID 체크",member.getId());
        dao.join(member);
    }
}
