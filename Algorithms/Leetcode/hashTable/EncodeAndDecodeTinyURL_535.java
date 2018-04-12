package hashTable;

import java.util.HashMap;
import java.util.Map;

/** companion problem to the System Design problem: Design TinyURL.534 */

public class EncodeAndDecodeTinyURL_535 {
	
	/** 第一个 solution */
	
    Map<Integer, String> map = new HashMap<>();

    public String encode(String longUrl) {
        map.put(longUrl.hashCode(), longUrl);
        return "http://tinyurl.com/" + longUrl.hashCode();
    }

    public String decode(String shortUrl) {
        return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
    }
    
    /** 第二个 Solution */
    
    Map<String, String> index = new HashMap<>();
    Map<String, String> revIndex = new HashMap<>();
    static String BASE_HOST = "http://tinyurl.com/";
    
    // Encodes a URL to a shortened URL.
	public String encode2(String longUrl) {
		if (revIndex.containsKey(longUrl))
			return BASE_HOST + revIndex.get(longUrl);
		
		String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String key = null;
		do {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 6; i++) {
				int r = (int) (Math.random() * charSet.length());
				sb.append(charSet.charAt(r));
			}
			key = sb.toString();
		} while (index.containsKey(key));
		
		index.put(key, longUrl);
		revIndex.put(longUrl, key);
		
		return BASE_HOST + key;
	}

    // Decodes a shortened URL to its original URL.
    public String decode2(String shortUrl) {
        return index.get(shortUrl.replace(BASE_HOST, ""));
    }
}
