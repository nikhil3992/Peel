package com.peel.models;

import java.util.List;

public class PreRoll {
	
	private String name;
	private List<Video> preRollVideoList;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Video> getPreRollVideoList() {
		return preRollVideoList;
	}
	
	public void setPreRollVideoList(List<Video> preRollVideoList) {
		this.preRollVideoList = preRollVideoList;
	}

	@Override
	public String toString() {
		return "PreRoll [name=" + name + ", preRollVideoList=" + preRollVideoList + "]";
	}
	
	
	
	
}
