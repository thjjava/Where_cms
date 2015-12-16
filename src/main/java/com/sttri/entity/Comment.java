package com.sttri.entity;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    //
    private Integer id;

    //评论人编号
    private Integer uid;

    //文件编号
    private Integer fid;

    //上层评论人编号
    private Integer pid;

    //状态 0-显示 1-不显示
    private Integer flag;

    //内容
    private String content;

    //评论日期
    private Date addtime;

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

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", fid=").append(fid);
        sb.append(", pid=").append(pid);
        sb.append(", flag=").append(flag);
        sb.append(", content=").append(content);
        sb.append(", addtime=").append(addtime);
        sb.append("]");
        return sb.toString();
    }
}