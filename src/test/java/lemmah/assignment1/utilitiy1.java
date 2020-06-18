package lemmah.assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
public class utilitiy1 {

	/**
	 * To read complete content from given file as String
	 * 
	 * @param filePath
	 * @return String
	 */
	public static String readTextFromFileAsString(String filePath) {
		try {
			// Verify if the file exists
			if (verifyFileExists(filePath)) {
				// Read File content as string - Works with JDK 11 or above
				// return Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);

				// Works with JDK 8 or below
				File file = new File(filePath);
				BufferedReader br = new BufferedReader(new FileReader(file));
				String fileContent = "";
				String line;
				while ((line = br.readLine()) != null)
					fileContent = fileContent + line + System.lineSeparator();
				br.close();
				return fileContent;
			}
		} catch (Exception e) {
			System.out.println("Failed to read the text from file." + e.getLocalizedMessage());
		}
		return null;
	}

	/**
	 * To verify if a file exists
	 * 
	 * @param filePath
	 * @return True/False
	 */
	public static boolean verifyFileExists(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			System.out.println("File not present - " + filePath);
			return false;
		}
		return true;
	}

}
