/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cms.view;

import org.springframework.stereotype.Component;

/**
 *
 * @author w-yan
 */
@Component
public class CmsView {
    public String displayIndexPage(){
        return "index";
    }
}
