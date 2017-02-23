package com.porster.gift;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.porster.gift.utils.ApiUtils;
import com.porster.gift.widget.PathSearch;

public class StartActivity extends Activity implements PathSearch.OnPathEnd{
    private PathSearch mPathSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportLolinpop();
        setContentView(R.layout.activity_start);

        mPathSearch= (PathSearch) findViewById(R.id.pathSearch);
        mPathSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((PathSearch)v).start(StartActivity.this);
            }
        });
        mPathSearch.post(new Runnable() {
            @Override
            public void run() {
                mPathSearch.performClick();
            }
        });
    }

    @Override
    public void OnEnd() {
        ObjectAnimator alpha=ObjectAnimator.ofFloat(findViewById(R.id.wel_txt),"alpha",0.0f,1.0f);
        alpha.start();
    }
    /**支持5.0
     * 5.0可以让布局延伸到状态栏*/
    public void supportLolinpop(){
        if(ApiUtils.isLolinpop()){
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
