package day05;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class Test01 {

    WebDriver driver;
    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
    }
    //1-Test01 isimli bir class olusturun
    @Test
    public void test01() throws InterruptedException {
        //2- https://www.amazon.com/ adresine gidin
        driver.get("https://www.amazon.com");
        //3- Browseri tam sayfa yapin
        driver.manage().window().maximize();
        Thread.sleep(2000);
        //4-Sayfayi “refresh” yapin
        driver.navigate().refresh();
        Thread.sleep(2000);
        //5- Sayfa basliginin “Amazon” ifadesi icerdigini control edin
        String actuallTitle= driver.getTitle();
        String title="Amazon";
        if (actuallTitle.contains(title)){
            System.out.println("Amazon ifadesi içeriği testi PASSED");
        }else {
            System.out.println("Amazon ifadesi içeriği testi FAILED");
        }
        Thread.sleep(2000);
        //6-Sayfa basliginin “Amazon.com. Spend less. Smile more.” a esit oldugunu control ediniz
        String expectedTitle=  "Amazon.com. Spend less. Smile more.";
        if (actuallTitle.equals(expectedTitle)){
            System.out.println("Sayfa basligi testi PASSED");
        }else {
            System.out.println("Sayfa basligi testi FAILED");
        }
        Thread.sleep(2000);
        //7- URL in amazon.com icerdigini control edin
        String actuallURL=driver.getCurrentUrl();
        String expectedURL="amazon.com";
        if (actuallURL.contains(expectedURL)){
            System.out.println("amazon.com ifadesi içeriği testi PASSED");
        }else{
            System.out.println("amazon.com ifadesi içeriği testi FAILED");
        }
        //8-”Nutella” icin arama yapiniz
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("NUTELLA"+ Keys.ENTER);
        Thread.sleep(2000);
        //9- Kac sonuc bulundugunu yaziniz
        WebElement result=driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']"));
        System.out.println(result.getText());
    }
    //10-Sayfayi kapatin
    @After
    public void tearDown () {
        driver.close();
    }
}
