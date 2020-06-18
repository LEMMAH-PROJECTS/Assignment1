package lemmah.assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class utility {
	
	/**
	 * To read complete content from given file
	 * 
	 * @param filePath
	 * @return List<String>
	 */
	public static List<String> readTextFromFile(String filePath) {
		try {
			// Verify if the file exists
			if (verifyFileExists(filePath)) {
				// Read File content as List<String> - Works with JDK 11 or above
				// return Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);

				// Works with JDK 8 or below
				File file = new File(filePath);
				BufferedReader br = new BufferedReader(new FileReader(file));
				List<String> fileContent = new ArrayList<String>();
				String line;
				while ((line = br.readLine()) != null)
					fileContent.add(line);
				br.close();
				return fileContent;
			}
		} catch (Exception e) {
			System.out.println("Failed to read the text from file." + e.getLocalizedMessage());
		}
		return null;
	}

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
	 * Find the line index which contains the given text
	 * 
	 * @param fileContent, textToSearch
	 * @return index
	 */
	public static int findLineIndex(List<String> fileContent, String textToSearch) {
		try {
			int index = -1;
			for (index = 0; index < fileContent.size(); index++) {
				if (fileContent.get(index).contains(textToSearch))
					break;
			}
			return index;
		} catch (Exception e) {
			System.out
					.println("Failed to find the line index which contains the given text." + e.getLocalizedMessage());
			return -1;
		}
	}

	/**
	 * To write string array to file
	 * 
	 * @param filePath, fileContent
	 * @return boolean
	 */
	public static boolean writeTextToFile(String filePath, List<String> fileContent) {
		try {
			Files.write(Paths.get(filePath), fileContent);
			// Verify if the file exists
			if (verifyFileExists(filePath)) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("Failed to write the text to file." + e.getLocalizedMessage());
		}
		return false;
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
