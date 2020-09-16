package textproc;

//import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MultiWordCounter implements TextProcessor {

	private Map<String, Integer> words = new TreeMap<>();
	
	public MultiWordCounter(String[] words) {
		//prepopulating the map with the word as the key and the occurance of the word
		for(int i = 0; i < words.length; i++) {
			String key = words[i];
			this.words.put(key, 0);
		}
	}

	@Override
	public void process(String w) {
		
		if(this.words.containsKey(w)) {
			int value = this.words.get(w);
			value++;
			this.words.put(w, value);
		}
		
	}

	@Override
	public void report() {
		for(String key : this.words.keySet()) {
			System.out.println(key + ":" + words.get(key));
		}
		
	}
}
