package org.cadeau.projectManager.service.impl;

import org.cadeau.projectManager.dao.GenericDAOWithJPA;
import org.cadeau.projectManager.domain.Comment;
import org.cadeau.projectManager.service.CommentService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service("commentService")
@Repository
public class CommentServiceImpl extends GenericDAOWithJPA<Comment, Long> implements CommentService {


   
}