package hashtable;

import java.util.HashMap;
import java.util.Map;

/** companion problem to the System Design problem: Design TinyURL.534 */

public class EncodeAndDecodeTinyURL_535 {
    Map<Integer, String> map = new HashMap<>();

    public String encode(String longUrl) {
        map.put(longUrl.hashCode(), longUrl);
        return "http://tinyurl.com/" + longUrl.hashCode();
    }

    public String decode(String shortUrl) {
        return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
    }
}
