package day06;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class C04_Calisma {
    //Navigate to "https://react-shopping-cart-67954.firebaseapp.com/"
// List all the elements on the website
// Create a String Arraylist and put Product Names into the ArrayList
    //(bir Dize Dizi Listesi oluşturun ve Ürün Adlarını ArrayList'e yerleştirin)
// Choose 5 items randomly, add to cart and print the names of items
//// (Each product cannot be added more then 1 time!)
//// CHECK THE PRICES!
//// Sum each item's price and compare your result with the website
//// If results are matching print success message
//// Get string price values from Website, convert them to double and add to <Double> ArrayList and print the price list
//// Click Checkout
    //// Web sitesindeki tüm öğeleri listeleyin
//// Bir Dize Dizi Listesi oluşturun ve Ürün Adlarını ArrayList'e yerleştirin
//// Rastgele 5 öğe seçin, sepete ekleyin ve öğelerin adlarını yazdırın
//// (Her ürün 1 defadan fazla eklenemez!)
//
//// FİYATLARI GÖR!
//// Her bir öğenin fiyatını toplayın ve sonucunuzu web sitesiyle karşılaştırın
//// Sonuçlar eşleşiyorsa yazdırma başarısı mesajı
//// Web Sitesinden dize fiyat değerlerini alın, ikiye dönüştürün ve <Double> ArrayList'e ekleyin ve fiyat listesini yazdırın
//// Ödemeye Tıkla
    static WebDriver driver;
    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://react-shopping-cart-67954.firebaseapp.com/");
    }
    @Test
    public void test() throws InterruptedException {
        List<WebElement> elements = driver.findElements(By.className("shelf-item__title"));
        ArrayList<String> elementsInList = new ArrayList<>();
        elements.stream().forEach(t -> elementsInList.add(t.getText()));
        List<String> randomlySelectedItems = new ArrayList<>();
        while (randomlySelectedItems.size() != 5) {
            int randomNumeber = (int) (Math.random() * (elementsInList.size()));
            if (!randomlySelectedItems.contains(elementsInList.get(randomNumeber))) {
                randomlySelectedItems.add(elementsInList.get(randomNumeber));
                String item = "//p[text()='" + randomlySelectedItems.get(randomlySelectedItems.size() - 1) + "']";
                driver.findElement(By.xpath(item)).click();
                Thread.sleep(250);
                driver.findElement(By.xpath("//div[@class='float-cart__close-btn']")).click();
            }
        }
        Thread.sleep(250);
        driver.findElement(By.xpath("//span[@class='bag bag--float-cart-closed']")).click();
        randomlySelectedItems.stream().forEach(t -> System.out.println("-> " + t));
        double totalAmount = 0;
        ArrayList<Double> totalAmountList = new ArrayList<>();
        for (int i = 1; i < (randomlySelectedItems.size() + 1); i++) {
            Thread.sleep(250);
            String elementAmount = "(//div[@class='shelf-item__price']/p)[" + i + "]";
            String amount = driver.findElement(By.xpath(elementAmount)).getText();
            amount = amount.replace("$ ", "");
            double money = Double.parseDouble(amount);
            totalAmountList.add(money);
            totalAmount += money;
        }
        String strDouble = String.valueOf(totalAmount);
        strDouble = strDouble.substring(0, strDouble.indexOf(".")) + strDouble.substring(strDouble.indexOf("."), (strDouble.indexOf(".") + 3));
        if ((driver.findElement(By.className("sub-price__val")).getText().replace("$ ", "")).equals(strDouble)) {
            System.out.println("Test for 'Total Amount' is passed!");
        } else {
            System.out.println("Test for 'Total Amount' is failed!");
        }
        totalAmountList.stream().forEach(t -> System.out.println("$ " + t));
        driver.findElement(By.className("buy-btn")).click();
    }
     @AfterClass
    public static void tearDown() {
    driver.quit();
}
}