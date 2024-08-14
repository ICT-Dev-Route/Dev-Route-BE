package com.teamdevroute.devroute.video.service;

import com.teamdevroute.devroute.video.exception.TechnologyNotFoundException;
import com.teamdevroute.devroute.video.Repository.TechnologyStackRepository;
import com.teamdevroute.devroute.video.domain.TechnologyStack;
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

}

