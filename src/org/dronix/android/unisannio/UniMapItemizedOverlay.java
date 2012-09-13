package org.dronix.android.unisannio;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class UniMapItemizedOverlay extends ItemizedOverlay
{
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context mContext;

	private static final int FONT_SIZE = 20;
	private static final int TITLE_MARGIN = 3;
	private int markerHeight;

	public UniMapItemizedOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		mContext = context;
	}

	@Override
	protected OverlayItem createItem(int i)
	{
		return mOverlays.get(i);
	}

	@Override
	public int size()
	{
		return mOverlays.size();
	}

	public void addOverlay(OverlayItem overlay)
	{
		mOverlays.add(overlay);
		populate();
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow)
	{
		if (!shadow)
		{
			super.draw(canvas, mapView, false);
		}

		// go through all OverlayItems and draw title for each of them
		for (OverlayItem item : mOverlays)
		{
			/*
			 * Converts latitude & longitude of this overlay item to coordinates
			 * on screen. As we have called boundCenterBottom() in constructor,
			 * so these coordinates will be of the bottom center position of the
			 * displayed marker.
			 */
			GeoPoint point = item.getPoint();
			Point markerBottomCenterCoords = new Point();
			mapView.getProjection().toPixels(point, markerBottomCenterCoords);

			/* Find the width and height of the title */
			TextPaint paintText = new TextPaint();
			Paint paintRect = new Paint();

			Rect rect = new Rect();
			paintText.setTextSize(FONT_SIZE);
			paintText.getTextBounds(item.getTitle(), 0, item.getTitle().length(), rect);

			rect.inset(-TITLE_MARGIN, -TITLE_MARGIN);
			rect.offsetTo(markerBottomCenterCoords.x - rect.width() / 2,
					(markerBottomCenterCoords.y + 20) - markerHeight - rect.height());

			paintText.setTextAlign(Paint.Align.CENTER);
			paintText.setTextSize(FONT_SIZE);
			paintText.setARGB(255, 23, 97, 160);
			paintRect.setARGB(255, 253, 190, 23);

			canvas.drawRoundRect(new RectF(rect), 2, 2, paintRect);
			canvas.drawText(item.getTitle(), rect.left + rect.width() / 2, rect.bottom
					- TITLE_MARGIN, paintText);
		}
	}

	@Override
	protected boolean onTap(int index)
	{
		OverlayItem item = mOverlays.get(index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		dialog.setTitle(item.getTitle());
		dialog.setMessage(item.getSnippet());
		dialog.show();
		return true;
	}
}
