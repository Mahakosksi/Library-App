package com.example.libraryproject.DTO;

import java.util.Date;

public class PublicationDTO {
	
	private String idPub;
    private Integer yearPub;
    private Integer idStatus;
    
    private String status;
    private String issuedEmail;
    private Date dateProposition; 
	
	public Date getDateProposition() {
		return dateProposition;
	}
	public void setDateProposition(Date dateProposition) {
		this.dateProposition = dateProposition;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIdPub() {
		return idPub;
	}
	public void setIdPub(String idPub) {
		this.idPub = idPub;
	}
	public Integer getYearPub() {
		return yearPub;
	}
	public void setYearPub(Integer yearPub) {
		this.yearPub = yearPub;
	}
	public Integer getIdStatus() {
		return idStatus;
	}
	public void setIdStatus(Integer idStatus) {
		this.idStatus = idStatus;
	}
	public String getIssuedEmail() {
		return issuedEmail;
	}
	public void setIssuedEmail(String issuedEmail) {
		this.issuedEmail = issuedEmail;
	}
    
    
    
    

}


