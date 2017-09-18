package com.moxl.movie.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import java.io.Serializable;

/**
 * room
 * @author otom3
 * @date 2017-06-05
 */
 @Table("room")
public class Room  {
	/**
	 * room
	 */
	 @Name
	private java.lang.String name;//放映厅编号
	 @Column
	private java.lang.String seatnum;//总座位号
	 @Column
	private java.lang.Integer cz;//操作
	public java.lang.String getName(){
		return this.name;
	}
	public void setName(java.lang.String name){
		this.name	= name;
	}
	public java.lang.String getSeatnum(){
		return this.seatnum;
	}
	public void setSeatnum(java.lang.String seatnum){
		this.seatnum	= seatnum;
	}
	public java.lang.Integer getCz(){
		return this.cz;
	}
	public void setCz(java.lang.Integer cz){
		this.cz	= cz;
	}
}