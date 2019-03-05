package cn.imeina.avm.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    /**
     * 用户ID
     */
    private String uid;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别：0男 1女
     */
    private Integer sex;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String telephone;
    /**
     * 住址
     */
    private String address;
    /**
     * 个性签名
     */
    private String signature;
    /**
     * 账号状态：0正常 -1禁用
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * JWT生成的Token信息
     */
    private String token;

    public User() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            this.createTime = format.parse(createTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            this.updateTime = format.parse(updateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", avatar='" + avatar + '\'' +
                ", userName='" + userName + '\'' +
                ", realName='" + realName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", signature='" + signature + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", token='" + token + '\'' +
                '}';
    }
}
