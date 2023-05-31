package ru.skypro.homework.service;

<<<<<<< HEAD
import ru.skypro.homework.dto.ads.CommentDto;

=======
import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.ads.CommentDto;

import java.lang.reflect.AnnotatedArrayType;
>>>>>>> dev
import java.util.Collection;

public interface AdsService {
    Collection<CommentDto> getAdsComments(int adsId);

<<<<<<< HEAD
=======
    CommentDto addComment(Integer id, CommentDto comment, Authentication authentication);
    void deleteComment(Integer adId, Integer commentId, Authentication authentication);

    CommentDto updateComment(Integer adId, Integer commentId, CommentDto comment, Authentication authentication);
>>>>>>> dev
}
