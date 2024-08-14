package com.teamdevroute.devroute.video.Repository;

import com.teamdevroute.devroute.video.domain.Videos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VideoRepository extends JpaRepository<Videos,Long> {
    Optional<List<Videos>> findByPlatformNameAndTeckStack(String platform_name, String teck_stack);
}
