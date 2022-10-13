package com.codingdojo.troy.bookclubtm.repositories;

import com.codingdojo.troy.bookclubtm.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

}
