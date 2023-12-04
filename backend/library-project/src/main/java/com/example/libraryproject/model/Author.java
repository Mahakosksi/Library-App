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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



@Entity
@Table(name="author")
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer idauthor;
	
	String name;
	
	
	
	@ManyToMany
    @JoinTable( name = "regularbookauthor",
                joinColumns = @JoinColumn( name = "idauthor" ),
                inverseJoinColumns = @JoinColumn( name = "idpub" ) )
    
    private List<RegularBook> regularBooks=  new ArrayList<>();

	
	@ManyToMany
    @JoinTable( name = "scientificreportauthor",
                joinColumns = @JoinColumn( name = "idauthor" ),
                inverseJoinColumns = @JoinColumn( name = "idpub" ) )
    
    private List<ScientificReport> scientificsReports=  new ArrayList<>();
	
	
	@OneToMany( targetEntity=Thesis.class, mappedBy="author" )
    private List<Thesis> thesis = new ArrayList<>();


	public Integer getIdauthor() {
		return idauthor;
	}



	public List<ScientificReport> getScientificsReports() {
		return scientificsReports;
	}



	public void setScientificsReports(List<ScientificReport> scientificsReports) {
		this.scientificsReports = scientificsReports;
	}



	public void setIdauthor(Integer idauthor) {
		this.idauthor = idauthor;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public List<RegularBook> getRegularBooks() {
		return regularBooks;
	}



	public void setRegularBooks(List<RegularBook> regularBooks) {
		this.regularBooks = regularBooks;
	}

	
	
	

}


