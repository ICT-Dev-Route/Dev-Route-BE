package com.teamdevroute.devroute.crawling;

import com.teamdevroute.devroute.video.dto.infrean.InfreanVideoDTO;
import com.teamdevroute.devroute.video.enums.TechnologyStackName;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.teamdevroute.devroute.user.enums.DevelopField.DATA_SCIENCE;
import static com.teamdevroute.devroute.video.constans.ApiConstans.INFREAN_CRAWRLING_URL_SEARCH;
import static com.teamdevroute.devroute.video.enums.TechnologyStackName.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VideoCrawlingTest {
    @Autowired
    private InfreanVideoCrawling infreanVideoCrawling;

    @DisplayName(("Web Driver가 잘 연결 되는지를 확인한다."))
    @Test
    public void isGetWebDriver() {
        WebDriver driver = infreanVideoCrawling.getWebDriver(String.valueOf(python));
        driver.get(INFREAN_CRAWRLING_URL_SEARCH + python + "&types=ONLINE");
        Assert.assertTrue(driver.getTitle() != null);
    }

    //이것보다 단위 테스트를 만드는 게 우선이 아닐까 고민됨.
    @DisplayName(("크롤링 후, 각 기술 스택에 대해서 title,url,thumnailurl,price를 잘 반환하는지 확인한다."))
    @Test
    public void isRightResponseWhenCrawling(){
        Assertions.assertTrue(Arrays.stream(TechnologyStackName.values())
                .allMatch(value -> {
                    ArrayList<InfreanVideoDTO> infreanVideoDTOS = infreanVideoCrawling.crawlingInfreanVideo(value.toLowerCaseHyphen());
                    return infreanVideoDTOS.stream().allMatch(
                            infreanVideoDTO ->
                                infreanVideoDTO.title() != null &&
                                        infreanVideoDTO.url() != null &&
                                        infreanVideoDTO.thumbnailUrl() != null &&
                                        infreanVideoDTO.price() != null
                    );
                }));

    }
    @DisplayName(("htmlcss,AI와 data science의 크롤링 후, 각 기술 스택에 대해서 title,url,thumnailurl,price를 잘 반환하는지 확인한다."))
    @Test
    public void isRightResponseWhenCrawlingAtAiAndDatascience(){
        String[] values = {
                String.valueOf(TechnologyStackName.DATA_SCIENCE.toLowerCaseHyphen()), String.valueOf(ARTIFICIAL_INTELLIGENCE.toLowerCaseHyphen()),
                String.valueOf(htmlcss)};
        Assertions.assertTrue(Arrays.stream(values)
                .allMatch(value -> {
                    ArrayList<InfreanVideoDTO> infreanVideoDTOS = infreanVideoCrawling.crawlingInfreanVideo(value);
                    return infreanVideoDTOS.stream().allMatch(
                            infreanVideoDTO ->
                                    infreanVideoDTO.title() != null &&
                                            infreanVideoDTO.url() != null &&
                                            infreanVideoDTO.thumbnailUrl() != null &&
                                            infreanVideoDTO.price() != null
                    );
                }));

    }


}
