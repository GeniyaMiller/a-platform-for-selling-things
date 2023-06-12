package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.Exception.CommentNotFoundException;
import ru.skypro.homework.dto.ads.CommentDto;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class AdsServiceImpl implements AdsService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public AdsServiceImpl(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    /**
     * Возвращает комментарии объявления по идентификатору объявления
     *
     * @param adsId идентификатор объявления {@link Integer}
     * @return коллекция комментариев {@link CommentDto}
     */
    @Override
    public Collection<CommentDto> getAdsComments(int adsId) {
        Collection<Comment> comments = commentRepository.getByAdsId(adsId);
        return comments.stream()
                .map(CommentMapper.INSTANCE::commentToCommentDto)
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

        Integer userId = userRepository.getUserById(authentication.getName());
        String authorFirstName = userRepository.getUserByFirstName(userId);
        String avatar = userRepository.getAvatarUserById(userId);
        Comment newComment = CommentMapper.INSTANCE.commentDtoToComment(comment);
        newComment.setAdsId(adsId);
        newComment.setAuthorId(userId);
        newComment.setCreatedAt(LocalDateTime.now().toString());
        newComment.setAuthorFirstName(authorFirstName);
        newComment.setAuthorImage(avatar);

        commentRepository.save(newComment);
        return CommentMapper.INSTANCE.commentToCommentDto(newComment);
    }

    /**
     * Удаляет комментарий к объявлению по идентификатору объявления
     *
     * @param adsId идентификатор объявления {@link Integer}
     * @param commentId идентификатор объявления {@link Integer}
     */
    @Override
    public void deleteComment(Integer adsId, Integer commentId, Authentication authentication) {
        commentRepository.deleteByAdsIdAndId(adsId,commentId);
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

        comment.setText(comment.getText());
        CommentDto commentDto = CommentMapper.INSTANCE.commentToCommentDto(commentRepository.save(updateComment));
        return commentDto;
    }
}
