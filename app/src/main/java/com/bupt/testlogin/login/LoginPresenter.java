package com.bupt.testlogin.login;

import com.bupt.testlogin.entry.UserEntry;

/*
* @author Jerry
* create at 2019/4/27 下午8:29
* description:登陆Presenter，实现具体的登陆逻辑
*/
public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    //登陆判断方法Demo，具体逻辑还需进一步完善
    @Override
    public void login(UserEntry user) {

        if(user.getAccount().equals("admin") && user.getPassword().equals("123456")){
            view.loginSuccessed();
        }else{
            view.loginFailed();
        }
    }

}
