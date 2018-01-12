package com.peel.testclient;

import java.util.List;
import java.util.Scanner;

import com.peel.api.PlaylistService;
import com.peel.impl.PlaylistServiceImpl;

public class TestClient {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		String contentName = scanner.next();
		String country = scanner.next();
		
		PlaylistService playlistService = new PlaylistServiceImpl();
		List<List<String>> list = playlistService.getValidPlayLists(contentName, country);
		
		if(list.isEmpty()) {
			System.out.println("No legal playlist possible because the Pre-Roll Video isn't compatible with the aspect ratio of the Content Video for "+country);
		} else {	
			printValidPlaylists(list);
		}
		scanner.close();
	}
	
	public static void printValidPlaylists(List<List<String>> list) {
		
		for(int i=0;i<list.size();i++) {
			System.out.println("Playlist "+(i+1));
			System.out.println(list.get(i).toString());
		}
	}
	
}
