package ru.skypro.homework.dto.ads;

import lombok.Data;

@Data
public class ResponseWrapperAdsDto {

    private int count;

    private AdsDto[] result;

    public ResponseWrapperAdsDto(int count, AdsDto[] result) {
        this.count = count;
        this.result = result;
    }


}
