package TestIdol;

import Browser.BrowserFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class Test01 {
    WebDriver driver;
    BrowserFactory na;
    @Before
    public void setUP() throws Throwable {
        this.driver = BrowserFactory.getBrowser("300,1080", "FireFox");

       // this.driver = BrowserFactory.getBrowser("300,1080", "chrome");

    }

    @Test
    public void test_01(){}

    @After
    public void cerrar(){
        driver.close();
    }


}
