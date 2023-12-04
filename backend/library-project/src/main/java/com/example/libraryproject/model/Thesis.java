package com.example.libraryproject.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name="thesis")
public class Thesis {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	String idpub;
	
	
	@ManyToOne @JoinColumn(name="idauthor", nullable=false)
    private Author author;
	
	
	
	public String getIdpub() {
		return idpub;
	}


	public void setIdpub(String idpub) {
		this.idpub = idpub;
	}


	public Author getAuthor() {
		return author;
	}


	public void setAuthor(Author author) {
		this.author = author;
	}

	

}


