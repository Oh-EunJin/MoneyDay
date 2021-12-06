package com.example.moneyday;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import androidx.fragment.app.DialogFragment;

public class EndT  extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,this, hour, min, false);

        return timePickerDialog;

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        TextView eTv = (TextView) getActivity().findViewById(R.id.eTv);
        String aMpM = "AM";
        if (hourOfDay > 11) {
            aMpM = "PM";
        }
        int currentHour;
        if (hourOfDay > 11) {
            currentHour = hourOfDay - 12;
        }
        else {
            currentHour = hourOfDay;
        }

        eTv.setText(String.valueOf(hourOfDay) + "시 " + String.valueOf(minute) + "분");
    }

}
