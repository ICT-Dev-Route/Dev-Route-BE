package com.teamdevroute.devroute.roadmap;

import com.teamdevroute.devroute.roadmap.dto.DetailedRoadmapResponseDTO;
import com.teamdevroute.devroute.roadmap.dto.RoadmapDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.teamdevroute.devroute.roadmap.enums.DevelopmentField.BACKEND;
import static com.teamdevroute.devroute.roadmap.enums.DevelopmentField.FRONTEND;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RoadmapServiceTest {

    @Autowired
    private RoadmapService roadmapService;

    @BeforeEach
    public void setUp() {
        roadmapService.updateAllRoadmaps();
    }

    @DisplayName("백엔드 개발 분야에 해당하는 로드맵 단계는 10개이다")
    @Test
    public void getRoadmapListByDevelopmentField(){
        List<RoadmapDTO> byDevelpmentField = roadmapService.findByDevelpmentField(String.valueOf(BACKEND));
        Assertions.assertEquals(byDevelpmentField.size(),10);
    }
    @DisplayName("프론트엔드 개발 분야에 Html 단계가 존재한다.")
    @Test
    public void getDetaiedRoadmapByDevelopmentFieldAndSteps(){
        DetailedRoadmapResponseDTO html = roadmapService.findByDevelpmentFieldAndStepsName(String.valueOf(FRONTEND), "Html");
        Assertions.assertNotNull(html);
    }
    @DisplayName("개발 분야에 없고, 단계가 없을 시 Exception이 발생한다.")
    @Test
    public void throwDeveloperFieldOrStepsdNotFoundException(){
//        DetailedRoadmapResponseDTO html = roadmapService.findByDevelpmentFieldAndStepsName(String.valueOf(FRONTEND), "ssss");
        assertThrows(RuntimeException.class, () -> roadmapService.findByDevelpmentFieldAndStepsName(String.valueOf(FRONTEND), "SSS"));
    }
    @DisplayName("개발 분야에 없는 것을 찾을 시 Exception이 발생한다.")
    @Test
    public void throwDeveloperFieldNotFoundException(){
        assertThrows( RuntimeException.class,()->roadmapService.findByDevelpmentField("SSSS"));
    }
}