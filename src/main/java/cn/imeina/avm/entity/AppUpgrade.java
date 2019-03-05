package cn.imeina.avm.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;

/**
 * @author gaopengfei
 * App更新
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppUpgrade {
    /**
     * ID
     */
    private String id;
    /**
     * 应用ID（唯一）
     */
    private String appId;
    /**
     * 版本名称：1.0.0
     */
    private String versionName;
    /**
     * 版本号
     */
    private Long versionCode;
    /**
     * 接口环境：dev:开发环境 test:测试环境 staging:演示环境 production:生产环境
     */
    private String env;
    /**
     * 渠道名称：e.g: app_android_360.
     */
    private String channel;
    /**
     * 升级标题
     */
    private String title;
    /**
     * 更新日志
     */
    private String changeLog;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 升级检查周期：0:每次启动检查 1:一天一次
     */
    private boolean upgradeCheckPeriod;
    /**
     * 是否强制更新：0:否 1:是
     */
    private boolean upgradeIsForce;
    /**
     * 是否静默下载：0:否 1:是
     */
    private boolean upgradeIsSilent;
    /**
     * 应用状态：0:审核中 1:已上线
     */
    private boolean status;
    /**
     * 官网下载地址
     */
    private String website;
    /**
     * 应用类型：0安卓 1IOS
     */
    private Integer os;
    /**
     * 资源ID
     */
    private String resourceId;
    /**
     * 资源
     */
    private Resource resource;

    public AppUpgrade() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Long getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Long versionCode) {
        this.versionCode = versionCode;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChangeLog() {
        return changeLog;
    }

    public void setChangeLog(String changeLog) {
        this.changeLog = changeLog;
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

    public boolean getUpgradeCheckPeriod() {
        return upgradeCheckPeriod;
    }

    public void setUpgradeCheckPeriod(boolean upgradeCheckPeriod) {
        this.upgradeCheckPeriod = upgradeCheckPeriod;
    }

    public boolean getUpgradeIsForce() {
        return upgradeIsForce;
    }

    public void setUpgradeIsForce(boolean upgradeIsForce) {
        this.upgradeIsForce = upgradeIsForce;
    }

    public boolean getUpgradeIsSilent() {
        return upgradeIsSilent;
    }

    public void setUpgradeIsSilent(boolean upgradeIsSilent) {
        this.upgradeIsSilent = upgradeIsSilent;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getOs() {
        return os;
    }

    public void setOs(Integer os) {
        this.os = os;
    }

    @Override
    public String toString() {
        return "AppUpgrade{" +
                "id='" + id + '\'' +
                ", appId='" + appId + '\'' +
                ", versionName='" + versionName + '\'' +
                ", versionCode=" + versionCode +
                ", env='" + env + '\'' +
                ", channel='" + channel + '\'' +
                ", title='" + title + '\'' +
                ", changeLog='" + changeLog + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", upgradeCheckPeriod=" + upgradeCheckPeriod +
                ", upgradeIsForce=" + upgradeIsForce +
                ", upgradeIsSilent=" + upgradeIsSilent +
                ", status=" + status +
                ", website='" + website + '\'' +
                ", os=" + os +
                ", resourceId='" + resourceId + '\'' +
                ", resource=" + resource +
                '}';
    }
}
