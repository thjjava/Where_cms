package com.sttri.entity;

import java.io.Serializable;
import java.util.Date;

public class Share implements Serializable {
    //
    private Integer id;

    //用户编号
    private Integer uid;

    //文件编号
    private Integer fid;

    //状态
    private Integer flag;

    //说明
    private String summary;

    //添加日期
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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
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
        sb.append(", flag=").append(flag);
        sb.append(", summary=").append(summary);
        sb.append(", addtime=").append(addtime);
        sb.append("]");
        return sb.toString();
    }
}