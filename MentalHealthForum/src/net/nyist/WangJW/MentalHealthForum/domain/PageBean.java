package net.nyist.WangJW.MentalHealthForum.domain;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.alibaba.fastjson.annotation.JSONField;

public class PageBean {
	private int currentPage;//当前页码
	private int pageSize;//没有显示记录数
	private DetachedCriteria detachedCriteria;//分页查询条件
	
	private int total;//总记录数
	private List rows;//当前页展示的数据集合
	
	@JSONField(serialize=false)
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	@JSONField(serialize=false)
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@JSONField(serialize=false)
	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}
	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
}
