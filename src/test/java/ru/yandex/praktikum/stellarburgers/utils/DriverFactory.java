package ru.yandex.praktikum.stellarburgers.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverFactory {

    public static WebDriver createDriver(String browserName) {
        WebDriver driver;
        switch (browserName.toLowerCase()) {
            case "chrome":
                driver = createChromeDriver();
                break;
            case "yandex":
                driver = createYandexDriver();
                break;
            default:
                System.out.println("WARNING: Browser '" + browserName + "' not supported, using Chrome instead");
                driver = createChromeDriver();
                break;
        }
        
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-extensions");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1920,1080");
        return new ChromeDriver(options);
    }

    private static WebDriver createYandexDriver() {
        String driverPath = getLocalChromeDriverPath();
        if (driverPath != null) {
            System.setProperty("webdriver.chrome.driver", driverPath);
        } else {
            WebDriverManager.chromedriver().clearDriverCache().setup();
        }
            
        ChromeOptions options = new ChromeOptions();
        
        String yandexPath = getYandexBrowserPath();
        if (yandexPath != null) {
            options.setBinary(yandexPath);
        } else {
            throw new RuntimeException("Yandex Browser not found on this system");
        }
        
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-extensions");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1920,1080");
        
        return new ChromeDriver(options);
    }
    
    private static String getYandexBrowserPath() {
        String os = System.getProperty("os.name").toLowerCase();
        
        if (os.contains("mac")) {
            String macPath = "/Applications/Yandex.app/Contents/MacOS/Yandex";
            if (new java.io.File(macPath).exists()) {
                return macPath;
            }
        } else if (os.contains("win")) {
            String[] winPaths = {
                "C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe",
                "C:\\Program Files (x86)\\Yandex\\YandexBrowser\\Application\\browser.exe",
                "C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe"
            };
            for (String path : winPaths) {
                if (new java.io.File(path).exists()) {
                    return path;
                }
            }
        } else if (os.contains("linux")) {
            String[] linuxPaths = {
                "/usr/bin/yandex-browser",
                "/opt/yandex/browser/yandex-browser"
            };
            for (String path : linuxPaths) {
                if (new java.io.File(path).exists()) {
                    return path;
                }
            }
        }
        
        return null;
    }
    
    private static String getLocalChromeDriverPath() {
        String os = System.getProperty("os.name").toLowerCase();
        String driverName;
        
        if (os.contains("win")) {
            driverName = "chromedriver.exe";
        } else {
            driverName = "chromedriver";
        }
        
        try {
            String resourcePath = "/drivers/" + driverName;
            java.net.URL resource = DriverFactory.class.getResource(resourcePath);
            if (resource != null) {
                String path = resource.getPath();
                java.io.File file = new java.io.File(path);
                if (file.exists()) {
                    if (!os.contains("win")) {
                        file.setExecutable(true);
                    }
                    return path;
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to load local ChromeDriver: " + e.getMessage());
        }
        
        return null;
    }
    
}