package cn.imeina.avm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * 应用信息
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppInfoDTO {

    private String id;

    private String name;

    private String packageName;

    private Byte appType;

    private Date createTime;

    private Date updateTime;

    public AppInfoDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Byte getAppType() {
        return appType;
    }

    public void setAppType(Byte appType) {
        this.appType = appType;
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

    @Override
    public String toString() {
        return "AppInfoDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", appType=" + appType +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}