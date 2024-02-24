package com.sparta.board.postservice;

import com.sparta.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


// PostRepository 인터페이스 정의
public interface PostRepository extends JpaRepository<Post, Long> {

    // 사용자별로 수정일자 내림차순으로 모든 게시물을 찾는 메서드
    List<Post> findAllByOrderByModifiedAtDesc();

    // ID를 사용하여 게시물을 찾는 메서드
    Optional<Post> findById(Long id);
}
