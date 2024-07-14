package com.teamdevroute.devroute.video;

import com.teamdevroute.devroute.video.domain.Videos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Videos,Long> {
}
