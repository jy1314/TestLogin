package com.bupt.testlogin.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.bupt.testlogin.R;
import com.bupt.testlogin.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/*
* @author Jerry
* create at 2019/4/27 下午8:30
* description:主界面
*/
public class MainActivity extends BaseActivity {
    @BindView(R.id.finishall)
    Button finishall_button;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    //强制下线
    @OnClick(R.id.finishall)
    public void finishallActivity(){
        Intent intent = new Intent("com.bupt.testlogin.FORCE_OFFLINE");//发送广播
        sendBroadcast(intent);
    }
}
