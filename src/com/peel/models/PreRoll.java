package com.peel.models;

import java.util.List;

public final class PreRoll {
	
	private final String name;
	private final List<Video> preRollVideoList;
	
	public PreRoll(String name, List<Video> preRollVideoList) {
		this.name = name;
		this.preRollVideoList = preRollVideoList;
	}

	public String getName() {
		return name;
	}
	
	public List<Video> getPreRollVideoList() {
		return preRollVideoList;
	}
	
	@Override
	public String toString() {
		return "PreRoll [name=" + name + ", preRollVideoList=" + preRollVideoList + "]";
	}
}
