package com.edu.neu.csye6200.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;


@Entity(name = "table_teacher")
public class Teacher extends Person {
    @Column(name = "credits")
    private int credits;
    @Column(name = "hourly_wage")
    private int hourlyWage;
    @Column(name = "assigned_class_room_id", columnDefinition = "int default 0")
    private int assignedClassRoomId;

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String emailId, String dateOfBirth, String parentFullName, String address, int credits, int hourlyWage) {
        super(firstName, lastName, emailId, dateOfBirth, parentFullName, address);
        this.credits = credits;
        this.hourlyWage = hourlyWage;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(int hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public int getAssignedClassRoomId() {
        return assignedClassRoomId;
    }

    public void setAssignedClassRoomId(int assignedClassRoomId) {
        this.assignedClassRoomId = assignedClassRoomId;
    }
}