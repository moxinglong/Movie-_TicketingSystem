package com.moxl.movie.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import java.io.Serializable;

/**
 * cmenu
 * @author otom3
 * @date 2017-06-05
 */
 @Table("cmenu")
public class Cmenu  {
	/**
	 * cmenu
	 */
	 @Name
	private java.lang.String id;//体现层级，如三级000100030010
	 @Column
	private java.lang.String name;//栏目名称
	 @Column
	private java.lang.String url;//链接
	 @Column
	private java.lang.String permission;//权限标识
	public java.lang.String getId(){
		return this.id;
	}
	public void setId(java.lang.String id){
		this.id	= id;
	}
	public java.lang.String getName(){
		return this.name;
	}
	public void setName(java.lang.String name){
		this.name	= name;
	}
	public java.lang.String getUrl(){
		return this.url;
	}
	public void setUrl(java.lang.String url){
		this.url	= url;
	}
	public java.lang.String getPermission(){
		return this.permission;
	}
	public void setPermission(java.lang.String permission){
		this.permission	= permission;
	}
}