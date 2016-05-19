package project.freehelp.common.vo;

import project.master.fw.sh.common.JsonObjectFactory;

public class UserInfoVo extends AbstractAuthorityVo {

	private transient static final long serialVersionUID = 1L;

	private String name; // 姓名
	private String address;// 地址
	private String sex;// 性别
	private String idCard;// 身份证
	private String phone;// 手机
	private String bankAccount;// 银行卡号
	private String bank;// 开户银行
	private String bankAddress;// 开户行地址
	private String payAccount;// 支付帐号
	private String job;// 职业
	private String school;// 学校
	private String city;// 城市
	private String brithday;// 生日
	private String introduce;// 简介
	private String tag;// 个人标签

	public String getName() {
		return name;
	}

	public UserInfoVo setName(String name) {
		this.name = name;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public UserInfoVo setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getSex() {
		return sex;
	}

	public UserInfoVo setSex(String sex) {
		this.sex = sex;
		return this;
	}

	public String getIdCard() {
		return idCard;
	}

	public UserInfoVo setIdCard(String idCard) {
		this.idCard = idCard;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public UserInfoVo setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public UserInfoVo setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
		return this;
	}

	public String getBank() {
		return bank;
	}

	public UserInfoVo setBank(String bank) {
		this.bank = bank;
		return this;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public UserInfoVo setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
		return this;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public UserInfoVo setPayAccount(String payAccount) {
		this.payAccount = payAccount;
		return this;
	}

	public String getJob() {
		return job;
	}

	public UserInfoVo setJob(String job) {
		this.job = job;
		return this;
	}

	public String getSchool() {
		return school;
	}

	public UserInfoVo setSchool(String school) {
		this.school = school;
		return this;
	}

	public String getCity() {
		return city;
	}

	public UserInfoVo setCity(String city) {
		this.city = city;
		return this;
	}

	public String getBrithday() {
		return brithday;
	}

	public UserInfoVo setBrithday(String brithday) {
		this.brithday = brithday;
		return this;
	}

	public String getIntroduce() {
		return introduce;
	}

	public UserInfoVo setIntroduce(String introduce) {
		this.introduce = introduce;
		return this;
	}

	public String getTag() {
		return tag;
	}

	public UserInfoVo setTag(String tag) {
		this.tag = tag;
		return this;
	}

	public static void main(String[] args) {
		System.out.println(JsonObjectFactory.newInstance().toJson(new UserInfoVo(), false));
	}

}
