package com.sttri.entity;

import java.io.Serializable;
import java.util.Date;

public class File implements Serializable {
    //
    private Integer id;

    //上传文件的用户编号
    private Integer uid;

    //文件名称
    private String filename;

    //文件存储路径
    private String filepath;

    //文件的审批状态 0-通过 1-封禁
    private Integer flag;

    //文件状态 0-个人 1-公共
    private Integer status;

    //标题
    private String subject;

    //文件描述
    private String summary;

    //点赞数
    private Integer praises;

    //封禁图片路径
    private String forbiddenpic;

    //上传日期
    private Date uploadtime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath == null ? null : filepath.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Integer getPraises() {
        return praises;
    }

    public void setPraises(Integer praises) {
        this.praises = praises;
    }

    public String getForbiddenpic() {
        return forbiddenpic;
    }

    public void setForbiddenpic(String forbiddenpic) {
        this.forbiddenpic = forbiddenpic == null ? null : forbiddenpic.trim();
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", filename=").append(filename);
        sb.append(", filepath=").append(filepath);
        sb.append(", flag=").append(flag);
        sb.append(", status=").append(status);
        sb.append(", subject=").append(subject);
        sb.append(", summary=").append(summary);
        sb.append(", praises=").append(praises);
        sb.append(", forbiddenpic=").append(forbiddenpic);
        sb.append(", uploadtime=").append(uploadtime);
        sb.append("]");
        return sb.toString();
    }
}