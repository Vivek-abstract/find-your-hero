package com.vibrantdesigners.findyourhero;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    TextView dateTextView;
    Button selectDateButton;
    TextView selectDateLabel;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        Log.d("Hello", "year: " + year);
        Log.d("Hello", "month: " + month);
        Log.d("Hello", "day: " + day);

        dateTextView = getActivity().findViewById(R.id.dateTextView);

        dateTextView.setText(day + "/" + (month + 1) + "/" + year);
        dateTextView.setVisibility(View.VISIBLE);

        selectDateButton = getActivity().findViewById(R.id.datePickerButton);
        selectDateButton.setText("Change");

        selectDateLabel = getActivity().findViewById(R.id.selectBirthdayLabel);
        selectDateLabel.setText("You chose:");

    }

}