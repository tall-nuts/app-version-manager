package cn.imeina.avm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * @author gaopengfei
 * @date 2019-02-28
 * @description
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppVersionDTO {

    private Integer id;
    /**
     * 应用ID
     */
    private String appId;
    /**
     * 0-非审核中 1-审核中
     */
    private String status;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 版本
     */
    private String version;
    /**
     * 弹框规则
     */
    private String boxDisplayRules;
    /**
     * 是否强制更新
     */
    private String isForceUpdate;
    /**
     * 是否弹框
     */
    private String isBoxDisplay;
    /**
     * 应用下载地址
     */
    private String downloadUrl;
    /**
     * 版本Code
     */
    private String code;
    /**
     * 应用文件MD5
     */
    private String fileMd5;
    /**
     * 升级创建时间
     */
    private Date createTime;
    /**
     * 升级修改时间
     */
    private Date updateTime;
    /**
     * app名称
     */
    private String name;
    /**
     * 应用包名称
     */
    private String packageName;
    /**
     * 设备类型 1:IOS 2:Android
     */
    private String appType;
    /**
     * 是否为最新版本
     */
    private String isLatest;
    //分页
    private Integer page;
    private Integer limit;

    public AppVersionDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBoxDisplayRules() {
        return boxDisplayRules;
    }

    public void setBoxDisplayRules(String boxDisplayRules) {
        this.boxDisplayRules = boxDisplayRules;
    }

    public String getIsForceUpdate() {
        return isForceUpdate;
    }

    public void setIsForceUpdate(String isForceUpdate) {
        this.isForceUpdate = isForceUpdate;
    }

    public String getIsBoxDisplay() {
        return isBoxDisplay;
    }

    public void setIsBoxDisplay(String isBoxDisplay) {
        this.isBoxDisplay = isBoxDisplay;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
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

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getIsLatest() {
        return isLatest;
    }

    public void setIsLatest(String isLatest) {
        this.isLatest = isLatest;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "AppVersionDTO{" +
                "id=" + id +
                ", appId='" + appId + '\'' +
                ", status='" + status + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", version='" + version + '\'' +
                ", boxDisplayRules='" + boxDisplayRules + '\'' +
                ", isForceUpdate='" + isForceUpdate + '\'' +
                ", isBoxDisplay='" + isBoxDisplay + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", code='" + code + '\'' +
                ", fileMd5='" + fileMd5 + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", appType='" + appType + '\'' +
                ", isLatest='" + isLatest + '\'' +
                ", page=" + page +
                ", limit=" + limit +
                '}';
    }
}
