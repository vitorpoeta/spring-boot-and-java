package br.com.poeta.spring_boot_and_java_erudio.services;

import br.com.poeta.spring_boot_and_java_erudio.exception.ResourceNotFoundException;
import br.com.poeta.spring_boot_and_java_erudio.model.Person;
import br.com.poeta.spring_boot_and_java_erudio.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    PersonRepository repository;

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public List<Person> findAll(){
        logger.info("Finding All People!");
        return repository.findAll();
    }

    public Person findById(Long id){
        logger.info("Finding one Person");

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    }

    public Person create(Person person){
        logger.info("Creating One Person!");
        return repository.save(person);
    }
    public Person update(Person person){
        logger.info("Updating One Person!");
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return repository.save(person);
    }
    public void delete(Long id){
        logger.info("Deleting One Person!");
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }


}
