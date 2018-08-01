package com.mumu.utils;
import java.io.Serializable;
import java.util.List;

/**
 * 后台管理系统EasyUI需要返回的结果集，rows是每一行的数据，total是总数
 * @author mumu
 *
 */
public class EasyUIDataGridResult implements Serializable{
	private long total;
	private List rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	
}