package com.moxl.movie.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import java.io.Serializable;

/**
 * user
 * @author otom3
 * @date 2017-06-05
 */
 @Table("user")
public class User  {
	/**
	 * user
	 */
	 @Column
	private java.lang.Integer uid;//uid
	 @Name
	private java.lang.String email;//邮箱
	 @Column
	private java.lang.String xm;//昵称
	 @Column
	private java.lang.String pwd;//密码
	 @Column
	private java.lang.String sex;//性别
	 @Column
	private java.lang.String role;//角色(1/管理员。5/普通用户)
	
	public User() {
		super();
	}
	
	public User(Integer uid, String email, String xm, String pwd, String sex, String role) {
		super();
		this.uid = uid;
		this.email = email;
		this.xm = xm;
		this.pwd = pwd;
		this.sex = sex;
		this.role = role;
	}

	public java.lang.Integer getUid(){
		return this.uid;
	}
	public void setUid(java.lang.Integer uid){
		this.uid	= uid;
	}
	public java.lang.String getEmail(){
		return this.email;
	}
	public void setEmail(java.lang.String email){
		this.email	= email;
	}
	public java.lang.String getXm(){
		return this.xm;
	}
	public void setXm(java.lang.String xm){
		this.xm	= xm;
	}
	public java.lang.String getPwd(){
		return this.pwd;
	}
	public void setPwd(java.lang.String pwd){
		this.pwd	= pwd;
	}
	public java.lang.String getSex(){
		return this.sex;
	}
	public void setSex(java.lang.String sex){
		this.sex	= sex;
	}
	public java.lang.String getRole(){
		return this.role;
	}
	public void setRole(java.lang.String role){
		this.role	= role;
	}
}