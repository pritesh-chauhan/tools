/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.model;

/**
 *
 * @author prite
 */
public class DoctorSelect {
    
    private String patient_name;
    private int patient_id;
    private String doctor_name;
    private int doctor_id;

    private String message;
    
    public DoctorSelect(String patient_name, int patient_id, String doctor_name, int doctor_id) {
        this.patient_name = patient_name;
        this.patient_id = patient_id;
        this.doctor_name = doctor_name;
        this.doctor_id = doctor_id;
    }
    public DoctorSelect(){}

    public DoctorSelect(String patient_name, int patient_id, String doctor_name, int doctor_id, String message) {
        this.patient_name = patient_name;
        this.patient_id = patient_id;
        this.doctor_name = doctor_name;
        this.doctor_id = doctor_id;
        this.message = message;
    }
    
    

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }
    
    
    
}
