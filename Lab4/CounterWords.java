import java.util.HashMap;

public class CounterWords {
	public static StringBuffer cleanStr(String raw_data) {
		StringBuffer str = new StringBuffer(raw_data);
		while(str.indexOf("  ") != -1) {
			str.delete(str.indexOf("  "), str.indexOf("  ") + 1);
		}
		if(str.charAt(0) == ' ') {
			str.delete(0, 1);
		}
		if(str.charAt(str.length() - 1) == ' ') {
			str.setLength(str.length() - 1);
		}
		String stopwords[] = {".",",","!","?"};
		for(int i = 0; i < stopwords.length; i++) {
			while(str.indexOf(stopwords[i]) != -1)
				str.delete(str.indexOf(stopwords[i]), str.indexOf(stopwords[i]) + 1);
		}
		if(Debug.DEBUG)System.out.println("#Чистая строка: " + str);
		return str;
	}
	public static HashMap<String, Integer> countWords(String raw_data) {
		StringBuffer str = cleanStr(raw_data);
		HashMap< String, Integer > map = new HashMap< String, Integer >();
		int start = 0;
		int end = 0;
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == ' ') {
				end = i;
				if(map.get(str.substring(start, end)) != null) {
					map.put(str.substring(start, end), map.get(str.substring(start, end)) + 1);
					if(Debug.DEBUG)System.out.println("#Записую: " + str.substring(start, end));
					start = i + 1;
					continue;
				}
				map.put(str.substring(start, end), 1);
				if(Debug.DEBUG)System.out.println("#Записую: " + str.substring(start, end));
				start = i + 1;
			}
		}
		if(map.get(str.substring(start, str.length())) != null){
			map.put(str.substring(start, str.length()), map.get(str.substring(start, str.length())) + 1);
		}else {
			map.put(str.substring(start, str.length()), 1);
		}
		if(Debug.DEBUG)System.out.println("#Записую: " + str.substring(start, str.length()));
		return map;
	}
}
