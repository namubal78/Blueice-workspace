package com.blueice.member.model.vo;

public class BillGraph {
	
	// 필드부
	private int monthDate;
	private int sumBill;
	
	// 생성자부
	public BillGraph() { }

	public BillGraph(int monthDate, int sumBill) {
		super();
		this.monthDate = monthDate;
		this.sumBill = sumBill;
	}

	public int getMonthDate() {
		return monthDate;
	}

	public void setMonthDate(int monthDate) {
		this.monthDate = monthDate;
	}

	public int getSumBill() {
		return sumBill;
	}

	public void setSumBill(int sumBill) {
		this.sumBill = sumBill;
	}

	
	@Override
	public String toString() {
		return "BillGraph [monthDate=" + monthDate + ", sumBill=" + sumBill + "]";
	}
}
