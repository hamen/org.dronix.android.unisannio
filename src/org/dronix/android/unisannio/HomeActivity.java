package org.dronix.android.unisannio;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HomeActivity extends Activity
{
	private static final String SEA_URL = "http://www.sea.unisannio.it";
	private static final String SCI_URL = "http://www.sci.unisannio.it";
	private static final String GIURISPRUDENZA_URL = "http://www.economia.unisannio.it";
	private static final String INGEGNERIA_URL = "http://www.ing.unisannio.it";
	private static final String ATENEO_URL = "http://www.unisannio.it";

	private HomeActivity ac;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		this.ac = this;

		ListView news_list = (ListView) findViewById(R.id.news_list);
		ListView portal_list = (ListView) findViewById(R.id.portal_list);

		// @verbatim
		final String[] news_labels = new String[] { 
				getString(R.string.ateneo),
				getString(R.string.ingegneria),
				getString(R.string.giurisprudenza)	
		};
		// @/verbatim

		// @verbatim
		final String[] portal_labels = new String[] { 
				getString(R.string.ateneo),
				getString(R.string.ingegneria),
				getString(R.string.giurisprudenza),
				getString(R.string.sea),
				getString(R.string.scienze)
		};
		// @/verbatim

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.home_news_list_row,
				R.id.item, news_labels);

		// Assign adapter to ListView
		news_list.setAdapter(adapter);

		news_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Intent gbNews = new Intent(HomeActivity.this, GBNewsActivity.class);
				gbNews.putExtra("TABNUMBER", position);
				startActivity(gbNews);
			}
		});

		ArrayAdapter<String> faculties_adapter = new ArrayAdapter<String>(this,
				R.layout.home_news_list_row, R.id.item, portal_labels);

		// Assign adapter to ListView
		portal_list.setAdapter(faculties_adapter);

		portal_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				String url = "";

				switch (position) {
				case 0:
					url = ATENEO_URL;
					break;
				case 1:
					url = INGEGNERIA_URL;
					break;
				case 2:
					url = GIURISPRUDENZA_URL;
					break;
				case 3:
					url = SEA_URL;
					break;
				case 4:
					url = SCI_URL;
					break;
				default:
					break;
				}
				
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				startActivity(browserIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}
}
