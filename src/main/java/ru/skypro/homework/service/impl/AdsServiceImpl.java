package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.ads.CommentDto;
import ru.skypro.homework.service.AdsService;

import java.util.Collection;
@Service
public class AdsServiceImpl implements AdsService {

    @Override
    public Collection<CommentDto> getAdsComments(int adsId) {
        return null;
    }

    @Override
    public CommentDto addComment(Integer id, CommentDto comment, Authentication authentication) {
        return null;
    }

    @Override
    public void deleteComment(Integer adId, Integer commentId, Authentication authentication) {
    }

    @Override
    public CommentDto updateComment(Integer adId, Integer commentId, CommentDto comment, Authentication authentication) {
        return null;
    }
}
