package cn.imeina.avm.entity;

import java.util.Date;

/**
 * 资源文件
 */
public class Resource {
    /**
     * 索引
     */
    private String id;
    /**
     * 资源文件名称
     */
    private String name;
    /**
     * 下载地址
     */
    private String url;
    /**
     * 文件类型
     */
    private String mimeType;
    /**
     * 文件大小
     */
    private Long size;
    /**
     * 文件MD5
     */
    private String md5;
    /**
     * 文件扩展名
     */
    private String extension;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 文件描述
     */
    private String description;
    /**
     * OSS文件名
     */
    private String objectKey;

    public Resource() {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", mimeType='" + mimeType + '\'' +
                ", size=" + size +
                ", md5='" + md5 + '\'' +
                ", extension='" + extension + '\'' +
                ", createTime=" + createTime +
                ", description='" + description + '\'' +
                ", objectKey='" + objectKey + '\'' +
                '}';
    }
}