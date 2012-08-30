package org.dronix.android.unisannio;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsIng implements Parcelable {
	
	public static final Parcelable.Creator<NewsIng> CREATOR = new Parcelable.Creator<NewsIng>() {
		public NewsIng createFromParcel(Parcel in) {
			return new NewsIng(in);
		}

		public NewsIng[] newArray(int size) {
			return new NewsIng[size];
		}
	};
	
	private String title;
	private String link;
	private String description;
	private String pubDate;
	private String author;

	public NewsIng(String title, String link, String description, String pubDate, String author) {

		this.title = title;
		this.link = link;
		this.description = description;
		this.pubDate = pubDate;
		this.setAuthor(author);
	}

	public NewsIng(Parcel in) {
		readFromParcel(in);
	}

	public NewsIng() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(title);
		out.writeString(link);
		out.writeString(description);
		out.writeString(pubDate);
		out.writeString(author);
	}

	private void readFromParcel(Parcel in) {
		title = in.readString();
		link = in.readString();
		description = in.readString();
		pubDate = in.readString();
		author = in.readString();
	}

}
