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
	private String info;// 所有信息
	private String extend;// 扩展字段
	private String weixin;// 微信信息
	// private String reallyName;
	// private String idCard;// 身份证
	private String idCardImage;// 身份证图片(正面、反面、人头+正面);
	private String authority;// 权限
	private int master;// 业主 状态 -1,0,1,2(初始化，申请，修改再申请，完成通过)
	private int steward;// 管家 状态 -1,0,1,2(初始化，申请，修改再申请，完成通过)
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

	public String getInfo() {
		return info;
	}

	public UserInfo setInfo(String info) {
		this.info = info;
		return this;
	}

	public String getExtend() {
		return extend;
	}

	public UserInfo setExtend(String extend) {
		this.extend = extend;
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

	public int getMaster() {
		return master;
	}

	public UserInfo setMaster(int master) {
		this.master = master;
		return this;
	}

	public int getSteward() {
		return steward;
	}

	public UserInfo setSteward(int steward) {
		this.steward = steward;
		return this;
	}

	public int getStatus() {
		return status;
	}

	public UserInfo setStatus(int status) {
		this.status = status;
		return this;
	}

	public String getWeixin() {
		return weixin;
	}

	public UserInfo setWeixin(String weixin) {
		this.weixin = weixin;
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
