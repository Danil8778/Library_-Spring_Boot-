package com.pnevsky.library_spring_boot.services;

import com.pnevsky.library_spring_boot.models.Book;
import com.pnevsky.library_spring_boot.models.Person;
import com.pnevsky.library_spring_boot.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findOne(int id){
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(Person person, int id){
        person.setId(id);
        peopleRepository.save(person);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }

    public Optional<Person> getPersonByName(String name){
        return peopleRepository.findByName(name);
    }

    public List<Book> getPersonBooks(int id) {
        return peopleRepository.findById(id).get().getBooks();
    }
}
