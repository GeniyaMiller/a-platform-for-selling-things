package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.Exception.NotFoundException;
import ru.skypro.homework.dto.ads.CommentDto;
import ru.skypro.homework.dto.ads.ResponseWrapperCommentDto;
import ru.skypro.homework.dto.profile.CustomUserDetails;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.validator.Validator;

import java.util.stream.Collectors;

@Service
public class CommentsService {
    private final CommentRepository commentRepository;
    private final AdsRepository adsRepository;

    public CommentsService(CommentRepository commentRepository, AdsRepository adsRepository) {
        this.commentRepository = commentRepository;
        this.adsRepository = adsRepository;
    }

    public ResponseWrapperCommentDto getAll(Integer adsId) {
        ResponseWrapperCommentDto wrapper = new ResponseWrapperCommentDto();
        wrapper.setResults(commentRepository.findAllByAdsPk(adsId).stream().map(CommentMapper::mapToDTO).collect(Collectors.toList()));
        wrapper.setCount(wrapper.getResults().size());
        return wrapper;
    }

    public CommentDto addComment(Integer id, CommentDto commentDto, Authentication authentication) {
        Comment comment = CommentMapper.mapFromDto(Validator.checkValidateObj(commentDto));
        User user = UserMapper.customUserDetailsToUser((CustomUserDetails) authentication.getPrincipal());
        comment.setAuthor(user);
        comment.setAds(adsRepository.findById(id).orElseThrow(NotFoundException::new));
        commentRepository.save(comment);
        return CommentMapper.mapToDTO(comment);
    }

    @Transactional
    public void deleteComment(Integer commentId, Integer adId) {
        commentRepository.deleteByPkAndAdsPk(Validator.checkValidateObj(commentId), adId);
    }

    @Transactional
    public CommentDto update(Integer commentId, Integer adsId, CommentDto commentDTO) {
        Comment comment = commentRepository.findByPkAndAdsPk(commentId, adsId).orElseThrow(NotFoundException::new);
        comment.setText(commentDTO.getText());
        return CommentMapper.mapToDTO(commentRepository.save(comment));
    }

    public Comment getById(Integer id) {
        return commentRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}
