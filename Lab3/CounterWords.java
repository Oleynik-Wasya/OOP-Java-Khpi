import java.util.HashMap;

public class CounterWords {
	StringBuffer str;
	public CounterWords(String str){
		this.str = new StringBuffer(str);
	}
	public void cleanStr() {
		while(this.str.indexOf("  ") != -1) {
			this.str.delete(this.str.indexOf("  "), this.str.indexOf("  ") + 1);
		}
		if(this.str.charAt(0) == ' ') {
			this.str.delete(0, 1);
		}
		if(this.str.charAt(this.str.length() - 1) == ' ') {
			this.str.setLength(this.str.length() - 1);
		}
		String stopwords[] = {".",",","!","?"};
		for(int i = 0; i < stopwords.length; i++) {
			while(this.str.indexOf(stopwords[i]) != -1)
				this.str.delete(this.str.indexOf(stopwords[i]), this.str.indexOf(stopwords[i]) + 1);
		}
	}
	public HashMap<String, Integer> countWords() {
		cleanStr();
		HashMap< String, Integer > map = new HashMap< String, Integer >();
		int start = 0;
		int end = 0;
		for(int i = 0; i < this.str.length(); i++) {
			if(this.str.charAt(i) == ' ') {
				end = i;
				if(map.get(this.str.substring(start, end)) != null) {
					map.put(this.str.substring(start, end), map.get(this.str.substring(start, end)) + 1);
					start = i + 1;
					continue;
				}
				map.put(this.str.substring(start, end), 1);
				start = i + 1;
			}
		}
		if(map.get(this.str.substring(start, this.str.length())) != null){
			map.put(this.str.substring(start, this.str.length()), map.get(this.str.substring(start, this.str.length())) + 1);
		}else {
			map.put(this.str.substring(start, str.length()), 1);
		}
		return map;
	}
}
