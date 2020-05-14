package search.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import search.model.dao.SearchSongDao;
import search.model.vo.SearchResult;
import song.vo.SearchSong;

public class SearchSongService {

	public SearchResult searchByKeword(String keyword) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int start = 1;
		int end = 50;
		
		int totalResult = new SearchSongDao().getTotalCount(conn,keyword);
		ArrayList<SearchSong> list = new SearchSongDao().searchByKeword(conn, keyword, start, end);
			
		
		JDBCTemplate.close(conn);
		
		return new SearchResult(list, totalResult);
	}

	public SearchResult searchByKeword(String keyword, String category) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int start = 1;
		int end = 50;
		
		int totalResult = new SearchSongDao().getTotalCount(conn,keyword);
		
		switch(Integer.parseInt(category)) {
		case 1:
			category = "song_title";
			break;
			
		case 2:
			category = "song_artist";
			break;
			
		case 3:
			category = "album_name";
			break;
		}
		
		ArrayList<SearchSong> list = new SearchSongDao().searchByKeword(conn, keyword, category, start, end);			
		
		JDBCTemplate.close(conn);
		
		return new SearchResult(list, totalResult);
	}

}
