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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Ads[] getResult() {
        return result;
    }

    public void setResult(Ads[] result) {
        this.result = result;
    }
}
