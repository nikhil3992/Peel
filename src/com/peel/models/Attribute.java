package com.peel.models;

import java.util.List;

public class Attribute {

	private List<String> countries;
	private String language;
	private String aspectRatio;
	
	public List<String> getCountries() {
		return countries;
	}
	
	public void setCountries(List<String> countries) {
		this.countries = countries;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getAspectRatio() {
		return aspectRatio;
	}
	
	public void setAspectRatio(String aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	@Override
	public String toString() {
		return "Attribute [countries=" + countries + ", language=" + language + ", aspectRatio=" + aspectRatio + "]";
	}
	
}
