package com.edu.neu.csye6200.controllers;

import com.edu.neu.csye6200.Listeners;
import com.edu.neu.csye6200.ThreadManager;
import com.edu.neu.csye6200.utils.Constants;
import com.edu.neu.csye6200.utils.FunctionalUtilities;
import com.edu.neu.csye6200.model.Teacher;
import com.edu.neu.csye6200.repositories.TeacherRepository;
import com.edu.neu.csye6200.session.AuthenticationAndSessionManager;
import com.edu.neu.csye6200.views.AddPersonLayout;
import com.edu.neu.csye6200.views.AddTeacherLayout;
import com.edu.neu.csye6200.views.ApplicationLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddTeacherController extends AppViewsController {
    @Autowired
    private TeacherRepository teacherRepository;


    @Override
    public ApplicationLayout getAppPage() {
        return new AddTeacherLayout(Constants.getImageFromName("teacher.jpeg"), ApplicationLayout.BACKGROUND_TYPE_IMAGE);
    }

    @Override
    protected void onCreate(Listeners.AppControlEventListener appControlListener) {
        super.onCreate(appControlListener);
        ((AddPersonLayout) this.getCurrentFrame()).setDbCrudCallBack(dbCrudFunction);
    }

    protected FunctionalUtilities.BiFunctionWithReturnType<Object, Integer, Boolean> dbCrudFunction = (teacher, eventType) -> {
        teacherRepository.save((Teacher) teacher);
        ThreadManager.getInstance().getFixedPoolThread().execute(() -> {
            AuthenticationAndSessionManager.getInstance().sendEmail(((Teacher) teacher).getEmailId(), "Daycare Account created", "" +
                    "Hi,\n\n Your Account created. Your Password is " + ((Teacher) teacher).getPassword() + "  \n\n Thanks");
        });
        return true;
    };

    @Override
    protected void goToNextScreen(Listeners.AppControlEventListener appControlListener) {
        appControlListener.onGoToNextScreenEvent(ViewAllTeachersController.class);
    }
}