package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.Exception.NotFoundException;
import ru.skypro.homework.dto.ads.*;
import ru.skypro.homework.dto.profile.CustomUserDetails;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.validator.Validator;

import java.io.IOException;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class AdsService {
    private final AdsRepository adsRepository;

    public AdsService(AdsRepository adsRepository) {
        this.adsRepository = adsRepository;
    }

    public ResponseWrapperAdsDto getAll() {
        ResponseWrapperAdsDto wrap = new ResponseWrapperAdsDto();
        wrap.setResults(adsRepository.findAll().stream().map(AdsMapper::mapToDTO).collect(Collectors.toList()));
        wrap.setCount(wrap.getResults().size());
        return wrap;
    }

    public AdsDto create(MultipartFile imageFile, CreateAdsDto createAdsDto, Authentication authentication) {
        Validator.checkValidateObj(imageFile);
        Ads ads = AdsMapper.mapFromCreateAds(Validator.checkValidateObj(createAdsDto));
        try {
            byte[] bytes = imageFile.getBytes();
            ads.setImage(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        User user = UserMapper.customUserDetailsToUser((CustomUserDetails) authentication.getPrincipal());
        ads.setAuthor(user);
        return AdsMapper.mapToDTO(adsRepository.saveAndFlush(ads));
    }


    public FullAdsDto getById(Integer id) {
        return AdsMapper.mapToFullDTO(adsRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Transactional
    public void remove(Integer id) {
        adsRepository.deleteById(id);
    }

    @Transactional
    public AdsDto update(Integer id, CreateAdsDto createAds) {
        Validator.checkValidateObj(createAds);
        Ads ads = adsRepository.findById(id).orElseThrow(NotFoundException::new);
        ads.setTitle(createAds.getTitle());
        ads.setDescription(createAds.getDescription());
        ads.setPrice(createAds.getPrice());
        return AdsMapper.mapToDTO(adsRepository.save(ads));
    }

    public ResponseWrapperAdsDto getMeAll(Authentication authentication) {
        ResponseWrapperAdsDto wrap = new ResponseWrapperAdsDto();
        User user = UserMapper.customUserDetailsToUser((CustomUserDetails) authentication.getPrincipal());
        wrap.setResults(adsRepository.findAllByAuthorId(user.getId()).stream().map(AdsMapper::mapToDTO).collect(Collectors.toList()));
        wrap.setCount(wrap.getResults().size());
        return wrap;
    }

    @Transactional
    public byte[] updateImage(Integer id, MultipartFile avatar) {
        Validator.checkValidateObj(avatar);
        Ads ads = adsRepository.findById(id).orElseThrow(NotFoundException::new);
        try {
            byte[] bytes = avatar.getBytes();
            ads.setImage(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return adsRepository.saveAndFlush(ads).getImage();
    }

    public byte[] showImage(Integer id) {
        return adsRepository.findById(id).orElseThrow(NotFoundException::new).getImage();
    }

    public ResponseWrapperAdsDto search(String title) {
        ResponseWrapperAdsDto wrapper = new ResponseWrapperAdsDto();
        wrapper.setResults(adsRepository.findAllByTitleContainsIgnoreCase(title).stream().map(AdsMapper::mapToDTO).collect(Collectors.toList()));
        wrapper.setCount(wrapper.getResults().size());
        return wrapper;
    }
}
