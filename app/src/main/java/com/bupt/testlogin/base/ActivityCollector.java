package com.bupt.testlogin.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;
/*
* @author Jerry
* create at 2019/4/23 下午5:58
* description:管理Activity工具
*/
public class ActivityCollector {
    public static List<Activity> activitys = new ArrayList<>();//保存还没被销毁的Activity
    //添加Activity
    public static void addActivity(Activity activity){
        activitys.add(activity);
    }
    //移除Activity
    public static void removeActivity(Activity activity){
        activitys.remove(activity);
    }
    //关闭所有Activity
    public static void finishAll(){
        for(Activity activity : activitys){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }

}
