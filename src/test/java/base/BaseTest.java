package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import util.ConfigurationManager;

import java.time.Duration;

public class BaseTest {
    public RemoteWebDriver driver = null;
    @BeforeClass
    public void setUpBrowser() {
        String browser = ConfigurationManager.getProperty("browser");
        ConfigurationManager.loadConfig();

        if (ConfigurationManager.getProperty("browser").equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            initializeDriver();
        } else if (ConfigurationManager.getProperty("browser").equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
            initializeDriver();
        } else if (ConfigurationManager.getProperty("browser").equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
            initializeDriver();
        } else {
            System.out.println("Unsupported browser: " + browser);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    private void initializeDriver() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfigurationManager.getProperty("siteURl"));

    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}



