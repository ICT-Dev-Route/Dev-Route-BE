package com.teamdevroute.devroute.global.aop.crawl;

import com.teamdevroute.devroute.crawling.WebDriverUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CrawlingAspect {

    public WebDriverUtil webDriverUtil;

    public CrawlingAspect(WebDriverUtil webDriverUtil) {
        this.webDriverUtil = webDriverUtil;
    }

    @After("@annotation(com.teamdevroute.devroute.global.aop.crawl.Crawling)")
    public void afterMethod() {
        webDriverUtil.closeChromeDriver();
    }
}
