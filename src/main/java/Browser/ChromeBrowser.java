package Browser;

import com.google.gson.Gson;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ChromeBrowser extends ChromeDriver {
    /**
     *Se maneja opciones
     * @param pantalla
     * @return
     * @throws Throwable
     */
    public static WebDriver buildChromeBrowser(String pantalla) throws Throwable {

        File driverFile = new File("/usr/bin/chromedriver");
        if(!driverFile.exists()) WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        String downloadFilepath = System.getProperty("user.dir")+ File.separator+"target"+File.separator+"downloads";
        System.setProperty("browserfactory.chrome.downloadpath", downloadFilepath);
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("cmd", "Page.setDownloadBehavior");
        chromePrefs.put("behavior", "allow");
        chromePrefs.put("download.prompt_for_download", "false");
        chromePrefs.put("download.directory_upgrade", "true");
        chromePrefs.put("safebrowsing.enabled", "false");
        chromePrefs.put("safebrowsing.disable_download_protection", "true");
        chromePrefs.put("download.default_directory", downloadFilepath);

        options.setExperimentalOption("prefs", chromePrefs);

        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-notifications");
        options.addArguments("disable-infobars");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--disable-extensions");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-gpu");

        String windowSize = System.getProperty("window-size", pantalla);
        options.addArguments(String.format("--window-size=%s", windowSize));

        String headless = System.getProperty("headless", "false");
        if("true".equals(headless)) options.addArguments("headless");

        //Para la descarga
        ChromeDriverService driverService = ChromeDriverService.createDefaultService();
        ChromeDriver driver = new ChromeDriver(driverService,options);

        Map<String, Object> commandParams = new HashMap<>();
        commandParams.put("cmd", "Page.setDownloadBehavior");

        Map<String, String> params = new HashMap<>();
        params.put("behavior", "allow");
        params.put("downloadPath", downloadFilepath);
        commandParams.put("params", params);

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        Gson gson = new Gson();
        String command = gson.toJson(commandParams);

        String u = driverService.getUrl().toString() + "/session/" + driver.getSessionId() + "/chromium/send_command";

        System.setProperty("browserfactory.chrome.driverServiceUrl", u);

        HttpPost request = new HttpPost(u);
        request.addHeader("content-type", "application/json");
        request.setEntity(new StringEntity(command));
        httpClient.execute(request);

        return driver;
    }

    private ChromeBrowser(){
        super();
    }

}
