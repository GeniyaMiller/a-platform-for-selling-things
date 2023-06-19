package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdsDto;
import ru.skypro.homework.dto.ads.CommentDto;
import ru.skypro.homework.dto.ads.CreateAdsDto;
import ru.skypro.homework.dto.ads.FullAdsDto;
import ru.skypro.homework.model.Ads;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.lang.reflect.AnnotatedArrayType;
import java.util.Collection;

public interface AdsService {
    Collection<CommentDto> getAdsComments(Ads adsId);

    CommentDto addComment(Ads adsId, CommentDto comment, Authentication authentication);
    void deleteComment(Ads adsId, Integer commentId, Authentication authentication);

    CommentDto updateComment(Ads adsId, Integer commentId, @NotNull CommentDto comment, Authentication authentication);


    AdsDto save(CreateAdsDto ads, Authentication authentication, MultipartFile photo) throws IOException;
    void removeAds(Integer adsId, Authentication authentication);
    FullAdsDto getFullAds(Integer adsId);
    AdsDto updateAds(Integer id, CreateAdsDto updatedAds, Authentication authentication);

    Collection<AdsDto> getAllAds(String title);
    Collection<AdsDto> getAdsByUser(String email);
}
