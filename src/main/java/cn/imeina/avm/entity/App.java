package cn.imeina.avm.entity;

import java.util.Date;

public class App {
    /**
     * Id
     */
    private String id;
    /**
     * 应用包名
     */
    private String packageName;
    /**
     * 应用名称
     */
    private String appName;
    /**
     * 应用图标地址
     */
    private String icon;
    /**
     * 系统类型：0:Android 1:IOS
     */
    private Integer os;
    /**
     * 应用官网下载地址：IOS填写AppStore地址 Android可填写官网下载地址
     */
    private String website;
    /**
     * 上传时间
     */
    private Date createTime;
    /**
     * 信息更改时间
     */
    private Date updateTime;
    /**
     * 构建人ID
     */
    private String createUid;
    /**
     * 应用简介
     */
    private String description;
    /**
     * 一句话简介
     */
    private String logline;
    /**
     * 最新版本更新信息
     */
    private AppUpgrade lastUpgradeInfo;
    public App() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getOs() {
        return os;
    }

    public void setOs(Integer os) {
        this.os = os;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUid() {
        return createUid;
    }

    public void setCreateUid(String createUid) {
        this.createUid = createUid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogline() {
        return logline;
    }

    public void setLogline(String logline) {
        this.logline = logline;
    }

    public AppUpgrade getLastUpgradeInfo() {
        return lastUpgradeInfo;
    }

    public void setLastUpgradeInfo(AppUpgrade lastUpgradeInfo) {
        this.lastUpgradeInfo = lastUpgradeInfo;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }


    @Override
    public String toString() {
        return "App{" +
                "id='" + id + '\'' +
                ", packageName='" + packageName + '\'' +
                ", appName='" + appName + '\'' +
                ", icon='" + icon + '\'' +
                ", os=" + os +
                ", website='" + website + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUid='" + createUid + '\'' +
                ", description='" + description + '\'' +
                ", logline='" + logline + '\'' +
                ", lastUpgradeInfo=" + lastUpgradeInfo +
                '}';
    }
}
