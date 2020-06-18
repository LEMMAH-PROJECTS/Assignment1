package lemmah.assignment1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;



public class fusionChart {

	static String jsonFile = "/Volumes/MyDrive/Automation/AutomationPractice/assignment1/Files/json.txt";
	static String htmlFile = "/Volumes/MyDrive/Automation/AutomationPractice/assignment1/Files/FusionExport.html";

	@Test
	public static void Assignment1() {

		List<String> htmlContent = utility.readTextFromFile(htmlFile);
		String jsonContent = utility.readTextFromFileAsString(jsonFile);
		if (jsonContent == null) {
			System.out.println("Failed to get the json content.");
			return;
		}

		int lineIndex = -1;
		if (htmlContent != null) {
			lineIndex = utility.findLineIndex(htmlContent, "var chartConfig");
		}
		if (lineIndex == -1) {
			System.out.println("Failed to find the line index from HTML content.");
			return;
		}

		String newValue = htmlContent.get(lineIndex).replace("var chartConfig", "var chartConfig=" + jsonContent);
		htmlContent.set(lineIndex, newValue);

		// Add Timestamp to the file
		SimpleDateFormat ft = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
		htmlFile = htmlFile.replace(".html", "_" + ft.format(new Date()) + ".html");
		utility.writeTextToFile(htmlFile, htmlContent);
		System.out.println("Successfully updated the json content to HTML file.");
	}

	@AfterTest
	public void afterTest() {
		try {

			if (OSValidator.isWindows()) {
				System.out.println("This is Windows");
				System.setProperty("webdriver.chrome.driver",
						"/Volumes/MyDrive/Automation/AutomationPractice/Lemmah/drivers/windows/chromedriver.exe");
				WebDriver driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);
				driver.get(htmlFile);
			} else if (OSValidator.isMac()) {
				System.out.println("This is Mac");
				System.out.println(htmlFile);
				System.setProperty("webdriver.chrome.driver",
						"/Volumes/MyDrive/Automation/AutomationPractice/Lemmah/drivers/mac/chromedriver");
				WebDriver driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);
				String filepath = "file://" + htmlFile;
				System.out.println("Final file path :" + filepath);
				driver.get(filepath);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.navigate().to(driver.getCurrentUrl());
				driver.navigate().refresh();
			} else {
				System.out.println("Your OS is not support!!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			// e.printStackTrace(e.getMessage());
		}

	}
}
