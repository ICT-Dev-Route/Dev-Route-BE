package com.teamdevroute.devroute.dataloader;

import com.teamdevroute.devroute.bookmark.Bookmark;
import com.teamdevroute.devroute.bookmark.BookmarkRepository;
import com.teamdevroute.devroute.company.domain.Company;
import com.teamdevroute.devroute.global.aop.timetrace.TimeTrace;
import com.teamdevroute.devroute.video.domain.Videos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BookmarkDataLoader {

    private final BookmarkRepository bookmarkRepository;
    private final CompanyDataLoader companyDataLoader;
    private final VideoDataLoader videoDataLoader;

    @TimeTrace
    public void loadBookmark(Bookmark bookmark) {
        List<Company> companyList = companyDataLoader.getCompanyList();
        companyList.remove(29);
        companyList.forEach(company -> {
            bookmark.addCompany(company);
            bookmarkRepository.save(bookmark);
        });

//        List<Videos> videoList = videoDataLoader.getVideosList();
//        videoList.forEach(videos -> {
//            bookmark.addVideo(videos);
//            bookmarkRepository.save(bookmark);
//        });
    }
}
