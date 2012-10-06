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
