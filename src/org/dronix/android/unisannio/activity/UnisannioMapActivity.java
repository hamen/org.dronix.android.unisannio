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

import java.util.ArrayList;
import java.util.List;

import org.dronix.android.unisannio.R;
import org.dronix.android.unisannio.UniMapItemizedOverlay;
import org.dronix.android.unisannio.UniPoint;
import org.dronix.android.unisannio.UnisannioMaps;
import org.dronix.android.unisannio.R.drawable;
import org.dronix.android.unisannio.R.id;
import org.dronix.android.unisannio.R.layout;
import org.dronix.android.unisannio.R.menu;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class UnisannioMapActivity extends MapActivity
{
	private MapView mapView = null;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_uni_map);
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(false);

		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.grad_hat);
		UniMapItemizedOverlay itemizedoverlay = new UniMapItemizedOverlay(drawable, this);

		List<UniPoint> points = new ArrayList<UniPoint>();

		Bundle extras = getIntent().getExtras();
		int faculty = -1;
		if (extras != null)
		{
			faculty = extras.getInt("faculty");
		}

		switch (faculty) {
		case 0:
			points = UnisannioMaps.INSTANCE.getAteneo();
			break;
		case 1:
			points = UnisannioMaps.INSTANCE.getIngegneria();
			break;
		default:
			break;
		}

		MapController mc = mapView.getController();
		mc.animateTo(points.get(0).getGeoPoint());
		mc.setZoom(19);

		for (UniPoint point : points)
		{
			OverlayItem overlayitem = new OverlayItem(point.getGeoPoint(), point.getName(),
					point.getAddress());
			itemizedoverlay.addOverlay(overlayitem);
			mapOverlays.add(itemizedoverlay);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_uni_map, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.menu_satellite:
			mapView.setSatellite(!mapView.isSatellite());
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected boolean isRouteDisplayed()
	{
		return false;
	}
}
