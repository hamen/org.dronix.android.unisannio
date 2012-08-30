package org.dronix.android.unisannio;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class AvvisiIngFragment extends Fragment {
	private String URL = "http://www.ing.unisannio.it/avvisi/rss20.xml";
	private ArrayList<NewsIng> news;
	private NewsIngAdapter mAdapter;
	private PullToRefreshListView mPullRefreshListView;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tabtwo, container, false);
		mPullRefreshListView = (PullToRefreshListView) view.findViewById(R.id.pull_refresh_list);

		// Set a listener to be invoked when the list should be refreshed.
		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				mPullRefreshListView.setLastUpdatedLabel(DateUtils.formatDateTime(getActivity(),
						System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME
								| DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL));

				new NewsRetriever().execute();
			}
		});

		ListView actualListView = mPullRefreshListView.getRefreshableView();

		news = new ArrayList<NewsIng>();
		news.add(new NewsIng(getString(R.string.pull), "", null, null, ""));

		mAdapter = new NewsIngAdapter(getActivity(), news);

		actualListView.setAdapter(mAdapter);

		actualListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				Intent i = new Intent(getActivity(), NewsDetailActivity.class);
				i.putExtra("newsing", news.get(--position));
				startActivity(i);
			}
		});

		return view;
	}

	public List<NewsIng> getNews() {
		List<NewsIng> newsList = new ArrayList<NewsIng>();

		try {
			Document doc = Jsoup.connect(URL).timeout(10000).get();
			Elements newsItems = doc.select("item");

			for (Element e : newsItems) {
				String title = e.select("title").first().text();
				String description = e.select("description").first().text();
				String link = e.select("link").first().text();
				String pubDate = e.select("pubDate").first().text();

				newsList.add(new NewsIng(title, link, description, pubDate, ""));
			}

		} catch (SocketException e) {
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * for (News n : newsList) { Log.i("NEWS", n.getDate() + " " +
		 * n.getBody()); }
		 */
		return newsList;
	}

	class NewsRetriever extends AsyncTask<Void, Void, List<NewsIng>> {

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected List<NewsIng> doInBackground(Void... params) {
			return getNews();
		}

		@Override
		protected void onPostExecute(List<NewsIng> result) {
			if (result != null) {
				news.clear();
				news.addAll(result);
				mAdapter.notifyDataSetChanged();
			}
			mPullRefreshListView.onRefreshComplete();
		}
	}
}
