package com.peel.models;

import java.util.List;

public class Content {
	
	private final String contentName;
	private final List<String> preRollNamesList;
	private final List<Video> contentVideoList;
	
	public Content(String contentName, List<String> preRollNamesList, List<Video> contentVideoList) {
		
		this.contentName = contentName;
		this.preRollNamesList = preRollNamesList;
		this.contentVideoList = contentVideoList;
	}

	public String getContentName() {
		return contentName;
	}
	
	public List<String> getPreRollNamesList() {
		return preRollNamesList;
	}

	public List<Video> getContentVideoList() {
		return contentVideoList;
	}
	
	@Override
	public String toString() {
		return "Content [contentName=" + contentName + ", preRollNamesList=" + preRollNamesList + ", contentVideoList="
				+ contentVideoList + "]";
	}

	
}
