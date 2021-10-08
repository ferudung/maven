package day04;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Test03 {
    public static void main(String[] args) {
//        1. “https://www.saucedemo.com” Adresine gidin
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com");
//        2. Username kutusuna “standard_user” yazdirin
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
//        3. Password kutusuna “secret_sauce” yazdirin
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
//        4. Login tusuna basin
        driver.findElement(By.id("login-button")).click();
//        5. Ilk urunun ismini kaydedin ve bu urunun sayfasina gidin
        WebElement ilkIsım=driver.findElement(By.xpath("//a[@id='item_4_title_link']"));
        System.out.println(ilkIsım.getText());
//        6. Add to Cart butonuna basin
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
//        7. Alisveris sepetine tiklayin
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
//        8. Sectiginiz urunun basarili olarak sepete eklendigini control edin
        WebElement urunKontrol=driver.findElement(By.xpath("//a[@id='item_4_title_link']"));
        System.out.println(urunKontrol.getText());
//        9. Sayfayi kapatin
        driver.close();

    }
}
