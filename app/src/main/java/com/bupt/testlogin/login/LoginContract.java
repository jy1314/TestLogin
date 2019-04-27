package com.bupt.testlogin.login;

import com.bupt.testlogin.entry.UserEntry;

/*
* @author Jerry
* create at 2019/4/23 下午7:00
* description:
*/
public interface LoginContract {
    interface View{
        void loginSuccessed();//登陆成功调用此方法
        void loginFailed();//登陆失败调用此方法
    }

    interface Presenter{
        void login(UserEntry user);//登陆方法
    }
}
