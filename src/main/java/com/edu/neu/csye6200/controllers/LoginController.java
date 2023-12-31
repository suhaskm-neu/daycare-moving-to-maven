package com.edu.neu.csye6200.controllers;

import com.edu.neu.csye6200.Listeners;
import com.edu.neu.csye6200.session.AuthenticationAndSessionManager;
import com.edu.neu.csye6200.views.ApplicationLayout;
import com.edu.neu.csye6200.views.LoginPageLayout;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;


@Service
@Lazy
public class LoginController extends AppViewsController {
    LoginPageLayout loginPageLayout;

    @Override
    public ApplicationLayout getAppPage() {
        this.loginPageLayout = new LoginPageLayout("./src/main/resources/daycare_landing_background.jpg", ApplicationLayout.BACKGROUND_TYPE_IMAGE);
        this.loginPageLayout.setLoginListener((username, password, result) -> {
            AuthenticationAndSessionManager sessionManager = AuthenticationAndSessionManager.getInstance();
            sessionManager.authenticate(username, password, result);
        });
        return this.loginPageLayout;
    }

    @Override
    protected void goToNextScreen(Listeners.AppControlEventListener appControlListener) {
    }


}
