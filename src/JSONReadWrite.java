import java.util.*;
import java.io.*;
import java.nio.file.*;

public class JSONReadWrite {

	static ArrayList<Song> songList = new ArrayList<Song>();
	
	public static void main(String[] args) throws IOException {
		//init songList
		songList.add(new Song("title1", "artist1"));
		songList.add(new Song("title2", "artist2"));
		songList.add(new Song("title3", "artist3"));
		
		java2json(songList);
		ArrayList<Song>newSongList =  json2java("songList.json");
		
		System.out.println("compare result of default song list v. song list written to/read from json: " + songList.toString().compareTo(newSongList.toString()));
	}
	
	//converts ArrayList<Song> to JSON and saves to .json file
	public static void java2json(ArrayList<Song> songList) throws FileNotFoundException {
		JSONArray jsonSongList = new JSONArray();
		PrintWriter out = new PrintWriter("songList.json");
		
		for(int i = 0; i < songList.size(); i++) {
			jsonSongList.put(songList.get(i).toHashMap());
		}
		
		out.print(jsonSongList.write(out));
		out.close();
	}
	
	//converts text in .json file to ArrayList<Song>
	public static ArrayList<Song> json2java(String jsonFilename) throws IOException {
		List<String> jsonFile = Files.readAllLines(Paths.get(jsonFilename));
		JSONArray jsonSongList = new JSONArray(parseString(jsonFile.toString()));
		ArrayList<Song> songList = new ArrayList<Song>(jsonSongList.length());
		
		for(int i = 0; i < jsonSongList.length(); i++) {
			songList.add(new Song(jsonSongList.getJSONObject(i)));
		}
		
		return songList;
	}
	
	public static String parseString(String s)
	{
		StringBuffer sb = new StringBuffer(s);
		String keyword = "java.io.PrintWriter@";
		int idxKeyword = sb.indexOf(keyword);
		
		int endPos = idxKeyword;
		while(s.charAt(endPos) != ']') {
				endPos++;
		}
		
		sb.delete(idxKeyword, endPos + 1).toString();
		return sb.delete(0, 1).toString();
	}
}
