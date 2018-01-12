package com.peel.models;

import java.util.List;

public final class Attribute {

	private final List<String> countries;
	private final String language;
	private final String aspectRatio;
	
	public Attribute(List<String> countries,String language,String aspectRatio) {
		this.countries = countries;
		this.language = language;
		this.aspectRatio = aspectRatio;
	}
	
	public List<String> getCountries() {
		return countries;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public String getAspectRatio() {
		return aspectRatio;
	}
	
	@Override
	public String toString() {
		return "Attribute [countries=" + countries + ", language=" + language + ", aspectRatio=" + aspectRatio + "]";
	}
	
}
