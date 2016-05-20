package project.freehelp.common.entity;

import java.util.Date;

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
 * @see 订单信息
 *
 */
@Entity(name = "SERVICE_ORDER")
public class Order extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;// ID
	private String master;// 业主ID
	private String steward;// 管家ID
	private String house;// 房源ID
	private String service;// 服务内容（计划性大扫除...）
	private String total;// 总服用
	private int status;// 状态
	private String remark;// 备注
	private Date createDate;

	public String getId() {
		return id;
	}

	public Order setId(String id) {
		this.id = id;
		return this;
	}

	public String getMaster() {
		return master;
	}

	public Order setMaster(String master) {
		this.master = master;
		return this;
	}

	public String getSteward() {
		return steward;
	}

	public Order setSteward(String steward) {
		this.steward = steward;
		return this;
	}

	public String getHouse() {
		return house;
	}

	public Order setHouse(String house) {
		this.house = house;
		return this;
	}

	public String getService() {
		return service;
	}

	public Order setService(String service) {
		this.service = service;
		return this;
	}

	public String getTotal() {
		return total;
	}

	public Order setTotal(String total) {
		this.total = total;
		return this;
	}

	public int getStatus() {
		return status;
	}

	public Order setStatus(int status) {
		this.status = status;
		return this;
	}

	public String getRemark() {
		return remark;
	}

	public Order setRemark(String remark) {
		this.remark = remark;
		return this;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Order setCreateDate(Date createDate) {
		this.createDate = createDate;
		return this;
	}

	public Order(String id) {
		super();
		this.id = id;
	}

	public Order() {
		super();
	}

}
