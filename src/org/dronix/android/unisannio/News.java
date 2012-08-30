package org.dronix.android.unisannio;

public class News {
	private String date;
	private String body;

	public News(String date, String body) {
		this.date = date;
		this.body = body;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
