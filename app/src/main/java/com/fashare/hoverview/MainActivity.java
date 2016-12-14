package com.fashare.hoverview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.fashare.hover_view.HoverView;
import com.fashare.hover_view.ViewState;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn)
    Button mBtn;
    @BindView(R.id.hv)
    HoverView mHv;
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
                if (mHv.getState() == ViewState.CLOSE)  // "关闭" 状态
                    mHv.changeState(ViewState.HOVER);   // 打开至 "悬停" 状态
//                    mHv.changeState(ViewState.FILL);   // 打开至 "全屏" 状态
                else
                    mHv.changeState(ViewState.CLOSE);   // 切换至 "关闭" 状态
            }
        });

        mRv.setAdapter(mAdapter = new MyAdapter(this, this));
        mRv.setLayoutManager(new LinearLayoutManager(this));

        mAdapter.setDataList(Arrays.asList(
                "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20"
        ));
    }

}
