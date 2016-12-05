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
 * @author KD
 */
public class BeanSurveyModuleList implements Serializable {
    
    private List <BeanSurveyModule> beanSurveyModuleList;

    public void setBeanSurveyModuleList(List<BeanSurveyModule> beanSurveyModuleList) {
        this.beanSurveyModuleList = beanSurveyModuleList;
    }

    public List<BeanSurveyModule> getBeanSurveyModuleList() {
        return beanSurveyModuleList;
    }
}
