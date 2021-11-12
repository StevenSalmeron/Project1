package com.revature.model;

public class Request {

	private int rid;
	private int eid;
	private double amount;
	private String reason;
	private String status;

	public Request() {
		super();
	}

	public Request(int rid, int eid, double amount, String reason, String status) {
		super();
		this.rid = rid;
		this.eid = eid;
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

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
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
