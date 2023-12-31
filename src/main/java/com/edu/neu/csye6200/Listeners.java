package com.edu.neu.csye6200;

import com.edu.neu.csye6200.model.Person;

import java.sql.SQLException;


public class Listeners {
    public interface EventListener {
        void onEvent(int eventType);

        int getIntegerData(int dataType);
    }

    public interface AppControlEventListener {
        /**
         * Will be called when user wants to navigate to next page
         *
         * @param appViewsController -=
         * @param <T>
         */
        <T> void onGoToNextScreenEvent(Class<T> appViewsController);

        /**
         * Fired when for removing current view from the stack
         */
        void onBackPressed();
    }

    public interface SessionManager {
        Person validateAdmin(String userName, String password) throws SQLException;

        Person validateTeacher(String userName, String password) throws SQLException;

        Person validateParent(String userName, String password) throws SQLException;

        void onNewSessionEvent(int eventType);

        void sendEmail(String to, String subject, String body);
    }

}
