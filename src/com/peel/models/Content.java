package com.peel.models;

import java.util.List;

public class Content {
	
	private String contentName;
	private List<String> preRollNamesList;
	private List<Video> contentVideoList;
	
	public String getContentName() {
		return contentName;
	}
	
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	
	public List<String> getPreRollNamesList() {
		return preRollNamesList;
	}

	public void setPreRollNamesList(List<String> preRollNamesList) {
		this.preRollNamesList = preRollNamesList;
	}

	public List<Video> getContentVideoList() {
		return contentVideoList;
	}
	
	public void setContentVideoList(List<Video> contentVideoList) {
		this.contentVideoList = contentVideoList;
	}

	@Override
	public String toString() {
		return "Content [contentName=" + contentName + ", preRollNamesList=" + preRollNamesList + ", contentVideoList="
				+ contentVideoList + "]";
	}

	
}
