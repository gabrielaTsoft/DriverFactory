package Browser;

import org.openqa.selenium.WebDriver;

public class BrowserFactory {
    public static WebDriver getBrowser(String pantalla, String navegador) throws Throwable {
        String desiredBrowserName =  System.getProperty("browser" , navegador);
        WebDriver desiredBrowser = null;
        switch (desiredBrowserName){
            case "chrome":
                desiredBrowser = ChromeBrowser.buildChromeBrowser(pantalla);
            case "FireFox":
                desiredBrowser = FireFoxBrowser.buildFireFoxDriver();

                break;
            default:
                // TODO: 9/26/19 pgtoopx
                break;
        }
        return desiredBrowser;
    }
}

