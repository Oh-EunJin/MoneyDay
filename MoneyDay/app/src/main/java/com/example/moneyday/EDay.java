package com.example.moneyday;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import androidx.fragment.app.DialogFragment;

public class EDay extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    this, year, month, day);


        return datePickerDialog;

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

//        TextView EndDay = (TextView) getActivity().findViewById(R.id.EndDay);
//
//        EndDay.setText("종료일 : " + String.valueOf(month) + "-" + String.valueOf(dayOfMonth));

    }

}
