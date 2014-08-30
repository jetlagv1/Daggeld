package com.luchtvaartforum.daggeld;

import java.util.Calendar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import java.text.*;
import java.util.*;
import android.view.textservice.*;
import java.math.*;
import android.renderscript.*;
import android.app.*;

public class MainActivity extends FragmentActivity {

	private SimpleDateFormat date_format = new SimpleDateFormat("dd-MM-yy", Locale.US);
	private SimpleDateFormat time_format = new SimpleDateFormat("kk:mm", Locale.US);
	private SimpleDateFormat selected_format;
	public StopOver stopje = new StopOver();
	private Calendar selected_datetime;
	private Calendar on_blocks_datetime;
	private Calendar off_blocks_datetime;
	private Double sundries = Double.valueOf(0); // BigDecimal(0);
	private Double lunch = Double.valueOf(0); // BigDecimal(0);
	private Double diner = Double.valueOf(0); // BigDecimal(0);
	private String airport = "";
//	private String on_blocks_date_string = date_format.format(on_blocks_datetime.getTime());
//	private String on_blocks_time_string = time_format.format(on_blocks_datetime.getTime());
//	private String off_blocks_date_string = date_format.format(off_blocks_datetime.getTime());
//	private String off_blocks_time_string = time_format.format(off_blocks_datetime.getTime());
	private TextView selected_textview;
	private TextView on_blocks_date_view;
	private TextView on_blocks_time_view;
	private TextView off_blocks_date_view;
	private TextView off_blocks_time_view;
	private TextView output_view;
	private EditText sundries_edittext;
	private EditText lunch_edittext;
	private EditText diner_edittext;
	private EditText airport_edittext;

	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putInt("onby", stopje.getOnBlocksDatetime().get(Calendar.YEAR));
		savedInstanceState.putInt("onbmo", stopje.getOnBlocksDatetime().get(Calendar.MONTH));
		savedInstanceState.putInt("onbd", stopje.getOnBlocksDatetime().get(Calendar.DAY_OF_MONTH));
		savedInstanceState.putInt("onbh", stopje.getOnBlocksDatetime().get(Calendar.HOUR_OF_DAY));
		savedInstanceState.putInt("onbmi", stopje.getOnBlocksDatetime().get(Calendar.MINUTE));
		
		savedInstanceState.putInt("offby", stopje.getOffBlocksDatetime().get(Calendar.YEAR));
		savedInstanceState.putInt("offbmo", stopje.getOffBlocksDatetime().get(Calendar.MONTH));
		savedInstanceState.putInt("offbd", stopje.getOffBlocksDatetime().get(Calendar.DAY_OF_MONTH));
		savedInstanceState.putInt("offbh", stopje.getOffBlocksDatetime().get(Calendar.HOUR_OF_DAY));
		savedInstanceState.putInt("offbmi", stopje.getOffBlocksDatetime().get(Calendar.MINUTE));	
		savedInstanceState.putString("airport", stopje.getAirport());
		savedInstanceState.putDouble("sundries", stopje.getSundries_24().doubleValue());
		savedInstanceState.putDouble("lunch", stopje.getLunchAllowance().doubleValue());
		savedInstanceState.putDouble("diner", stopje.getDinerAllowance().doubleValue());
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		on_blocks_date_view = (TextView) findViewById(R.id.dateViewOn);
		on_blocks_time_view = (TextView) findViewById(R.id.timeViewOn);
		off_blocks_date_view = (TextView) findViewById(R.id.dateViewOff);
		off_blocks_time_view = (TextView) findViewById(R.id.timeViewOff);
		output_view = (TextView) findViewById(R.id.output1);
		sundries_edittext = (EditText) findViewById(R.id.sundries);
		lunch_edittext = (EditText) findViewById(R.id.lunch);
		diner_edittext = (EditText) findViewById(R.id.diner);
		airport_edittext = (EditText) findViewById(R.id.airport);
		on_blocks_datetime = stopje.getOnBlocksDatetime();
		off_blocks_datetime = stopje.getOffBlocksDatetime();
		
		if (null != savedInstanceState) {
			on_blocks_datetime.set(Calendar.YEAR, savedInstanceState.getInt("onby"));
			on_blocks_datetime.set(Calendar.MONTH, savedInstanceState.getInt("onbmo"));
			on_blocks_datetime.set(Calendar.DAY_OF_MONTH, savedInstanceState.getInt("onbd"));
			on_blocks_datetime.set(Calendar.HOUR_OF_DAY, savedInstanceState.getInt("onbh"));
			on_blocks_datetime.set(Calendar.MINUTE, savedInstanceState.getInt("onbmi"));
			off_blocks_datetime.set(Calendar.YEAR, savedInstanceState.getInt("offby"));
			off_blocks_datetime.set(Calendar.MONTH, savedInstanceState.getInt("offbmo"));
			off_blocks_datetime.set(Calendar.DAY_OF_MONTH, savedInstanceState.getInt("offbd"));
			off_blocks_datetime.set(Calendar.HOUR_OF_DAY, savedInstanceState.getInt("offbh"));
			off_blocks_datetime.set(Calendar.MINUTE, savedInstanceState.getInt("offbmi"));
			airport = savedInstanceState.getString("airport");
			sundries = savedInstanceState.getDouble("sundries");
			lunch = savedInstanceState.getDouble("lunch");
			diner = savedInstanceState.getDouble("diner");
			stopje.setStopOver(airport, on_blocks_datetime, off_blocks_datetime, sundries, lunch, diner);
//			Toast.makeText(getApplicationContext(), "tataaa!", Toast.LENGTH_SHORT).show();
//			updateValues();
		}
		updateValues();
		Toast.makeText(getApplicationContext(), "created", Toast.LENGTH_SHORT).show();

        on_blocks_date_view.setOnClickListener(new TextView.OnClickListener() {
			@Override
			public void onClick(View v) {
				selected_datetime = on_blocks_datetime;
				selected_textview = on_blocks_date_view;
				selected_format = date_format;
//				showDatePickerDialog(v);
//				stopje.setOnBlocks(selected_datetime);
//				on_blocks_date_view.setText(date_format.format(stopje.getOnBlocksDatetime().getTime()));
				DialogFragment newFragment = new DatePickerFragment(selected_textview, selected_datetime, selected_format);
				newFragment.show(getSupportFragmentManager(), "datePicker");
//				updateValues();
//				Toast.makeText(getApplicationContext(), "onclickfinished", Toast.LENGTH_SHORT).show();
			}
		});
        
		on_blocks_time_view.setOnClickListener(new TextView.OnClickListener() {	
			@Override
			public void onClick(View v) {
				selected_datetime = on_blocks_datetime;
				selected_textview = on_blocks_time_view;
				selected_format = time_format;
//				showTimePickerDialog(v);
				DialogFragment newFragment = new TimePickerFragment(selected_textview, selected_datetime, selected_format);
				newFragment.show(getSupportFragmentManager(), "timePicker");
//				stopje.setOnBlocks(selected_datetime);
//				updateValues();
				
				
			}
		});
		
		off_blocks_date_view.setOnClickListener(new TextView.OnClickListener() {
			@Override
			public void onClick(View v) {
				selected_datetime = off_blocks_datetime;
				selected_textview = off_blocks_date_view;
				selected_format = date_format;
//				showDatePickerDialog(v);
				DialogFragment newFragment = new DatePickerFragment(selected_textview, selected_datetime, selected_format);
				newFragment.show(getSupportFragmentManager(), "datePicker");
//				stopje.setOffBlocks(selected_datetime);
//				updateValues();
			}
		});

		off_blocks_time_view.setOnClickListener(new TextView.OnClickListener() {
			@Override
			public void onClick(View v) {
				selected_datetime = off_blocks_datetime;
				selected_textview = off_blocks_time_view;
				selected_format = time_format;
//				showTimePickerDialog(v);
				DialogFragment newFragment = new TimePickerFragment(selected_textview, selected_datetime, selected_format);
				newFragment.show(getSupportFragmentManager(), "timePicker");
//				stopje.setOffBlocks(selected_datetime);
//				updateValues();
			}
		});
			
		sundries_edittext.setOnFocusChangeListener(new EditText.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
//				Toast.makeText(getApplicationContext(), v.toString(), Toast.LENGTH_SHORT).show();
				if(hasFocus) { return; }
				
//				if(sundries_edittext.getText().toString().contentEquals( "" )) {
//					stopje.setSundries(0d);
//				}
//				else {
//					stopje.setSundries(new Double(sundries_edittext.getText().toString()));
//				}
				updateValues();
			}
		});
		
		lunch_edittext.setOnFocusChangeListener(new EditText.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus) { return; }

//				if(lunch_edittext.getText().toString().contentEquals( "" )) {
//					stopje.setLunchAllowance(0d);
//				}
//				else {
//					stopje.setLunchAllowance(new Double(lunch_edittext.getText().toString()));
//				}
				updateValues();
			}
		});
		
		diner_edittext.setOnFocusChangeListener(new EditText.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus) { return; }

//				if(diner_edittext.getText().toString().contentEquals( "" )) {
//					stopje.setDinerAllowance(0d);
//				}
//				else {
//					stopje.setDinerAllowance(new Double(diner_edittext.getText().toString()));
//				}
				updateValues();
			}
		});
		
		airport_edittext.setOnFocusChangeListener(new EditText.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus) { return; }
				updateValues();
			}
		});
		
		((Button) findViewById(R.id.buttonOutput)).setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				testClickButton();
			}
		});
//		updateValues();
//		Toast.makeText(getApplicationContext(), date_format.format(stopje.getOffBlocksDatetime().getTime()), Toast.LENGTH_SHORT).show();
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//      Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
//    public void showDatePickerDialog(View v) {
//        DialogFragment newFragment = new DatePickerFragment(selected_textview, selected_datetime, selected_format);
//        newFragment.show(getSupportFragmentManager(), "datePicker");
//		Toast.makeText(getApplicationContext(), "showdatepickermethodcomplete", Toast.LENGTH_SHORT).show();
//		
//    }
    
//    public void showTimePickerDialog(View v, Calendar sldt) {
//        DialogFragment newFragment = new TimePickerFragment(stopje, sldt);
//        newFragment.show(getSupportFragmentManager(), "timePicker");
//    }

	 public void updateValues() {
		
//		stopje.setStopOver(airport, on_blocks_datetime, off_blocks_datetime, sundries.doubleValue(), lunch.doubleValue(), diner.doubleValue());
//
//		if(stopje.getSundries_24() <= 0) {
//			//sundries = BigDecimal.valueOf(0d);
//			sundries_edittext.setText("");
//		}
//		else {
//			sundries_edittext.setText(stopje.getSundries_24().toString());
//		}
//		
//		if(stopje.getLunchAllowance() <= 0) {
//			//lunch = BigDecimal.valueOf(0d);
//			lunch_edittext.setText("");
//		}
//		else {
//			lunch_edittext.setText(stopje.getLunchAllowance().toString());
//		}
//		
//		if(stopje.getDinerAllowance() <= 0) {
//			//diner = BigDecimal.valueOf(0d);
//			diner_edittext.setText("");
//		}
//		else {
//			diner_edittext.setText(stopje.getDinerAllowance().toString());
//		}
		
		//		lunch_edittext.setText(stopje.getLunchAllowance().toString());
//		diner_edittext.setText(stopje.getDinerAllowance().toString());
		
//		if(sundries_edittext.getText().toString().contentEquals( "" )) {
//			stopje.setSundries_24(0d);
//		}
//		else {
//			stopje.setSundries_24(new Double(sundries_edittext.getText().toString()));
//		}
//		
//		if(lunch_edittext.getText().toString().contentEquals( "" )) {
//			stopje.setLunchAllowance(0d);
//		}
//		else {
//			stopje.setLunchAllowance(new Double(lunch_edittext.getText().toString()));
//		}
//		
//		if(diner_edittext.getText().toString().contentEquals( "" )) {
//			stopje.setDinerAllowance(0d);
//		}
//		else {
//			stopje.setDinerAllowance(new Double(diner_edittext.getText().toString()));
//		}
//		
//		if(airport_edittext.getText().toString().contentEquals( "")) {
//			stopje.setAirport("");
//		}
//		else {
//			stopje.setAirport(airport_edittext.getText().toString());
//		}
		
		stopje.setOnBlocks(on_blocks_datetime);
		stopje.setOffBlocks(off_blocks_datetime);
		
		on_blocks_date_view.setText(date_format.format(stopje.getOnBlocksDatetime().getTime()));
		on_blocks_time_view.setText(time_format.format(stopje.getOnBlocksDatetime().getTime()));
		off_blocks_date_view.setText(date_format.format(stopje.getOffBlocksDatetime().getTime()));
		off_blocks_time_view.setText(time_format.format(stopje.getOffBlocksDatetime().getTime()));
		output_view.setText(stopje.getAllowance().toString());
		
//		output_view.setText(Double.toString(stopje.getAllowance()));
		
		//Toast.makeText(getApplicationContext(), "updated", Toast.LENGTH_SHORT).show();
//		Toast.makeText(getApplicationContext(), Double.toString(stopje.getAllowance()), Toast.LENGTH_SHORT).show();
	}

    public void testClickButton() {
		//String s = sundries_edittext.getText().toString();
		updateValues();
		if(sundries_edittext.getText().toString().contentEquals( "" )) {
			Toast.makeText(getApplicationContext(), "leeg", Toast.LENGTH_SHORT).show();
		}
		else {
			Toast.makeText(getApplicationContext(), "niet leeg", Toast.LENGTH_SHORT).show();
		}
    }
}
