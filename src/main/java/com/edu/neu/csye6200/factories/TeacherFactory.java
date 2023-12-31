package com.edu.neu.csye6200.factories;

import com.edu.neu.csye6200.utils.Utils;
import com.edu.neu.csye6200.model.Person;
import com.edu.neu.csye6200.model.Teacher;


public class TeacherFactory extends AbstractPersonFactory {
    private static TeacherFactory mInstance;

    private TeacherFactory() {
    }

    @Override
    public Person getObject(String firstName, String lastName, String emailId, String dateOfBirth, String parentFullName, String address) {
        return new Teacher(firstName, lastName, emailId, dateOfBirth, parentFullName, address, 0, 0);
    }

    @Override
    public Person getObject() {
        return new Teacher();
    }

    @Override
    public Person getObject(String line) {
        String[] lines = line.split(",");
        int i = 0;
        Teacher teacher = new Teacher(lines[i++],
                lines[i++],
                lines[i++],
                lines[i++],
                lines[i++],
                lines[i++],
                Utils.parseInteger(lines[i++]),
                Utils.parseInteger(lines[i++]));
        return teacher;
    }

    public static TeacherFactory getInstance() {
        if (mInstance == null) {
            synchronized (TeacherFactory.class) {
                mInstance = new TeacherFactory();
            }
        }
        return mInstance;
    }
}