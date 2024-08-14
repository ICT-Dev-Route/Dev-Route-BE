package com.teamdevroute.devroute.roadmap;

import com.teamdevroute.devroute.roadmap.domain.RoadmapStep;
import com.teamdevroute.devroute.roadmap.domain.RoadmapStepInfo;
import com.teamdevroute.devroute.roadmap.dto.DetailedRoadmapResponseDTO;
import com.teamdevroute.devroute.roadmap.dto.RoadmapDTO;
import com.teamdevroute.devroute.roadmap.repository.RoadmapStepInfoRepository;
import com.teamdevroute.devroute.roadmap.repository.RoadmapStepRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import static com.teamdevroute.devroute.roadmap.description.Ai.*;
import static com.teamdevroute.devroute.roadmap.description.Backend.*;
import static com.teamdevroute.devroute.roadmap.description.Frontend.*;
import static com.teamdevroute.devroute.roadmap.description.Mobile.*;
import static com.teamdevroute.devroute.roadmap.enums.DevelopmentField.*;

@Service
public class RoadmapService {
    private final RoadmapStepRepository roadmapStepRepository;
    private final RoadmapStepInfoRepository roadmapStepInfoRepository;



    public RoadmapService(RoadmapStepRepository roadmapStepRepository, RoadmapStepInfoRepository roadmapStepInfoRepository) {
        this.roadmapStepRepository = roadmapStepRepository;
        this.roadmapStepInfoRepository = roadmapStepInfoRepository;
    }

    public List<RoadmapDTO> findByDevelpmentField(String developmentField) {
        List<RoadmapStep> roadmapSteps = roadmapStepRepository.findByDevelopmentField(developmentField)
                .orElseThrow(()->new RuntimeException("해당 개발 분야에 대한 로드맵 단계가 없습니다: " + developmentField));
        if(roadmapSteps.isEmpty()){
            throw new RuntimeException("해당 개발 분야에 대한 로드맵 단계가 없습니다: " + developmentField);
        }
        return roadmapSteps.stream().map(roadmapStep -> RoadmapDTO.builder()
                .id(roadmapStep.getId())
                .name(roadmapStep.getName()).build()).collect(Collectors.toList());
    }
    public DetailedRoadmapResponseDTO findByDevelpmentFieldAndStepsName(String develpmentField, String stepsName) {
        RoadmapStep roadmapStep=roadmapStepRepository.findByNameAndDevelopmentField(stepsName, develpmentField).orElseThrow(
                ()->new RuntimeException("해당 이름과 개발 분야에 대한 로드맵 단계가 없습니다")
        );

        RoadmapStepInfo roadmapStepInfo = roadmapStepInfoRepository.findByRoadmapStep(roadmapStep)
                .orElseThrow(  ()->new RuntimeException("해당 로드맵 단계에 대한 정보가 없습니다")
                );

        return  DetailedRoadmapResponseDTO.builder()
                .description(roadmapStepInfo.getDescription())
                .related_tecks(roadmapStepInfo.getTechnology_stack())
                .brief_info(roadmapStepInfo.getBrief_info())
                .build();
    }
    public void updateAllRoadmaps() {
        if(roadmapStepRepository.count()==0) {
            updateRoadMap(stepsBackendNames, stepBackendBriefNames, stepsBackendDetailedDescription, String.valueOf(BACKEND),  stepsBackendRelatedStacks);
            updateRoadMap(stepsFrontendNames, stepsFrontendBriefNames, stepsFrontendDetailedDescrption, String.valueOf(FRONTEND), stepsfrontendRelatedStacks);
            updateRoadMap(stepsAiNames, stepsAiBriefNames, stepsAiDetailedDescription, String.valueOf(AIANDDATA),  stepsAiRelatedStacks);
            updateRoadMap(stepsIosNames, stepsIosBriefNames, stepsIosDetailedDescription, String.valueOf(MOBILE_IOS),  stepsIosRelatedStacks);
            updateRoadMap(stepsAndroidNames, stepsAndroidBriefNames, stepsAndroidDetailedDescription, String.valueOf(MOBILE_ANDROID),  stepsAndroidRelatedStacks);
        }
    }


    private void updateRoadMap(String[] stepNames, String[] stepBriefNames, String[] descriptions, String developmentField,
                               List<String>[] teck_stacks
    ) {
        for (int i = 0; i < stepNames.length; i++) {
            RoadmapStep roadmapstep=roadmapStepRepository.save(RoadmapStep.builder()
                    .name(stepNames[i])
                    .developmentField(developmentField)
                    .build());
            roadmapStepInfoRepository.save(RoadmapStepInfo.builder().roadmapStep(roadmapstep)
                    .technology_stack(teck_stacks[i])
                            .brief_info(stepBriefNames[i])
                    .description(descriptions[i])
                    .build());
        }

    }

    public RoadmapStep findById(Long id) {
        return roadmapStepRepository.findById(id)
                .orElseThrow(RoadmapStepNotFoundException::new);
    }
}
