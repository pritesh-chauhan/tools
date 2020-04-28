/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.model;

import java.util.*;

/**
 *
 * @author prite
 */
public class DoctorList {
    
    String patient_name;
    int patient_id;
    List<String> doctor_name;
    List<String> doctor_id;
    List<String> doctor_app_count;

    public DoctorList(String patient_name, int patient_id, List<String> doctor_name, List<String> doctor_id, List<String> doctor_app_count) {
        this.patient_name = patient_name;
        this.patient_id = patient_id;
        this.doctor_name = doctor_name;
        this.doctor_id = doctor_id;
        this.doctor_app_count = doctor_app_count;
    }
    
    

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    

    public DoctorList(){}
    
    public List<String> getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(List<String> doctor_name) {
        this.doctor_name = doctor_name;
    }

    public List<String> getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(List<String> doctor_id) {
        this.doctor_id = doctor_id;
    }

    public List<String> getDoctor_app_count() {
        return doctor_app_count;
    }

    public void setDoctor_app_count(List<String> doctor_app_count) {
        this.doctor_app_count = doctor_app_count;
    }

    
    
    
}
