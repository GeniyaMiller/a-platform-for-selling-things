package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;

import java.io.IOException;

public interface ImageService {
    Image saveImage(MultipartFile image);

    byte[] updateAdsImage(MultipartFile newImage, Image oldImage) throws IOException;

    byte[] getImage(int id) throws IOException;
}
