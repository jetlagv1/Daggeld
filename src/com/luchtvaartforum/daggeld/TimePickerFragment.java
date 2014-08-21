package com.luchtvaartforum.daggeld;

import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.*;
import java.util.Calendar;

public class TimePickerFragment extends DialogFragment
implements TimePickerDialog.OnTimeSetListener {
	private Calendar selected_datetime;

	public TimePickerFragment(Calendar sldt) {
		this.selected_datetime = sldt;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		int hour = selected_datetime.get(Calendar.HOUR_OF_DAY);
		int minute = selected_datetime.get(Calendar.MINUTE);
		return new TimePickerDialog(getActivity(), this, hour, minute, true);
	}

	public void onTimeSet(TimePicker view, int h, int m) {
		
		selected_datetime.set(Calendar.HOUR_OF_DAY, h);
		selected_datetime.set(Calendar.MINUTE, m);
		//updateValues();
		//testClickButton();
	}
}
