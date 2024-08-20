package com.teamdevroute.devroute.bookmark;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookmarkDeleteRequest {
    private Long id;
    private String type;
}
