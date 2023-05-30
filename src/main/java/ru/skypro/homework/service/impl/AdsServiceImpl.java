package ru.skypro.homework.service.impl;

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
}
