package com.peel.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import com.peel.api.PlaylistService;
import com.peel.impl.PlaylistServiceImpl;
import com.peel.models.Attribute;
import com.peel.models.Content;
import com.peel.models.PreRoll;
import com.peel.models.Video;
import com.peel.parser.InputParser;

public class PlaylistServiceTest {
	
	PlaylistService playlistService = new PlaylistServiceImpl();

	
	/**
	 * Test for correctness of getValidPlaylists() method in PlaylistInterface
	 */
	@Test
	public void testGetValidPlaylists() {
		
		String contentName = "MI3";
		String country = "UK";

		List<List<String>> expectedList = new ArrayList<>();
		
		List<String> list1 = new ArrayList<>();
		list1.add("V6");
		list1.add("V2");
		expectedList.add(list1);
		
		List<String> list2 = new ArrayList<>();
		list2.add("V7");
		list2.add("V3");
		expectedList.add(list2);

		List<List<String>> obtainedList = playlistService.getValidPlayLists(contentName, country);
		assertEquals(expectedList, obtainedList);
	}

	/**
	 * Test for correctness of getValidPlaylists() method in PlaylistInterface
	 */
	@Test
	public void testGetNoPlaylists() {
		String contentName = "MI3";
		String country = "US";

		List<List<String>> obtainedList = playlistService.getValidPlayLists(contentName, country);
		assertEquals(0, obtainedList.size());
	}
	
	/**
	 * Test for correctness of loadDataIntoMap() method for Contents in PlaylistServiceImpl.java
	 */
	@Test
	public void testLoadDataIntoContentMap() {
		PlaylistServiceImpl serviceImpl = new PlaylistServiceImpl();
		serviceImpl.loadDataIntoMaps();
		Map<String, Content> contentMap = serviceImpl.getContentMap();
		assertTrue(contentMap.size() > 0);
	}

	/**
	 * Test for correctness of loadDataIntoMap() method for preRolls in PlaylistServiceImpl.java
	 */
	@Test
	public void testLoadDataIntoPrerollMap() {
		PlaylistServiceImpl serviceImpl = new PlaylistServiceImpl();
		serviceImpl.loadDataIntoMaps();
		Map<String, PreRoll> preRollMap = serviceImpl.getPreRollMap();
		assertTrue(preRollMap.size() > 0);
	}
	

	/**
	 * Test for correctness of getValidPlaylistsForGivenVideo() method in PlaylistServiceImpl.java
	 */
	@Test
	public void testGetValidPlaylistsForGivenVideo() {
		
		PlaylistServiceImpl serviceImpl = new PlaylistServiceImpl();
		serviceImpl.loadDataIntoMaps();
		Map<String, Content> contentMap = serviceImpl.getContentMap();
		
		Content content = contentMap.get("MI3");				
		List<Video> videosList = content.getContentVideoList();
		Video contentVideo = videosList.get(0);
		Attribute contentVideoAttribute = contentVideo.getVideoAttribute();	
				
		List<List<String>> obtainedList = serviceImpl.getValidPlaylistsForGivenVideo(
				content,contentVideoAttribute,contentVideo.getVideoName(),"CA");
		
		List<List<String>> expectedList = new ArrayList<>();
		List<String> list = new ArrayList<>();
		list.add("V5");
		list.add("V1");
		expectedList.add(list);
		
		assertEquals(expectedList,obtainedList);
	
	}

		/**
	 * Test for correctness of isAttributeMatching() method in PlaylistServiceImpl.java
	 */
	@Test
	public void testIfAttributeMatching() {
		
		PlaylistServiceImpl serviceImpl = new PlaylistServiceImpl();
		List<String> countries = new ArrayList<String>();
		countries.add("US");
		countries.add("UK");
		Attribute contentAttribute = new Attribute(countries,"English","16:9");
		Attribute preRollAttribute = new Attribute(countries,"English","16:9");
		String inputCountry = "US";
		assertTrue(serviceImpl.isAttributesMatching(contentAttribute, preRollAttribute, inputCountry));
	}

	/**
	 * Test for correctness of isAttributeMatching() method in PlaylistServiceImpl.java
	 */
	@Test
	public void testIfAttributeNotMatching() {
		
		PlaylistServiceImpl serviceImpl = new PlaylistServiceImpl();
		List<String> countries = new ArrayList<String>();
		countries.add("US");
		countries.add("UK");
		
		Attribute contentAttribute = new Attribute(countries,"English","16:9");
		Attribute preRollAttribute = new Attribute(countries,"English","4:9");
		String inputCountry = "US";
		assertFalse(serviceImpl.isAttributesMatching(contentAttribute, preRollAttribute, inputCountry));
	}

	/**
	 * Test for correctness of checkCountryMatch() method in PlaylistServiceImpl.java
	 */
	@Test
	public void testIfCountryMatch() {
		
		PlaylistServiceImpl serviceImpl = new PlaylistServiceImpl();
		List<String> countries = new ArrayList<String>();
		countries.add("US");
		countries.add("UK");
		Attribute contentAttribute = new Attribute(countries,"English","16:9");
		assertTrue(serviceImpl.checkCountryMatch(contentAttribute, "US"));
	}

	/**
	 * Test for correctness of checkCountryMatch() method in PlaylistServiceImpl.java
	 */
	@Test
	public void testIfCountryMismatch() {

		PlaylistServiceImpl serviceImpl = new PlaylistServiceImpl();
		List<String> countries = new ArrayList<String>();
		countries.add("US");
		countries.add("UK");
		Attribute contentAttribute = new Attribute(countries,"English","16:9");
		assertFalse(serviceImpl.checkCountryMatch(contentAttribute, "CA"));
	}
	
	/**
	 * Test for correctness of parseJSONFile() method in InputParser.java
	 */
	@Test
	public void testParseJSONFile() {
		
		InputParser parser = new InputParser();
		JSONObject rootObject = parser.parseJSONFile("input.json");
		assertTrue(rootObject != null);
	}
	
	/**
	 * Test for correctness of parseContents() method in InputParser.java
	 */
	@Test
	public void testParseContents() {
		
		InputParser parser = new InputParser();
		JSONObject rootObject = parser.parseJSONFile("input.json");
		List<Content> contentList = parser.parseContents(rootObject);
		assertTrue(contentList.size() > 0);
		
	}
	
	/**
	 * Test for correctness of parsePreRolls() method in InputParser.java
	 */
	@Test
	public void testParsePreRolls() {
		
		InputParser parser = new InputParser();
		JSONObject rootObject = parser.parseJSONFile("input.json");
		List<PreRoll> preRollList = parser.parsePreRolls(rootObject);
		assertTrue(preRollList.size() > 0);
		
	}
	
	/**
	 * Test for correctness of getPreRollNames() method in InputParser.java
	 */
	@Test
	public void testGetPreRollNames() {
		
		InputParser parser = new InputParser();
		JSONObject rootObject = parser.parseJSONFile("input.json");
		List<String> preRollNamesList = parser.getPreRollNames(rootObject);
		
		List<String> expectedList = new ArrayList<>();
		expectedList.add("WB1");
		assertEquals(expectedList,preRollNamesList);
		
	}
	
	/**
	 * Test for correctness of getVideosFromJSON() method in InputParser.java
	 */
	@Test
	public void testGetVideosFromJSON() {
		
		InputParser parser = new InputParser();
		JSONObject rootObject = parser.parseJSONFile("input.json");
		
		JSONArray contentArray = (JSONArray) rootObject.get("content");
		
		// assert that contentArray is null
		if(contentArray == null) {
			assertTrue(contentArray == null);
			return;
		}
		
		Iterator<JSONObject> contentIterator = contentArray.iterator();
		
		// get videos of first content object and add to videosList
        if(contentIterator.hasNext()) {
        	
        	JSONObject contentJSONObject = contentIterator.next();    	
        	List<Video> videosList = parser.getVideosFromJSON(contentJSONObject);
        	assertTrue(videosList.size() > 0);
        }
        
	}
}
