package com.teamdevroute.devroute.crawling;

import com.teamdevroute.devroute.crawling.dto.CrawledRecruitmentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class RecruitmentCrawling {

    private final static int CRAWLING_RECRUIT_NUM_MAX = 10;
    private final static String JUMPIT_URL = "https://www.jumpit.co.kr/search?sort=relation&";

    private WebDriverUtil webDriverUtil;
    private RecruitmentCrawlingService recruitmentCrawlingService;

    public RecruitmentCrawling(WebDriverUtil webDriverUtil) {
        this.webDriverUtil = webDriverUtil;
    }

    public List<CrawledRecruitmentDto> crawlingJUMPIT(List<String> enterpriseNames) {

        List<CrawledRecruitmentDto> crawledRecruitmentDtoList = new ArrayList<>(10);
        try {

            for (int i = 0; i < enterpriseNames.size(); i++) {
                webDriverUtil.getChromeDriver(JUMPIT_URL+ enterpriseNames.get(i));
                WebDriver driver = webDriverUtil.getDriver();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


                driver.manage().window().maximize();


                Thread.sleep(100);
                int idx = 0;

                for (int j = 1; j <= CRAWLING_RECRUIT_NUM_MAX; j++) {
                    String css = "body > main > div > section.sc-c12e57e5-3.gjgpzi > section > div:nth-child(" + j + ") > a > div.sc-15ba67b8-0.kkQQfR > div > div";


                    WebElement element = driver.findElement(By.cssSelector(css));
                    if(element == null){
                        break;
                    }

                    String companyName = element.getText();
                    log.info("RecruitmentCrawling CompanyName: " + companyName);
                    crawledRecruitmentDtoList.add(
                            CrawledRecruitmentDto.builder().companyName(companyName).build()
                    );
                }

                idx = 0;
                for (WebElement element : driver.findElements(By.className("position_card_info_title"))) {
                    if(idx == CRAWLING_RECRUIT_NUM_MAX){
                        break;
                    }

                    String title = element.getText();
                    log.info("RecruitmentCrawling Title: " + title);
                    crawledRecruitmentDtoList.get(idx++).setTitle(title);
                }

                idx = 0;
                for(int j = 1; j <= CRAWLING_RECRUIT_NUM_MAX; j++) {
                    if(idx == CRAWLING_RECRUIT_NUM_MAX){
                        break;
                    }
                    String css = "body > main > div > section.sc-c12e57e5-3.gjgpzi > section > div:nth-child(" + j + ") > a > div.sc-15ba67b8-0.kkQQfR > ul.sc-15ba67b8-1.iFMgIl";
                    WebElement ul = driver.findElement(By.cssSelector(css));

                    List<WebElement> ilList = ul.findElements(By.tagName("li"));
                    List<String> techList = new ArrayList<>();

                    ilList.forEach(e -> techList.add(e.getText()));

                    log.info("RecruitmentCrawling TechList: "+techList.toString());
                    crawledRecruitmentDtoList.get(idx++).setTechList(techList);
                }

                idx = 0;
                for(int j= 1;j<=CRAWLING_RECRUIT_NUM_MAX;j++){
                    if(idx == CRAWLING_RECRUIT_NUM_MAX){
                        break;
                    }
                    String css = "body > main > div > section.sc-c12e57e5-3.gjgpzi > section > div:nth-child(" + j + ") > a > div.sc-15ba67b8-0.kkQQfR > ul.sc-15ba67b8-1.cdeuol";
                    WebElement ul = driver.findElement(By.cssSelector(css));

                    List<WebElement> ilList = ul.findElements(By.tagName("li"));
                    log.info("RecruitmentCrawling Listsize: "+ ilList.size());
                    String area = ilList.get(0).getText();
                    String career = ilList.get(1).getText();
                    crawledRecruitmentDtoList.get(idx).setArea(area);
                    crawledRecruitmentDtoList.get(idx++).setCareer(career);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        webDriverUtil.closeChromeDriver();

        return crawledRecruitmentDtoList;
    }
}