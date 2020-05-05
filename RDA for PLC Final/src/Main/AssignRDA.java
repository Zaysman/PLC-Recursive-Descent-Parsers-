package Main;
//imports

//libraries
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;

public class AssignRDA {
	
	static File file = new File("Input");
	static LinkedList<String> lexemes = new LinkedList<String>();
	static int lexemesIndex = 0;
	static String lex = new String();
	
	static LinkedList<String> expressions = new LinkedList<String>();

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		Scanner fileInput = new Scanner(file);
		String str = new String();

		while(fileInput.hasNext()) {
			str = fileInput.next();

			if(str.contains(";") == true) {
				String sub = str.substring(0, (str.length() - 1));
				lexemes.add(sub);
				lexemes.add(";");
			} else {
				lexemes.add(str);
			}
		}
		fileInput.close();

		printStringList(lexemes);
		
		
		lex();
		
		assign();

	}
	
	public static void lex() {
		if(lexemesIndex >= lexemes.size()) {
			System.out.println("Out of Lexemes");
			return;
		} else {
			lex = lexemes.get(lexemesIndex++);
		}
		
	}
	
	public static void assign() {
		System.out.println("Entering Assign_expr");
		
		if(isID(lex) == true) {
			lex();
			assignOP();
			
			
		}
		
		System.out.println("Exiting Assign_expr");
	}
	
	public static void assignOP() {
		System.out.println("Entering Assign_operator");
		
		if(lex.compareTo("=") == 0 || lex.compareTo("*=") == 0 || lex.compareTo("/=") == 0 || lex.compareTo("%=") == 0 || lex.compareTo("-=") == 0 || lex.compareTo("+=") == 0) {
			lex();
			expr();
		} else {
			error();
		}
		
		System.out.println("Exiting Assign_operator");
	}
	
	
	
	public static void expr() {
		System.out.println("Entering expr");
		term();
		
		if(isID(lex) == true || isConstant(lex) == true) {
			
			while(lex.compareTo("+") == 0 || lex.compareTo("-") == 0) {
				lex();
				term();
			}
		}
		
		System.out.println("Exiting expr");
	}
	
	public static void term() {
		System.out.println("Entering term");
		factor();
		
		while(lex.compareTo("*") == 0 || lex.compareTo("/") == 0) {
			lex();
			factor();
		}
		
		System.out.println("Exiting Term");
	}
	
	public static void factor() {
		System.out.println("Entering factor");
		
		if(isID(lex) == true || isConstant(lex) == true) {
			lex();
			
		} else {
			if(lex.compareTo("(") == 0) {
				lex();
				expr();
				
				if(lex.compareTo(")") == 0) {
					lex();
				} else {
					error();
				}

			} else {
				error();
			}
		}
		
		System.out.println("Exiting factor");
	}
	
	
	
	public static boolean isID(String str) {
		char check;
		
		check = str.charAt(0);
		
		if(Character.isLetter(check) == false) { //The first character in an id must be a letter
			return false;
		}
		
		
		for(int i = 1; i < str.length(); i++) { //The rest of the string may be letters or numbers
			check = str.charAt(i);
			
			if(Character.isLetter(check) == false && isDecimal(check) == false) {
				return false;
			}
			
		}
		
		return true;
		
	}
	
	public static boolean isConstant(String str) {
		if(isInt(str) == true) {
			return true;
		} else if(isDouble(str) == true) {
			return true;
		} else if(isFloat(str) == true) {
			return true;
		} else if(isChar(str) == true) {
			return true;
		} else if(isBool(str) == true) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isInt(String str) {
		for(int i = 0; i < str.length(); i++) {
			char check = str.charAt(i);
			
			if(isDecimal(check) == false) {
				return false;
			}
		}
		
		
		return true;
	}
	
	public static boolean isDouble(String str) {
		for(int i = 0; i < str.length(); i++) {
			
			char check = str.charAt(i);
			
			if(isDecimal(check) == false && check != '.') {
				return false;
			}
			
		}
		
		return true;
	}
	
	public static boolean isFloat(String str) {
		char check;
		check = str.charAt(str.length() - 1);
		
		if(check != 'f') {
			return false;
		}
		
		for(int i = 0; i < (str.length() - 1); i++) {
			check = str.charAt(i);
			
			if(isDecimal(check) == false && check != '.') {
				return false;
			}
			
		}
		
		return true;
	}
	
	public static boolean isChar(String str) {
		
		if(str.length() != 1) {
			return false;
		}
		
		char check = str.charAt(0);
		
		if(Character.isLetter(check) == false) {
			return false;
		}
		
		return true;
	}
	
	public static boolean isBool(String str) {
		if(str.compareTo("true") != 0 && str.compareTo("false") != 0) {
			return false;
			
		}
		return true;
		
	}
	
	public static void error() {
		System.out.println("An error has occurred!");
		System.exit(0);
	}

	public static void getExpressions() {
		String expr = new String();
		String str = new String();
		int index = 0;
		int endExprIndex = -1;

		for(int i = 0; i < lexemes.size(); i++) {
			str = lexemes.get(i);

			if(str.compareTo(";") == 0) {
				expr = expr.trim();
				expr += str;
				expressions.add(expr);

				expr = new String();

			} else {
				expr += str + " ";
			}
		}
	}
	
	
	public static boolean isDecimal(char ch) {

		switch(ch) {
		case '0':
		case '1':
		case '2':
		case '3':	
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':	
			return true;
		default:
			return false;
		}

	}



	public static void printStringList(List<String> list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.println("List(" + i + "): " + list.get(i));
		}

		System.out.println();
	}
	
	public static void trimStringList(List<String> list) {
		for(int i = 0; i < list.size(); i++) {
			String str = list.get(i).trim();
			
			list.set(i, str);
		}
		
	}

	
}
