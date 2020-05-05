package Main;

//libraries
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.List; 

public class BooleanExpr {
	
	static File file = new File("Input");
	static LinkedList<String> lexemes = new LinkedList<String>();
	static int lexemesIndex = 0;
	static String lex = new String();

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
		
		
		
		lex();
		boolExpr();
	}


	public static void lex() {
		if(lexemesIndex >= lexemes.size()) {
			System.out.println("Out of Lexemes to Process");
			return;
		} else {
			lex = lexemes.get(lexemesIndex++);
		}
		
	}
	
	
	public static void boolExpr() {
		System.out.println("Entering Boolean Expression");
		boolTerm();
		
		if(lex.compareTo("||") == 0) {
			lex();
			boolTerm();
		}
		
		System.out.println("Exiting Boolean Expression");
	}
	
	public static void boolTerm() {
		System.out.println("Entering Boolean Term");
		boolFactor();
		
		if(lex.compareTo("&&") == 0) {
			lex();
			boolFactor();
		}
		
		
		System.out.println("Exiting Boolean Term");
	}
	
	public static void boolFactor() {
		System.out.println("Entering Boolean Factor");
		relationExpr();
		
		
		if(lex.compareTo("!") == 0) {
			lex();
			boolFactor();
		}
		
		
		System.out.println("Exiting Boolean Factor");
	}
	
	public static void relationExpr() {
		System.out.println("Entering Relational Expression");
		
		if(isID(lex) == true || isConstant(lex) == true) {
			lex();
			
			if(lex.compareTo("==") == 0 || lex.compareTo("!=") == 0 || lex.compareTo("<") == 0 || lex.compareTo("<=") == 0 || lex.compareTo(">=") == 0 || lex.compareTo(">") == 0) {
				lex();
				
				if(isID(lex) == true || isConstant(lex) == true) {
					lex();
				} else {
					error();
				}
				
			} else {
				error();
			}
			
		} else {
			error();
		}
		
		
		System.out.println("Exiting Relational Expression");
	}
	
	public static void error() {
		System.out.println("An error has occurred!");
		System.exit(0);
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
	
	public static void printStringList(List<String> list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.println("List(" + i + "): " + list.get(i));
		}

		System.out.println();
	}
	
}



