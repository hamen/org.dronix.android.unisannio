package org.dronix.android.unisannio.activity;

import org.dronix.android.unisannio.fragment.AvvisiIngFragment;
import org.dronix.android.unisannio.fragment.TabOne;
import org.dronix.android.unisannio.fragment.TabThree;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

public class GBNewsActivity extends FragmentActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int tabnumber = -1;
		if (savedInstanceState == null) {
			Bundle extras = getIntent().getExtras();
			if (extras == null) {
				tabnumber = -1;
			} else {
				tabnumber = extras.getInt("TABNUMBER");
			}
		}
		Fragment myFragment = null;
		switch (tabnumber) {
		case 0:
			myFragment = new TabOne();
			break;
		case 1:
			myFragment = new AvvisiIngFragment();
			break;
		case 2:
			myFragment = new TabThree();
			break;
		}

		if (tabnumber != -1) {
			// Create a frame layout, so we can add fragments to it.
			FrameLayout layout = new FrameLayout(this);
			layout.setId(0x1234);

			// Set the frame layout as the view container for this activity.
			setContentView(layout);

			// Create and add a fragment to frame layout created above.
			FragmentTransaction t = getSupportFragmentManager().beginTransaction();
			t.add(layout.getId(), myFragment, "myFirstFragment");
			t.commit();
		}
	}
}
