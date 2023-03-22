import java.util.*;

public class Encoder {
	
	public static String reference = "abcdefghijklmnopqrstuvwxyz0123456789()*+,-./";
	public static int shift;
	public int letter;
	public int shiftedLetter;	
	public String encodedText = "";
	public String decodedText = "";
	public static String shiftChar;
	public static String selected;
	//method to determine the number of shifts via the offset character
	public static void main(String[] args) {
		
		System.out.println("Type 'encode' or 'decode' to select mode.");
		Scanner select = new Scanner(System.in);
		selected = select.nextLine();
		if(selected.equals("encode")) {
			System.out.println("Enter offset character:");
			Scanner input = new Scanner(System.in);
			shiftChar = input.nextLine();
			shift = reference.indexOf(shiftChar); 
			//System.out.println(shift);


			System.out.println("Enter message:");
			
			String plainText = input.nextLine();
			
			
			Encoder en = new Encoder();
			en.encode(plainText);
			input.close();
			System.out.println(shiftChar + en.encodedText);
		}
		else if(selected.equals("decode")) {
			System.out.println("Enter message to decode:");
			Scanner input2 = new Scanner(System.in);
			String encText = input2.nextLine();
			
			Encoder de = new Encoder();
			de.decode(encText);
			
			input2.close();
			System.out.println(de.decodedText);
		}
		else {
			System.out.println("Invalid input.");
		}
		select.close();

	}
	
	/*
	 * if letter in string is valid, iterate through the string and shift each letter
	 * else just put it back into the string
	 */
	public String encode(String plainText) {
		for(int i = 0; i < plainText.length(); i++) {
			//traverse message and shift characters by shift amount
			if(reference.indexOf(plainText.substring(i,i + 1)) != -1) {
				letter = reference.indexOf(plainText.substring(i,i + 1));
				shiftedLetter = letter - shift + 44; //prevent underflow
				shiftedLetter %= 44; //prevent overflow
				encodedText += reference.substring(shiftedLetter,shiftedLetter + 1);
			}
			else {
				encodedText += plainText.substring(i,i + 1);
			}
		}
		return encodedText;
	}
	
	public String decode(String encodedText) {
		shift = reference.indexOf(encodedText.indexOf(0)); //check first letter of encoded text and determine shift amount
		encodedText = encodedText.substring(1); //remove offset character
		
		for(int i = 0; i < encodedText.length(); i++) {
			if(reference.indexOf(encodedText.substring(i,i + 1)) != -1) {
				letter = reference.indexOf(encodedText.substring(i,i + 1));
				shiftedLetter = letter - shift + 44;
				shiftedLetter %= 44;
				decodedText += reference.substring(shiftedLetter,shiftedLetter + 1);
			}
			else {
				decodedText += encodedText.substring(i,i + 1);
			}
		}
		return decodedText;
	}
}