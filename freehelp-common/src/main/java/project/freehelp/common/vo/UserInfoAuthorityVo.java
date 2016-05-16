package project.freehelp.common.vo;

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
 * @see 房东、管家
 *
 */
public class UserInfoAuthorityVo extends AbstractAuthorityVo {

	private transient static final long serialVersionUID = 1L;
	/***
	 * 状态 -1,0,1,2(初始化，申请，修改再申请，完成通过)
	 */
	private int master;// 房东
	private int steward;// 管家

	public int getMaster() {
		return master;
	}

	public UserInfoAuthorityVo setMaster(int master) {
		this.master = master;
		return this;
	}

	public int getSteward() {
		return steward;
	}

	public UserInfoAuthorityVo setSteward(int steward) {
		this.steward = steward;
		return this;
	}

	public UserInfoAuthorityVo(int master, int steward) {
		super();
		this.master = master;
		this.steward = steward;
	}

	public UserInfoAuthorityVo() {
		super();
	}

}
