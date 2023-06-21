package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
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
    private final AdsMapper adsMapper;

    public AdsServiceImpl(CommentRepository commentRepository, UserRepository userRepository, AdsRepository adsRepository, ImageService imageService, AdsMapper adsMapper) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.adsRepository = adsRepository;
        this.imageService = imageService;
        this.adsMapper = adsMapper;
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

    @Override
    public AdsDto save(CreateAdsDto ads, Authentication authentication, MultipartFile image) throws IOException {
        Ads newAds = adsMapper.createAdsToAds(ads);
        newAds.setAuthor(userRepository.findByEmail(authentication.getName()).orElseThrow(UserNotFoundException::new));
        adsRepository.save(newAds);

        imageService.updateAdsImage(newAds.getId(),image,authentication);

        return adsMapper.adsToAdsDto(newAds);
    }

    @Override
    public void removeAds(Integer adsId, Authentication authentication) {
        adsRepository.deleteAllById(adsId);
    }

    @Override
    public FullAdsDto getFullAds(Integer adsId) {
        Ads ads = adsRepository.findById(adsId).orElseThrow(AdsNotFoundException::new);
        return adsMapper.toFullAdsDto(ads);
    }

    @Override
    public AdsDto updateAds(Integer id, CreateAdsDto updatedAds, Authentication authentication) {
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);

        adsMapper.partialUpdate(updatedAds,ads);

        return adsMapper.adsToAdsDto(adsRepository.save(ads));
    }

    @Override
    public Collection<AdsDto> getAllAds(String title) {
        Collection<Ads> ads;
        if (!isEmpty(title)) {
            ads = adsRepository.findByTitleContainsOrderByTitle(title);
        } else {
            ads = adsRepository.findAll();
        }

        return adsMapper.adsCollectionToAdsDto(ads);
    }

    @Override
    public Collection<AdsDto> getAdsByUser(String email) {
        int authorId = userRepository.getUserProfileId(email);
        Collection<Ads> ads = adsRepository.findByAuthorId(authorId);
        return adsMapper.adsCollectionToAdsDto(ads);
    }
}
