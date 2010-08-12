package org.cadeau.projectManager.service;

import java.util.List;

import org.cadeau.projectManager.domain.Comment;
import org.cadeau.projectManager.domain.Person;
import org.cadeau.projectManager.domain.Project;
import org.springframework.transaction.annotation.Transactional;

public interface ProjectService {

  List<Project> findAll();

  List<Project> findMyProjects(Person person);
  
  void addComment(Project project, Comment comment) ;

  @Transactional
  void persist(Project entity);

  @Transactional
  void remove(Project entity);

  Project find(Long id);

}
