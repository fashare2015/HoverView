package com.fashare.hoverview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.fashare.hover_view.HoverViewContainer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final HoverViewContainer hoverViewContainer = (HoverViewContainer) findViewById(R.id.hv);


        final View btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hoverViewContainer.showAsHover();
            }
        });
    }
}
