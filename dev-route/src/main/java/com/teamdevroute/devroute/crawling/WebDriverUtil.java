package com.teamdevroute.devroute.crawling;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class WebDriverUtil {

    @Value("${crawling.link.jobplanet}")
    private String JobplanetURL;

    private WebDriver driver;

    public void getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.jobplanet.co.kr/companies?industry_id=700&_rs_act=industries&_rs_con=gnb&_rs_element=category");

        driver.quit();
    }
}
