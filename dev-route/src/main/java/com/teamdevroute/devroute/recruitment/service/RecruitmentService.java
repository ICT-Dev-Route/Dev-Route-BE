package com.teamdevroute.devroute.recruitment.service;

import com.teamdevroute.devroute.company.domain.Company;
import com.teamdevroute.devroute.company.repository.CompanyRepository;
import com.teamdevroute.devroute.global.exception.CompanyNotFoundException;
import com.teamdevroute.devroute.recruitment.domain.Recruitment;
import com.teamdevroute.devroute.recruitment.dto.RecruitmentFindResponse;
import com.teamdevroute.devroute.recruitment.dto.TechStackFrequencyDto;
import com.teamdevroute.devroute.recruitment.repository.RecruitmentRepository;
import com.teamdevroute.devroute.recruitment.utils.TechnologyStackCategory;
import com.teamdevroute.devroute.user.enums.DevelopField;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final TechnologyStackCategory technologyStackCategory;
    private final CompanyRepository companyRepository;

    public List<RecruitmentFindResponse> findByType(String type) {
        DevelopField developField = DevelopField.toEnum(type);
        log.info("RecruitmentService - findByType(): type=" + developField);
        if(developField.equals(DevelopField.NONE)){
            throw new IllegalArgumentException("개발직군에 이상이 있습니다. 확인해주세요.");
        }
        List<Recruitment> result = recruitmentRepository.findByDevelopField(developField);
        return result.stream().map(RecruitmentFindResponse::from).toList();
    }

    public List<TechStackFrequencyDto> filterTechStackRate(DevelopField type) {
        List<Recruitment> recruitments = recruitmentRepository.findByDevelopFieldAndSource(type);

        Map<String, Integer> frequencyMap = new HashMap<>();

        for (Recruitment recruitment : recruitments) {
            List<String> techStacks = recruitment.getTechStacks();
            for (String tech : techStacks) {
                frequencyMap.put(tech, frequencyMap.getOrDefault(tech, 0) + 1);
            }
        }

        Set<String> categoryKeywords = technologyStackCategory.getStackCategoryByType(type);
        Map<String, Integer> stackCount = new LinkedHashMap<>();

        int otherCount = countTechStack(frequencyMap, categoryKeywords, stackCount);
        int totalCount = stackCount.values().stream().mapToInt(Integer::intValue).sum() + otherCount;

        List<TechStackFrequencyDto> result = stackCount.entrySet().stream()
                .map(entry -> TechStackFrequencyDto.of(entry.getKey(), entry.getValue(), totalCount))
                .sorted(Comparator.comparingDouble(TechStackFrequencyDto::percentage).reversed())
                .collect(Collectors.toList());

        return result;
    }

    private static int countTechStack(Map<String, Integer> frequencyMap, Set<String> categoryKeywords, Map<String, Integer> stackCount) {
        int otherCount = 0;
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            String tech = entry.getKey();
            int count = entry.getValue();

            if (categoryKeywords.contains(tech)) {
                if (count > 2) {
                    stackCount.put(tech, count);
                } else {
                    otherCount += count;
                }
            }
        }

        if (otherCount > 0) {
            stackCount.put("기타", otherCount);
        }

        return otherCount;
    }
}
