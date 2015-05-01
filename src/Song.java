import java.util.HashMap;

public class Song {
	private String title;
	private String artist;
	
	public Song(String title, String artist) {
		setTitle(title);
		setArtist(artist);
	}
	
	public Song(JSONObject jsonSong) {
		setTitle(jsonSong.getString("title"));
		setArtist(jsonSong.getString("artist"));
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public HashMap<String, String> toHashMap() {
		HashMap<String, String> song = new HashMap<String, String>();
		
		song.put("title", getTitle());
		song.put("artist", getArtist());
		return song;
	}
}
