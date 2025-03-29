package Assignment;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Assign {
	WebDriver driver;

	@BeforeClass()
	void Setup() {
		driver = new ChromeDriver();
		driver.get("https://www.republicworld.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test(priority = 0)
	void Home() {
		driver.findElement(By.xpath("//img[@alt='Dropdown arrow']")).click();
		List<WebElement> list = driver.findElements(By.xpath("//div[@id='fixedheader']//child::a"));
		System.out.println("Total Available Links are :"+ list.size());
		for (int i = 0; i < list.size(); i++) {
			String txt = list.get(i).getText();
			System.out.println(txt);
			if (txt.equalsIgnoreCase("Travel")) {
				list.get(i).click();
				System.out.println(driver.getTitle());
				driver.navigate().back();
			}
		}

	}

	@Test(priority = 1)
	void HomePageTitle() {
		String Actualtxt = driver.getTitle();
		String Expectedtxt = "Latest News, News Today, Breaking News, India News and Current News | Republic World";
		Assert.assertEquals(Actualtxt, Expectedtxt);
		System.out.println("Title of Home Page is: " + Actualtxt);

		
	}

	@Test(priority = 2)
	void TopTab() {
		List<WebElement> list = driver.findElements(By.xpath("//a[text()='Republic World']/../.."));
		System.out.println("Header Links are Mentioned Below");
		for (WebElement el : list) {
			String title = el.getText();
			System.out.println(title);
		}

	}

	@Test(priority = 3)
	void Story() {

		WebElement dv=driver.findElement(By.xpath("//h2[text()='Trending']/..//child::div//child::div[@class='grid  svelte-ooozxy']"));
		int size=dv.findElements(By.tagName("a")).size();
		
		System.out.println(size);
		
		for(int i=0;i<size;i++) {
		String title=dv.findElements(By.tagName("a")).get(i).getText();
		
		System.out.println(title);
		
		String tab=Keys.chord(Keys.CONTROL,Keys.ENTER);
			dv.findElements(By.tagName("a")).get(i).sendKeys(tab);
	}}

}
