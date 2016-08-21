package com.week.app.app160806.mypage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.week.app.app160806.member.MemberBean;
import com.week.app.app160806.R;

import java.util.ArrayList;

/**
 * Created by 1027 on 2016-08-20.
 */
public class MemberAdapter extends BaseAdapter {
    ArrayList<MemberBean> list;
    LayoutInflater inflater;
    int[] imgs={
            R.drawable.cupcake,
            R.drawable.donut,
            R.drawable.eclair,
            R.drawable.froyo,
            R.drawable.gingerbread,
            R.drawable.honeycomb,
            R.drawable.icecream,
            R.drawable.jellybean,
            R.drawable.kitkat,
            R.drawable.lollipop
    };

    public MemberAdapter(Context context, ArrayList<MemberBean> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View v, ViewGroup g) {
        ViewHolder holder;
        if(v==null){
            v =inflater.inflate(R.layout.member_list, null);
            holder = new ViewHolder();
            holder.ivPhoto = (ImageView) v.findViewById(R.id.iv_photo);
            holder.tv_Name = (TextView) v.findViewById(R.id.tv_name);
            holder.tv_phone = (TextView) v.findViewById(R.id.tv_phone);
            v.setTag(holder);
        }else{
            holder = (ViewHolder) v.getTag();
        }
        int j;
        j = i;
        if (j > 9) j = 0;
        holder.ivPhoto.setImageResource(imgs[j]);//개수 10개인데 DB record수는 10개 이상임
        holder.tv_Name.setText(list.get(i).getName());
        holder.tv_phone.setText(list.get(i).getPhone());

        return v;
    }
    static class ViewHolder{
        ImageView ivPhoto;
        TextView tv_Name;
        TextView tv_phone;
    }
}
