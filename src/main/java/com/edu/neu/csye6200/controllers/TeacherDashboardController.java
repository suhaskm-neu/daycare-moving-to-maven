package com.edu.neu.csye6200.controllers;

import com.edu.neu.csye6200.Listeners;
import com.edu.neu.csye6200.utils.Constants;
import com.edu.neu.csye6200.model.ClassSections;
import com.edu.neu.csye6200.model.Teacher;
import com.edu.neu.csye6200.repositories.ClassroomRepository;
import com.edu.neu.csye6200.views.ApplicationLayout;
import com.edu.neu.csye6200.views.TeacherDashboardLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TeacherDashboardController extends AppViewsController {
    public static final int EVENT_GOTO_VIEW_STUDENT = 71801;
    public static int EVENT_GET_STUDENT_COUNT = 72801;

    @Autowired
    private ClassroomRepository classRoomRepository;

    @Override
    public ApplicationLayout getAppPage() {
        return new TeacherDashboardLayout(Constants.getImageFromName("teacher.jpeg"), ApplicationLayout.BACKGROUND_TYPE_IMAGE);
    }

    @Override
    protected void processEvent(int eventType, Listeners.AppControlEventListener appControlListener) {
        super.processEvent(eventType, appControlListener);
        switch (eventType) {
            case EVENT_GOTO_VIEW_STUDENT: {
                appControlListener.onGoToNextScreenEvent(ViewAllStudentsController.class);
                break;
            }
        }
    }

    @Override
    protected void goToNextScreen(Listeners.AppControlEventListener appControlListener) {

    }

    @Override
    public int getIntegerData(int dataType) {
        if (dataType == EVENT_GET_STUDENT_COUNT) {
            Teacher teacher = ((Teacher) this.getCurrentLoggedInUser());
            Optional<ClassSections> classSections = classRoomRepository.findById(teacher.getAssignedClassRoomId());
            if (classSections.isPresent()) {
                String[] teacherIds = classSections.get().getTeacherIds().replaceAll("#", "").split(",");
                int teacherGroup = -1;
                for (int i = 0; i < teacherIds.length; i++) {
                    if (teacherIds[i].trim().equalsIgnoreCase(Integer.toString(teacher.getId()))) {
                        teacherGroup = i;
                        break;
                    }
                }
                if (teacherGroup == -1) return 0;
                if (classSections.get().getCurrentCapacity() >= classSections.get().getGroupSize() * (teacherGroup + 1))
                    return classSections.get().getGroupSize();
                return classSections.get().getCurrentCapacity() - classSections.get().getGroupSize() * teacherGroup;
            }
            return 0;
        }
        return super.getIntegerData(dataType);
    }
}
