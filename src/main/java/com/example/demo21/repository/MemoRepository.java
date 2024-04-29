package com.example.demo21.repository;

import com.example.demo21.entity.Memo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    //기존 DAO, MAPPER과 같은 역할을 한다.
    // 단, JPARepository를 상속함에 따라 JPA로 DB에 접근 가능
    // JPA/JAVA 표준 orm 기술
    // JPARepository<사용할 class, pk타입>
    // findbyId - id로 찾기 읽기 getOne // select
    // findbyAll - 전부 찾기 // select
    // save(entity) 저장 // insert
    // save(entity) 수정 // update
    // deletebyId 삭제 // delete

    //페이징 | 정령 처리하기
    //Jpa에서는 pageable을 사용하여 페이징처리함
    //sql 코드는 기존 limit을 사용함

    //Query Methods
    Memo findByMno (Long a);
    Memo findByMemoText (String memoText);
    // select * from tbl_memo where memo_text like concat('%', #{memoText}, '%')
    List<Memo> findByMemoTextContains (String memoText);
    List<Memo> findByMemoTextContainsOrMno (String memoText, Long mno);
    //select * from tbl_memo where mno between a and b order by desc
    List<Memo> findByMnoBetweenOrderByMnoDesc(Long a, Long b);
    List<Memo> findByMnoBetween(Long a, Long b, Pageable pageable);
}
