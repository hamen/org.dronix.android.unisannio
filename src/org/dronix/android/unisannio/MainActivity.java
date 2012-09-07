package org.dronix.android.unisannio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.SimpleExpandableListAdapter;

@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity
{

	FragmentTransaction transaction;
	static ViewPager mViewPager;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		Log.i("ANDROID API VERSION", String.valueOf(Build.VERSION.SDK_INT));

		if (Build.VERSION.SDK_INT < 11)
		{
			setContentView(R.layout.activity_main_old);

			ExpandableListView feedList = (ExpandableListView) findViewById(R.id.list);

			SimpleExpandableListAdapter expListAdapter = new SimpleExpandableListAdapter(this,
					createGroupList(), R.layout.group_row, new String[] { "Group Item" },
					new int[] { R.id.row_name }, createChildList(), R.layout.child_row,
					new String[] { "Sub Item" }, new int[] { R.id.grp_child });

			feedList.setAdapter(expListAdapter);
			feedList.setTextFilterEnabled(true);

			feedList.setOnChildClickListener(new OnChildClickListener() {
				public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
						int childPosition, long id)
				{
					Intent gbNews = new Intent(MainActivity.this, GBNewsActivity.class);
					gbNews.putExtra("TABNUMBER", childPosition);
					startActivity(gbNews);
					return false;
				}
			});
		}
		if (Build.VERSION.SDK_INT >= 11)
		{
			setContentView(R.layout.main);
			Fragment tabOneFragment = new TabOne();
			Fragment tabTwoFragment = new AvvisiIngFragment();
			Fragment tabThreeFragment = new TabThree();

			PagerAdapter mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
			mPagerAdapter.addFragment(tabOneFragment);
			mPagerAdapter.addFragment(tabTwoFragment);
			mPagerAdapter.addFragment(tabThreeFragment);

			mViewPager = (ViewPager) findViewById(R.id.pager);
			mViewPager.setAdapter(mPagerAdapter);
			mViewPager.setOffscreenPageLimit(2);
			mViewPager.setCurrentItem(0);

			mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
				@Override
				public void onPageSelected(int position)
				{
					// When swiping between pages, select the
					// corresponding tab.
					getActionBar().setSelectedNavigationItem(position);
				}
			});

			ActionBar ab = getActionBar();
			ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			Tab tab1 = ab.newTab().setText(getString(R.string.ateneo))
					.setTabListener(new TabListener<TabOne>(this, "tabone", TabOne.class));

			Tab tab2 = ab
					.newTab()
					.setText(getString(R.string.tabtwo))
					.setTabListener(
							new TabListener<AvvisiIngFragment>(this, "tabtwo",
									AvvisiIngFragment.class));

			Tab tab3 = ab.newTab().setText(getString(R.string.tabthree))
					.setTabListener(new TabListener<TabThree>(this, "tabtree", TabThree.class));

			ab.addTab(tab1);
			ab.addTab(tab2);
			ab.addTab(tab3);
		}
	}

	public static class TabListener<T extends Fragment> implements ActionBar.TabListener
	{
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

		public void onTabSelected(Tab tab, FragmentTransaction ft)
		{
			// Check if the fragment is already initialized
			if (mFragment == null)
			{
				// If not, instantiate and add it to the activity
				mFragment = Fragment.instantiate(mActivity, mClass.getName());
				ft.add(android.R.id.content, mFragment, mTag);
			} else
			{
				// If it exists, simply attach it in order to show it
				ft.attach(mFragment);
			}
		}

		public void onTabUnselected(Tab tab, FragmentTransaction ft)
		{
			if (mFragment != null)
			{
				// Detach the fragment, because another one is being attached
				ft.detach(mFragment);
			}
		}

		public void onTabReselected(Tab tab, FragmentTransaction ft)
		{
			// User selected the already selected tab. Usually do nothing.
		}

		public void onTabReselected(Tab arg0, android.app.FragmentTransaction arg1)
		{

		}

		public void onTabSelected(Tab arg0, android.app.FragmentTransaction arg1)
		{
			mViewPager.setCurrentItem(arg0.getPosition());
		}

		public void onTabUnselected(Tab arg0, android.app.FragmentTransaction arg1)
		{

		}
	}

	public class PagerAdapter extends FragmentPagerAdapter
	{

		private final ArrayList<Fragment> mFragments = new ArrayList<Fragment>();

		public PagerAdapter(FragmentManager manager) {
			super(manager);
		}

		public void addFragment(Fragment fragment)
		{
			mFragments.add(fragment);
			notifyDataSetChanged();
		}

		@Override
		public int getCount()
		{
			return mFragments.size();
		}

		@Override
		public Fragment getItem(int position)
		{
			return mFragments.get(position);
		}
	}

	private List<HashMap<String, String>> createGroupList()
	{
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> m = new HashMap<String, String>();
		m.put("Group Item", getString(R.string.avvisi));
		result.add(m);

		return (List<HashMap<String, String>>) result;
	}

	private List<ArrayList<HashMap<String, String>>> createChildList()
	{

		ArrayList<ArrayList<HashMap<String, String>>> result = new ArrayList<ArrayList<HashMap<String, String>>>();
		for (int i = 0; i < 15; ++i)
		{

			String[] feeds = { getString(R.string.ateneo), getString(R.string.ingegneria),
					getString(R.string.giurisprudenza) };

			ArrayList<HashMap<String, String>> secList = new ArrayList<HashMap<String, String>>();
			for (int n = 0; n < 3; n++)
			{
				HashMap<String, String> child = new HashMap<String, String>();
				child.put("Sub Item", feeds[n]);
				secList.add(child);
			}
			result.add(secList);
		}
		return result;
	}
}
