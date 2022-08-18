package com.example.model;

import javafx.collections.ObservableList;

public class Schedule {
    private ObservableList<Appointment> scheduledAppointments;

    public ObservableList<Appointment> getScheduledAppointments() {
        return scheduledAppointments;
    }

    public void setScheduledAppointments(ObservableList<Appointment> scheduledAppointments) {
        this.scheduledAppointments = scheduledAppointments;
    }

    public Schedule(ObservableList<Appointment> scheduledAppointments) {
        this.scheduledAppointments = scheduledAppointments;
    }
}
