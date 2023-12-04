
package com.example.libraryproject.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idcategory;
	
	String categoryname;

	@ManyToMany
    @JoinTable( name = "bookcategory",
                joinColumns = @JoinColumn( name = "idcategory" ),
                inverseJoinColumns = @JoinColumn( name = "idpub" ) )
	private Set<RegularBook> regularBooks;

	public Integer getIdcategory() {
		return idcategory;
	}

	public void setIdcategory(Integer idcategory) {
		this.idcategory = idcategory;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public Set<RegularBook> getRegularBooks() {
		return regularBooks;
	}

	public void setRegularBooks(Set<RegularBook> regularBooks) {
		this.regularBooks = regularBooks;
	}
	

	

	
	
}