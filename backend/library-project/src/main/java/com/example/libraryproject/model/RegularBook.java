
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
@Table(name="regularbook")
public class RegularBook {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	String idpub;
	
	String publisher;
	
	String title;
	
	Integer idbookshop;
	
	Integer edition;
	
	Integer price;
	
	Float currentexchange;
	
	
	@ManyToMany
    @JoinTable( name = "bookcategory",
                joinColumns = @JoinColumn( name = "idpub" ),
                inverseJoinColumns = @JoinColumn( name = "idcategory" ) )
    
    private List<Category> categories=  new ArrayList<>();
	
	
	@ManyToMany
    @JoinTable(
        name = "regularbookauthor",
        joinColumns = @JoinColumn(name = "idpub"),
        inverseJoinColumns = @JoinColumn(name = "idauthor")
    )
    private List<Author> authors = new ArrayList<>();

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public String getIdpub() {
		return idpub;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
