package com.teamdevroute.devroute.dataloader;

import com.teamdevroute.devroute.roadmap.RoadmapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoadmapDataLoader {
    @Autowired
    private RoadmapService roadmapService;

public void loadRoadmapData() {
    createAllRoadmaps();
}

    private void createAllRoadmaps(){
        roadmapService.updateAllRoadmaps();
    }
}
