package com.teamdevroute.devroute.video.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teamdevroute.devroute.video.domain.Videos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.teamdevroute.devroute.video.domain.QVideos.videos;

@Repository
@RequiredArgsConstructor
public class VideoQueryRepository {
    private final JPAQueryFactory query;
    public List<Videos> findLectures(String platformName,String techStack){
        return query.select(videos)
                .from(videos)
                .where(isPlatform(platformName), isTechStack(techStack))
                .fetch();
    }
    private BooleanExpression isPlatform(String platformName){
        System.out.println(platformName);

        if(platformName!=null){
            return videos.platformName.eq(platformName);
        }

        return null;
    }
    private BooleanExpression isTechStack(String techStack){
        if(techStack!=null)
            return videos.teckStack.eq(techStack);
        return null;
    }
}
