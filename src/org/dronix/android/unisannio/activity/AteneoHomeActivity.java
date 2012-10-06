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
package org.dronix.android.unisannio.activity;

import org.dronix.android.unisannio.R;
import org.dronix.android.unisannio.R.id;
import org.dronix.android.unisannio.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class AteneoHomeActivity extends Activity
{
	private AteneoHomeActivity ac;
	private static final String ATENEO_URL = "http://www.unisannio.it";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ateneo_home);
		this.ac = this;

		TextView avvisi_tv = (TextView) findViewById(R.id.ateneo_avvisi);
		avvisi_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				Intent gbNews = new Intent(ac, GBNewsActivity.class);
				gbNews.putExtra("TABNUMBER", 0);
				startActivity(gbNews);
			}
		});

		TextView website_tv = (TextView) findViewById(R.id.website);
		website_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ATENEO_URL));
				startActivity(browserIntent);
			}
		});

		TextView map_tv = (TextView) findViewById(R.id.ateneo_map);
		map_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				Intent ateneoMap = new Intent(ac, UnisannioMapActivity.class);
				ateneoMap.putExtra("faculty", 0);
				startActivity(ateneoMap);
			}
		});
	}
}
