package com.edu.neu.csye6200.factories;

import com.edu.neu.csye6200.model.Admin;
import com.edu.neu.csye6200.model.Person;

public class AdminFactory extends AbstractPersonFactory {
    private static AdminFactory mInstance;

    private AdminFactory() {
    }

    @Override
    public Person getObject(String firstName, String lastName, String emailId, String dateOfBirth, String parentFullName, String address) {
        return new Admin(firstName, lastName, emailId, dateOfBirth, parentFullName, address);
    }

    @Override
    public Person getObject() {
        return new Admin();
    }

    @Override
    public Person getObject(String line) {
        String[] lines = line.split(",");
        int i = 0;
        Admin admin = new Admin(lines[i++],
                lines[i++],
                lines[i++],
                lines[i++],
                lines[i++],
                lines[i++]);
        return admin;
    }

    public static AdminFactory getInstance() {
        if (mInstance == null) {
            synchronized (AdminFactory.class) {
                mInstance = new AdminFactory();
            }
        }
        return mInstance;
    }


}
