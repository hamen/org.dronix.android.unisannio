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
