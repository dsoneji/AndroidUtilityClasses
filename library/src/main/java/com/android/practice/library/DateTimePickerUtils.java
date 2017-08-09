package com.android.practice.library;

import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.Context;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by android.
 */

public class DateTimePickerUtils {
    public static void showDatePicker(FragmentManager fragmentManager, DatePickerDialog.OnDateSetListener listener,
                                      Date selectedStartDate, Date minDate) {
        Calendar calendar = Calendar.getInstance();
        if (selectedStartDate != null) {
            calendar.setTime(selectedStartDate);
        }
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(listener, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        if (minDate != null) {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(minDate);
            datePickerDialog.setMinDate(calendar1);
        }
        datePickerDialog.show(fragmentManager, "Datepickerdialog");
    }

    public static void showTimePicker(Context context,
                                      TimePickerDialog.OnTimeSetListener listener) {
        Calendar date = Calendar.getInstance();
        int minute = date.get(Calendar.MINUTE);
        int hour = date.get(Calendar.HOUR_OF_DAY);

        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(context, listener, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }
}
