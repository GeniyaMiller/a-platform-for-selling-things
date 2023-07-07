package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.ads.AdsDto;
import ru.skypro.homework.dto.ads.CreateAdsDto;
import ru.skypro.homework.dto.ads.FullAdsDto;
import ru.skypro.homework.model.Ads;

public class AdsMapper {
    public static AdsDto mapToDTO(Ads ads) {
        AdsDto adsDto = new AdsDto();
        adsDto.setPk(ads.getPk());
        adsDto.setAuthor(ads.getAuthor().getId());
        adsDto.setTitle(ads.getTitle());
        adsDto.setPrice(ads.getPrice());
        adsDto.setImage("/ads/image/" + ads.getPk());
        return adsDto;
    }
    public static FullAdsDto mapToFullDTO(Ads ads) {
        FullAdsDto fullAds = new FullAdsDto();
        fullAds.setPk(ads.getPk());
        fullAds.setTitle(ads.getTitle());
        fullAds.setDescription(ads.getDescription());
        fullAds.setPrice(ads.getPrice());
        fullAds.setImage("/ads/image/" + ads.getPk());
        fullAds.setEmail(ads.getAuthor().getUsername());
        fullAds.setPhone(ads.getAuthor().getPhone());
        return fullAds;
    }

    public static Ads mapFromCreateAds(CreateAdsDto createAds) {
        Ads ads = new Ads();
        ads.setTitle(createAds.getTitle());
        ads.setDescription(createAds.getDescription());
        ads.setPrice(createAds.getPrice());
        return ads;
    }
}
