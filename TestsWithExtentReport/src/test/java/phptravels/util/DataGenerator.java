package phptravels.util;

import java.util.Random;

public class DataGenerator {
	
	static Random random = new Random(System.nanoTime()); 

	public static String getValidEmail(String email, int sizeID, String domain) {
		String id="";
		for(int i=0;i<sizeID;i++) id+=randDigit();
		
		return email+id+randUpperChar()+randSpecialChar()+randLowChar()+domain;
	}
	
	public static String getPassword(int length) {
		String password="", randString="";
		for(int i=0; i<length; i++) 
			randString=randString+randLowChar()+randUpperChar()+randDigit()+randSpecialChar();
		
		for(int i=0;i<length;i++) 
			password+=randString.charAt(random.nextInt(randString.length()));
		
		return password;
	}
	
	private static char randLowChar() {
		return (char)(97+random.nextInt(26));
	}
	
	private static char randUpperChar() {
		return(char) (65+random.nextInt(26));
	}
	
	private static char randDigit() {
		return (char) (48+random.nextInt(10));
	}
	
	private static char randSpecialChar() {
		String specialChars="!#$%&'*+-/=?^_`{|}~";
		return specialChars.charAt(random.nextInt(specialChars.length()));
	}
	@SuppressWarnings("unused")
	private static char randSpecialRestrictChar() {
		String specialRestrictChars="(),:;<>@[\\]";
		return specialRestrictChars.charAt(random.nextInt(specialRestrictChars.length()));
	}
	@SuppressWarnings("unused")
	private static char dot() {
		return '.';
	}
	@SuppressWarnings("unused")
	private static char space() {
		return ' ';
	}

	@SuppressWarnings("unused")
	private static char at() {
		return '@';
	}
}
