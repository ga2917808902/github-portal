package com.portal.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portal.models.Book;
import com.portal.models.Comment;

public interface ICommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByBook(Book book);
}
