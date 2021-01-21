package com.example.dailycc;

public class Details {
	private long id;
	private int type;
	private double count;
	private String date;
	private String note;

	public Details() {
	}

	public Details(int type, double count, String date, String note) {
		this.type = type;
		this.count = count;
		this.date = date;
		this.note = note;
	}

	public Details(long id, int type, double count, String date, String note) {
		this.id = id;
		this.type = type;
		this.count = count;
		this.date = date;
		this.note = note;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int isType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Details{" +
				"id=" + id +
				", type=" + type +
				", count=" + count +
				", date='" + date + '\'' +
				", note='" + note + '\'' +
				'}';
	}
}

