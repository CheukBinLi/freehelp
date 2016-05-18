package project.freehelp.common.entity;

import java.util.Date;

import javax.persistence.Column;
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
 * @see 房源信息
 *
 */
@Entity(name = "HOUSE_INFO")
public class HouseInfo extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String master;// 业主ID
	private String title;// 标题
	@Column(columnDefinition = "text")
	private String image;// 照片(json image:[{image:1},{iamge:2}])
	private String mainImage;// 标题显示图片
	private String address;// 详细地址(可API选择)
	// private String coordinates;// 坐标
	private double longitude;// 经度
	private double latitude;// 纬度
	@Column(columnDefinition = "text")
	private String traffic;// 交通描述
	private int area;// 面积
	private int count;// 入住人数
	private String design;// 户型
	@Column(columnDefinition = "text")
	private String facility;// 配套设施(数据字典)
	@Column(columnDefinition = "text")
	private String bed;// 床型(数据字典)
	private int price;// 日价
	private int pledge;// 押金
	private int foregn;// 是否接纳外国人
	@Column(name = "SERVICE_CHARGE")
	private int serviceCharge;// 服务费
	@Column(name = "CHECK_IN")
	private int checkIn;// 入住数
	private int score;// 总分数
	private int status;// 状态
	@Column(updatable = false, name = "CREATE_DATE")
	private Date createDate;
	private String remark;// 备注

	public String getId() {
		return id;
	}

	public HouseInfo setId(String id) {
		this.id = id;
		return this;
	}

	public String getMaster() {
		return master;
	}

	public HouseInfo setMaster(String master) {
		this.master = master;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public HouseInfo setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getImage() {
		return image;
	}

	public HouseInfo setImage(String image) {
		this.image = image;
		return this;
	}

	public String getMainImage() {
		return mainImage;
	}

	public HouseInfo setMainImage(String mainImage) {
		this.mainImage = mainImage;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public HouseInfo setAddress(String address) {
		this.address = address;
		return this;
	}

	public double getLongitude() {
		return longitude;
	}

	public HouseInfo setLongitude(double longitude) {
		this.longitude = longitude;
		return this;
	}

	public double getLatitude() {
		return latitude;
	}

	public HouseInfo setLatitude(double latitude) {
		this.latitude = latitude;
		return this;
	}

	public String getTraffic() {
		return traffic;
	}

	public HouseInfo setTraffic(String traffic) {
		this.traffic = traffic;
		return this;
	}

	public int getArea() {
		return area;
	}

	public HouseInfo setArea(int area) {
		this.area = area;
		return this;
	}

	public int getCount() {
		return count;
	}

	public HouseInfo setCount(int count) {
		this.count = count;
		return this;
	}

	public String getDesign() {
		return design;
	}

	public HouseInfo setDesign(String design) {
		this.design = design;
		return this;
	}

	public String getFacility() {
		return facility;
	}

	public HouseInfo setFacility(String facility) {
		this.facility = facility;
		return this;
	}

	public String getBed() {
		return bed;
	}

	public HouseInfo setBed(String bed) {
		this.bed = bed;
		return this;
	}

	public int getPrice() {
		return price;
	}

	public HouseInfo setPrice(int price) {
		this.price = price;
		return this;
	}

	public int getPledge() {
		return pledge;
	}

	public HouseInfo setPledge(int pledge) {
		this.pledge = pledge;
		return this;
	}

	public int getForegn() {
		return foregn;
	}

	public HouseInfo setForegn(int foregn) {
		this.foregn = foregn;
		return this;
	}

	public int getCheckIn() {
		return checkIn;
	}

	public HouseInfo setCheckIn(int checkIn) {
		this.checkIn = checkIn;
		return this;
	}

	public int getScore() {
		return score;
	}

	public HouseInfo setScore(int score) {
		this.score = score;
		return this;
	}

	public int getStatus() {
		return status;
	}

	public HouseInfo setStatus(int status) {
		this.status = status;
		return this;
	}

	public String getRemark() {
		return remark;
	}

	public HouseInfo setRemark(String remark) {
		this.remark = remark;
		return this;
	}

	public HouseInfo(String id) {
		super();
		this.id = id;
	}

	public int getServiceCharge() {
		return serviceCharge;
	}

	public HouseInfo setServiceCharge(int serviceCharge) {
		this.serviceCharge = serviceCharge;
		return this;
	}

	public HouseInfo(boolean createId) {
		this.id = generatedValue();
	}

	public HouseInfo() {
		super();
	}

}