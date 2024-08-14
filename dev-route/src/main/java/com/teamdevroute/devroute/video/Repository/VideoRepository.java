package com.teamdevroute.devroute.video.Repository;

import com.teamdevroute.devroute.video.domain.Videos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VideoRepository extends JpaRepository<Videos,Long> {
    Optional<List<Videos>> findByPlatformNameAndTeckStack(String platform_name, String teck_stack);
    default Optional<List<Videos>> findTop3ByOrderByCountDesc() {
        Pageable topThree = PageRequest.of(0, 3, Sort.by("count").descending());
        Page<Videos> page = findAll(topThree);
        return Optional.ofNullable(page.getContent().isEmpty() ? null : page.getContent());
    }
}
