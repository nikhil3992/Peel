package com.peel.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.peel.models.Attribute;
import com.peel.models.Content;
import com.peel.models.PreRoll;
import com.peel.models.Video;


// This class mainly contains methods to parse the JSON file and convert the data into models

public class InputParser {

	/**
	 * This method will take the filePath as argument and return
	 * a reference to the root JSON object
	 * 
	 * @param filePath The path to the file location
	 * @return JSONObject The root object of the given JSON
	 */
	public JSONObject parseJSONFile(String filePath) {
		
		JSONObject rootObject = null;
		
		try {
			rootObject = (JSONObject) new JSONParser().parse(new FileReader(filePath));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		};
        
		return rootObject; 
	}
	
	/**
	 * This method takes a list of content objects as arguments and 
	 * returns a map where key is the content name and value is the content object
	 * 
	 * @param contentList - The list of contents
	 * @return map - The map having <contentName,Content> as <key,value> pair
	 * 
	 */
	
	public Map<String,Content> getContentMap(List<Content> contentList) {
		
		Map<String,Content> contentMap = new HashMap<>();
		if(contentList.size() > 0) {
			for(Content content : contentList) {
				contentMap.put(content.getContentName(), content);
			}
		}
		return contentMap;
	}
	
	/**
	 * This method takes a list of preRoll objects as arguments and 
	 * returns a map where key is the preRoll name and value is the preRoll object
	 * 
	 * @param preRollList - The list of preRolls
	 * @return map - The map having <preRollName,PreRoll> as <key,value> pair
	 * 
	 */
	public Map<String,PreRoll> getPreRollMap(List<PreRoll> preRollList) {
		
		Map<String,PreRoll> preRollMap = new HashMap<>();		
		if(preRollList.size() > 0) {
			for(PreRoll preRoll : preRollList) {
				preRollMap.put(preRoll.getName(), preRoll);
			}
		}
		return preRollMap;
	}
	
	
	/**
	 * This method parses the contents from the given JSON reference
	 * and returns a list of Content Objects
	 * 
	 * @param rootObject - Reference to the JSON
	 * @return - List of Content Objects
	 */
	public List<Content> parseContents(JSONObject rootObject) {
		
		List<Content> contentList = new ArrayList<>();
		JSONArray contentArray = (JSONArray) rootObject.get("content");
		
		// return empty contentList if contentArray is not available in JSON
		if(contentArray == null) return contentList;
		
		Iterator<JSONObject> contentIterator = contentArray.iterator();
		
		// iterate through each content object and add to contentList
        	while(contentIterator.hasNext()) {
        	
			JSONObject contentJSONObject = contentIterator.next();    	
			
			// Get the Name of the content first
			String contentName = (String) contentJSONObject.get("name");
	
			// Get preRoll names from preRollArray
			List<String> preRollNamesList = getPreRollNames(contentJSONObject);
			
			// Get videos from JSON Object
			List<Video> videosList = getVideosFromJSON(contentJSONObject);
			
			Content content = new Content(contentName,preRollNamesList,videosList);
			contentList.add(content);
        	}
		return contentList;
	}
	

	/**
	 * This method takes content objects in JSON format as arguments
	 * and returns a list of preRoll names associated with it 
	 * 
	 * @param contentJSONObject - Reference to the content object JSON
	 * @return - List of preRoll names
	 */
	public List<String> getPreRollNames(JSONObject contentJSONObject) {
		
		List<String> preRollNamesList = new ArrayList<>();
		
		// Get the array of preRoll Names	
    		JSONArray preRollNamesArray = (JSONArray) contentJSONObject.get("preroll");
    	
    		// Return empty list if array is null
    		if(preRollNamesArray == null) return preRollNamesList;
    	
    		Iterator<JSONObject> preRollNamesIterator = preRollNamesArray.iterator();
    	
    		// Iterate through all the names and add to the list
    		while(preRollNamesIterator.hasNext()) {
    		
			JSONObject nameJSONObject = (JSONObject) preRollNamesIterator.next();
			String preRollName = (String) nameJSONObject.get("name");
			preRollNamesList.add(preRollName);
    		
    		}
    	
    		return preRollNamesList;
	}
	
	/**
	 * This method parses the preRolls from the given JSON reference
	 * and returns a list of preRoll Objects
	 * 
	 * @param rootObject - Reference to the JSON
	 * @return - List of preRoll Objects
	 */
	public List<PreRoll> parsePreRolls(JSONObject rootObject) {
		
		List<PreRoll> preRollList = new ArrayList<>();
		JSONArray rootPreRollArray = (JSONArray) rootObject.get("preroll");
		
		// Return empty list if array is null
		if(rootPreRollArray == null) return preRollList;
        
        	Iterator<JSONObject> preRollIterator = rootPreRollArray.iterator();
        
        	// Iterate through preRolls objects and add to preRollList
        	while(preRollIterator.hasNext()) {
        	
			JSONObject preRollJSONObject =  preRollIterator.next();

			// Get preRoll name from JSON object
			String preRollName = (String) preRollJSONObject.get("name");
			
			// Get videos from JSON Object
			List<Video> videosList = getVideosFromJSON(preRollJSONObject);
			
			PreRoll preRoll = new PreRoll(preRollName,videosList);
			// Add the preRoll to preRoll List
			preRollList.add(preRoll);	
        	}
        	return preRollList;
	}
	
	
	/**
	 * This method takes content objects in JSON format as arguments
	 * and returns a list of videos associated with it 
	 * 
	 * @param contentJSONObject - Reference to the content object JSON
	 * @return - List of video objects
	 */
	public List<Video> getVideosFromJSON(JSONObject contentJSONObject) {
		
		JSONArray videosArray = (JSONArray) contentJSONObject.get("videos");
    	
		// Iterate through all the videos 
    		Iterator<JSONObject> videosIterator = videosArray.iterator();
    	
    		List<Video> videosList = new ArrayList<>();
    	
    		while(videosIterator.hasNext()) {
    		
			Video video = new Video();

			JSONObject videoJSONObject = videosIterator.next();

			String name = (String) videoJSONObject.get("name");
			
			JSONObject attributeJSONObject = (JSONObject) videoJSONObject.get("attributes");
			Attribute attribute = getAttributeFromJSON(attributeJSONObject);
			
    			Video video = new Video(name,attribute);

			videosList.add(video);
    		}
    	
    		return videosList;
	}
	
	
	/**
	 * This method takes attribute objects in JSON format as arguments
	 * and converts it into an attribute object
	 * 
	 * @param attributeJSONObject - Reference to the attribute object JSON
	 * @return - An attribute object
	 */
	public Attribute getAttributeFromJSON(JSONObject attributeJSONObject)  {
		
		JSONArray countriesArray = (JSONArray) attributeJSONObject.get("countries");
		
		Iterator<String> countriesIterator = countriesArray.iterator();
		List<String> countries = new ArrayList<>();
		
		while(countriesIterator.hasNext()) {
			String country = countriesIterator.next();
			countries.add(country);
		}
		String language = (String) attributeJSONObject.get("language");
		String aspectRatio = (String) attributeJSONObject.get("aspect");
		
		Attribute attribute = new Attribute(countries,language,aspectRatio);
		return attribute;
	}
	
}
