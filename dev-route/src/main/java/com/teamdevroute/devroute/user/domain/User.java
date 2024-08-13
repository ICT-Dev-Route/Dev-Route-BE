package com.teamdevroute.devroute.user.domain;

import com.teamdevroute.devroute.bookmark.Bookmark;
import com.teamdevroute.devroute.global.BaseTimeEntity;
import com.teamdevroute.devroute.user.enums.DevelopField;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.LastModifiedDate;

import java.awt.print.Book;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Email @Column(unique=true)
    @Size(min = 1, max = 50, message = "이메일은 1 ~ 50자 이여야 합니다.")
    private String email;

    private String password;

    @NotNull
    @Size(min = 1, max = 10, message = "이름은 1 ~ 10자 이여야 합니다.")
    private String name;

    @LastModifiedDate
    private LocalDateTime last_password_changed;

    @Column(name = "login_type")
    private String loginType;

    @Column(name = "user_role")
    @ColumnDefault("'USER'")
    private String userRole;

    @Column(name = "development_field")
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'NONE'")
    private DevelopField developField;

    @Column
    @Size(min = 1, max = 100, message = "소개말은 1 ~ 100자 이여야 합니다.")
    private String introduce_info;

    @Column
    @Size(min = 1, max = 10, message = "목표는 1 ~ 10자 이여야 합니다.")
    private String goal_info;

    @OneToOne
    private Bookmark bookmark;

    @Builder
    public User(Long id, String email, String password, String name, LocalDateTime last_password_changed, String loginType, String userRole, DevelopField developField, String introduce_info, String goal_info, Bookmark bookmark) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.last_password_changed = last_password_changed;
        this.loginType = loginType;
        this.userRole = userRole;
        this.developField = developField;
        this.introduce_info = introduce_info;
        this.goal_info = goal_info;
        this.bookmark = bookmark;
    }

    public void initBookmark(Bookmark bookmark) {
        this.bookmark = bookmark;
    }
}
