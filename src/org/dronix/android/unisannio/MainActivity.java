package org.dronix.android.unisannio;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity {

	FragmentTransaction transaction;
	static ViewPager mViewPager;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.i("ANDROID API VERSION", String.valueOf(Build.VERSION.SDK_INT));

		if (Build.VERSION.SDK_INT < 11) {
			setContentView(R.layout.activity_main_old);

			String[] feeds = { "Avvisi Ateneo", "Avvisi Ingegneria", "Avvisi Giurisprudenza" };

			ListView feedList = (ListView) findViewById(R.id.feed_list);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, feeds);
			feedList.setAdapter(adapter);
			feedList.setTextFilterEnabled(true);

			feedList.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					Intent gbNews = new Intent(MainActivity.this, GBNewsActivity.class);
					gbNews.putExtra("TABNUMBER", position);
					startActivity(gbNews);					
				}
			});
		}
		if (Build.VERSION.SDK_INT >= 11) {
			setContentView(R.layout.main);
			Fragment tabOneFragment = new TabOne();
			Fragment tabTwoFragment = new TabTwo();
			Fragment tabThreeFragment = new TabThree();

			PagerAdapter mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
			mPagerAdapter.addFragment(tabOneFragment);
			mPagerAdapter.addFragment(tabTwoFragment);
			mPagerAdapter.addFragment(tabThreeFragment);

			// transaction = getSupportFragmentManager().beginTransaction();

			mViewPager = (ViewPager) findViewById(R.id.pager);
			mViewPager.setAdapter(mPagerAdapter);
			mViewPager.setOffscreenPageLimit(2);
			mViewPager.setCurrentItem(0);

			mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
				@Override
				public void onPageSelected(int position) {
					// When swiping between pages, select the
					// corresponding tab.
					getActionBar().setSelectedNavigationItem(position);
				}
			});

			ActionBar ab = getActionBar();
			ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			Tab tab1 = ab.newTab().setText(getString(R.string.tabone))
					.setTabListener(new TabListener<TabOne>(this, "tabone", TabOne.class));

			Tab tab2 = ab.newTab().setText(getString(R.string.tabtwo))
					.setTabListener(new TabListener<TabTwo>(this, "tabtwo", TabTwo.class));

			Tab tab3 = ab.newTab().setText(getString(R.string.tabthree))
					.setTabListener(new TabListener<TabThree>(this, "tabtree", TabThree.class));

			ab.addTab(tab1);
			ab.addTab(tab2);
			ab.addTab(tab3);
		}
	}

	public static class TabListener<T extends Fragment> implements ActionBar.TabListener {
		private Fragment mFragment;
		private final Activity mActivity;
		private final String mTag;
		private final Class<T> mClass;

		/**
		 * Constructor used each time a new tab is created.
		 * 
		 * @param activity
		 *            The host Activity, used to instantiate the fragment
		 * @param tag
		 *            The identifier tag for the fragment
		 * @param clz
		 *            The fragment's Class, used to instantiate the fragment
		 */
		public TabListener(Activity activity, String tag, Class<T> clz) {
			mActivity = activity;
			mTag = tag;
			mClass = clz;
		}

		/* The following are each of the ActionBar.TabListener callbacks */

		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// Check if the fragment is already initialized
			if (mFragment == null) {
				// If not, instantiate and add it to the activity
				mFragment = Fragment.instantiate(mActivity, mClass.getName());
				ft.add(android.R.id.content, mFragment, mTag);
			} else {
				// If it exists, simply attach it in order to show it
				ft.attach(mFragment);
			}
		}

		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			if (mFragment != null) {
				// Detach the fragment, because another one is being attached
				ft.detach(mFragment);
			}
		}

		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// User selected the already selected tab. Usually do nothing.
		}

		public void onTabReselected(Tab arg0, android.app.FragmentTransaction arg1) {
			// TODO Auto-generated method stub

		}

		public void onTabSelected(Tab arg0, android.app.FragmentTransaction arg1) {
			// TODO Auto-generated method stub
			mViewPager.setCurrentItem(arg0.getPosition());
		}

		public void onTabUnselected(Tab arg0, android.app.FragmentTransaction arg1) {
			// TODO Auto-generated method stub

		}
	}

	public class PagerAdapter extends FragmentPagerAdapter {

		private final ArrayList<Fragment> mFragments = new ArrayList<Fragment>();

		public PagerAdapter(FragmentManager manager) {
			super(manager);
		}

		public void addFragment(Fragment fragment) {
			mFragments.add(fragment);
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return mFragments.size();
		}

		@Override
		public Fragment getItem(int position) {
			return mFragments.get(position);
		}
	}

}
