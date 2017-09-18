package com.moxl.movie.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import java.io.Serializable;

/**
 * loginlog
 * @author otom3
 * @date 2017-06-05
 */
 @Table("loginlog")
public class Loginlog  {
	/**
	 * loginlog
	 */
	 @Column
	private java.lang.Integer id;//id
	 @Column
	private java.lang.String email;//考号
	 @Column
	private java.lang.String loginip;//登录ip
	 @Column
	private java.sql.Timestamp logintime;//登录时间
	 @Column
	private java.lang.String bver;//登录浏览器
	public java.lang.Integer getId(){
		return this.id;
	}
	public void setId(java.lang.Integer id){
		this.id	= id;
	}
	public java.lang.String getEmail(){
		return this.email;
	}
	public void setEmail(java.lang.String email){
		this.email	= email;
	}
	public java.lang.String getLoginip(){
		return this.loginip;
	}
	public void setLoginip(java.lang.String loginip){
		this.loginip	= loginip;
	}
	public java.sql.Timestamp getLogintime(){
		return this.logintime;
	}
	public void setLogintime(java.sql.Timestamp logintime){
		this.logintime	= logintime;
	}
	public java.lang.String getBver(){
		return this.bver;
	}
	public void setBver(java.lang.String bver){
		this.bver	= bver;
	}
}