package com.example.model;

import javafx.collections.ObservableList;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

public abstract class ScheduleApplication {
    private ObservableList<Customer> allCustomers;
    private ObservableList<Appointment> allAppointments;
    private LocalTime businessOpenTime;
    private LocalTime businessCloseTime;
    private ArrayList<DayOfWeek> businessOpenDays;

    public ObservableList<Customer> getAllCustomers() {
        return allCustomers;
    }

    public void setAllCustomers(ObservableList<Customer> allCustomers) {
        this.allCustomers = allCustomers;
    }

    public ObservableList<Appointment> getAllAppointments() {
        return allAppointments;
    }

    public void setAllAppointments(ObservableList<Appointment> allAppointments) {
        this.allAppointments = allAppointments;
    }

    public LocalTime getBusinessOpenTime() {
        return businessOpenTime;
    }

    public void setBusinessOpenTime(LocalTime businessOpenTime) {
        this.businessOpenTime = businessOpenTime;
    }

    public LocalTime getBusinessCloseTime() {
        return businessCloseTime;
    }

    public void setBusinessCloseTime(LocalTime businessCloseTime) {
        this.businessCloseTime = businessCloseTime;
    }

    public ArrayList<DayOfWeek> getBusinessOpenDays() {
        return businessOpenDays;
    }

    public void setBusinessOpenDays(ArrayList<DayOfWeek> businessOpenDays) {
        this.businessOpenDays = businessOpenDays;
    }
}
