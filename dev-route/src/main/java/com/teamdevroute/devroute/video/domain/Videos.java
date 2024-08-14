package com.teamdevroute.devroute.video.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.IOException;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
//@Setter
public class Videos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Long id;
    @Column(length = 2048)
    private String url;
    private String title;
    @Column(length = 2048)
    private String thumnail_url;
    private Long price;
    @Column(name = "video_rank")
    private Long rank;
    private String platformName;
    private Long count;
    private String teckStack;
    public Videos(){
    }
    @Builder
    public Videos(String url,String title,String thumnail_url,Long price,Long rank,
                  String platform_name,Long count,String teck_stack){
        this.url=url;
        this.title=title;
        this.thumnail_url = thumnail_url;
        this.price = price;
        this.rank = rank;
        this.platformName=platform_name;
        this.count=count;
        this.teckStack = teck_stack;

    }
    public Videos setAddedCount() {
        this.count = this.count + 1;
        return this;
    }

}
