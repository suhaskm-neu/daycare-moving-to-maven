package com.edu.neu.csye6200.views;

import com.edu.neu.csye6200.utils.Constants;
import com.edu.neu.csye6200.utils.FileUtils;
import com.edu.neu.csye6200.utils.FunctionalUtilities;
import com.edu.neu.csye6200.utils.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


public class ViewAndReImportLayout extends NavBarLayout {

    private FunctionalUtilities.BiFunctionWithReturnType<Object, Integer, Boolean> dbCrudCallBack;
    private DefaultTableModel defaultTableModel;
    private JButton reImportButton;

    /**
     * Main Constructor of the class
     *
     * @param imagePathOrColor which needs to be used as background
     * @param backgroundType   is type of background {Example: Image or Color}
     */
    public ViewAndReImportLayout(String imagePathOrColor, int backgroundType) {
        super(imagePathOrColor, backgroundType);
    }

    @Override
    protected void initComponents() {
        super.initComponents();
        this.setLeftComponent(Constants.getIconPathFromName("back-button.png"), "Back");
    }

    @Override
    protected String getNavBarTitle() {
        return "View";
    }

    @Override
    protected void onCreate() {
        super.onCreate();
    }

    private void setUpTable(String[] header, String[][] data, String title) {
        this.navTitle.setText(title);
        this.defaultTableModel = new DefaultTableModel(data, header);
        JTable table = new JTable(defaultTableModel);
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(Color.yellow);
        table.setPreferredScrollableViewportSize(new Dimension(Constants.APP_PREFERRED_WIDTH - 120, Constants.APP_PREFERRED_HEIGHT - 480));
        table.setFillsViewportHeight(true);
        table.setOpaque(true);
        JScrollPane js = new JScrollPane(table);
        js.setVisible(true);
        this.mainPanel.add(js, BorderLayout.LINE_START);

        this.reImportButton = new JButton("RE-IMPORT FROM CSV");
        reImportButton.setPreferredSize(new Dimension(200, 60));
        reImportButton.setForeground(Color.BLACK);
        this.mainPanel.add(reImportButton, BorderLayout.PAGE_END);

        reImportButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                openFileDialogForCSVImport();
            }
        });
    }

    public void setUpDataForTable(String[] header, String[][] data, String title) {
        if (defaultTableModel != null) defaultTableModel.setRowCount(0);
        else {
            this.setUpTable(header, data, title);
            return;
        }
        for (String[] datum : data) {
            defaultTableModel.addRow(datum);
        }
        defaultTableModel.fireTableDataChanged();
    }

    private void openFileDialogForCSVImport() {
        FileDialog dialog = new FileDialog((Frame) null, "Select a CSV file to Re-Import Class Rules (Previous rows will be over-rided");
        dialog.setFilenameFilter((dir, name) -> name.toLowerCase().endsWith(".csv"));
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String file = dialog.getFile();
        if (file == null) {
            JOptionPane.showMessageDialog(new JFrame(), "No File Chosen", "Error!!",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(new JFrame(), "Starting file processing", "Success!!",
                JOptionPane.INFORMATION_MESSAGE);
        JDialog jDialog = Utils.geLoadingDialog("File is being processed");
        jDialog.setVisible(true);
        this.mainPanel.setVisible(false);
        try {
            List<String> list = new ArrayList<>();
            FileUtils.readTxtFileLines(dialog.getDirectory() + file, list::add, (result) -> {
                ViewAndReImportLayout.this.dbCrudCallBack.accept(list, 0);
                jDialog.dispose();
                mainPanel.setVisible(true);
                JOptionPane.showMessageDialog(new JFrame(), "File processed successfully", "Success!!",
                        JOptionPane.INFORMATION_MESSAGE);
            });
        } catch (Exception e) {
            e.printStackTrace();
            jDialog.dispose();
            mainPanel.setVisible(true);
            JOptionPane.showMessageDialog(new JFrame(), "Error while processing file, Try Again!!", "Error!!",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public void setDbCrudCallBack(FunctionalUtilities.BiFunctionWithReturnType<Object, Integer, Boolean> dbCrudCallBack) {
        this.dbCrudCallBack = dbCrudCallBack;
    }
}
