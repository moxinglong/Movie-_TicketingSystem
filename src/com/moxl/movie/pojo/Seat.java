package com.moxl.movie.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * seat
 * @author otom3
 * @date 2017-06-05
 */
 @Table("seat")
public class Seat  {
	/**
	 * seat
	 */
	 @Column
	private java.lang.String rname;//放映室编号
	 @Column
	private java.lang.String sid;//座位号
	 @Column
	private java.sql.Timestamp starttime;//开始时间
	 @Column
	private java.sql.Timestamp endtime;//结束时间
	
	public Seat() {
		super();
	}
	
	public Seat(String rname, String sid, Timestamp starttime, Timestamp endtime) {
		super();
		this.rname = rname;
		this.sid = sid;
		this.starttime = starttime;
		this.endtime = endtime;
	}

	public java.lang.String getRname(){
		return this.rname;
	}
	public void setRname(java.lang.String rname){
		this.rname	= rname;
	}
	public java.lang.String getSid(){
		return this.sid;
	}
	public void setSid(java.lang.String sid){
		this.sid	= sid;
	}
	public java.sql.Timestamp getStarttime(){
		return this.starttime;
	}
	public void setStarttime(java.sql.Timestamp starttime){
		this.starttime	= starttime;
	}
	public java.sql.Timestamp getEndtime(){
		return this.endtime;
	}
	public void setEndtime(java.sql.Timestamp endtime){
		this.endtime	= endtime;
	}
}