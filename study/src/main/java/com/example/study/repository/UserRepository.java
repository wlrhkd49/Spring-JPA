package com.example.study.repository;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //기본적인 CRUD 실행 가능
public interface UserRepository extends JpaRepository<User, Long> { //user repository와 기본키 자료형 삽입
    // 가장마지막에 핸드폰 번호 같은 아이를 리턴함
    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);
}
