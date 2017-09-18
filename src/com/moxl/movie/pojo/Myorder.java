package com.moxl.movie.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * myorder
 * @author otom3
 * @date 2017-06-15
 */
 @Table("myorder")
public class Myorder  {
	/**
	 * myorder
	 */
	 @Name
	private java.lang.String id;//id
	 @Column
	private java.lang.String uemail;//uemail
	 @Column
	private java.lang.String rname;//rname
	 @Column
	private java.lang.String seat;//seat
	 @Column
	private java.lang.String mname;//mname
	 @Column
	private java.lang.String mtime;//mtime
	 @Column
	private java.lang.String allprice;//allprice
	 @Column
	private java.sql.Timestamp starttime;//starttime
	 @Column
	private java.sql.Timestamp endtime;//endtime
	 @Column
	private java.sql.Timestamp otime;//otime
	
	public Myorder() {
		super();
	}
	
	public Myorder(String id, String uemail, String rname, String seat, String mname, String mtime, String allprice,
			Timestamp starttime, Timestamp endtime, Timestamp otime) {
		super();
		this.id = id;
		this.uemail = uemail;
		this.rname = rname;
		this.seat = seat;
		this.mname = mname;
		this.mtime = mtime;
		this.allprice = allprice;
		this.starttime = starttime;
		this.endtime = endtime;
		this.otime = otime;
	}

	public java.lang.String getId(){
		return this.id;
	}
	public void setId(java.lang.String id){
		this.id	= id;
	}
	public java.lang.String getUemail(){
		return this.uemail;
	}
	public void setUemail(java.lang.String uemail){
		this.uemail	= uemail;
	}
	public java.lang.String getRname(){
		return this.rname;
	}
	public void setRname(java.lang.String rname){
		this.rname	= rname;
	}
	public java.lang.String getSeat(){
		return this.seat;
	}
	public void setSeat(java.lang.String seat){
		this.seat	= seat;
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
	public java.lang.String getAllprice(){
		return this.allprice;
	}
	public void setAllprice(java.lang.String allprice){
		this.allprice	= allprice;
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
	public java.sql.Timestamp getOtime(){
		return this.otime;
	}
	public void setOtime(java.sql.Timestamp otime){
		this.otime	= otime;
	}
}