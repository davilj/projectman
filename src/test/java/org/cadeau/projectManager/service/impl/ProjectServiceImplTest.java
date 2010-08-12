package org.cadeau.projectManager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.cadeau.projectManager.domain.Comment;
import org.cadeau.projectManager.domain.Project;
import org.cadeau.projectManager.service.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager")
@ContextConfiguration(locations = { "classpath:ProjectManager-test.xml" })
public class ProjectServiceImplTest {

  @Autowired
  ProjectService projectService;
  
  @Test
  @Rollback
  public void testAddComment() {
    Project project = new Project();
    project.setName("TestProject");
    Assert.assertEquals(0, project.getComments().size());
    Comment comment = new Comment();
    comment.setText("Text1");
    projectService.addComment(project, comment);
    Comment comment2 = new Comment();
    comment2.setText("Text2");
    projectService.addComment(project, comment2);
    Assert.assertEquals(2, project.getComments().size());
    
    Project projectOne = projectService.find(1L);
    Assert.assertEquals("TestProject", projectOne.getName());
    Assert.assertEquals(2, projectOne.getComments().size());
    
  }

}
