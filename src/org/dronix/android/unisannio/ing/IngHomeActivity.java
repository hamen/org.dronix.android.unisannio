package org.dronix.android.unisannio.ing;

import org.dronix.android.unisannio.GBNewsActivity;
import org.dronix.android.unisannio.R;
import org.dronix.android.unisannio.UnisannioMapActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class IngHomeActivity extends Activity
{
	private IngHomeActivity ac;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inghome);
		this.ac = this;

		// @verbatim
		String [] features_labels = {
				getString(R.string.avvisi),
				getString(R.string.mappa),
				getString(R.string.cercapersone),
				getString(R.string.eventi),
				getString(R.string.aule),
				getString(R.string.galleria),
				getString(R.string.contatti)
		};
		// @/verbatim

		ListView feature_list = (ListView) findViewById(R.id.feature_list);
		ArrayAdapter<String> faculties_adapter = new ArrayAdapter<String>(this,
				R.layout.home_news_list_row, R.id.item, features_labels);

		feature_list.setAdapter(faculties_adapter);

		feature_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				switch (position) {
				case 0:
					Intent gbNews = new Intent(ac, GBNewsActivity.class);
					gbNews.putExtra("TABNUMBER", 1);
					startActivity(gbNews);
					break;
				case 1:
					Intent ingMap = new Intent(ac, UnisannioMapActivity.class);
					ingMap.putExtra("faculty", 1);
					startActivity(ingMap);
					break;
				case 3:
					Intent eventi = new Intent(ac, IngEventiActivity.class);
					ac.startActivity(eventi);
					break;
				case 4:
				case 5:
					comingSoon();
					break;
				case 2:
					Intent cercaPersone = new Intent(ac, IngCercaPersone.class);
					ac.startActivity(cercaPersone);
					break;
				default:
					break;
				}
			}

			private void comingSoon()
			{
				Toast.makeText(ac, "Coming soon", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
