package ru.skypro.homework.dto.ads;

import lombok.Data;
import ru.skypro.homework.dto.ads.Ads;

@Data
public class ResponseWrapperAds {

    private int count;

    private Ads[] result;

    public ResponseWrapperAds(int count, Ads[] result) {
        this.count = count;
        this.result = result;
    }


}
