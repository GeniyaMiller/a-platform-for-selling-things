package ru.skypro.homework.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ads.AdsDto;
import ru.skypro.homework.dto.ads.CreateAdsDto;
import ru.skypro.homework.dto.ads.FullAdsDto;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Image;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AdsMapper {

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "image", expression = "java(mappedImages(ads))")
    static AdsDto adsToAdsDto(Ads ads) {
        return null;
    }


    @Mapping(target = "pk", source = "id")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "image", expression = "java(mappedImages(ads))")
    static FullAdsDto toFullAdsDto(Ads ads) {
        return null;
    }


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    static Ads createAdsToAds(CreateAdsDto createAdsDto) {
        return null;
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    static void partialUpdate(CreateAdsDto createAdsDto, @MappingTarget Ads ads) {
    }

    static Collection<AdsDto> adsCollectionToAdsDto(Collection<Ads> adsCollection) {
        return null;
    }

    default String mappedImages(Ads ads) {
        String image= ads.getImage();
        if (image == null||image.isEmpty()) {
            return null;
        }
        return  "/ads/"+ads.getId()+"/getImage";
    }
}
