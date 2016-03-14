package remote.salesforce20160311;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class MorseCodeProblem {
	Map<Character, String> dict = new HashMap<Character, String>(); //c -> code
	Map<String, String> context = new HashMap<String, String>(); //word -> code
	Map<String, List<String>> reverseContext = new HashMap<String, List<String>>(); //code -> all possible words
	List<String> input = new ArrayList<String>();
	List<String> output = new ArrayList<String>();
	
	/*
	 * 
	 */
	public static void main(String[] args) {
		MorseCodeProblem mcp = new MorseCodeProblem();
		mcp.read();
		mcp.decode();
		mcp.output();
	}
	
	/*
	 * read from input file
	 * populate dict, context, reverseContext, input 
	 */
	private void read() {
		File file = new File("problemc.in.txt");
	    try {

	        Scanner sc = new Scanner(file);
	        // read char -> code mapping
	        while (sc.hasNextLine()) {
	            String line = sc.nextLine().trim();
	            if (line.startsWith("*")) {
	            	break;
	            }
	            if (line.length() == 0) {
	            	continue;
	            }
	            char c = line.charAt(0);
	            if (!Character.isDigit(c) && !Character.isLetter(c)) {
	            	continue;
	            }
	            String code = line.substring(1).trim();
	            if (code.length() == 0) {
	            	continue;
	            }
	            dict.put(c, code);
	        }
	        // read context words
	        while (sc.hasNextLine()) {
	        	String line = sc.nextLine().trim();
	        	if (line.startsWith("*")) {
	            	break;
	            }
	        	StringBuilder sb = new StringBuilder();
	        	for (int i = 0; i < line.length(); i++) {
	        		char c = line.charAt(i);
	        		if (dict.containsKey(c)) {
	        			sb.append(dict.get(c));
	        		}
	        	}
	        	String code = sb.toString();
	        	context.put(line, code);
	        	if (!reverseContext.containsKey(code)) { //initialize ArrayList<String>
	        		reverseContext.put(code, new ArrayList<String>());
	        	}
	        	reverseContext.get(code).add(line);
	        }
	        
	        //read morse code that needs to be decoded
	        while (sc.hasNext()) {
	        	String line = sc.next().trim();
	        	if (line.startsWith("*")) {
	            	break;
	            }
	        	input.add(line);
	        	System.out.println(line);
	        }
	        sc.close();
	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	/*
	 * decode word from input and populate output
	 */
	private void decode() {
		for (String s : input) {
			if (reverseContext.containsKey(s)) {
				List<String> contextWordList = reverseContext.get(s);
				if (contextWordList.size() == 1) {
					output.add(contextWordList.get(0));
				} else {
					String shortest = null;
					for (String word : contextWordList) {
						if (shortest == null || word.length() < shortest.length()) {
							shortest = word;
						}
					}
					output.add(shortest + "!");
				}
			} else {
				//may able to improve performance with trie
				String appr = bfs(s);
				output.add(appr + "?");
			}
		}
	}
	
	/*
	 * bfs for the closest context word to code
	 */
	private String bfs(String code) {
		Queue<String> q = new ArrayDeque<String>();
		q.offer(code);
		while (true) {
			String next = q.poll();
			if (next.length() == 0 || next.length() > code.length() * 2) {
				break;
			}
			if (reverseContext.containsKey(next)) {
				return reverseContext.get(next).get(0); //any will work
			}
			q.offer(next.substring(1, next.length() - 1));
			q.offer(next + ".");
			q.offer(next + "-");
		}
		return "";
	}
	
	/*
	 * print to file
	 */
	private void output() {
		try {
			//project root folder
			PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
			for (String s : output) {
				writer.println(s);
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
