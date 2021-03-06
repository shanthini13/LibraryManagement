package com.equifax.library.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.equifax.library.model.Book;


public interface BookRepo extends CrudRepository<Book, Integer> {


	@Transactional
	@Modifying
	@Query("update Book b set b.userId = null where b.userId = :userId")
	int updateUserStatusForBooks(@Param("userId") Integer userId);
  
	Object findByBookName(String bookName);

	Book findByBookId(Integer bookId);

}
