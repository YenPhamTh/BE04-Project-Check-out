package project.model;

import java.util.Date;

public class POrder {
	private int id;
	private int userId;
	private Date submitDate;
	private boolean approve;
	public POrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public POrder(int id, int userId, Date submitDate, boolean approve) {
		super();
		this.id = id;
		this.userId = userId;
		this.submitDate = submitDate;
		this.approve = approve;
	}
	public POrder(int userId, boolean approve) {
		super();
		this.userId = userId;
		this.approve = approve;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	public boolean isApprove() {
		return approve;
	}
	public void setApprove(boolean approve) {
		this.approve = approve;
	}
	
}
