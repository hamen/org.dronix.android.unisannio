package org.dronix.android.unisannio;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class NewsDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newsdetail);

		Bundle b = getIntent().getExtras();
		NewsIng news = b.getParcelable("newsing");
		
		TextView title = (TextView) findViewById(R.id.title);
		WebView description = (WebView) findViewById(R.id.description);
		TextView pubDate = (TextView) findViewById(R.id.date);
		
		pubDate.setText(news.getPubDate());
		title.setText(news.getTitle());
		description.loadData(news.getDescription(), "text/html", null);
	}
}
