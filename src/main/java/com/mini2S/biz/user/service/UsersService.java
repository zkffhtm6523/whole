package com.mini2S.biz.user.service;

import com.mini2S.biz.user.model.dto.UsersSigninDto;
import com.mini2S.biz.user.model.dto.UsersSignupDto;
import com.mini2S.configuration.security.TokenDto;
import com.mini2S.model.dto.TokenRequestDto;

public interface UsersService {
    TokenDto signIn(UsersSigninDto dto);

    void signUpUser(UsersSignupDto dto);

    TokenDto reIssue(TokenRequestDto dto);
}
