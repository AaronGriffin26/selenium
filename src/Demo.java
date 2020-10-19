import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Demo {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "E:\\selenium\\lib\\chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "E:\\selenium\\lib\\geckodriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//		googleTest1(driver);
		//		googleTest2(driver);
		seleniumPracticeTest1(driver);
		//		seleniumPracticeTest2(driver);
		//		seleniumPracticeTest3(driver);
		//		seleniumPracticeTest4(driver);
	}

	private static void googleTest1(WebDriver driver) {
		driver.get("http://google.com");
		driver.findElement(By.cssSelector("#tsf > div:nth-child(2) > div.A8SBwf > div.RNNXgb > div > div.a4bIc > input")).sendKeys("automated query");
		try {
			driver.findElement(By.xpath("//*[@id='tsf']/div[2]/div[1]/div[3]/center/input[1]")).click();
		}
		catch(ElementClickInterceptedException e) {
			driver.findElement(By.xpath("//*[@id='tsf']/div[2]/div[1]/div[2]/div[2]/div[2]/center/input[1]")).click();
		}
		driver.findElement(By.xpath("//*[contains(@id,'gb')]/div/div[1]/a")).click();
	}

	private static void googleTest2(WebDriver driver) {
		driver.get("http://google.com");
		driver.findElement(By.xpath("//*[contains(@id,'tsf')]/div[2]/div[1]/div[1]/div/div[2]/input")).click();
	}

	private static void seleniumPracticeTest1(WebDriver driver) {
		driver.get("https://rahulshettyacademy.com/seleniumPractise/");
		String[] itemsNeeded = { "Cucumber", "Brocolli", "Beetroot", "Carrot" };
		List<String> itemsNeededList = Arrays.asList(itemsNeeded);
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div/div[3]/following-sibling::div[1]/div[2]/a[2]")).click();
		List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));
		for(int i = 0, productsSize = products.size(); i < productsSize; i++) {
			WebElement p = products.get(i);
			String name = p.getText().split("-")[0].trim();
			if(itemsNeededList.contains(name))
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
		}
		driver.findElement(By.cssSelector("img[alt='Cart']")).click();
		driver.findElement(By.xpath("//button[contains(text(), 'PROCEED TO CHECKOUT')]")).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement promoCodeBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.promoCode")));
		promoCodeBox.sendKeys("rahulshettyacademy");
		driver.findElement(By.cssSelector("button.promoBtn")).click();
		WebElement promoInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.promoInfo")));
		Assert.assertTrue(promoInfo.getText().contains("Code applied"));
	}

	private static void seleniumPracticeTest2(WebDriver driver) {
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		Select s = new Select(driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency")));
		s.selectByValue("USD");
		driver.findElement(By.id("divpaxinfo")).click();
		for(int i = 1; i < 5; i++) {
			driver.findElement(By.id("hrefIncAdt")).click();
		}
		Assert.assertEquals(driver.findElement(By.id("divpaxinfo")).getText(), "5 Adult");
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
		driver.findElement(By.xpath("//a[@value='DEL']")).click();
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA']")).click();
		driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight")).click();

		driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();
		Assert.assertTrue(driver.findElement(By.id("Div1")).getAttribute("style").contains("1;"));

		driver.findElement(By.id("autosuggest")).sendKeys("Ind");
		List<WebElement> options = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));
		for(WebElement e : options) {
			if(e.getText().equalsIgnoreCase("India")) {
				e.click();
				break;
			}
		}
		Assert.assertFalse(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());
		driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());
		System.out.println(driver.findElements(By.cssSelector("input[type='checkbox']")).size());

		driver.findElement(By.cssSelector("input[type='submit']")).click();
	}

	private static void seleniumPracticeTest3(WebDriver driver) {
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		Assert.assertFalse(driver.findElement(By.id("checkBoxOption1")).isSelected());
		driver.findElement(By.id("checkBoxOption1")).click();
		Assert.assertTrue(driver.findElement(By.id("checkBoxOption1")).isSelected());
		driver.findElement(By.id("checkBoxOption1")).click();
		Assert.assertFalse(driver.findElement(By.id("checkBoxOption1")).isSelected());
		System.out.println(driver.findElements(By.cssSelector("input[type='checkbox']")).size());
		String text = "Aaron";
		driver.findElement(By.id("name")).sendKeys(text);
		driver.findElement(By.id("confirmbtn")).click();
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().dismiss();
	}

	private static void seleniumPracticeTest4(WebDriver driver) {
		driver.get("https://www.cleartrip.com/");
		Select s = new Select(driver.findElement(By.id("Adults")));
		s.selectByValue("2");
		s = new Select(driver.findElement(By.id("Childrens")));
		s.selectByValue("1");
		driver.findElement(By.id("DepartDate")).click();
		driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight.ui-state-active")).click();
		driver.findElement(By.id("MoreOptionsLink")).click();
		driver.findElement(By.id("AirlineAutocomplete")).sendKeys("indigo");
		driver.findElement(By.xpath("//input[contains(@value, 'Search')]")).click();
		System.out.println(driver.findElement(By.id("homeErrorMessage")).getText());
	}
}
