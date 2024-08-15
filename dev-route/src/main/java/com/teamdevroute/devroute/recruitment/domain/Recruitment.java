package com.teamdevroute.devroute.recruitment.domain;

import com.teamdevroute.devroute.company.domain.Company;
import com.teamdevroute.devroute.recruitment.enums.Source;
import com.teamdevroute.devroute.user.enums.DevelopField;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruitment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "technology_stacks", columnDefinition = "json")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> techStacks;

    private String annual;

    private LocalDate dueDate;

    private String url;

    @Enumerated(EnumType.STRING)
    private DevelopField developField;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    @Enumerated(EnumType.STRING)
    private Source source;

    @Builder
    public Recruitment(Long id, List<String> techStacks, String annual, LocalDate dueDate, String url, DevelopField developField, Company company, Source source) {
        this.id = id;
        this.techStacks = techStacks;
        this.annual = annual;
        this.dueDate = dueDate;
        this.url = url;
        this.developField = developField;
        this.company = company;
        this.source = source;
    }
}
