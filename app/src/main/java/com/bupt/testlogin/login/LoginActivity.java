package com.bupt.testlogin.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bupt.testlogin.R;
import com.bupt.testlogin.base.BaseActivity;
import com.bupt.testlogin.entry.UserEntry;
import com.bupt.testlogin.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/*
* @author Jerry
* create at 2019/4/23 下午6:55
* description:登陆界面
*/
public class LoginActivity extends BaseActivity implements LoginContract.View{
    private static final String TAG = "LoginActivity";
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.account)
    EditText account;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.isRememberPwd)
    CheckBox isRememberPwd;

    private UserEntry user;//用户实体
    private SharedPreferences pref;//保存信息
    private SharedPreferences.Editor editor;
    private LoginContract.Presenter presenter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        presenter = new LoginPresenter(this);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        editor = pref.edit();
        boolean isRemember = pref.getBoolean("isRemember",false);
        if(isRemember){
            isRememberPwd.setChecked(true);
            account.setText(pref.getString("account",""));
            password.setText(pref.getString("password",""));
        }
    }

    @OnClick(R.id.login)
    public void loginAccount(){
        String acc = account.getText().toString();
        String pwd = password.getText().toString();
        user = new UserEntry(acc,pwd);
        presenter.login(user);
    }
    @Override
    public void loginSuccessed() {
        //保存用户名密码
        if(isRememberPwd.isChecked()){
            editor.putBoolean("isRemember",true);
            editor.putString("account",user.getAccount());
            editor.putString("password",user.getPassword());
        }else{
            editor.clear();
        }
        editor.apply();
        //跳转到主界面
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();//销毁登陆Activity
    }

    @Override
    public void loginFailed() {
        Toast.makeText(this,"用户名或密码有误",Toast.LENGTH_SHORT).show();
    }



}
