package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.Exception.AdsNotFoundException;
import ru.skypro.homework.Exception.CommentNotFoundException;
import ru.skypro.homework.Exception.UserNotFoundException;
import ru.skypro.homework.dto.ads.AdsDto;
import ru.skypro.homework.dto.ads.CommentDto;
import ru.skypro.homework.dto.ads.CreateAdsDto;
import ru.skypro.homework.dto.ads.FullAdsDto;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class AdsServiceImpl implements AdsService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AdsRepository adsRepository;
    private final ImageService imageService;

    private CommentMapper commentMapper;

    public AdsServiceImpl(CommentRepository commentRepository, UserRepository userRepository, AdsRepository adsRepository, ImageService imageService, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.adsRepository = adsRepository;
        this.imageService = imageService;
        this.commentMapper = commentMapper;
    }

    /**
     * Возвращает комментарии объявления по идентификатору объявления
     *
     * @param adsId идентификатор объявления {@link Integer}
     * @return коллекция комментариев {@link CommentDto}
     */
    @Override
    public Collection<CommentDto> getAdsComments(Integer adsId) {
        Ads ads = adsRepository.getAdsById(adsId);
        Collection<Comment> comments = commentRepository.getByAdsId(ads.getId());
        return comments.stream()
                .map(commentMapper::commentToCommentDto)
                .collect(Collectors.toSet());
    }

    /**
     * Добавляет новый комментарий к объявлению по идентификатору объявления
     *
     * @param adsId идентификатор объявления {@link Integer}
     * @param comment идентификатор объявления {@link CommentDto}
     * @return добавленный комментарий {@link CommentDto}
     */
    @Override
    public CommentDto addComment(Integer adsId, CommentDto comment, Authentication authentication) {
        Ads ads = adsRepository.getAdsById(adsId);
        User userId = userRepository.getUserById(authentication.getName());
        Comment newComment = commentMapper.commentDtoToComment(comment);
        newComment.setAdsId(ads);
        newComment.setAuthorId(userId);
        newComment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(newComment);
        return commentMapper.commentToCommentDto(newComment);
    }

    /**
     * Удаляет комментарий к объявлению по идентификатору объявления
     *
     * @param adsId идентификатор объявления {@link Integer}
     * @param commentId идентификатор объявления {@link Integer}
     */
    @Override
    public void deleteComment(Integer adsId, Integer commentId, Authentication authentication) {
        Comment comment = commentRepository.getByAdsIdAndId(adsId, commentId)
                .orElseThrow(CommentNotFoundException::new);
        commentRepository.delete(comment);
    }

    /**
     * Обновляет комментарий к объявлению по идентификатору объявления
     *
     * @param adsId идентификатор объявления {@link Integer}
     * @param commentId идентификатор объявления {@link Integer}
     * @param comment идентификатор объявления {@link CommentDto}
     */
    @Override
    public CommentDto updateComment(Integer adsId, Integer commentId, @NotNull CommentDto comment, Authentication authentication) {
        Comment updateComment = commentRepository.getByAdsIdAndId(adsId, commentId).orElseThrow(CommentNotFoundException::new);
        updateComment.setText(comment.getText());
        CommentDto commentDto = commentMapper.commentToCommentDto(commentRepository.save(updateComment));
        return commentDto;
    }

    @Override
    public AdsDto save(CreateAdsDto ads, Authentication authentication, MultipartFile photo) throws IOException {
        return null;
    }

    @Override
    public void removeAds(Integer adsId, Authentication authentication) {

    }

    @Override
    public FullAdsDto getFullAds(Integer adsId) {
        return null;
    }

    @Override
    public AdsDto updateAds(Integer id, CreateAdsDto updatedAds, Authentication authentication) {
        return null;
    }

    @Override
    public Collection<AdsDto> getAllAds(String title) {
        return null;
    }

    @Override
    public Collection<AdsDto> getAdsByUser(String email) {
        return null;
    }
}
