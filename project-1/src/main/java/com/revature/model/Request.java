package com.revature.model;

public class Request {

	private int rid;
	private int aid;
	private double amount;
	private String reason;
	private String status;

	public Request() {
		super();
	}

	public Request(int rid, int aid, double amount, String reason, String status) {
		super();
		this.rid = rid;
		this.aid = aid;
		this.amount = amount;
		this.reason = reason;
		this.status = status;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
