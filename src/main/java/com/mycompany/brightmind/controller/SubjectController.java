/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brightmind.controller;

import com.mycompany.brightmind.model.SubjectDAO;
import com.mycompany.brightmind.view.SubjectPanel;

/**
 *
 * @author Poorna
 */
public class SubjectController {
    private SubjectPanel subjectPanel;
    private SubjectDAO subjectDAO;

    public SubjectController(SubjectPanel subjectPanel, SubjectDAO subjectDAO) {
        this.subjectPanel = subjectPanel;
        this.subjectDAO = subjectDAO;
    }
    
    
}
