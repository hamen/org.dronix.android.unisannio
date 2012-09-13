package org.dronix.android.unisannio;

import java.util.ArrayList;
import java.util.List;


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
