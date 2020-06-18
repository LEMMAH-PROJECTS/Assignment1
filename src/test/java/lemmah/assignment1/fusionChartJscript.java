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
}
