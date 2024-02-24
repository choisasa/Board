package com.sparta.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Timestamped {
    // 생성 일자 기록
    @CreatedDate
    // 변경 x
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    // 수정일자
    @LastModifiedDate
    @Column // 변경 o
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedAt;
}
