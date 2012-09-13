package org.dronix.android.unisannio;

import org.dronix.android.unisannio.ing.IngHomeActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

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

		TextView ateneo_avvisi_tv = (TextView) findViewById(R.id.ateneo_avvisi);
		TextView ingegneria_avvisi_tv = (TextView) findViewById(R.id.ingegneria_avvisi);
		TextView giurisprudenza_avvisi_tv = (TextView) findViewById(R.id.giurisprudenza_avvisi);

		ateneo_avvisi_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				Intent gbNews = new Intent(HomeActivity.this, GBNewsActivity.class);
				gbNews.putExtra("TABNUMBER", 0);
				startActivity(gbNews);
			}
		});
		ingegneria_avvisi_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				Intent gbNews = new Intent(HomeActivity.this, GBNewsActivity.class);
				gbNews.putExtra("TABNUMBER", 1);
				startActivity(gbNews);
			}
		});
		giurisprudenza_avvisi_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				Intent gbNews = new Intent(HomeActivity.this, GBNewsActivity.class);
				gbNews.putExtra("TABNUMBER", 2);
				startActivity(gbNews);
			}
		});

		TextView ateneo_tv = (TextView) findViewById(R.id.ateneo);
		TextView ingegneria_tv = (TextView) findViewById(R.id.ingegneria);
		TextView giurisprudenza_tv = (TextView) findViewById(R.id.giurisprudenza);
		TextView sea_tv = (TextView) findViewById(R.id.sea);
		TextView scienze_tv = (TextView) findViewById(R.id.sci);

		ateneo_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ATENEO_URL));
				startActivity(browserIntent);
			}
		});
		ingegneria_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				Intent ing = new Intent(ac, IngHomeActivity.class);
				ac.startActivity(ing);
			}
		});
		giurisprudenza_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(GIURISPRUDENZA_URL));
				startActivity(browserIntent);
			}
		});
		sea_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(SEA_URL));
				startActivity(browserIntent);
			}
		});
		scienze_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(SCI_URL));
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
