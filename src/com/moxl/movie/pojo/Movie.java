package com.moxl.movie.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import java.io.Serializable;

/**
 * movie
 * @author otom3
 * @date 2017-06-05
 */
 @Table("movie")
public class Movie  {
	/**
	 * movie
	 */
	 @Name
	private java.lang.String name;//电影名字
	 @Column
	private java.lang.String time;//放映时间
	 @Column
	private java.lang.String typ;//类型1
	 @Column
	private java.lang.String degr;//放映场次
	 @Column
	private java.lang.String pic;//照片
	 @Column
	private java.lang.String des;//描述
	 @Column
	private java.lang.String grad;//评分
	 @Column
	private java.lang.String pric;//价格
	
	public Movie() {
		super();
	}
	
	public Movie(String name, String time, String typ, String degr, String pic, String des, String grad, String pric) {
		super();
		this.name = name;
		this.time = time;
		this.typ = typ;
		this.degr = degr;
		this.pic = pic;
		this.des = des;
		this.grad = grad;
		this.pric = pric;
	}

	public java.lang.String getName(){
		return this.name;
	}
	public void setName(java.lang.String name){
		this.name	= name;
	}
	public java.lang.String getTime(){
		return this.time;
	}
	public void setTime(java.lang.String time){
		this.time	= time;
	}
	public java.lang.String getTyp(){
		return this.typ;
	}
	public void setTyp(java.lang.String typ){
		this.typ	= typ;
	}
	public java.lang.String getDegr(){
		return this.degr;
	}
	public void setDegr(java.lang.String degr){
		this.degr	= degr;
	}
	public java.lang.String getPic(){
		return this.pic;
	}
	public void setPic(java.lang.String pic){
		this.pic	= pic;
	}
	public java.lang.String getDes(){
		return this.des;
	}
	public void setDes(java.lang.String des){
		this.des	= des;
	}
	public java.lang.String getGrad(){
		return this.grad;
	}
	public void setGrad(java.lang.String grad){
		this.grad	= grad;
	}
	public java.lang.String getPric(){
		return this.pric;
	}
	public void setPric(java.lang.String pric){
		this.pric	= pric;
	}
}