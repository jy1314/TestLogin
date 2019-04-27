package com.bupt.testlogin.entry;
/*
* @author Jerry
* create at 2019/4/27 下午8:28
* description: 用户信息实体
*/
public class UserEntry {
    private String account;
    private String password;

    public UserEntry() {
    }

    public UserEntry(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
