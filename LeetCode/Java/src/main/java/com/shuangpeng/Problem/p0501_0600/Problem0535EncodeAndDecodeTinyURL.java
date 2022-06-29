package com.shuangpeng.Problem.p0501_0600;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Description: Problem0535EncodeAndDecodeTinyURL（TinyURL的加密与解密）
 * @Date 2022/6/29 9:58 AM
 * @Version 1.0
 */
public class Problem0535EncodeAndDecodeTinyURL {

    public class Codec {

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            return longUrl;
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return shortUrl;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
}

class Problem0535EncodeAndDecodeTinyURL0 {

    class Codec {
        private int id = 0;
        Map<Integer, String> map = new HashMap<>();

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            String tinyUrl = "https://tinyurl.com/" + id;
            map.put(id, longUrl);
            id++;
            return tinyUrl;
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            int index = shortUrl.lastIndexOf("/") + 1;
            int id = Integer.parseInt(shortUrl.substring(index));
            return map.get(id);
        }
    }
}

class Problem0535EncodeAndDecodeTinyURL1 {
    class Codec {

        int k1 = 1117, k2 = (int) 1e9 + 7;
        Map<String, String> toShortMap = new HashMap<>();
        Map<String, String> toLongMap = new HashMap<>();

        public String encode(String longUrl) {
            if (toShortMap.containsKey(longUrl)) {
                return toShortMap.get(longUrl);
            }
            long key = 0;
            int n = longUrl.length();
            long base = 1;
            for (int i = 0; i < n; i++) {
                key = (key * base + longUrl.charAt(i)) % k2;
                base = (base * k1) % k2;
            }
            String url = "http://tinyurl.com/";
            while (toLongMap.containsKey(url + key)) {
                key++;
            }
            url += key;
            toShortMap.put(longUrl, url);
            toLongMap.put(url, longUrl);
            return url;
        }

        public String decode(String shortUrl) {
            return toLongMap.get(shortUrl);
        }
    }
}

class Problem0535EncodeAndDecodeTinyURL2 {
    class Codec {

        Map<String, Integer> urlToKey = new HashMap<>();
        Map<Integer, String> database = new HashMap<>();
        Random random = new Random();

        public String encode(String longUrl) {
            if (urlToKey.containsKey(longUrl)) {
                return database.get(urlToKey.get(longUrl));
            }
            int key = random.nextInt();
            while (database.containsKey(key)) {
                key = random.nextInt();
            }
            String url = "http://tinyurl.com/" + key;
            urlToKey.put(longUrl, key);
            database.put(key, longUrl);
            return url;
        }

        public String decode(String shortUrl) {
            int index = shortUrl.lastIndexOf("/") + 1;
            int key = Integer.parseInt(shortUrl.substring(index));
            return database.get(key);
        }
    }
}