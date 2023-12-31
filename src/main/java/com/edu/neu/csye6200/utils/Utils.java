package com.edu.neu.csye6200.utils;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.function.Function;
import java.util.regex.Pattern;

public class Utils {
    public static Function<String, Boolean> VALIDATE_EMAIL_ADDRESS = (emailId) -> {
        Pattern pattern = Pattern.compile("^.+@.+\\..+$");
        return pattern.matcher(emailId).matches();
    };

    public static JDialog geLoadingDialog(String message) {
        JDialog downloadingDialog = new JDialog((JFrame) null, "Please Wait..", false);
        downloadingDialog.setLayout(new FlowLayout(FlowLayout.CENTER));
        downloadingDialog.setUndecorated(true);
        Icon myImgIcon = new ImageIcon("./src/main/resources/loading-gif.gif");
        JLabel imageLbl = new JLabel(myImgIcon);
        downloadingDialog.add(imageLbl, BorderLayout.CENTER);
        downloadingDialog.setSize(400, 400);
        downloadingDialog.setVisible(true);
        return downloadingDialog;
    }


    public static int parseInteger(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Double parseDouble(String text) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1.0;
        }
    }

    public static boolean isDateValid(String text, boolean isDob) {
        try {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            df.setLenient(false);
            Date current = df.parse(text);
            if (isDob) {
                Date today = new Date();
                return current.getTime() < today.getTime();
            }
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static Function<Date, String> GET_DATE_STRING = (date) -> {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(date);
    };

    public static final String getDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(date);
    }

    public static Date getDateFromString(String text) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            return dateFormat.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date getDateAfterDays(Date date, int days) {
        
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return c.getTime();
    }

    public static final Function<String, Integer> GET_AGE_IN_MONTHS_FROM_DOB = (date) -> {
        LocalDate today = LocalDate.now();
        Date bDate = getDateFromString(date);
        Calendar calendar = Calendar.getInstance();
        assert bDate != null;
        calendar.setTime(bDate);
        LocalDate birthday = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        Period p = Period.between(birthday, today);
        return p.getYears() * 12 + p.getMonths();
    };

    public static final Function<String, String> GENERATE_PASSWORD = (string) -> {
        if (string == null) return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            sb.append(string.trim().charAt(random.nextInt(string.trim()
                    .length())));
        }
        return sb.toString();
    };
}
