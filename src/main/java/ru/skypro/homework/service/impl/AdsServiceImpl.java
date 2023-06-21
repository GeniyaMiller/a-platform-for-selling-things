package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdsDto;
import ru.skypro.homework.dto.ads.CommentDto;
import ru.skypro.homework.dto.ads.CreateAdsDto;
import ru.skypro.homework.dto.ads.FullAdsDto;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;
import java.util.Collection;
@Service
public class AdsServiceImpl implements AdsService {


    @Override
    public Collection<CommentDto> getAdsComments(int adsId) {
        return null;
    }

    @Override
    public CommentDto addComment(Integer adsId, CommentDto comment, Authentication authentication) {
        return null;
    }

    @Override
    public void deleteComment(Integer adsId, Integer commentId, Authentication authentication) {

    }

    @Override
    public CommentDto updateComment(Integer adsId, Integer commentId, CommentDto comment, Authentication authentication) {
        return null;
    }

    @Override
    public AdsDto save(CreateAdsDto ads, Authentication authentication, MultipartFile photo) throws IOException {
        return null;
    }

    @Override
    public void removeAds(Integer adsId, Authentication authentication) {

    }

    @Override
    public FullAdsDto getFullAds(Integer adsId) {
        return null;
    }

    @Override
    public AdsDto updateAds(Integer id, CreateAdsDto updatedAds, Authentication authentication) {
        return null;
    }

    @Override
    public Collection<AdsDto> getAllAds(String title) {
        return null;
    }

    @Override
    public Collection<AdsDto> getAdsByUser(String email) {
        return null;
    }
}
