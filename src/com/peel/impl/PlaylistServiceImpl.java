package com.peel.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import com.peel.api.PlaylistService;
import com.peel.models.Attribute;
import com.peel.models.Content;
import com.peel.models.PreRoll;
import com.peel.models.Video;
import com.peel.parser.InputParser;


public class PlaylistServiceImpl implements PlaylistService {

	
	// Variables used to store all the contents and preRolls
	private Map<String,Content> contentMap;
	private Map<String,PreRoll> preRollMap;
	
	
	@Override
	public List<List<String>> getValidPlayLists(String contentName, String country) {
		
		List<List<String>> validPlaylists = new ArrayList<>();
		
		loadDataIntoMaps();
		
		// Get content based on user input content Name
		Content content = contentMap.get(contentName);
		
		// Get all videos related to the content
		List<Video> videosList = content.getContentVideoList();
		
		// Iterate through each video and find matching preRolls
		for(Video contentVideo : videosList) {
			
			Attribute contentVideoAttribute = contentVideo.getVideoAttribute();	
			
			if(checkCountryMatch(contentVideoAttribute, country)) {
				
				validPlaylists.addAll(getValidPlaylistsForGivenVideo(content,contentVideoAttribute,
						contentVideo.getVideoName(),country));
			}
		}
		
		return validPlaylists;
	}
	
	
	/**
	 * 
	 * This method returns all the valid Playlists for a particular video
	 * 
	 * @param content - The content object for which we need to find the valid preRolls
	 * @param contentVideoAttribute - The attribute of a given content video
	 * @param contentVideoName - The name given to the video
	 * @param country - User provided input country
	 * @return All the valid playlists for a particular video
	 * 
	 */
	public List<List<String>> getValidPlaylistsForGivenVideo(Content content,Attribute contentVideoAttribute,
			String contentVideoName,String country) {
		
		List<List<String>> validPlaylists = new ArrayList<>();
		List<String> preRollNamesList = content.getPreRollNamesList();
		
		for(String preRollName : preRollNamesList) {
			
			PreRoll preRoll = preRollMap.get(preRollName);
			List<Video> preRollVideoList = preRoll.getPreRollVideoList();
			
			for(Video preRollVideo : preRollVideoList) {
				
				Attribute preRollVideoAttribute = preRollVideo.getVideoAttribute();
				if(isAttributesMatching(contentVideoAttribute,preRollVideoAttribute,country)) {
					
					List<String> list = new ArrayList<>();
					list.add(preRollVideo.getVideoName());
					list.add(contentVideoName);
					validPlaylists.add(list);							
				}
			}	
		}
		return validPlaylists;
	}

	
	/**
	 * This method takes attribute object and user provided country as arguments
	 * and checks if the country is present in the countries list associated 
	 * with the attribute.
	 * 
	 * @param attribute - The attribute of a video
	 * @param country - User provided input country
	 * @return true if it is present, false otherwise
	 * 
	 */
	public boolean checkCountryMatch(Attribute attribute, String country) {
		return (attribute.getCountries().contains(country));
	}
	
	
	
	/**
	 * This method checks if the attributes of a given video in content object and a given
	 * video in preRoll object is matching
	 * 
	 * @param contentVideoAttribute - Content attribute
	 * @param preRollVideoAttribute - preRoll attribute
	 * @param country - User provided input
	 * @return true if attributes are matching, false otherwise
	 * 
	 */
	public boolean isAttributesMatching(Attribute contentVideoAttribute, 
			Attribute preRollVideoAttribute,String country) {
		
		if(checkCountryMatch(preRollVideoAttribute, country)) {
			
			String contentVideoLanguage = contentVideoAttribute.getLanguage();
			String preRollVideoLanguage = preRollVideoAttribute.getLanguage();
			
			String contentVideoAspectRatio = contentVideoAttribute.getAspectRatio();
			String preRollVideoAspectRatio = preRollVideoAttribute.getAspectRatio();
			
			return contentVideoLanguage.equals(preRollVideoLanguage) && 
					contentVideoAspectRatio.equals(preRollVideoAspectRatio);
		}
		
		return false;
	}
	
	
	/**
	 * This method parses the given JSON file and loads the content
	 * in maps. The content objects are stored in contentMap and
	 * preRolls are stored in preRollMap
	 *  
	 */
	public void loadDataIntoMaps() {
		
		InputParser parser = new InputParser();
		JSONObject rootObject = parser.parseJSONFile("input.json");
		
		if(rootObject == null) {
			System.out.println("Error with parsing");
			return;
		}
		
		List<Content> contentList = parser.parseContents(rootObject);
		
		List<PreRoll> preRollsList = parser.parsePreRolls(rootObject);
		
		contentMap = parser.getContentMap(contentList);
		preRollMap = parser.getPreRollMap(preRollsList);
	}
	
	
	/**
	 * This method just returns the loaded content map. Written mainly for testing purpose
	 * @return contentMap
	 * 
	 */
	public Map<String,Content> getContentMap() {
		return contentMap;
	}
	

	/**
	 * This method just returns the loaded preRoll map. Written mainly for testing purpose
	 * @return preRollMap
	 * 
	 */
	public Map<String,PreRoll> getPreRollMap() {
		return preRollMap;
	}
	
}
