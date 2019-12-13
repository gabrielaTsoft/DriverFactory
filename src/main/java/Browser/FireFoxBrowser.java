package Browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import static io.github.bonigarcia.wdm.DriverManagerType.FIREFOX;

import java.io.File;

public class FireFoxBrowser extends FirefoxDriver {

    private static WebDriver driver = null;

    public static WebDriver buildFireFoxDriver(){
        WebDriverManager.getInstance(FIREFOX).setup();
        //WebDriver driver = new FirefoxDriver();
        //driver.manage().window().maximize();
        FirefoxOptions options = new FirefoxOptions();
        WebDriver driver = new FirefoxDriver(options);
        options.addArguments("--window-size=1920,1080");









        return driver;
    }
}
