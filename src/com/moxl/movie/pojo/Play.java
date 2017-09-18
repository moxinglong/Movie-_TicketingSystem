package com.moxl.movie.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * play
 * @author otom3
 * @date 2017-06-08
 */
 @Table("play")
public class Play  {
	/**
	 * play
	 */
	 @Name
	private java.lang.String id;//id
	 @Column
	private java.lang.String rname;//影厅号
	 @Column
	private java.lang.String mname;//电影名字
	 @Column
	private java.lang.String mtime;//电影时长
	 @Column
	private java.lang.String seatnum;//总座位号
	 @Column
	private java.sql.Timestamp starttime;//开始时间
	 @Column
	private java.sql.Timestamp endtime;//结束时间
	 @Column
	private java.lang.Integer cz;//操作	
	public Play() {
		super();
	}
	
	public Play(String id, String rname, String mname, String mtime, String seatnum, Timestamp starttime,
			Timestamp endtime, Integer cz) {
		super();
		this.id = id;
		this.rname = rname;
		this.mname = mname;
		this.mtime = mtime;
		this.seatnum = seatnum;
		this.starttime = starttime;
		this.endtime = endtime;
		this.cz = cz;
	}

	public java.lang.String getId(){
		return this.id;
	}
	public void setId(java.lang.String id){
		this.id	= id;
	}
	public java.lang.String getRname(){
		return this.rname;
	}
	public void setRname(java.lang.String rname){
		this.rname	= rname;
	}
	public java.lang.String getMname(){
		return this.mname;
	}
	public void setMname(java.lang.String mname){
		this.mname	= mname;
	}
	public java.lang.String getMtime(){
		return this.mtime;
	}
	public void setMtime(java.lang.String mtime){
		this.mtime	= mtime;
	}
	public java.lang.String getSeatnum(){
		return this.seatnum;
	}
	public void setSeatnum(java.lang.String seatnum){
		this.seatnum	= seatnum;
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
	public java.lang.Integer getCz(){
		return this.cz;
	}
	public void setCz(java.lang.Integer cz){
		this.cz	= cz;
	}
}