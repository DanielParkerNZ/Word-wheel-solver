import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class WordWheel {
	
	ArrayList<String> possibleSolutions = new ArrayList<String>();
	ArrayList<String> solvedWords = new ArrayList<String>();
	
	public WordWheel(String letters) throws FileNotFoundException {
		createAllConfigurations(letters);
	}
	
	public void createAllConfigurations(String wordInput) throws FileNotFoundException {
		
		String s = wordInput;
		char a = s.charAt(0);
		char b = s.charAt(1);
		char c = s.charAt(2);
		char d = s.charAt(3);
		char e = s.charAt(4);
		char f = s.charAt(5);
		char g = s.charAt(6);

		String s1 = ""+a+b+c+d+e+f+g;
		for(int y=0; y<2; y++) {
			for(int i=7; i>=0; i--) {
				String s2 = s1;
				for(char alphabet = 'a'; alphabet <= 'z';alphabet++) {
					StringBuilder sb = new StringBuilder(s2);
					sb.insert(i, alphabet);
					possibleSolutions.add(sb.toString());
					sb.deleteCharAt(i);
				}
				String s3 = s2.substring(0,1);
				s2 = s2.substring(1) + s3;
			}
			StringBuilder sb = new StringBuilder(s1);
			sb.reverse();
			s1 = sb.toString();
		}
		System.out.println("Number of possible configurations: " + possibleSolutions.size());
		solve();
	}
	
	public void solve() throws FileNotFoundException {
		
		BufferedReader br = new BufferedReader(new FileReader("words.txt"));
		String q = null;
		String token = null;
		try {
			while ((q = br.readLine()) !=null) {
			    StringTokenizer st = new StringTokenizer(q);
			    while (st.hasMoreTokens()) {
			        token = st.nextToken();
			        for (int i = 0; i < possibleSolutions.size(); i++) {
			        	String x = possibleSolutions.get(i);
			            if (token.equals(x)) {
			                solvedWords.add(x);
			            }
			        }
			    }
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("The solution to the Word Wheel is: " + solvedWords.toString());
	}
	
	//Main method for testing
	public static void main(String [] args) throws FileNotFoundException {
		System.out.println("Please input the seven letters in order, ");
		System.out.println("with the first letter being one space anticlockwise ");
		System.out.println("from the empty letter space.");
		Scanner scanner = new Scanner(System.in);
		WordWheel ww = new WordWheel(scanner.nextLine());
		scanner.close();
	}
	
}
