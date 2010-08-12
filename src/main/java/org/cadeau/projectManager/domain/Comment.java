package org.cadeau.projectManager.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.cadeau.projectManager.utils.Util;
@Entity
public class Comment implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;
  
  @Version
  @Column(name = "version")
  private java.lang.Integer version;
  
  @NotNull
  private String text;
  
  @ManyToOne
  private Person commenter;
  
  @NotNull
  private Date commentDate;

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

  

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Person getCommenter() {
    return commenter;
  }

  public void setCommenter(Person commenter) {
    this.commenter = commenter;
  }

  public Date getCommentDate() {
    return commentDate;
  }

  public void setCommentDate(Date commentDate) {
    this.commentDate = commentDate;
  }

  @Override
  public String toString() {
    Map<String, Object> info = new HashMap<String, Object>();
    info.put("id", this.id);
    info.put("text",this.text);
   return Util.makeString(info, ", ");
  }
  
  

}
