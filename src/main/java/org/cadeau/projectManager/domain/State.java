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
import javax.persistence.OneToMany;
import javax.persistence.Version;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.cadeau.projectManager.utils.Util;

@Entity
public class State implements Serializable {
 
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private java.lang.Long id;
  
  @Version
  @Column(name = "version")
  private java.lang.Integer version;

  @NotNull
  private String name;

 
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

  
  @Override
  public String toString() {
    Map<String, Object > info = new HashMap<String, Object>();
    info.put("id", this.id);
    info.put("name", this.name);
    return Util.makeString(info, ", ");
  }
  
  
}
