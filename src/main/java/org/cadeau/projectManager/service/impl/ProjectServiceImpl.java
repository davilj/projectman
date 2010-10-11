package org.cadeau.projectManager.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.cadeau.projectManager.dao.GenericDAOWithJPA;
import org.cadeau.projectManager.domain.Comment;
import org.cadeau.projectManager.domain.Person;
import org.cadeau.projectManager.domain.Project;

import org.cadeau.projectManager.service.CommentService;
import org.cadeau.projectManager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service("projectService")
@Repository
public class ProjectServiceImpl extends GenericDAOWithJPA<Project, Long> implements ProjectService {
  
  @Autowired
  private CommentService commentService;

  @Override
  public void remove(Project entity) {
    entity.setActive(false);
    entity.setDeactivationDate(new Date());
  }

  public List<Project> findMyProjects(Person person) {
    
      return entityManager.createQuery("Select pr from " + Project.class.getSimpleName() + " pr join pr.owner pe where pe.id=" + person.getId()).getResultList();
    
  }

  public void addComment(Project project,Comment comment) {
    System.err.println("Before");
    System.err.println("Project: " + project);
    System.err.println("Comment: " + comment);
    
    //load project
   //Project tmp = this.find(project.getId());
    System.err.println("Loaded");
    //System.err.println("Project: " + tmp);
    
    commentService.persist(comment);
    Set<Comment> newComments = project.getComments();
    newComments.add(comment);
    project.setComments(newComments);
    entityManager.persist(project);
    System.err.println("completed");
    System.err.println("Project: " + project);
    System.err.println("Comment: " + comment);
    
    //test
    Project tmp2 = this.find(project.getId());
    System.err.println("Tested");
    System.err.println("Project: " + tmp2);

  } 
}
