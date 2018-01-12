package com.peel.api;

import java.util.List;

public interface PlaylistService {
	
	
	/**
	 * 
	 * This method returns all the valid playlists for the given input file.
	 * This interface is implemented by PlaylistServiceImpl class.
	 * @param contentName - User provided content Name
	 * @param country - User provided country
	 * @return All valid playlists for the given input file
	 * 
	 */
	List<List<String>> getValidPlayLists(String contentName, String country);

}
