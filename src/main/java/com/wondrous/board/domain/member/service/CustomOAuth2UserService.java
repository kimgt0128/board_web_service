package com.wondrous.board.domain.member.service;

import com.wondrous.board.domain.member.dto.CustomOAuth2User;
import com.wondrous.board.domain.member.dto.OAuth2Response;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getAttributes());


        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;

        //Naver, Google, Kakao 선택 후 강제 로그인 로직 구현

        String role = "ROLE_USER";
        //나머지 구현
        return new CustomOAuth2User(oAuth2Response, role);
    }
}

