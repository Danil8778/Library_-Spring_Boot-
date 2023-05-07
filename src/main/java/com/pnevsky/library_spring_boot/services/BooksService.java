package com.pnevsky.library_spring_boot.services;

import com.pnevsky.library_spring_boot.models.Book;
import com.pnevsky.library_spring_boot.models.Person;
import com.pnevsky.library_spring_boot.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
@Transactional(readOnly = true)
public class BooksService {

    BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public void returnBook(int id) {
        Book book = booksRepository.findById(id).get();
        book.setOwner(null);
    }

    public List<Book> indexFreeBooks() {
        return booksRepository.findAllByOwnerIsNull();
    }

    public List<Book> getPersonBooks(Person person) {
        return booksRepository.findByOwner(person);
    }

    public List<Book> indexBusyBooks() {
        return booksRepository.findAllByOwnerIsNotNull();
    }

    public Book show(int id) {
        return booksRepository.findById(id).get();
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(Book book, int id) {
        book.setId(id);
        booksRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public Person statusOfBook(int id) {
        return booksRepository.findById(id).get().getOwner();
    }

        @Transactional
        public void assign(int id, Person selectedPerson){
            Book book = booksRepository.findById(id).get();
            book.setOwner(selectedPerson);
            booksRepository.save(book);
        }
}

