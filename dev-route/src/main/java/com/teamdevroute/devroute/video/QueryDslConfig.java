package com.teamdevroute.devroute.video;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.ReadingConverter;

import javax.swing.text.html.parser.Entity;

@Configuration
@RequiredArgsConstructor
public class QueryDslConfig {
    private final EntityManager em;
    @Bean
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(em);
    }
}
