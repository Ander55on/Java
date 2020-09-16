package textproc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
//import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

public class GeneralWordCounter implements TextProcessor {

	private Map<String, Integer> words = new HashMap<>();
	private Set<String> stopwords;
	
	public GeneralWordCounter(Set<String> stopwords) {
		this.stopwords = stopwords;
	}
	
	@Override
	public void process(String w) {
		
		if(!stopwords.contains(w)) {	
			Integer value = words.get(w); //returns null if the map contains no mapping for the key
			
			if(value == null) {
				value = 0;
			} 
			
			value++;
			words.put(w, value);
		}
		
	}

	@Override
	public void report() {			
		
		Set<Map.Entry<String, Integer>> wordSet = words.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		wordList.sort(new WordCountComparator());
		
		for(int i = 0; i < 5; i++) {
			System.out.println(wordList.get(i).getKey() + ":" + wordList.get(i).getValue());
		}
		
	}

}
