/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survey.models;

import java.beans.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Ratul
 */
public class BeanSurveyModule implements Serializable {
    
    
    
    private PropertyChangeSupport propertySupport;
    private long surveyID;
    private String surveyTitle;
    private String surveyDesc;
    private List<BeanQuestionModule> questionModules;
    
    
    public BeanSurveyModule() {
        propertySupport = new PropertyChangeSupport(this);
    }
    
       
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    public String getSurveyTitle() {
        return surveyTitle;
    }

    public void setSurveyTitle(String surveyTitle) {
        this.surveyTitle = surveyTitle;
    }

    public String getSurveyDesc() {
        return surveyDesc;
    }

    public void setSurveyDesc(String surveyDesc) {
        this.surveyDesc = surveyDesc;
    }

    public List<BeanQuestionModule> getQuestionModules() {
        return questionModules;
    }

    public void setQuestionModules(List<BeanQuestionModule> questionModules) {
        this.questionModules = questionModules;
    }

    public long getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(long surveyID) {
        this.surveyID = surveyID;
    }
    
}
