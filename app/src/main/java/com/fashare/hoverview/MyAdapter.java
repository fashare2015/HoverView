package com.fashare.hoverview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinliangshan on 16/12/14.
 */
class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private MainActivity mMainActivity;
    Context mContext;
    List<String> mDataList;

    public void setDataList(List<String> dataList) {
        mDataList = dataList;
        notifyDataSetChanged();
    }

    public MyAdapter(MainActivity mainActivity, Context context) {
        mMainActivity = mainActivity;
        mContext = context;
        mDataList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mContext);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList != null ? mDataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(Context context) {
            super(View.inflate(context, android.R.layout.simple_list_item_1, null));
            mTextView = (TextView) itemView.findViewById(android.R.id.text1);
            mTextView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            itemView.requestLayout();
        }

        public void bind(final String data) {
            mTextView.setText(data);
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mMainActivity, data, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
