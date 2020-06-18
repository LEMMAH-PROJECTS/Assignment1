package lemmah.assignment1;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class fusionChartJscript {

	static String jsonFile = System.getProperty("user.dir") + "/Files/json.txt";
	static String htmlFile = "file:" + System.getProperty("user.dir") + "/Files/FusionExport.html";

	@Test
	public static void Assignment1() {
		System.out.println("HTML File Location : " + htmlFile);
		String chartData = utilitiy1.readTextFromFileAsString(jsonFile);
		if (chartData == null) {
			System.out.println("Failed to read the json chart data.");
			return;
		}

		// Set the path of the ChromeDriver
		if (OSValidator.isWindows()) {
			System.out.println("This is Windows");
			System.setProperty("webdriver.chrome.driver",
					"" + System.getProperty("user.dir") + "/drivers/windows/chromedriver.exe");
		} else if (OSValidator.isMac()) {
			System.out.println("This is Mac");
			System.out.println(htmlFile);
			System.setProperty("webdriver.chrome.driver",
					"" + System.getProperty("user.dir") + "/drivers/mac/chromedriver");
		}

		try {
			ChromeDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			// Launch the HTML file
			driver.get(htmlFile);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// Replace the content and render the chart again
			js.executeScript("window.chartConfig=" + chartData + ";");
			js.executeScript("FusionCharts.ready(function(){\r\n"
					+ "                var fusioncharts = new FusionCharts(chartConfig);\r\n"
					+ "                fusioncharts.render();\r\n" + "            });");
			System.out.println("Successfully updated the json content to HTML file.");
			Thread.sleep(10000);
			driver.close();
			driver.quit();
		}

		catch (Exception ex) {
			System.out.println("Failed to replace the chart data in the html." + ex.getMessage());
		}

	}

	@AfterTest
	public void afterTest() {
		/*
		 * 
		 * try {
		 * 
		 * if (OSValidator.isWindows()) { System.out.println("This is Windows");
		 * System.setProperty("webdriver.chrome.driver",
		 * "/Volumes/MyDrive/Automation/AutomationPractice/Lemmah/drivers/windows/chromedriver.exe"
		 * ); WebDriver driver = new ChromeDriver();
		 * driver.manage().window().maximize();
		 * driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);
		 * driver.get(htmlFile); } else if (OSValidator.isMac()) {
		 * System.out.println("This is Mac"); System.out.println(htmlFile);
		 * System.setProperty("webdriver.chrome.driver",
		 * "/Volumes/MyDrive/Automation/AutomationPractice/Lemmah/drivers/mac/chromedriver"
		 * ); WebDriver driver = new ChromeDriver();
		 * driver.manage().window().maximize();
		 * driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS); String
		 * filepath = "file://" + htmlFile; System.out.println("Final file path :" +
		 * filepath); driver.get(filepath);
		 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 * driver.navigate().to(driver.getCurrentUrl()); driver.navigate().refresh(); }
		 * else { System.out.println("Your OS is not support!!"); } } catch (Exception
		 * e) { // TODO Auto-generated catch block System.out.println(e.getMessage());
		 * // e.printStackTrace(e.getMessage()); }
		 * 
		 * 
		 * }
		 * 
		 */}
}
