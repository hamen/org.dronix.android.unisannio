package org.dronix.android.unisannio;

import com.google.android.maps.GeoPoint;

public class UniPoint
{

	private String faculty;
	private String name;
	private String address;
	private double lat;
	private double lng;
	private GeoPoint geopoint;

	public UniPoint(String namefaculty, String name, String address, double lat, double lng) {
		this.faculty = namefaculty;
		this.name = name;
		this.address = address;
		this.lat = lat;
		this.lng = lng;
		geopoint = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
	}

	public String getFaculty()
	{
		return faculty;
	}

	public String getName()
	{
		return name;
	}

	public String getAddress()
	{
		return address;
	}

	public double getLat()
	{
		return lat;
	}

	public double getLng()
	{
		return lng;
	}

	public GeoPoint getGeoPoint() {
		return geopoint;
	}
}
