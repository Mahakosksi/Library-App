package com.example.libraryproject.model;



import java.util.Date;

import jakarta.persistence.*;



@Entity
@Table(name="publication")
public class Publication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	String idpub;
	
	Integer yearpub;
	
	Date dateproposition;
	
	Integer idlab;
	
	Integer idstatus;
	
	String email;
	
	String issuedemail;

	public String getIdpub() {
		return idpub;
	}

	public void setIdpub(String idpub) {
		this.idpub = idpub;
	}

	public Integer getYearpub() {
		return yearpub;
	}

	public void setYearpub(Integer yearpub) {
		this.yearpub = yearpub;
	}

	public Date getDateproposition() {
		return dateproposition;
	}

	public void setDateproposition(Date dateproposition) {
		this.dateproposition = dateproposition;
	}

	public Integer getIdlab() {
		return idlab;
	}

	public void setIdlab(Integer idlab) {
		this.idlab = idlab;
	}

	public Integer getIdstatus() {
		return idstatus;
	}

	public void setIdstatus(Integer idstatus) {
		this.idstatus = idstatus;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIssuedemail() {
		return issuedemail;
	}

	public void setIssuedemail(String issuedemail) {
		this.issuedemail = issuedemail;
	}

	

	
	
	
	
	
	
	
	
	
	

}
