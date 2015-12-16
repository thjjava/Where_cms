package com.sttri.bean;

import java.util.ArrayList;
import java.util.List;

public class PageUtil<T> {
	 /** 总记录数**/
	private int totalCount;
	 /** 总页数**/
    private int totalPage;
    /** 上一页**/
    private int prePage;
    /** 下一页**/
    private int nextPage;
    /** 当前页**/
    private int currentPage;
    private List<T> datas = new ArrayList<T>();
    
    public PageUtil(int pageLimit, int totalCount, int requestPage) {
    	this.totalCount = totalCount;
        this.totalPage = (int) Math.ceil((double) totalCount / (double) pageLimit);
        if(totalPage<=0) {
        	totalPage = 1;
        }
        this.currentPage = caculateCurrentPage(requestPage);
        this.prePage = (this.currentPage > 1) ? this.currentPage - 1 : 0;
        this.nextPage = (totalPage > this.currentPage) ? this.currentPage + 1 : totalPage;
    }
    
    private int caculateCurrentPage(int requestPage) {
        int smallestPage = 1;
        try {
            return Math.min(totalPage, Math.max(smallestPage, requestPage));
        } catch (Exception e) {
            return smallestPage;
        }
    }

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

}
