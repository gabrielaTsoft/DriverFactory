package Browser;

import org.openqa.selenium.WebDriver;

public class BrowserFactory {
    public static WebDriver getBrowser(String pantalla) throws Throwable {
        String desiredBrowserName =  System.getProperty("browser" , "chrome");
        WebDriver desiredBrowser = null;
        switch (desiredBrowserName){
            case "chrome":
                desiredBrowser = ChromeBrowser.buildChromeBrowser(pantalla);

                break;
            default:
                // TODO: 9/26/19 pgtoopx
                break;
        }
        return desiredBrowser;
    }
}

