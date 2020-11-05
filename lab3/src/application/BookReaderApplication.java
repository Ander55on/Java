package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import controller.BookReaderController;
import textproc.GeneralWordCounter;


public class BookReaderApplication {

	public static void main(String[] args) throws FileNotFoundException {

		
		
		Set<String> stopwords = new HashSet<>();
		Scanner scan = new Scanner(new File("undantagsord.txt"));

		while (scan.hasNext()) {
			String word = scan.next().toLowerCase();
			stopwords.add(word);
		}

		scan.close();
		GeneralWordCounter generalWordCounter = new GeneralWordCounter(stopwords);

		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");

		while (s.hasNext()) {
			generalWordCounter.process(s.next().toLowerCase());
		}
		
		BookReaderController controller = new BookReaderController(generalWordCounter);

	}
	
	

}
