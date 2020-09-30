package com.android.baihuahu.act_main;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.baihuahu.R;
import com.android.baihuahu.bean.BillInfo;
import com.android.baihuahu.core.utils.KeyConst;
import com.android.baihuahu.core.utils.TextUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Dylan
 */

public class MainListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<BillInfo> billInfos = new ArrayList<>();
    private MainActivity context;

    public MainListAdapter(MainActivity context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return billInfos.size();
    }

    @Override
    public Object getItem(int i) {
        return billInfos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final BillInfo info = billInfos.get(position);
        ViewHolder holder;
        if (convertView == null) {
            holder = new MainListAdapter.ViewHolder();
            convertView = mInflater.inflate(R.layout.main_item, viewGroup, false);
            holder.iconIv = convertView.findViewById(R.id.main_item_iv);
            holder.nameTv = convertView.findViewById(R.id.main_item_name_tv);
            holder.timeTv = convertView.findViewById(R.id.main_item_time_tv);
            holder.descTv = convertView.findViewById(R.id.main_item_desc_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position == getCount() - 1) {
            int px20 = context.getResources().getDimensionPixelSize(R.dimen.dm020);
            convertView.setPadding(0, px20, 0, px20 * 3);
        }
        if (null != info) {
            holder.nameTv.setText(info.getName());
            holder.descTv.setText(info.getUsePosition()+"　完成："+"0%");
            holder.timeTv.setText(TextUtil.subTimeYMD(info.getUpdateTime()));

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, BillDetailActivity.class);
                    intent.putExtra(KeyConst.id, info.getId());
                    context.startActivity(intent);

                }
            });
        }
        return convertView;
    }

    public void setData(List<BillInfo> infoList) {
        billInfos = infoList;
        notifyDataSetChanged();
    }


    public class ViewHolder {
        private TextView nameTv, timeTv, descTv;
        private ImageView iconIv;
    }

}
