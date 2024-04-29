package com.example.demo21.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity // entity임을 명시 db table과 같은 구조로 만들어짐
        // entity를 사용하고 properties 설정하면 table이 생성됨
@Table(name = "tbl_memo") // table 이름 설정
@ToString
@Getter
@Builder
// builder 사용 시 이용하기 위해 @AllArgsConstructor, @NoArgsConstructor 필요
@AllArgsConstructor
@NoArgsConstructor
public class Memo {
    @Id // table의 pk mapping
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 값 증가 지정 auto_increment와 동일
    private Long mno;

    @Column(length = 200, nullable = false) // 길이는 200, not null 설정
    private String memoText;
}
