package org.cadeau.projectManager.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.cadeau.projectManager.utils.Util;

@Entity
public class Project implements Serializable {
 
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private java.lang.Long id;
  
  @Version
  @Column(name = "version")
  private java.lang.Integer version;

  @NotNull
  @Size(min = 1, max = 30)
  @Pattern(regexp = ".+", message = "Name must not be empty!")
  private String name;

  private String description;
  
  //can not delete a project, just active or inactive - deleted is inactive
  private boolean active=false;
  //date project was deleted (made inactive)
  private Date deactivationDate;
  
  //date project was created
  private Date creationDate;
  
  @OneToMany(cascade = CascadeType.ALL)
  private Set<Comment> comments = new HashSet<Comment>();
  
  @NotNull
  @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
  @JoinColumn
  Person owner;
  
  @OneToMany(cascade = CascadeType.ALL)
  private Set<Person> resources = new HashSet<Person>();
  
  
  
  
  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Person getOwner() {
    return owner;
  }

  public void setOwner(Person owner) {
    this.owner = owner;
  }

  public Set<Person> getResources() {
    return resources;
  }

  public void setResources(Set<Person> resources) {
    this.resources = resources;
  }

  public Set<Comment> getComments() {
    return comments;
  }

  public void setComments(Set<Comment> comments) {
    this.comments = comments;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public java.lang.Integer getVersion() {
    return version;
  }

  public void setVersion(java.lang.Integer version) {
    this.version = version;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  
  

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public Date getDeactivationDate() {
    return deactivationDate;
  }

  public void setDeactivationDate(Date deactivationDate) {
    this.deactivationDate = deactivationDate;
  }

  @Override
  public String toString() {
    Map<String, Object > info = new HashMap<String, Object>();
    info.put("id", this.id);
    info.put("name", this.name);
    info.put("desc", this.description);
    info.put("commnet.size", this.comments.size());
    
    return Util.makeString(info, ", ");
  }
  
  
}
