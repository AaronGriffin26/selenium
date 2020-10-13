import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "E:\\selenium\\lib\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "E:\\selenium\\lib\\geckodriver.exe");
        WebDriver driver = new ChromeDriver();
//        googleTest1(driver);
//        googleTest2(driver);
//        seleniumPracticeTest1(driver);
//        seleniumPracticeTest2(driver);
//        seleniumPracticeTest3(driver);
        seleniumPracticeTest4(driver);
    }

    private static void googleTest1(WebDriver driver) {
        driver.get("http://google.com");
        driver.findElement(By.cssSelector("#tsf > div:nth-child(2) > div.A8SBwf > div.RNNXgb > div > div.a4bIc > input")).sendKeys("automated query");
        try {
            driver.findElement(By.xpath("//*[@id='tsf']/div[2]/div[1]/div[3]/center/input[1]")).click();
        } catch(ElementClickInterceptedException e) {
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
        wait(1500);
        driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div/div[3]/following-sibling::div[1]/div[2]/a[2]")).click();
        driver.findElement(By.xpath("//*[text()='Tomato - 1 Kg']/following-sibling::div[2]/button")).click();
    }

    private static void seleniumPracticeTest2(WebDriver driver) {
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        Select s = new Select(driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency")));
        s.selectByValue("USD");
        driver.findElement(By.id("divpaxinfo")).click();
        wait(500);
        for(int i = 1; i < 5; i++) {
            driver.findElement(By.id("hrefIncAdt")).click();
        }
        Assert.assertEquals(driver.findElement(By.id("divpaxinfo")).getText(), "5 Adult");
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        wait(500);
        driver.findElement(By.xpath("//a[@value='DEL']")).click();
        wait(500);
        driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA']")).click();
        wait(500);
        driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight")).click();

        driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();
        Assert.assertTrue(driver.findElement(By.id("Div1")).getAttribute("style").contains("1;"));

        driver.findElement(By.id("autosuggest")).sendKeys("Ind");
        wait(1000);
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
        wait(4000);
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

    private static WebElement findElementWithAttempts(WebDriver driver, By by) {
        NoSuchElementException exception = new NoSuchElementException("Fake");
        for(int i = 0; i < 10; i++) {
            try {
                return driver.findElement(by);
            } catch(NoSuchElementException e) {
                exception = e;
                if(i < 9)
                    wait(111);
            }
        }
        throw exception;
    }

    private static void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch(InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
