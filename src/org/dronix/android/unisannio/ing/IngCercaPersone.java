/*******************************************************************************
 * Copyright 2012 Ivan Morgillo
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.dronix.android.unisannio.ing;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.dronix.android.unisannio.R;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

public class IngCercaPersone extends Activity
{
	private static final String URL = "http://www.ing.unisannio.it/cerca_persone/risultato.php";
	private static final String TAG = "UNISANNIO";

	public List<Person> people = new ArrayList<Person>();

	public ProgressDialog progressDialog;
	private IngCercaPersone ac;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ingcercapersone);
		this.ac = this;

		EditText name_input = (EditText) findViewById(R.id.name_input);
		name_input.setSelected(true);

		Button search_button = (Button) findViewById(R.id.search);

		search_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				EditText name_input = (EditText) findViewById(R.id.name_input);
				String name = name_input.getText().toString();

				EditText lastname_input = (EditText) findViewById(R.id.lastname_input);
				String lastname = lastname_input.getText().toString();

				if (name != null && lastname != null)
				{

					String[] params = { name.trim(), lastname.trim() };
					try
					{
						people = new PeopleRetriever().execute(params).get();

						if (people != null)
						{
							LinearLayout form = (LinearLayout) findViewById(R.id.form);
							form.setVisibility(View.GONE);

							ListView result = (ListView) findViewById(R.id.result);
							LazyAdapter adapter = new LazyAdapter(ac, people);
							result.setAdapter(adapter);

						}
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					} catch (ExecutionException e)
					{
						e.printStackTrace();
					}
				}
			}
		});
	}

	private class PeopleRetriever extends AsyncTask<String, Void, List<Person>>
	{
		@Override
		protected void onPreExecute()
		{
			progressDialog = new ProgressDialog(IngCercaPersone.this);
			progressDialog.setMessage(getString(R.string.searching));
			progressDialog.setIndeterminate(true);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected List<Person> doInBackground(String... params)
		{
			List<Person> peopleList = new ArrayList<Person>();

			Document doc = null;
			try
			{
				//@verbatim
				Response res = null;
				try
				{
					res = Jsoup.connect(URL).timeout(10 * 1000)
							.data("cerca", "Cerca")
							.data("nome", params[0])
							.data("cognome", params[1])
							.data("pbx", "%%")
							.data("qualifica", "%%")
							.data("ufficio", "%%")
							.method(Method.POST).timeout(20 * 1000).execute();
				} catch (SocketTimeoutException ste)
				{
					return null;
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//@/verbatim
				if (res != null)
				{
					doc = res.parse();
					Element contenuto = doc.select("#contenuto").first();
					if (contenuto != null)
					{
						Elements p = contenuto.select("p:not(.pcentrato)");

						if (p != null)
						{
							for (Element person : p)
							{
								Element strong = person.select("strong").first();

								if (strong != null)
								{
									String name = strong.text();
									String qualifica = person.select("em").first().text()
											.replace("- ", "");
									String description = person.text().replace(name, "")
											.replace(qualifica, "");

									String[] tokens = description.split("pbx");
									String address = tokens[0].replace("-  ", "").trim();

									tokens = tokens[1].replace(address, "").split("fax");
									String pbx = tokens[0].trim();

									// Log.i(TAG, name);
									// Log.i(TAG, qualifica);
									// Log.i(TAG, address);
									// Log.i(TAG, pbx);

									tokens = tokens[1].replace(pbx, "").split("email");
									String fax = tokens[0].trim();
									// Log.i(TAG, fax);

									// Log.i(TAG, tokens[0] + " ------- " +
									// tokens[1]);
									String email = tokens[1].trim().replace(" [at] ", "@")
											.replace(" - Home Page", "");
									// Log.i(TAG, email);

									Element homepage = person.select("a").first();
									String url = null;
									if (homepage != null)
									{
										url = homepage.attr("href");
										// Log.i(TAG, url);
									}
									peopleList.add(new Person(name, qualifica, address, pbx, fax,
											url));
								}
							}
						}
					}
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			return peopleList;
		}

		protected void onPostExecute(List<Person> result)
		{
			progressDialog.dismiss();
		}
	}
}
