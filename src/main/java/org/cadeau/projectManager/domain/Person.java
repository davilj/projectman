package org.cadeau.projectManager.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * A JPA entity for the person domain object.
 * 
 * @author Stefan Schmidt
 * @since 0.1
 * 
 */
@XStreamAlias("person")
@Entity
public class Person implements Serializable {
	
	private static final int MAX_DISPLAY_LEN = 20;

  public static Person emptyPerson;
	static {
	  Person person = new Person();
	  person.setFirstName("Empty");
	  person.setLastName("Person");
	  person.setId(-1L);
	}

  @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Version
	@Column(name = "version")
	private Integer version;

	@NotNull
	@Pattern(regexp = ".+", message = "Last name must not be empty!")
	private String lastName;

	@NotNull
	@Pattern(regexp = ".+", message = "First name must not be empty!")
	private String firstName;

	@NotNull
	@Pattern(message = "Email invalid", regexp = "[a-z0-9!#$%&amp;'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&amp;'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
	private String email;
	
	@NotNull
	@Pattern(regexp = ".+", message = "Password must not be empty!")
	private String password;	

	@Valid
	@NotNull
	@Embedded
	private Address address;
	
	private String displayName;

	public Person() {
		address = new Address();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getDisplayName() {
	    if (displayName==null) {
	      this.displayName=createDisplayName(this.firstName, this.lastName);
	    }
	    return this.displayName;
	}

	protected String createDisplayName(String firstName, String lastName) {
    if (this.firstName.length() + this.lastName.length()+2>MAX_DISPLAY_LEN) {
      String tmp = this.firstName.substring(0, 1)+ ". " + this.lastName;
      if (tmp.length()>MAX_DISPLAY_LEN) {
        tmp = this.firstName.substring(0, 1)+ ". " + this.lastName.substring(0,18);
      }
      return tmp;
    }
    return this.firstName + ". " + this.lastName;
  }
	
	 

  public String toString() {
		StringBuilder string = new StringBuilder();
		string.append(getFirstName()).append(" ");
		string.append(getLastName()).append(", ");
		string.append(getAddress().getCity()).append(", ");
		string.append(getEmail());
		return string.toString();
	}
}
