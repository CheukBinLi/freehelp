package project.freehelp.common.vo;

public class DesignVo extends AbstractAuthorityVo {

	private transient static final long serialVersionUID = 1L;
	private int room;// 室
	private int hall;// 厅
	private int toilet;// 卫
	private int kitchen;// 厨
	private int balcony;// 阳台

	@Override
	public String toString() {
		return String.format("%d房%d厅%d卫生间%d阳台", room, hall, toilet, kitchen, balcony);
	}

	public int getRoom() {
		return room;
	}

	public DesignVo setRoom(int room) {
		this.room = room;
		return this;
	}

	public int getHall() {
		return hall;
	}

	public DesignVo setHall(int hall) {
		this.hall = hall;
		return this;
	}

	public int getToilet() {
		return toilet;
	}

	public DesignVo setToilet(int toilet) {
		this.toilet = toilet;
		return this;
	}

	public int getKitchen() {
		return kitchen;
	}

	public DesignVo setKitchen(int kitchen) {
		this.kitchen = kitchen;
		return this;
	}

	public int getBalcony() {
		return balcony;
	}

	public DesignVo setBalcony(int balcony) {
		this.balcony = balcony;
		return this;
	}

}
