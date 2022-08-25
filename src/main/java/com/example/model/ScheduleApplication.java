package com.example.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.*;
import java.util.ArrayList;

public abstract class ScheduleApplication {
    private ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private static LocalDateTime businessOpenTime = ZonedDateTime.of(LocalDate.now(), LocalTime.of(8, 0), ZoneId.of("America/New_York")).toLocalDateTime();
    private static LocalDateTime businessCloseTime = ZonedDateTime.of(LocalDate.now(), LocalTime.of(20, 0), ZoneId.of("America/New_York")).toLocalDateTime();;

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

    public static LocalDateTime getBusinessOpenTime() {
        return businessOpenTime;
    }

    public void setBusinessOpenTime(LocalDateTime businessOpenTime) {
        this.businessOpenTime = businessOpenTime;
    }

    public static LocalDateTime getBusinessCloseTime() {
        return businessCloseTime;
    }

    public void setBusinessCloseTime(LocalDateTime businessCloseTime) {
        this.businessCloseTime = businessCloseTime;
    }
}

