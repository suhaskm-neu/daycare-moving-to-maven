package com.edu.neu.csye6200.views;

import com.edu.neu.csye6200.factories.AdminFactory;

public class AddAdminLayout extends AddPersonLayout {
    public AddAdminLayout(String imagePathOrColor, int backgroundType) {
        super(imagePathOrColor, backgroundType);
    }

    @Override
    protected String getSuffixForAdd() {
        return "Admin";
    }

    @Override
    protected void setUpPersonFactory() {
        this.abstractPersonFactory = AdminFactory.getInstance();
    }
}
