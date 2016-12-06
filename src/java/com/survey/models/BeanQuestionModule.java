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
public class BeanQuestionModule implements Serializable {

    private long questionId;
    private String questionDesc;
    private long surveyId;
    private List<BeanOptionModule> options;

    private PropertyChangeSupport propertySupport;
    
    public BeanQuestionModule() {
        propertySupport = new PropertyChangeSupport(this);
    }

    public void addOption(BeanOptionModule option)
    {
        options.add(option);
        //return options;
    }
    
    public BeanOptionModule getNthOption(int nTh)
    {
        return options.get(nTh);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }

    public List<BeanOptionModule> getOptions() {
        return options;
    }

    public void setOptions(List<BeanOptionModule> options) {
        this.options = options;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(long surveyId) {
        this.surveyId = surveyId;
    }

}
