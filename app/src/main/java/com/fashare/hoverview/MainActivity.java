package com.fashare.hoverview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fashare.hover_view.HoverViewContainer;
import com.fashare.hover_view.ViewState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn)
    Button mBtn;
    @BindView(R.id.hv)
    HoverViewContainer mHv;
    @BindView(R.id.rv)
    RecyclerView mRv;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mHv.getState().getTop() > ViewState.HOVER.getTop())
                    mHv.changeState(ViewState.HOVER);
            }
        });

        mRv.setAdapter(mAdapter = new MyAdapter(this));
        mRv.setLayoutManager(new LinearLayoutManager(this));

        mAdapter.setDataList(Arrays.asList(
                "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20"
        ));
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        Context mContext;
        List<String> mDataList;

        public void setDataList(List<String> dataList) {
            mDataList = dataList;
            notifyDataSetChanged();
        }

        public MyAdapter(Context context) {
            mContext = context;
            mDataList = new ArrayList<>();
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyAdapter.ViewHolder(mContext);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
            holder.bind(mDataList.get(position));
        }

        @Override
        public int getItemCount() {
            return mDataList!=null? mDataList.size(): 0;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;

            public ViewHolder(Context context) {
                super(View.inflate(context, android.R.layout.simple_list_item_1, null));
                mTextView = (TextView)itemView.findViewById(android.R.id.text1);
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
                        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}
