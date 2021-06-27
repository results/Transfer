package com.perscholas.spring.entity;

import java.time.LocalDate;

import com.perscholas.spring.Utilities;

public class TransactionWebObject {
	
	private int id;
	private String toPhone;
	private String fromPhone;
	private String toPhoneFMT;
	private String fromPhoneFMT;
	private String amount;
	private String description;
	private LocalDate date;
	
	public TransactionWebObject(int id, String toPhone, String fromPhone, String amount, String description,
			LocalDate date) {
		this.id = id;
		this.toPhone = toPhone;
		this.fromPhone = fromPhone;
		this.amount = amount;
		this.description = description;
		this.date = date;
		if(fromPhone.equals("9999999999")) {
			this.fromPhoneFMT = "Deleted Account";
		} else {
			this.fromPhoneFMT = Utilities.formatPhoneNumber(fromPhone);	
		}
		if(toPhone.equals("9999999999")) {
			this.toPhoneFMT = "Deleted Account";
		} else {
			this.toPhoneFMT = Utilities.formatPhoneNumber(toPhone);
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getToPhone() {
		return toPhone;
	}
	public void setToPhone(String toPhone) {
		this.toPhone = toPhone;
	}
	public String getFromPhone() {
		return fromPhone;
	}
	public void setFromPhone(String fromPhone) {
		this.fromPhone = fromPhone;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "TransactionWebObject [id=" + id + ", toPhone=" + toPhone + ", fromPhone=" + fromPhone + ", amount="
				+ amount + ", description=" + description + ", date=" + date + "]";
	}
	public String getFromPhoneFMT() {
		return fromPhoneFMT;
	}
	public void setFromPhoneFMT(String fromPhoneFMT) {
		this.fromPhoneFMT = fromPhoneFMT;
	}
	public String getToPhoneFMT() {
		return toPhoneFMT;
	}
	public void setToPhoneFMT(String toPhoneFMT) {
		this.toPhoneFMT = toPhoneFMT;
	}
	
	
	
	

}
