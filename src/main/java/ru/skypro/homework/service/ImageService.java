package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    byte[] updateAdsImage(Integer id, MultipartFile image, Authentication authentication) throws IOException;

    byte[] getImage(int id) throws IOException;
}
