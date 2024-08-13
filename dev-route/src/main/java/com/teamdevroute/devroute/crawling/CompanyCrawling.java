package com.teamdevroute.devroute.crawling;

import com.teamdevroute.devroute.crawling.dto.CrawledCompanyDto;
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

@Slf4j
@Component
public class CompanyCrawling {

    private final static String JOBPLANET_URL = "https://www.jobplanet.co.kr/companies?industry_id=700&_rs_act=industries&_rs_con=gnb&_rs_element=category";
    private final static String JOBPLANET_PAGE_2_URL = "https://www.jobplanet.co.kr/companies?industry_id=700&page=2";
    private final static String JOBPLANET_PAGE_3_URL = "https://www.jobplanet.co.kr/companies?industry_id=700&page=3";

    private WebDriverUtil webDriverUtil;

    private CompanyCrawlingService companyCrawlingService;

    public CompanyCrawling(WebDriverUtil webDriverUtil, CompanyCrawlingService companyCrawlingService) {
        this.webDriverUtil = webDriverUtil;
        this.companyCrawlingService = companyCrawlingService;
    }
    public CrawledCompanyDto getCompanyThreePage() {
        CrawledCompanyDto crawledCompanyDto = CrawledCompanyDto.builder()
                .enterpriseNames(new ArrayList<>())
                .enterpriseGrades(new ArrayList<>())
                .enterpriseSalaries(new ArrayList<>())
                .enterpriseLogo(new ArrayList<>())
                .build();

        for(int page=1;page<=3;page++) {
            crawledCompanyDto.mergeCrawledCompanyDto(getTenCompany(page));
        }

        return crawledCompanyDto;
    }

    private CrawledCompanyDto getTenCompany(int page) {

        String URL = switch (page) {
            case 2 -> JOBPLANET_PAGE_2_URL;
            case 3 -> JOBPLANET_PAGE_3_URL;
            default -> JOBPLANET_URL;
        };

        webDriverUtil.getChromeDriver(URL);
        WebDriver driver = webDriverUtil.getDriver();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 정보를 담을 JSON
        JSONObject info = new JSONObject();

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 현재 페이지의 소스코드 가져오기
        Document doc = Jsoup.parse(driver.getPageSource());

        // 상위 10개 기업 이름 및 연봉 가져오기
        List<String> enterpriseNames = new ArrayList<>();
        List<String> enterpriseSalaries = new ArrayList<>();
        List<String> enterpriseGrades = new ArrayList<>();
        List<String> enterpriseLogo = new ArrayList<>();

        // 팝업 제거를 위한 뒤로 갔다 앞으로 오기
        driver.navigate().back();
        driver.navigate().forward();

        CrawledCompanyDto crawledCompanyDto = null;


        try{  // Cloudflare 보안 페이지가 사라질 때까지 기다림
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("us_titb_l3")));

//            System.out.println(driver.findElements(By.cssSelector("#listCompanies > div > div.section_group > section:nth-child(1) > div > div > dl.content_col2_3.cominfo > dt > a")));
            // 상위 10개 기업 이름 받아서 List에 저장
            for(WebElement element : driver.findElements(By.className("us_titb_l3"))){

                String data = element.getText();

                if(data.isBlank()){
                    continue;
                }

                String subData = data.substring(0, data.length() - 9);
                enterpriseNames.add(subData);

            }
            for(WebElement element : driver.findElements(By.className("notranslate"))){
                String data = element.getText();
                enterpriseSalaries.add(data);
                log.info("CompanyCrawling enterpriseSalaries: " + data);
            }
            for(WebElement element : driver.findElements(By.className("gfvalue"))) {
                String data = element.getText();
                enterpriseGrades.add(data);
                log.info("CompanyCrawling enterpriseGrades: " + data);
            }
            for(WebElement element: driver.findElements(By.className("llogo"))){
                String data=element.findElement(By.tagName("img")).getAttribute("src");
                enterpriseLogo.add(element.findElement(By.tagName("img")).getAttribute("src"));
                log.info("CompanyCrawling enterpriseLogo: " + data);
            }

            // 연봉 리스트의 0, 1은 더미 데이터라 삭제
            enterpriseSalaries.remove(1);
            enterpriseSalaries.remove(0);

            crawledCompanyDto = CrawledCompanyDto.builder()
                    .enterpriseNames(enterpriseNames)
                    .enterpriseGrades(enterpriseGrades)
                    .enterpriseLogo(enterpriseLogo)
                    .enterpriseSalaries(enterpriseSalaries)
                    .build();

            companyCrawlingService.createCompany(crawledCompanyDto);

        } catch(Exception e){
            e.printStackTrace();
        }

        webDriverUtil.closeChromeDriver();

        return crawledCompanyDto;
    }
}