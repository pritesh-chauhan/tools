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
public class PatientAttend {
    
    private String patient_id;
    private int doctor_id;
    private String patient_name;
    private String doctor_name;
    private String message;
    private String medicine;
    private int dose;
    private int quantity;

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "PatientAttend{" + "patient_id=" + patient_id + ", doctor_id=" + doctor_id + ", patient_name=" + patient_name + ", doctor_name=" + doctor_name + ", message=" + message + ", medicine=" + medicine + ", dose=" + dose + ", quantity=" + quantity + '}';
    }

    public PatientAttend(String patient_id, int doctor_id, String patient_name, String doctor_name, String message, String medicine, int dose, int quantity) {
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.patient_name = patient_name;
        this.doctor_name = doctor_name;
        this.message = message;
        this.medicine = medicine;
        this.dose = dose;
        this.quantity = quantity;
    }

    
    public PatientAttend(){}

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
    
    
}
