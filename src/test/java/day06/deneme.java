package Day06;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;
public class deneme {
    static WebDriver driver;
    @BeforeClass
    public  static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }
    //2-Oturum aç butonunu tıkla.

    @Test
    public void test() {
        driver.get("https://www.kyani.com");
        System.out.println("ilk sayfanin handle degeri : "+driver.getWindowHandle());
        String ilkSayfaHandle=driver.getWindowHandle();
   driver.findElement(By.xpath("//*[@id=\"menu-item-89\"]/a")).click();
        Set<String> tumWindowHandlelari = driver.getWindowHandles();
        System.out.println("Tum Handlelar : "+tumWindowHandlelari);
                String ikinciWindowhandle="";
        for (String each:tumWindowHandlelari
        ) {
            if (!each.equals(ilkSayfaHandle)){
                ikinciWindowhandle=each;
            }
        }

        System.out.println("ikinci sayfa handle degeri : " + ikinciWindowhandle);

        driver.switchTo().window(ikinciWindowhandle);
       driver.findElement(By.id("username")).sendKeys("3802571");
       driver.findElement(By.id("password")).sendKeys("2019.Ismail");
      driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[3]/button")).click();

    }
    @AfterClass
    public  static void tearDown() {
       driver.close();
    }
}
