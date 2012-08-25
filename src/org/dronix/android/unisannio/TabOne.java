package org.dronix.android.unisannio;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class TabOne extends Fragment {
	private String URL = "http://www.unisannio.it/notizie/avvisi/index.php";
	private ArrayList<News> news;
	private LazyAdapter mAdapter;
	private PullToRefreshListView mPullRefreshListView;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tabone, container, false);
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

		news = new ArrayList<News>();
		news.add(new News("Tira", ""));

		mAdapter = new LazyAdapter(getActivity(), news);

		actualListView.setAdapter(mAdapter);
		return view;
	}

	public List<News> getNews() {
		List<News> newsList = new ArrayList<News>();

		try {
			Document doc = Jsoup.connect(URL).timeout(10000).get();
			Elements newsItems = doc.select("div.meta > table > tbody > tr");

			for (int i = 2; i < newsItems.size(); i++) {
				String date = null;
				Element dateElement = newsItems.get(i).select("p").first();
				if (dateElement != null) {
					date = dateElement.text();
				}

				String body = null;
				Element bodyElement = newsItems.get(i).select("a").first();
				if (bodyElement != null) {
					body = bodyElement.text();
				}

				if (date != null && body != null)
					newsList.add(new News(date, body));
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

	class NewsRetriever extends AsyncTask<Void, Void, List<News>> {

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected List<News> doInBackground(Void... params) {
			return getNews();
		}

		@Override
		protected void onPostExecute(List<News> result) {
			if (result != null) {
				news.clear();
				news.addAll(result);
				mAdapter.notifyDataSetChanged();
			}
			mPullRefreshListView.onRefreshComplete();
		}
	}
}
