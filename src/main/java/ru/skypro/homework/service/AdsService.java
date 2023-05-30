package ru.skypro.homework.service;

import ru.skypro.homework.dto.ads.CommentDto;

import java.util.Collection;

public interface AdsService {
    Collection<CommentDto> getAdsComments(int adsId);

}
