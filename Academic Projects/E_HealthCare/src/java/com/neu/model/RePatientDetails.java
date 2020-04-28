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
public class RePatientDetails {
    
    private String patient_name;
    private int patient_id;
    private String doctor_name;
    private int doctor_id;
    private int doctor_count;
    private int count_illness;
    private int count_medicine;

    public RePatientDetails(String patient_name, int patient_id, String doctor_name, int doctor_id, int doctor_count, int count_illness, int count_medicine) {
        this.patient_name = patient_name;
        this.patient_id = patient_id;
        this.doctor_name = doctor_name;
        this.doctor_id = doctor_id;
        this.doctor_count = doctor_count;
        this.count_illness = count_illness;
        this.count_medicine = count_medicine;
    }

    
    

    public int getCount_illness() {
        return count_illness;
    }

    public void setCount_illness(int count_illness) {
        this.count_illness = count_illness;
    }

    public int getCount_medicine() {
        return count_medicine;
    }

    public void setCount_medicine(int count_medicine) {
        this.count_medicine = count_medicine;
    }
    
    
    
    
    public RePatientDetails(){}

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

    public int getDoctor_count() {
        return doctor_count;
    }

    public void setDoctor_count(int doctor_count) {
        this.doctor_count = doctor_count;
    }
    
    
    
    
}
