package com.pnevsky.library_spring_boot.repositories;

import com.pnevsky.library_spring_boot.models.Book;
import com.pnevsky.library_spring_boot.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
   List<Book> findAllByOwnerIsNull();
   List<Book> findAllByOwnerIsNotNull();

   List<Book> findByOwner(Person person);
}
