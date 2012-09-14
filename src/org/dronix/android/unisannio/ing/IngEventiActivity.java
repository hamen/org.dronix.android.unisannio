package org.dronix.android.unisannio.ing;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.dronix.android.unisannio.R;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;

public class IngEventiActivity extends Activity
{

	public static final String EVENTI_URL = "http://www.ing.unisannio.it/eventi/index.html";
	public static final String TAG = "UNISANNIO";
	public ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ing_eventi);

		new EventiRetriever().execute();
	}

	private class EventiRetriever extends AsyncTask<Void, Void, String>
	{

		@Override
		protected void onPreExecute()
		{
			progressDialog = new ProgressDialog(IngEventiActivity.this);
			progressDialog.setMessage(getString(R.string.loading));
			progressDialog.setIndeterminate(true);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(Void... params)
		{
			Document doc = null;
			Elements div = null;
			try
			{
				doc = Jsoup.connect(EVENTI_URL).timeout(20 * 1000).get();
			} catch (IOException e)
			{
				e.printStackTrace();
			}

			if (doc != null)
			{
				div = doc.select("#contenuto");
				if (div.first() != null)
					return div.first().html();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result)
		{
			WebView eventi_wv = (WebView) findViewById(R.id.eventi);
			eventi_wv.loadDataWithBaseURL("http://www.ing.unisannio.it/eventi/", result,
					"text/html", "UTF-8", null);
			progressDialog.dismiss();
		}

	}
}
