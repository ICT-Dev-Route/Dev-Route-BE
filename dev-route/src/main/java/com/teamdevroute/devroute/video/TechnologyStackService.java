package com.teamdevroute.devroute.video;

import com.teamdevroute.devroute.video.Repository.TechnologyStackRepository;
import com.teamdevroute.devroute.video.domain.TechnologyStack;
import com.teamdevroute.devroute.video.enums.TechnologyStackName;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class TechnologyStackService {

    private final TechnologyStackRepository technologyStackRepository;

    public TechnologyStack findById(Long id) {
        return technologyStackRepository.findById(id)
                .orElseThrow(TechnologyNotFoundException::new);
    }
    public void initializeTechnologyStack() {
        for (TechnologyStackName value : TechnologyStackName.values()) {
            technologyStackRepository.save(TechnologyStack.builder().
                    name(String.valueOf(value))
                    .count(0L).
                    build());
        }
    }
}

