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
@Table(name="dbuser")
public class DbUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	String email;
	
	String name;
	
	String role;
	
	

	@ManyToMany
    @JoinTable( name = "userlab",
                joinColumns = @JoinColumn( name = "email" ),
                inverseJoinColumns = @JoinColumn( name = "idlab" ) )
    
    private List<Lab> labs=  new ArrayList<>();;

	public List<Lab> getLabs() {
		return labs;
	}

	public void setLabs(List<Lab> labs) {
		this.labs = labs;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	
	

}
