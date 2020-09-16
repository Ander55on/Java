package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "västergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		List<TextProcessor> wordCounters = new ArrayList<>();
		Set<String> stopwords = new HashSet<>();
	
		
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		
		while(scan.hasNext()) {
			String word = scan.next().toLowerCase();
			stopwords.add(word);
		}
		
		scan.close();
		
		TextProcessor multiWordCounterRegions = new MultiWordCounter(REGIONS);
		TextProcessor generalWordCounter = new GeneralWordCounter(stopwords);
		wordCounters.add(generalWordCounter);
		wordCounters.add(multiWordCounterRegions);
		
			
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		long t0 = System.nanoTime();
		while (s.hasNext()) {
			String word = s.next().toLowerCase();

			for (TextProcessor t : wordCounters) {
				t.process(word);
			}
		}
	
		s.close();
	
		
		for (TextProcessor t : wordCounters) {
			t.report();
			System.out.println("");
		}
		
		long t1 = System.nanoTime();
		System.out.println("time: " + (t1 - t0) / 1000000.0 + " ms");
	}
}