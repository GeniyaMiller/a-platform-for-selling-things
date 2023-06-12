package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.ads.CommentDto;

import javax.validation.constraints.NotNull;
import java.lang.reflect.AnnotatedArrayType;
import java.util.Collection;

public interface AdsService {
    Collection<CommentDto> getAdsComments(int adsId);

    CommentDto addComment(Integer adsId, CommentDto comment, Authentication authentication);
    void deleteComment(Integer adsId, Integer commentId, Authentication authentication);

    CommentDto updateComment(Integer adsId, Integer commentId, @NotNull CommentDto comment, Authentication authentication);
}
