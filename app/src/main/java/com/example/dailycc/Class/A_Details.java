package com.example.dailycc.Class;

public class A_Details {
	private long id;
	private int type;
	private String note;
	private double value;


	public A_Details() {
	}

	public A_Details(long id, int type, String note, double value) {
		this.id = id;
		this.type = type;
		this.note = note;
		this.value = value;
	}

	public A_Details(int type, String note, double value) {
		this.type = type;
		this.note = note;
		this.value = value;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}


	@Override
	public String toString() {
		return "Details{" +
				"id=" + id +
				", type=" + type +
				", note='" + note + '\'' +
				", value=" + value +
				'}';
	}
}

