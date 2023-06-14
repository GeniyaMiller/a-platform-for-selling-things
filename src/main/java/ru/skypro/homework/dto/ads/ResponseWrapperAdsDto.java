package ru.skypro.homework.dto.ads;

import lombok.Data;

import java.util.Collection;

@Data
public class ResponseWrapperAdsDto {

    private int count;

    private AdsDto[] result;

    public ResponseWrapperAdsDto(int count, AdsDto[] result) {
        this.count = count;
        this.result = result;
    }


    public ResponseWrapperAdsDto(Collection<AdsDto> allAds) {
    }
}
