package com.edu.neu.csye6200.views;

import com.edu.neu.csye6200.utils.Constants;
import com.edu.neu.csye6200.utils.Utils;
import com.edu.neu.csye6200.model.Student;
import com.edu.neu.csye6200.session.AuthenticationAndSessionManager;
import com.edu.neu.csye6200.views.CustomViews.DashboardCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;


public class StudentDashboardLayout extends DashboardLayout {

    private JLabel viewImmunizationLabel;
    private JLabel logoutLabel;
    private JLabel reviewTeacher;
    private DashboardCard teacherIdCard;
    private DashboardCard classRoomIdCard;
    private DashboardCard gpaCard;
    private DashboardCard immunizationUpdateCard;
    private DashboardCard annualRegistrationCard;

    /**
     * Main Constructor of the class
     *
     * @param imagePathOrColor which needs to be used as background
     * @param backgroundType   is type of background {Example: Image or Color}
     */
    public StudentDashboardLayout(String imagePathOrColor, int backgroundType) {
        super(imagePathOrColor, backgroundType);
    }

    @Override
    protected void addItemsToLeftMenu(JPanel leftSidePanel) {
        super.addItemsToLeftMenu(leftSidePanel);
        leftSidePanel.add(this.getSpaceComponent());
        this.viewImmunizationLabel = this.getMenuJLabels("View Immunization");
        this.leftSidePanel.add(this.viewImmunizationLabel);
        leftSidePanel.add(this.getSpaceComponent());
        this.reviewTeacher = this.getMenuJLabels("Review Teacher");
        this.leftSidePanel.add(this.reviewTeacher);
        leftSidePanel.add(this.getSpaceComponent());
        this.logoutLabel = this.getMenuJLabels("Logout");
        leftSidePanel.add(this.logoutLabel);
    }

    @Override
    protected void setUpCards() {
        JPanel currentPanel = new JPanel(new GridBagLayout());
        currentPanel.setOpaque(false);
        currentPanel.setPreferredSize(new Dimension(Constants.APP_PREFERRED_WIDTH - 800, Constants.APP_PREFERRED_HEIGHT - 200));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(8, 100, 32, 100);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.weightx = 1;

        this.immunizationUpdateCard = new DashboardCard("Vaccine Due On", "NA", Color.GRAY, Color.WHITE, Color.RED);
        currentPanel.add(this.immunizationUpdateCard, gridBagConstraints);

        gridBagConstraints.gridx++;
        this.gpaCard = new DashboardCard("GPA", 0, Color.GRAY, Color.WHITE, Color.ORANGE);
        currentPanel.add(this.gpaCard, gridBagConstraints);

        gridBagConstraints.gridx++;
        this.classRoomIdCard = new DashboardCard("ClassRoom Id", 0, Color.GRAY, Color.WHITE, Color.BLUE);
        currentPanel.add(this.classRoomIdCard, gridBagConstraints);

        gridBagConstraints.gridy++;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weighty = 6;
        this.teacherIdCard = new DashboardCard("Teacher Name", "NA", Color.GRAY, Color.WHITE, Color.BLUE);
        currentPanel.add(this.teacherIdCard, gridBagConstraints);

        gridBagConstraints.gridx++;
        this.annualRegistrationCard = new DashboardCard("Registration Due", "NA", Color.GRAY, Color.WHITE, Color.BLUE);
        currentPanel.add(this.annualRegistrationCard, gridBagConstraints);

        this.mainAppLayout.add(currentPanel);
    }

    @Override
    public void refreshCards() {
        this.gpaCard.setMainText(Double.toString(((Student) AuthenticationAndSessionManager.getInstance().getCurrentPerson()).getGpa()));
        this.annualRegistrationCard.setMainText(Utils.getDateString(Utils.getDateAfterDays(Utils.getDateFromString(((Student) AuthenticationAndSessionManager.getInstance().getCurrentPerson()).getCreatedOn()), 365)));
    }

    public void refreshCards(String vaccineText, String vaccineDue) {
        this.immunizationUpdateCard.setTitleText(vaccineText);
        this.immunizationUpdateCard.setMainText(vaccineDue);
    }

    public void refreshCards(int classRoomId, String teacherName) {
        this.classRoomIdCard.setCount(classRoomId);
        this.teacherIdCard.setMainText(teacherName);
    }


    @Override
    protected String getNavBarTitle() {
        return "Student Dashboard";
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent().equals(this.logoutLabel)) super.onRightButtonClicked();
        if (e.getComponent().equals(this.viewImmunizationLabel))
            super.eventListener.onEvent(Constants.EVENT_NEXT_SCREEN);
        if (e.getComponent().equals(this.reviewTeacher)) {
            JOptionPane.showMessageDialog(new JFrame(), "Teacher Review will be open after year", "Error!!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
