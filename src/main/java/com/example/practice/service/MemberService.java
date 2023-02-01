package com.example.practice.service;

import com.example.practice.VO.TestVO;
import com.example.practice.entity.Member;
import com.example.practice.entity.MemberRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(username);

        if(isNull(member)){
            throw new UsernameNotFoundException("Not Found account");
        }

        return member;
    }

    public String testMethod(@Validated TestVO testVO) throws Exception{


        return "Test String";
    }
}
