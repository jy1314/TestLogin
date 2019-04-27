package com.bupt.testlogin.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bupt.testlogin.login.LoginActivity;

import butterknife.ButterKnife;

/*
* @author Jerry
* create at 2019/4/23 下午5:53
* description:所有Activity的父类
*/
public abstract class BaseActivity extends AppCompatActivity {

    private ForceOfflineReceiver forceOfflineReceiver;//强制下线广播接收器
    /*
     * @author: Jerry
     * @create at 2019/4/23 下午7:33
     * @description: 获取布局id
     */
    protected abstract int getContentViewLayoutID();
    /*
     * @author: Jerry
     * @create at 2019/4/23 下午7:37
     * @Param: 
     * @description: 进行初始化相关工作
     * @return: 
     */
    protected abstract void init(Bundle savedInstanceState);

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);//ButterKnife绑定Activity
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getContentViewLayoutID()!=0) {//布局id不为空
            setContentView(getContentViewLayoutID());//加载布局
            init(savedInstanceState);
        }
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //绑定广播接收器
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.bupt.testlogin.FORCE_OFFLINE");
        forceOfflineReceiver = new ForceOfflineReceiver();
        registerReceiver(forceOfflineReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //解绑广播接收器
        if(forceOfflineReceiver != null){
            unregisterReceiver(forceOfflineReceiver);
            forceOfflineReceiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    class ForceOfflineReceiver extends BroadcastReceiver{
        //收到广播后弹出窗口
        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning")
                    .setCancelable(false)//不可取消
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCollector.finishAll();
                            Intent intent = new Intent(context, LoginActivity.class);
                            startActivity(intent);
                        }
                    }).show();
        }
    }



}
