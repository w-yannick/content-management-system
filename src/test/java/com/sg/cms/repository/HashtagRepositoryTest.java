/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package com.sg.cms.repository;


import com.sg.cms.entity.Hashtag;
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

public class HashtagRepositoryTest {

    
       
    @Autowired 
    HashtagRepository hashtagRepository;
     
    public HashtagRepositoryTest() {
        
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
    public void testAddHashtag(){

        Hashtag hashtag = new Hashtag();
        hashtag.setName("name");
        hashtagRepository.save(hashtag);

        assertThat(hashtag).isNotNull();
        assertThat(hashtag.getId()).isGreaterThan(0);
    }
    
    @Test
    public void testAddAndGetHashtag(){

        Hashtag hashtag = new Hashtag();
        hashtag.setName("name");
        hashtagRepository.save(hashtag);
        
        
        Hashtag fromRepo = hashtagRepository.findById(hashtag.getId()).orElse(null);
        assertEquals(hashtag,fromRepo);
        assertEquals("name",fromRepo.getName());
        
    }
    
    @Test
    public void testAddAndDelete(){
        Hashtag hashtag = new Hashtag();
        hashtag.setName("name");
        hashtagRepository.save(hashtag);
        
        Hashtag fromRepoBeforeDelete = hashtagRepository.findById(hashtag.getId()).orElse(null);
        assertEquals(hashtag,fromRepoBeforeDelete);
        
        hashtagRepository.delete(hashtag);
        Hashtag fromRepoAfterDelete = hashtagRepository.findById(hashtag.getId()).orElse(null);
        
        Assertions.assertNull(fromRepoAfterDelete);
    }
    
    @Test
    public void testAddAndUpdate(){
        Hashtag hashtag = new Hashtag();
        hashtag.setName("name1");
        hashtagRepository.save(hashtag);
        
        //To update, first retrieve the element to update in order to get the entity in a managed state
        Hashtag fromRepoBeforeUpdate = hashtagRepository.findById(hashtag.getId()).orElse(null);
        assertEquals(hashtag,fromRepoBeforeUpdate);
        
        //then update the needed field, then use .save()
        //jpaRepository will automatically know which record to update with .save() thanks to the entity Manager
        //which is why its important to get the intity in a managed state.
        fromRepoBeforeUpdate.setName("name2");
        hashtagRepository.save(fromRepoBeforeUpdate);
        Hashtag fromRepoAfterDelete = hashtagRepository.findById(hashtag.getId()).orElse(null);
        
        assertEquals("name2", fromRepoAfterDelete.getName());
    }
    
    
}
