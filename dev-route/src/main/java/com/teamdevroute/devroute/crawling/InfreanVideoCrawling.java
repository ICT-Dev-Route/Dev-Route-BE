package com.teamdevroute.devroute.crawling;

import static com.teamdevroute.devroute.video.constans.ApiConstans.*;

import com.teamdevroute.devroute.video.dto.infrean.InfreanVideoDTO;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InfreanVideoCrawling {

    public ArrayList<InfreanVideoDTO> crawlingInfreanVideo(String teck_stack){
        WebDriver driver = getWebDriver(teck_stack);
        ArrayList infreanVideos = new ArrayList<InfreanVideoDTO>();
        try {
            //WebElement 추출
            List<WebElement> lectures = getLectures(driver);
            // 각 강의 요소를 순회하며 데이터 추출
            infreanVideos=getDataInLectureElements(lectures);
        } catch (Exception e) {
            log.error("An unexpected error occurred: " + e.getMessage(), e);
        }
        driver.quit();
        return infreanVideos;
    }

    private ArrayList getDataInLectureElements(List<WebElement> lectures) {
        ArrayList result = new ArrayList<InfreanVideoDTO>();
        for (WebElement lecture : lectures) {
            if (isMaxSize(result)) {
                break;
            }
            try {
                String thumbnailUrl = getUrl(lecture, "div.mantine-AspectRatio-root img", "src", "No image");
                String lectureUrl = getUrl(lecture, "a", "href", "No URL");
                String title = getTitle(lecture.getText());
                String price=getPrice(lecture.getText());
                InfreanVideoDTO infreanVideoDTO = new InfreanVideoDTO(lectureUrl,title,thumbnailUrl,Long.valueOf(price.replaceAll("[^\\d]", "")));
                result.add(infreanVideoDTO);
            } catch (org.openqa.selenium.NoSuchElementException e) {
                log.info("Element not found in this lecture element: " + e.getMessage());
            }
        }
        return result;
    }

    private static boolean isMaxSize(ArrayList result) {
        return result.size() >= 12;
    }

    private String getUrl(WebElement lecture, String cssSelector, String src, String x) {
        // 썸네일 이미지 URL 추출
        WebElement thumbnailElement = lecture.findElement(
                By.cssSelector(cssSelector));
        String thumbnailUrl = thumbnailElement != null ? thumbnailElement.getAttribute(src) : x;
        return thumbnailUrl;
    }
    private String getPrice(String lectureText) {
        // 가격은 "원"이 마지막에 있고, 숫자로 변환했을 때, 숫자 인것만 반환함
        String[] lines = lectureText.split("\n");
        for (String line : lines) {
            if (line.trim().endsWith("원")&&line.replaceAll("[^\\d]", "")!="") {
                return line.replaceAll("[^\\d]", "");
            }
        }
        return "0";
    }
    private String getTitle(String lectureText){
        String[] lines = lectureText.split("\n");
        for (String line : lines) {
            if (line.charAt(0) == '할' && line.charAt(1) == '인') {
                return lines[1];
            } else {
                return line;
            }
        }
        return "0";
    }
    private List<WebElement> getLectures(WebDriver driver) {
        // 페이지가 로드될 때까지 잠시 대기 (필요에 따라 조정)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul.css-y21pja li.mantine-1avyp1d")));
            // 강의 목록 요소를 선택
            List<WebElement> lectures = driver.findElements(By.cssSelector("ul.css-y21pja li.mantine-1avyp1d"));
            return lectures;
    }

    public WebDriver getWebDriver(String teck_stack) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);
        switch (teck_stack) {
            case "android":
            case "ios":
                driver.get(INFREAN_CRAWRLING_URL_SEARCH_MOBILE + teck_stack + "&types=ONLINE");
                return driver;
            case "data-science":
            case "artificial-intelligence":
                driver.get(INFREAN_CRAWRLING_URL_SEARCH_MOBILE_AI + teck_stack + "?types=ONLINE");
                return driver;
            case "htmlcss":
                driver.get(INFREAN_CRAWRLING_URL_SEARCH + "html-css" + "&types=ONLINE");
                return driver;
            case "angular":
                driver.get("https://www.inflearn.com/courses?s=angular");
                return driver;
        }
        driver.get(INFREAN_CRAWRLING_URL_SEARCH+teck_stack+"&types=ONLINE");
        return driver;
    }

}
