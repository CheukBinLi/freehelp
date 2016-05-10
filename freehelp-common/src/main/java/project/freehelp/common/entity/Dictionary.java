package project.freehelp.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @see 数据字典
 *
 */
@Entity(name = "DICT")
public class Dictionary extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;// ID
	private String parent;// 父ID(0：主节点)
	@Column(name = "dect_key")
	private String key;// 键
	@Column(name = "dect_value")
	private String value;// 值
	private String attachment;// 附加值(例：价钱)
	private String attachment2;// 附加值2(例：选中)
	private int status;// 停用，正常(预留)
	private String remark;// 备注

	public int getId() {
		return id;
	}

	public Dictionary setId(int id) {
		this.id = id;
		return this;
	}

	public String getParent() {
		return parent;
	}

	public Dictionary setParent(String parent) {
		this.parent = parent;
		return this;
	}

	public String getKey() {
		return key;
	}

	public Dictionary setKey(String key) {
		this.key = key;
		return this;
	}

	public String getValue() {
		return value;
	}

	public Dictionary setValue(String value) {
		this.value = value;
		return this;
	}

	public String getAttachment() {
		return attachment;
	}

	public Dictionary setAttachment(String attachment) {
		this.attachment = attachment;
		return this;
	}

	public String getAttachment2() {
		return attachment2;
	}

	public Dictionary setAttachment2(String attachment2) {
		this.attachment2 = attachment2;
		return this;
	}

	public int getStatus() {
		return status;
	}

	public Dictionary setStatus(int status) {
		this.status = status;
		return this;
	}

	public String getRemark() {
		return remark;
	}

	public Dictionary setRemark(String remark) {
		this.remark = remark;
		return this;
	}

	public Dictionary(int id) {
		super();
		this.id = id;
	}

	public Dictionary() {
		super();
	}

}
