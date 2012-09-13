package org.dronix.android.unisannio;

import java.util.ArrayList;
import java.util.List;

public enum UnisannioMaps {
	INSTANCE;

	public List<UniPoint> getIngegneria()
	{
		List<UniPoint> points = new ArrayList<UniPoint>();

		final String faculty = "Ingegneria";

		// Palazzo Giannone
		double lat = 41.1309567866226;
		double lng = 14.777568876743317;
		points.add(new UniPoint(faculty, "Palazzo Giannone", "Piazza Roma", lat, lng));

		// Palazzo Bosco Lucarelli
		lat = 41.13122346060738;
		lng = 14.778067767620087;
		points.add(new UniPoint(faculty, "Palazzo Bosco Lucarelli", "Corso Garibaldi, #", lat, lng));

		// Dipartimento
		lat = 41.13057495607544;
		lng = 14.777654707431793;
		points.add(new UniPoint(faculty, "Dipartimento", "Piazza Roma", lat, lng));

		// San Vittorino
		lat = 41.129754719978685;
		lng = 14.778901934623718;
		points.add(new UniPoint(faculty, "San Vittorino", "Via Tenente Pellegrini", lat, lng));
		
		// Labis
		lat = 41.130075946666736;
		lng = 14.7776198387146;
		points.add(new UniPoint(faculty, "Labis", "Via Odofredo", lat, lng));
		return points;
	}
		return points;
	}
}
