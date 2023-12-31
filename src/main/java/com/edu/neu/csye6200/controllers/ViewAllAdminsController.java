package com.edu.neu.csye6200.controllers;

import com.edu.neu.csye6200.Listeners;
import com.edu.neu.csye6200.model.Admin;
import com.edu.neu.csye6200.repositories.AdminRepository;
import com.edu.neu.csye6200.views.ApplicationLayout;
import com.edu.neu.csye6200.views.ViewAllDataLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ViewAllAdminsController extends AppViewsController {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public ApplicationLayout getAppPage() {
        return new ViewAllDataLayout("./src/main/resources/daycare_landing_background.jpg", ApplicationLayout.BACKGROUND_TYPE_IMAGE);
    }

    private String[][] getDataForTable() {
        List<Admin> list = adminRepository.findAll();
        String[][] data = new String[list.size()][7];
        for (int i = 0; i < list.size(); i++) {
            int j = 0;
            Admin admin = list.get(i);
            data[i][j++] = Integer.toString(i + 1);
            data[i][j++] = admin.getFirstName();
            data[i][j++] = admin.getLastName();
            data[i][j++] = Integer.toString(admin.getAge() / 12);
            data[i][j++] = admin.getParentFullName();
            data[i][j] = admin.getEmailId();
        }
        return data;
    }

    @Override
    protected void onCreate(Listeners.AppControlEventListener appControlListener) {
        super.onCreate(appControlListener);
        String[] header = new String[]{"Sl.No", "First Name", "Last Name", "Age (Years)", "Parents Name", "Email Id"};
        ((ViewAllDataLayout) this.getCurrentFrame()).setUpDataForTable(header, this.getDataForTable(), "Admins");
    }

    @Override
    protected void goToNextScreen(Listeners.AppControlEventListener appControlListener) {

    }
}
