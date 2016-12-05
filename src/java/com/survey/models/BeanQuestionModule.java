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
    
   private String questionDesc;
   private List<BeanOption> options;
    
    private PropertyChangeSupport propertySupport;
    
    public BeanQuestionModule() {
        propertySupport = new PropertyChangeSupport(this);
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

    public List<BeanOption> getOptions() {
        return options;
    }

    public void setOptions(List<BeanOption> options) {
        this.options = options;
    }
    
}
