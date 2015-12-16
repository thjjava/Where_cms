package com.sttri.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RoleMenus implements Serializable {
    //
    private Integer id;

    //管理员编号
    private Integer aid;

    //菜单编号
    private Integer mid;

    //
    private Date addtime;
    
    private List<Menus> menus;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public List<Menus> getMenus() {
		return menus;
	}

	public void setMenus(List<Menus> menus) {
		this.menus = menus;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", aid=").append(aid);
        sb.append(", mid=").append(mid);
        sb.append(", addtime=").append(addtime);
        sb.append("]");
        return sb.toString();
    }
}