package ru.skypro.homework.dto;

import lombok.Data;

import java.lang.reflect.Array;
@Data
public class ResponseWrapperAds {

    private int count;

    private Ads[] result;

    public ResponseWrapperAds(int count, Ads[] result) {
        this.count = count;
        this.result = result;
    }


}
