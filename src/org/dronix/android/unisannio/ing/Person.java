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
package org.dronix.android.unisannio.ing;

public class Person
{
	private String name;
	private String qualifica;
	private String address;
	private String pbx;
	private String fax;
	private String url;

	public Person(String name, String qualifica, String address, String pbx, String fax, String url) {
		this.name = name;
		this.qualifica = qualifica;
		this.address = address;
		this.pbx = pbx;
		this.fax = fax;
		this.url = url;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getQualifica()
	{
		return qualifica;
	}

	public void setQualifica(String qualifica)
	{
		this.qualifica = qualifica;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getPbx()
	{
		return pbx;
	}

	public void setPbx(String pbx)
	{
		this.pbx = pbx;
	}

	public String getFax()
	{
		return fax;
	}

	public void setFax(String fax)
	{
		this.fax = fax;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

}
