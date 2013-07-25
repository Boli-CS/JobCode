package testCode;

import java.util.ArrayList;
import java.util.List;

public class AppMain {
	
	public static void main(String[] args) {
		AppMain appMain = new AppMain();
		appMain.printAllCombinationOfString("wxyz");
	}

	/**
	 * print out all combinations of chars in a string
	 * "12" and "21" is the same one
	 * @param string
	 */
	private void printAllCombinationOfString(String string) {
		List<String> combinations = new ArrayList<>();
		for (int i = string.length() - 1; i > -1; i--) {
			char cur = string.charAt(i);
			Object[] tmp = combinations.toArray();
			for (int j = 0; j < tmp.length; j++) {
				combinations.add(cur + (String)(tmp[j]));
			}
			combinations.add(String.valueOf(cur));
		}
		System.out.println(combinations.toString());
	}
}
