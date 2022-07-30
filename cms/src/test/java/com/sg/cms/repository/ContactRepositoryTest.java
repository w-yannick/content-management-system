/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package com.sg.cms.repository;


import com.sg.cms.entity.Contact;
import com.sg.cms.entity.Contact;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)

public class ContactRepositoryTest {

    
    @Autowired 
    ContactRepository contactRepository;
     
    public ContactRepositoryTest() {
        
    }
    
    @BeforeAll
    public static void setUpClass() {
        
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach 
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
        
    }
    
    @Test
    public void testAddContact(){

        Contact contact = new Contact();
        contact.setMessage("message");
        contactRepository.save(contact);

        assertThat(contact).isNotNull();
        assertThat(contact.getId()).isGreaterThan(0);
    }
    
    @Test
    public void testAddAndGetContact(){

        Contact contact = new Contact();
        contact.setMessage("message");
        contactRepository.save(contact);
        
        
        Contact fromRepo = contactRepository.findById(contact.getId()).orElse(null);
        assertEquals(contact,fromRepo);
        assertEquals("message",fromRepo.getMessage());
        
    }
    
    @Test
    public void testAddAndDelete(){
        Contact contact = new Contact();
        contact.setMessage("message");
        contactRepository.save(contact);
        
        Contact fromRepoBeforeDelete = contactRepository.findById(contact.getId()).orElse(null);
        assertEquals(contact,fromRepoBeforeDelete);
        
        contactRepository.delete(contact);
        Contact fromRepoAfterDelete = contactRepository.findById(contact.getId()).orElse(null);
        
        Assertions.assertNull(fromRepoAfterDelete);
    }
    
    @Test
    public void testAddAndUpdate(){
        Contact contact = new Contact();
        contact.setMessage("message1");
        contactRepository.save(contact);
        
        //To update, first retrieve the element to update in order to get the entity in a managed state
        Contact fromRepoBeforeUpdate = contactRepository.findById(contact.getId()).orElse(null);
        assertEquals(contact,fromRepoBeforeUpdate);
        
        //then update the needed field, then use .save()
        //jpaRepository will automatically know which record to update with .save() thanks to the entity Manager
        //which is why its important to get the intity in a managed state.
        fromRepoBeforeUpdate.setMessage("message2");
        contactRepository.save(fromRepoBeforeUpdate);
        Contact fromRepoAfterDelete = contactRepository.findById(contact.getId()).orElse(null);
        
        assertEquals("message2", fromRepoAfterDelete.getMessage());
    }
    
    
}
