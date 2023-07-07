package ru.skypro.homework.dto.ads;

import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class ResponseWrapperAdsDto {

    private int count=0;
    private List<AdsDto> results;
}
