/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cms.repository;

import com.sg.cms.entity.StaticPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author w-yan
 */


@Repository
public interface StaticPageRepository extends JpaRepository<StaticPage, Integer> {
        
}
