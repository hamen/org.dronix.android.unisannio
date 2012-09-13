package org.dronix.android.unisannio.ing;

import java.util.List;

import org.dronix.android.unisannio.News;
import org.dronix.android.unisannio.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LazyAdapter extends BaseAdapter {

	private Activity activity;
	private List<Person> data;
	private LayoutInflater inflater = null;

	public LazyAdapter(Activity a, List<Person> people) {
		activity = a;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.data = people;
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
			vi = inflater.inflate(R.layout.activity_ingcercapersone_list_row, null);

		TextView name = (TextView) vi.findViewById(R.id.name);
		TextView qualifica = (TextView) vi.findViewById(R.id.qualifica);
		TextView address = (TextView) vi.findViewById(R.id.address);
		TextView pbx = (TextView) vi.findViewById(R.id.pbx);
		TextView fax = (TextView) vi.findViewById(R.id.fax);
		TextView homepage = (TextView) vi.findViewById(R.id.homepage);

		Person p = data.get(position);

		name.setText(p.getName());
		qualifica.setText(p.getQualifica());
		address.setText(p.getAddress());
		pbx.setText(p.getPbx());
		fax.setText(p.getFax());
		homepage.setText(p.getUrl());
		
		return vi;
	}
}
