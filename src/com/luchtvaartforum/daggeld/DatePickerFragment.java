package com.luchtvaartforum.daggeld;

import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.*;
import java.util.Calendar;
import android.view.View;
import android.content.*;
public class DatePickerFragment extends DialogFragment 
implements DatePickerDialog.OnDateSetListener {
	private Calendar selected_datetime;
	private Context context;

	public DatePickerFragment(Context c,Calendar sldt) {
		this.selected_datetime = sldt;
		this.context =c;
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
		Toast.makeText(context, "on blocks date clicked & set", Toast.LENGTH_SHORT).show();
		selected_datetime.set(year, month, day);
		
	}

	//public void  view, int y, int m, int d) {
		//selected_datetime.set(y, m, d);
		//updateValues();
		//testClickButton();
	//}
}
