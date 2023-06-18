package ru.skypro.homework.dto.ads;

import lombok.Data;

import java.util.Collection;

@Data
public class ResponseWrapperAdsDto {

    private int count;

    private AdsDto[] result;

    public ResponseWrapperAdsDto() {
    }
}
