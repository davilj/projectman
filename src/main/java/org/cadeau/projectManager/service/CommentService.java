package org.cadeau.projectManager.service;

import org.cadeau.projectManager.domain.Comment;
import org.springframework.transaction.annotation.Transactional;

public interface CommentService {

  
  @Transactional
  void persist(Comment entity);

  @Transactional
  void remove(Comment entity);

  Comment find(Long id);

}
