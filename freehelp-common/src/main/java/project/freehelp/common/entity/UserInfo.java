package project.freehelp.common.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import project.master.fw.sh.common.AbstractEntity;

/***
 * *
 * 
 * Copyright 2016 CHEUK.BIN.LI Individual All
 * 
 * ALL RIGHT RESERVED
 * 
 * CREATE ON 2016年5月4日
 * 
 * EMAIL:20796698@QQ.COM
 * 
 * 
 * @author CHEUK.BIN.LI
 * 
 * @see 管家/业主表
 *
 */
@Entity(name = "USER_INFO")
public class UserInfo extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;// master用户表ID，非生成
	private String openid;// 微信关注id
	private String reallyName;
	private String idCard;// 身份证
	private String idCardImage;// 身份证图片(正面、反面、人头+正面);
	private String authority;// 权限
	private int status;

	public String getId() {
		return id;
	}

	public UserInfo setId(String id) {
		this.id = id;
		return this;
	}

	public String getOpenid() {
		return openid;
	}

	public UserInfo setOpenid(String openid) {
		this.openid = openid;
		return this;
	}

	public String getReallyName() {
		return reallyName;
	}

	public UserInfo setReallyName(String reallyName) {
		this.reallyName = reallyName;
		return this;
	}

	public String getIdCard() {
		return idCard;
	}

	public UserInfo setIdCard(String idCard) {
		this.idCard = idCard;
		return this;
	}

	public String getIdCardImage() {
		return idCardImage;
	}

	public UserInfo setIdCardImage(String idCardImage) {
		this.idCardImage = idCardImage;
		return this;
	}

	public String getAuthority() {
		return authority;
	}

	public UserInfo setAuthority(String authority) {
		this.authority = authority;
		return this;
	}

	public int getStatus() {
		return status;
	}

	public UserInfo setStatus(int status) {
		this.status = status;
		return this;
	}

	public UserInfo(String id) {
		super();
		this.id = id;
	}

	public UserInfo() {
		super();
	}
}
