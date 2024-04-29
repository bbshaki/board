package com.example.demo21.repositoryTest;

import com.example.demo21.entity.Memo;
import com.example.demo21.repository.MemoRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemoRepositoryTest {
    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass(){
        log.info(memoRepository.getClass().getName());
    }

    @Test
    public void testInsert(){
        IntStream.rangeClosed(1, 100).forEach(
                value -> {
                    Memo memo = Memo.builder()
                            .memoText("sample" + value)
                            .build();

                    memoRepository.save(memo);
                    // repository에서 save는 기본적으로 entity type을 가지고 온다
                    // repository를 만들 때 지정한 <class type, pk type>
                }
        );
    }

    @Test
    public void testSelect(){
        Long mno = 13L;
        Optional<Memo> memo = memoRepository.findById(mno);
//        log.info(memo.orElseThrow()); // 값이 없다면 에러
        log.info(memo.get());
    }

    @Test
    public void testUpdate(){
        Memo memo = new Memo(10L, "수정합니다");
        log.info(memoRepository.save(memo));
    }

    @Test
    public void testDelete(){
        memoRepository.deleteById(10L);
        memoRepository.deleteById(11L);
    }

    @Test
    public void testAll(){
        List<Memo> list = memoRepository.findAll(); // 전체 검색
        list.forEach(memo -> log.info(memo));
    }

    @Test
    public void testPaging(){
        // 한 페이지에 10개씩 페이징처리
//        Pageable pageable = PageRequest.of(20, 5);

        // 정렬 조건 추가
        Sort sort = Sort.by("mno").descending();
        Pageable pageable = PageRequest.of(1, 5, sort);
//        Pageable pageable = PageRequest.of(20, 5, Sort.Direction.DESC);

        Page<Memo> memoPage = memoRepository.findAll(pageable);

        memoPage.forEach(memo -> log.info(memo));
        log.info("총 페이지 : " + memoPage.getTotalPages()); // 총 몇 페이지인가
        log.info("전체 row 갯수 : " + memoPage.getTotalElements()); // 전체 더미 값 수 row
        log.info("현재 페이지 번호 : " + memoPage.getNumber()); // 현재 페이지 번호
        log.info("현재 페이지 크기 : " + memoPage.getSize()); // 현재 페이지의 크기
        log.info("다음 페이지가 있음? : " + memoPage.hasNext()); // 다음 페이지 존재 여부
        log.info("시작 페이지임? : " + memoPage.isFirst()); // 시작페이지 여부
    }

    @Test
    public void testFindByMno(){
        Memo memo = memoRepository.findByMno(3L);
        log.info(memo);
        log.info(memoRepository.findByMemoText("sample3"));
        log.info(memoRepository.findByMemoTextContains("e3"));
        log.info(memoRepository.findByMemoTextContainsOrMno("e1", 9L));
        log.info(memoRepository.findByMnoBetweenOrderByMnoDesc(3L, 10L));
    }

    @Test
    public void testSearch(){
        Pageable pageable = PageRequest.of
                (0, 10, Sort.by("mno").descending());
        List<Memo> memoList = memoRepository.findByMnoBetween(34L, 87L, pageable);
        memoList.forEach(memo -> log.info(memo));

    }





}
