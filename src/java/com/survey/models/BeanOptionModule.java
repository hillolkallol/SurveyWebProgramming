/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survey.models;

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author Ratul
 */
public class BeanOptionModule implements Serializable {

    //public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";
    //private String sampleProperty;
    private long optionId;
    private String optionDesc;
    private int order;
    private long questionId;
    private int noOfUserSelected;

    private PropertyChangeSupport propertySupport;

    public BeanOptionModule() {
        propertySupport = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    public String getOptionDesc() {
        return optionDesc;
    }

    public void setOptionDesc(String optionDesc) {
        this.optionDesc = optionDesc;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public long getOptionId() {
        return optionId;
    }

    public void setOptionId(long optionId) {
        this.optionId = optionId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public int getNoOfUserSelected() {
        return noOfUserSelected;
    }

    public void setNoOfUserSelected(int noOfUserSelected) {
        this.noOfUserSelected = noOfUserSelected;
    }

}
