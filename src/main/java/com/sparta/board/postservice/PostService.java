package com.sparta.board.postservice;

import com.sparta.board.dto.PostRequestDto;
import com.sparta.board.dto.PostResponseDto;
import com.sparta.board.entity.Post;
import com.sparta.board.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    // 생성자를 통한 의존성 주입
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    // 게시물 생성
    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto){
        // RequestDto -> 게시물 객체 생성
        Post post = new Post(requestDto);

        // Db에 저장해주기
        Post savePost = postRepository.save(post);

        return new PostResponseDto(savePost);
    }

    // 게시물 조회
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물은 존재하지 않습니다. id = " + id));
        return new PostResponseDto(post);
    }

    // 모든 게시물 조회
    public List<PostResponseDto> getPosts(){
        return postRepository.findAllByOrderByModifiedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    // 게시물 수정
    @Transactional
    public PostResponseDto updatePost(Long id, String password, PostRequestDto requestDto){
        // 게시물 존재여부 확인하기 - DB
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물은 존재하지 않습니다. id=" + id));
        if (!post.getPassword().equals(password)){
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }
        post.update(requestDto);
        return new PostResponseDto(post);
    }

    // 게시물 삭제
    @Transactional
    public Long deletePost(Long id, String password){
        // 게시물 존재여부 확인하기 - DB
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물은 존재하지 않습니다. id=" + id));
        if (!post.getPassword().equals(password)){
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }
        postRepository.delete(post);
        return id;
    }
}

