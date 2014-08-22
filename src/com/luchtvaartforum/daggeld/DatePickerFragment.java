package com.luchtvaartforum.daggeld;

import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.*;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment 
implements DatePickerDialog.OnDateSetListener {
	private Calendar selected_datetime;

	public DatePickerFragment(Calendar sldt) {
		this.selected_datetime = sldt;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		int year = selected_datetime.get(Calendar.YEAR);
		int month = selected_datetime.get(Calendar.MONTH);
		int day = selected_datetime.get(Calendar.DAY_OF_MONTH);
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	@Override
	public void onDateSet(DatePicker p1, int year, int month, int day)
	{
		
		selected_datetime.set(year, month, day);
	}

	//public void  view, int y, int m, int d) {
		//selected_datetime.set(y, m, d);
		//updateValues();
		//testClickButton();
	//}
}
