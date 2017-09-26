package com.example.dell.jianshudemo.mvp.function.javabean;

/**
 * 作者：wl on 2017/9/26 15:11
 * 邮箱：wangl@ixinyongjia.com
 */
public class UserInfo {
    private int user_id;
    private String user_name;
    private String avatar;
    private String email;
    private String mobile;
    private int last_login_time;
    private String last_login_ip;
    private int reg_time;
    private String reg_ip;

    private int reg_type;
    private int status;
    private int vip_yes;
    private int vip_end_time;
    private String loginkey;



    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(int last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getLast_login_ip() {
        return last_login_ip;
    }

    public void setLast_login_ip(String last_login_ip) {
        this.last_login_ip = last_login_ip;
    }

    public int getReg_time() {
        return reg_time;
    }

    public void setReg_time(int reg_time) {
        this.reg_time = reg_time;
    }

    public String getReg_ip() {
        return reg_ip;
    }

    public void setReg_ip(String reg_ip) {
        this.reg_ip = reg_ip;
    }

    public int getReg_type() {
        return reg_type;
    }

    public void setReg_type(int reg_type) {
        this.reg_type = reg_type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getVip_yes() {
        return vip_yes;
    }

    public void setVip_yes(int vip_yes) {
        this.vip_yes = vip_yes;
    }

    public int getVip_end_time() {
        return vip_end_time;
    }

    public void setVip_end_time(int vip_end_time) {
        this.vip_end_time = vip_end_time;
    }

    public String getLoginkey() {
        return loginkey;
    }

    public void setLoginkey(String loginkey) {
        this.loginkey = loginkey;
    }
}
