/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.model;

import java.util.List;

/**
 *
 * @author prite
 */
public class DoctorAppointment {

    private List<String> patient_id;
    private int doctor_id;
    private List<String> patient_name;
    private String doctor_name;
    private List<String> message;

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
    public DoctorAppointment(){}
    public DoctorAppointment(List<String> patient_id,  List<String> patient_name, List<String> message, int doctor_id, String doctor_name) {
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.patient_name = patient_name;
        this.doctor_name = doctor_name;
        this.message = message;
    }
    
    public List<String> getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(List<String> patient_id) {
        this.patient_id = patient_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public List<String> getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(List<String> patient_name) {
        this.patient_name = patient_name;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }
    
    

}
