package com.prova.blog.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;


@Entity(name = "users")
public class User implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
     
    @Column(nullable = false, length = 50)
    @NotBlank(message = "Nome é uma informação obrigatória.")
    private String firstName;
     
    @Column(nullable = false, length = 150)
    @NotBlank(message = "Sobre nome é uma informação obrigatória.")
    private String lastName;
     
    @Column(nullable = false, length = 2000)
    @NotBlank(message = "Login é uma informação obrigatória.")
    private String login;
    
    @Column(nullable = false, length = 2000)
    @NotBlank(message = "Senha é uma informação obrigatória.")
    private String password;
     
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Data é uma informação obrigatória.")
    private Date birthDate;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

}
