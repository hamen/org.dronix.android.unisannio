package org.dronix.android.unisannio;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LazyAdapter extends BaseAdapter {

	private Activity activity;
	private List<News> data;
	private LayoutInflater inflater = null;

	public LazyAdapter(Activity a, List<News> Newss) {
		activity = a;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.data = Newss;
	}

	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.list_row, null);

		TextView body = (TextView) vi.findViewById(R.id.title);
		TextView date = (TextView) vi.findViewById(R.id.subtitle);
	
		News n = data.get(position);

		// Setting all values in listview
		body.setText(n.getBody());		
		date.setText(n.getDate());		
		return vi;
	}
}
