package com.peel.models;

public class Video {
	
	private String videoName;
	private Attribute videoAttribute;
	
	public String getVideoName() {
		return videoName;
	}
	
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	
	public Attribute getVideoAttribute() {
		return videoAttribute;
	}
	
	public void setVideoAttribute(Attribute videoAttribute) {
		this.videoAttribute = videoAttribute;
	}

	@Override
	public String toString() {
		return "Video [videoName=" + videoName + ", videoAttribute=" + videoAttribute + "]";
	}
	
	
	
}
