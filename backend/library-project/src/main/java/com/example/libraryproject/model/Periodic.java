

package com.example.libraryproject.model;

import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;



@Entity
@Table(name="periodic")
public class Periodic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	String idpub;
	
	String publisher;

	
	Integer idbookshop;
	
	Integer edition;
	
	Integer price;
	
	Float currentexchange;

	public String getIdpub() {
		return idpub;
	}

	public void setIdpub(String idpub) {
		this.idpub = idpub;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getIdbookshop() {
		return idbookshop;
	}

	public void setIdbookshop(Integer idbookshop) {
		this.idbookshop = idbookshop;
	}

	public Integer getEdition() {
		return edition;
	}

	public void setEdition(Integer edition) {
		this.edition = edition;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Float getCurrentexchange() {
		return currentexchange;
	}

	public void setCurrentexchange(Float currentexchange) {
		this.currentexchange = currentexchange;
	}
	
	
	
   
	
	
	

}
