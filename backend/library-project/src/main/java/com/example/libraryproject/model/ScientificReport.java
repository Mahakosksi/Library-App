
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
@Table(name="scientificreport")
public class ScientificReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	String idpub;

	
	
	
	@ManyToMany
    @JoinTable( name = "scientificreportauthor",
                joinColumns = @JoinColumn( name = "idpub" ),
                inverseJoinColumns = @JoinColumn( name = "idauthor" ) )
    
    private List<Author> authors=  new ArrayList<>();




	public String getIdpub() {
		return idpub;
	}




	public void setIdpub(String idpub) {
		this.idpub = idpub;
	}




	public List<Author> getAuthors() {
		return authors;
	}




	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	
	

}


