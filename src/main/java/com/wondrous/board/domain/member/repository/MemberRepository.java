package com.wondrous.board.domain.member.repository;

import com.wondrous.board.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //이미 가입된 사용자인지 확인하기 위함
    Optional<Member> findByEmail(String email);
}
