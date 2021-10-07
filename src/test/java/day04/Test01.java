package day04;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Test01 {
    public static void main(String[] args) {
//        1-Test01 isimli bir class olusturun
//        2- https://www.walmart.com/ adresine gidin
//        3- Browseri tam sayfa yapin
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.walmart.com/");
//        4-Sayfayi “refresh” yapin
        driver.navigate().refresh();
//        5- Sayfa basliginin “Save” ifadesi icerdigini control edin
        String actualTitle=driver.getTitle();
        String arananKelime="Save";
        if (actualTitle.contains(arananKelime)){
            System.out.println("title "+arananKelime+"kelimesini içeriyor, test PASS");
        }else{
            System.out.println("title "+arananKelime+"kelimesini içermiyor, test FAIL");
        }
//        6-Sayfa basliginin “Walmart.com | Save Money.Live Better” a esit oldugunu control ediniz
        String expectedTitle="Walmart.com | Save Money.Live Better";
        if (expectedTitle.equals(actualTitle)){
            System.out.println("sayfa title ı beklenen ile aynı , test PASS");
        }else{
            System.out.println("title eklenen ile aynı değil");
            System.out.println("actual title :"+actualTitle);
        }
//        7- URL in walmart.com icerdigini control edin
        String actualURL=driver.getCurrentUrl();
        String urlArananKelime="walmart.com";

        if (actualURL.contains(urlArananKelime)){
            System.out.println("url aranan kelimeyi içeriyor, test PASS");
        }else{
            System.out.println("url aranan kelimeyi içermiyor, test FAIL");
        }
        WebElement aramaKutusu=driver.findElement(By.xpath("//input[@name='q']"));
        aramaKutusu.sendKeys("Nutella"+ Keys.ENTER);
        //9- Kac sonuc bulundugunu yaziniz
        WebElement sonucYazisiElementi=driver.findElement(By.xpath("//div[@class='inline-flex mv3-xl mt3 ml3 ml4-xl mr1 db']"));
        System.out.println(sonucYazisiElementi.getText());
        //10-Sayfayi kapatin
        driver.close();
    }
}
