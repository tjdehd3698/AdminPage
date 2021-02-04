package com.example.study.repository;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {  //user entity의 id가 Long이기에 Long으로 설정
    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);

    /*// select * from user where account = ?
    Optional<User>findByAccount(String account);

    // select * from user where email = ?
    Optional<User>findByEmail(String email);

    // select * from user where account = ? and email = ?
    Optional<User>findByAccountAndEmail(String account, String email);  //보통 컬럼명과 변수명 같게 해준다.*/
}