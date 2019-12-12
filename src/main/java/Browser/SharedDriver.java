package Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import static Browser.BrowserFactory.getBrowser;


public class SharedDriver extends EventFiringWebDriver {
    private static final WebDriver REAL_DRIVER;
    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            REAL_DRIVER.close();

        }
    };

    static {
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
        try {
            String pantalla = null;
            REAL_DRIVER = getBrowser(pantalla);
        } catch (Throwable throwable) {
            throw new Error(throwable);
        }
    }

    public SharedDriver() {
        super(REAL_DRIVER);
    }

    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
    }
}
