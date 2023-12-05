package com.edu.neu.csye6200.controllers;

import com.edu.neu.csye6200.Listeners;
import com.edu.neu.csye6200.utils.Constants;
import com.edu.neu.csye6200.utils.FunctionalUtilities;
import com.edu.neu.csye6200.model.Admin;
import com.edu.neu.csye6200.repositories.AdminRepository;
import com.edu.neu.csye6200.views.AddAdminLayout;
import com.edu.neu.csye6200.views.ApplicationLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AddAdminController extends AppViewsController {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public ApplicationLayout getAppPage() {
        return new AddAdminLayout(Constants.getImageFromName("teacher.jpeg"), ApplicationLayout.BACKGROUND_TYPE_IMAGE);
    }

    @Override
    protected void onCreate(Listeners.AppControlEventListener appControlListener) {
        super.onCreate(appControlListener);
        ((AddAdminLayout) this.getCurrentFrame()).setDbCrudCallBack(dbCrudFunction);
    }

    protected FunctionalUtilities.BiFunctionWithReturnType<Object, Integer, Boolean> dbCrudFunction = (admin, eventType) -> {
        adminRepository.save((Admin) admin);
        return true;
    };

    @Override
    protected void goToNextScreen(Listeners.AppControlEventListener appControlListener) {
        appControlListener.onGoToNextScreenEvent(ViewAllAdminsController.class);
    }
}
