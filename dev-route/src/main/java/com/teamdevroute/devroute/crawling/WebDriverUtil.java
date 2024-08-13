package com.teamdevroute.devroute.crawling;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@Getter
public class WebDriverUtil {

    private WebDriver driver;

    public void getChromeDriver(String url) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        setChromeOption(chromeOptions);

        driver = new ChromeDriver(chromeOptions);

        // 10초까지 기다려준다. 10초 안에 웹 화면이 표시되면 바로 다음 작업이 진행됨
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        driver.get(url);
//        time.sleep(5)
    }

    private void setChromeOption(ChromeOptions chromeOptions) {
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--lang=ko");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-gpu");  // gpu 비활성화
        //Cloudfare의 보안을 우회하는 코드
        //User-Agent는 웹 서버가 클라이언트의 브라우저 및 운영 체제 정보를 알 수 있게 해주는 문자열
        chromeOptions.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        //false로 설정하여 웹 사이트가 브라우저가 자동화된 환경에서 실행되고 있다는 것을 감지하지 못하도록 힘.
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
        //ChromeDriver가 기본적으로 로드하는 자동화 확장 기능을 사용하지 않도록 힘. 이를 통해 자동화 탐지를 피할 수 있음.
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        //Chrome이 자동화된 환경에서 실행되고 있다는 경고 메시지를 비활성화. enable-automation 스위치를 제외하여 브라우저에서 자동화가 감지되는 것을 막음
        chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
    }

    public void closeChromeDriver() {
        driver.quit();
    }
}
