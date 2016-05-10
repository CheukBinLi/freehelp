package project.freehelp.common.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import project.master.fw.sh.common.AbstractEntity;

/**
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
 * @see 管家_房子中间表
 *
 */
@Entity(name = "HOUSE_STEWARD")
public class HouseSteward extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String house;// 房子ID
	private String steward;// 管家ID

	public String getId() {
		return id;
	}

	public HouseSteward setId(String id) {
		this.id = id;
		return this;
	}

	public String getHouse() {
		return house;
	}

	public HouseSteward setHouse(String house) {
		this.house = house;
		return this;
	}

	public String getSteward() {
		return steward;
	}

	public HouseSteward setSteward(String steward) {
		this.steward = steward;
		return this;
	}

	public HouseSteward(String id) {
		super();
		this.id = id;
	}

	public HouseSteward() {
		super();
	}

}
