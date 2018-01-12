package com.peel.models;

public final class Video {
	
	private final String videoName;
	private final Attribute videoAttribute;
	
	public Video(String videoName, Attribute videoAttribute) {
		this.videoName = videoName;
		this.videoAttribute = videoAttribute;
	}

	public String getVideoName() {
		return videoName;
	}
	
	public Attribute getVideoAttribute() {
		return videoAttribute;
	}
	
	@Override
	public String toString() {
		return "Video [videoName=" + videoName + ", videoAttribute=" + videoAttribute + "]";
	}
}
