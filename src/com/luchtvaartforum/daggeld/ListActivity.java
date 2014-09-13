package com.luchtvaartforum.daggeld;

import android.view.View;
import android.view.ViewGroup;
import android.app.*;
import android.text.*;
import android.os.Bundle;
import android.app.*;
import android.text.*;
import android.widget.ListView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.view.LayoutInflater;



public class ListActivity extends Activity
{
	
	static String[] words = {"one", "two", "three"};
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		ListView listView = (ListView) findViewById(R.id.ListView01);
        listView.setAdapter(new BaseAdapter(){
			
				public int getCount() {
					return words.length;
				}

				public Object getItem(int position) {
					return words[position];
				}

				public long getItemId(int position) {
					return position;
				}

				public View getView(int position, View convertView, ViewGroup parent) {
					LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
					View view = inflater.inflate(R.layout.overview_row, null);
					TextView textView = (TextView) view.findViewById(R.id.TextView01);
					textView.setText(words[position]);
					return view;
			}});
		}
}
