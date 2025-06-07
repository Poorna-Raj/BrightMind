/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brightmind.model;

/**
 *
 * @author Poorna
 */
public class Subject {
    private int subjectId;
    private String subjectCode;
    private String subjectName;
    private String description;
    private int creditPoints;

    /**
     * Default constructor
     */
    public Subject() {
    }

    /**
     * To update process
     * @param subjectId
     * @param subjectCode
     * @param subjectName
     * @param description
     * @param creditPoints 
     */
    public Subject(int subjectId, String subjectCode, String subjectName, String description, int creditPoints) {
        this.subjectId = subjectId;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.description = description;
        this.creditPoints = creditPoints;
    }
    
    /**
     * For create process
     * @param subjectCode
     * @param subjectName
     * @param description
     * @param creditPoints 
     */
    public Subject(String subjectCode, String subjectName, String description, int creditPoints) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.description = description;
        this.creditPoints = creditPoints;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreditPoints() {
        return creditPoints;
    }

    public void setCreditPoints(int creditPoints) {
        this.creditPoints = creditPoints;
    }
    
    
}
