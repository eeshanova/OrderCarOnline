package io.duotech;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class OrderingACarOnline {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumFiles\\BrowserDrivers\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		//Step 1 - Go to lexus.com
		driver.get("https://www.lexus.com/");
		//Step 2 - Verify the title of the page contains “Experience Amazing”. 
		if (driver.getTitle().contains("Experience Amazing")) {
			System.out.println("PASS");
		}
		else {
			System.err.println("FAIL");
		}
		//Step 3 - Click on Do not Sell My Personal information at the bottom of the page.
		
		driver.findElement(By.xpath("//a[@href='https://privacy.toyota.com/']")).click();
		
		//Step 4 - Verify the page title contains “Privacy Hub”.
		
		String parentWindowHandle = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		
		for (String str : windowHandles) {
			if(!str.equals(parentWindowHandle)) {
				driver.switchTo().window(str);
				
			}
		}
		
		if (driver.getTitle().contains("Privacy Hub")) {
			System.out.println("PASS");
		}
		else {
			System.err.println("FAIL");
		}
		//Step 5 - Click on Your Privacy Rights.
		driver.findElement(By.xpath("//a[@href='privacy-hub/privacyright.html']")).click();
		
		//Step 6 - Verify that the page url is “https://privacy.toyota.com/privacy-hub/privacyright.html”.
		for (String str : windowHandles) {
			if(!str.equals(parentWindowHandle)) {
				driver.switchTo().window(str);
			}
		}
		if (driver.getCurrentUrl().equals("https://privacy.toyota.com/privacy-hub/privacyright.html")) {
			System.out.println("PASS");
		} else {
			System.err.println("FAIL");
		
		}
		
		driver.close();
		//Step 7 - Go back to the main window and click on Build your Lexus.
		driver.switchTo().window(parentWindowHandle);
		driver.findElement(By.xpath("//a[@href='/build-your-lexus/#!/']")).click();
		//Step 8 - Enter “22182” for zipcode and click on Enter on pop-up window.
		driver.findElement(By.xpath("//input[@id=\"zip-overlay\"]")).sendKeys("22182", Keys.ENTER);
		//Step 9 - Click on model GS.
		driver.findElement(By.xpath("//img[@src=\"/byl2014/pub-share/images/series/gs.png\"]")).click();
		
		//Step 10 - Choose 2020 GS 350 F Sport AWD. Before clicking, get the price of the vehicle and save it into an int variable.
		
		int price = Integer.parseInt((driver.findElement(By.xpath("//*[@id=\"trimModelSelectionItemView\"]/li[2]/div[2]/ul/li[2]/a/span[2]")).getText()).replaceAll("[, $ *]", "").trim());
//		System.out.println(price);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"trimModelSelectionItemView\"]/li[2]/div[2]/ul/li[2]/a")).click();
		
		//Step 11 - On the next page, click on the price menu on top and retrieve and store 
		//the base price, dp&h fee and msrp into separate int variables. Verify that the base price 
		//is the same as the price shown at Step 10.  Verify that msrp equals to base price + dp&h fee;
		
		driver.findElement(By.xpath("//span[@id='total-price']")).click();
		int displayedPrice = Integer.parseInt((driver.findElement(By.xpath("//*[@id=\"price-breakup-list\"]/li[1]/div/span")).getText()).replaceAll("[$ , ]", "").trim());
//		System.out.println(displayedPrice);
		int dphFee = Integer.parseInt((driver.findElement(By.xpath("//ul[@id='price-breakup-list']/li[4]/div/span")).getText()).replaceAll("[$ , ]", "").trim());
//		System.out.println(dphFee);
		int msrp = Integer.parseInt((driver.findElement(By.xpath("//span[@id='total-amount']")).getText()).replaceAll("[$ , ]", "").trim());
//		System.out.println(msrp);
		if (displayedPrice==price) {
			System.out.println("PASS");
		}
		else {
			System.err.println("FAIL");
		}
		
		if(msrp == (displayedPrice + dphFee)) {
			System.out.println("PASS");
		}
		else {
			System.err.println("FAIL");
		}
		
		//Step 12 - Close the menu and click on Ultrasonic Blue Mica color.
		
		driver.findElement(By.xpath("//div[@class='list-total list-close byl-js-price-breakdown-close']")).click();
		driver.findElement(By.xpath("//a[@href=\"javascript:void('08X1')\"]")).click();
		
		//Step 13 - Click on the price menu on top again and retrieve the price for color and store into int variable. 
		//Retrieve msrp one more time and verify that msrp  now equals to base price + dp&h fee + color
		
		driver.findElement(By.xpath("//span[@id='total-price']")).click();
		int bluePrice = Integer.parseInt((driver.findElement(By.xpath("//*[@id=\"price-breakup-list\"]/li[1]/div/span")).getText()).replaceAll("[$ , ]", "").trim());
//		System.out.println(bluePrice);
		int dphFeeBlue = Integer.parseInt((driver.findElement(By.xpath("//ul[@id='price-breakup-list']/li[4]/div/span")).getText()).replaceAll("[$ , ]", "").trim());
//		System.out.println(dphFeeBlue);
		int msrpBlue = Integer.parseInt((driver.findElement(By.xpath("//span[@id='total-amount']")).getText()).replaceAll("[$ , ]", "").trim());
//		System.out.println(msrpBlue);
		int colorPrice = Integer.parseInt((driver.findElement(By.xpath("//*[@id=\"price-breakup-list\"]/li[2]/div/span")).getText()).replaceAll("[$ , ]", "").trim());
//		System.out.println(colorPrice);
		
		if(msrpBlue == (bluePrice + dphFeeBlue + colorPrice)) {
			System.out.println("PASS");
		}
		else {
			System.err.println("FAIL");
		}
		
		//Step 14 - Close the price menu and click on Next:Interior button
		driver.findElement(By.xpath("//div[@class='list-total list-close byl-js-price-breakdown-close']")).click();
		driver.findElement(By.xpath("//a[@id=\"configurator-interior-color-selected\"]")).click();
		
		//Step 15 - Choose “Rioja Red leather with Naguri Aluminum trim” from the options .Click on Next:packages 
		
		driver.findElement(By.xpath("//a[@href = \"javascript:void('LB36')\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"FooterLink\"]/div/a[1]")).click();
		
		//Step 16 - In the next menu, click on add button for Mark Levinson sound system.
		
		driver.findElement(By.xpath("//a[@href=\"javascript:void('ML')\"]")).click();
		
		//Step 17 - Click on price menu again and retrieve and store the price for Sound system into int variable. 
		//Retrieve msrp once again and verify that msrp now equals to to base price + dp&h fee + color+sound system.
		
		driver.findElement(By.xpath("//span[@id='total-price']")).click();
		int basePrice = Integer.parseInt((driver.findElement(By.xpath("//*[@id=\"price-breakup-list\"]/li[1]/div/span")).getText()).replaceAll("[$ , ]", "").trim());
//		System.out.println(basePrice);
		int soundPrice = Integer.parseInt((driver.findElement(By.xpath("//ul[@id='price-breakup-list']/li[4]/div/span")).getText()).replaceAll("[$ , ]", "").trim());
//		System.out.println(soundPrice);
		int msrpSound = Integer.parseInt((driver.findElement(By.xpath("//span[@id='total-amount']")).getText()).replaceAll("[$ , ]", "").trim());
//		System.out.println(msrpSound);
		int colorSound = Integer.parseInt((driver.findElement(By.xpath("//*[@id=\"price-breakup-list\"]/li[2]/div/span")).getText()).replaceAll("[$ , ]", "").trim());
//		System.out.println(colorSound);
		int dphFeeSound = Integer.parseInt((driver.findElement(By.xpath("//ul[@id='price-breakup-list']/li[5]/div/span")).getText()).replaceAll("[$ , ]", "").trim());
//		System.out.println(dphFeeSound);
		
		if(msrpSound == (basePrice + dphFeeSound + colorSound + soundPrice)) {
			System.out.println("PASS");
		}
		else {
			System.err.println("FAIL");
		}
		
		//Step 18 - Click on Next:Accessories , on the next menu don’t add anything and click on Next:summary
		
		driver.findElement(By.xpath("//*[@id=\"FooterLink\"]/div/a[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"FooterLink\"]/div/a")).click();
		
		//Step 19- On the next page, retrieve msrp and verify that it is equal to the final msrp from step 17. 
		//Then click on Send to Dealer.
		
		int msrpFinal = Integer.parseInt((driver.findElement(By.className("title-price")).getText()).replaceAll("[$ , *]", "").trim());
		if(msrpSound == msrpFinal) {
			System.out.println("PASS");
			
		}
		else {
			System.err.println("FAIL");
		}
		
		driver.findElement(By.xpath("//a[@href='purchase-inquiry.html']")).click();
		
		//Step 20 - Next, first verify that the page contains “Send us Your Dream Car” text. 
		//Then enter the below information to the form fields, choose Pohanka as preferred dealer and click on submit.
		Thread.sleep(1000);
		String parentWindowHandleAgain = driver.getWindowHandle();
		Set<String> windowHandlesAgain = driver.getWindowHandles();
		
		for (String str : windowHandlesAgain) {
			if(!str.equals(parentWindowHandleAgain)) {
				driver.switchTo().window(str);
//				System.out.println(driver.getPageSource());
			}
		}
		
		if (driver.getPageSource().contains("Send us Your Dream Car")) {
			System.out.println("PASS");
		}
		else {
			System.err.println("FAIL");
		}
		
		
		driver.findElement(By.id("first-name")).sendKeys("John");
		driver.findElement(By.id("last-name")).sendKeys("Doe");
		driver.findElement(By.id("email")).sendKeys("anymail@gmail.com");
		driver.findElement(By.id("phone")).sendKeys("3127250272");
		Thread.sleep(3000);
//		driver.findElement(By.xpath("//input[@value='64504']")).click();
		WebElement dealer = driver.findElement(By.xpath("//input[@value='64504']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", dealer);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"preferred-dealer-form\"]/footer[2]/button")).click();
		
		//Step 21 - In the last page, verify that the page contains “We'll Be In Touch Shortly” text.
		Thread.sleep(1000);
		if (driver.getPageSource().contains("We'll Be In Touch Shortly")) {
			System.out.println("PASS");
		}
		else {
			System.err.println("FAIL");
		}
		
//		driver.close();
//		driver.quit();
	}

}
