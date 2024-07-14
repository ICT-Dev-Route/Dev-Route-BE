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
import lombok.Getter;

@Entity
@Getter
//@Setter
public class Videos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Long id;
    private String url;
    private String titile;
    private String thumnail_url;
    private Long price;
    @Column(name = "video_rank")
    private Long rank;
    private String platform_name;
    private Long count;

    private String teck_stack;
    public Videos(){
    }

}
