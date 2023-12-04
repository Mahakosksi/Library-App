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
@Table(name="lab")
public class Lab {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idlab;
	
	public Integer getIdlab() {
		return idlab;
	}

	public void setIdlab(Integer idlab) {
		this.idlab = idlab;
	}

	@ManyToMany
    @JoinTable( name = "userlab",
                joinColumns = @JoinColumn( name = "idlab" ),
                inverseJoinColumns = @JoinColumn( name = "email" ) )
	private Set<DbUser> users;
	
	public Set<DbUser> getUsers() {
		return users;
	}

	public void setUsers(Set<DbUser> users) {
		this.users = users;
	}

	

	
	
}