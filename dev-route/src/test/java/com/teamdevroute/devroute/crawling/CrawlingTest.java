package com.teamdevroute.devroute.crawling;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CrawlingTest {
    @Test
    void driver_connect() {
        WebDriverUtil webDriverUtil = new WebDriverUtil();
        webDriverUtil.getChromeDriver();
        //driver.get("https://www.jobplanet.co.kr/companies?industry_id=700&_rs_act=industries&_rs_con=gnb&_rs_element=category");

    }
}
