package com.edu.neu.csye6200.views;

import com.edu.neu.csye6200.factories.StudentFactory;

import javax.swing.*;
import java.awt.*;


public class AddStudentLayout extends AddPersonLayout {
    public AddStudentLayout(String imagePathOrColor, int backgroundType) {
        super(imagePathOrColor, backgroundType);
    }

    @Override
    protected String getSuffixForAdd() {
        return "Student";
    }

    @Override
    protected void setUpPersonFactory() {
        this.abstractPersonFactory = StudentFactory.getInstance();
    }

    @Override
    protected void initSpecificFields(JPanel jPanel, GridBagConstraints gridBagConstraints) {
        //do nothing
    }
}
