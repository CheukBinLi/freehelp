package project.freehelp.common.vo;

import java.io.Serializable;

import project.master.fw.sh.common.JsonObjectFactory;

/***
 * *
 * 
 * Copyright 2016 CHEUK.BIN.LI Individual All
 * 
 * ALL RIGHT RESERVED
 * 
 * CREATE ON 2016年5月16日
 * 
 * EMAIL:20796698@QQ.COM
 * 
 * 
 * @author CHEUK.BIN.LI
 * 
 * @see 权限基类
 *
 */
public abstract class AbstractAuthorityVo implements Serializable {

	private transient static final long serialVersionUID = 1L;

	protected transient JsonObjectFactory jsonObjectFactory = JsonObjectFactory.newInstance();

	public String toJson() {
		return jsonObjectFactory.toJson(this, false);
	}

	public String toJsonSerializeNulls() {
		return jsonObjectFactory.toJson(this, true);
	}

}
