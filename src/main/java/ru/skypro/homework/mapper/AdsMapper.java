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
//   AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);
//
//    @Mapping(target = "pk", source = "id")
//    @Mapping(target = "author", source = "author.id")
//    @Mapping(target = "image", expression="java(mappedImages(ads))")
//    AdsDto adsToAdsDto(Ads ads);
//
//
//    @Mapping(target = "pk", source = "id")
//    @Mapping(target = "phone", source = "author.phone")
//    @Mapping(target = "email", source = "author.email")
//    @Mapping(target = "authorLastName", source = "author.lastName")
//    @Mapping(target = "authorFirstName", source = "author.firstName")
//    @Mapping(target = "image", expression="java(mappedImages(ads))")
//    FullAdsDto toFullAdsDto(Ads ads);
//
//
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
//            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
//    Ads createAdsToAds(CreateAdsDto createAdsDto);
//
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
//            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
//    void partialUpdate(CreateAdsDto createAdsDto, @MappingTarget Ads ads);
//
//    Collection<AdsDto> adsCollectionToAdsDto(Collection<Ads> adsCollection);
//
//    default String mappedImages(Ads ads) {
//        List<Image> image= ads.getImages();
//        if (image == null||image.isEmpty()) {
//            return null;
//        }
//        return  "/ads/"+ads.getId()+"/getImage";
//    }
}
