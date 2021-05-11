package com.mini2S.common.user.controller;

import com.mini2S.configuration.security.JwtTokenProvider;
import com.mini2S.configuration.security.TokenDto;
import com.mini2S.model.dto.TokenRequestDto;
import com.mini2S.common.user.model.dto.UsersSigninDto;
import com.mini2S.common.user.model.dto.UsersSignupDto;
import com.mini2S.model.response.CommonResult;
import com.mini2S.service.ResponseService;
import com.mini2S.common.user.service.UsersServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"1. Users"})
@RequestMapping("/v1")
@RestController
@AllArgsConstructor
public class RestUsersController {
    private final UsersServiceImpl usersServiceImpl;
    // 결과 반환 Service
    private final ResponseService responseService;

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 로그인
     * @param dto
     * @return 로그인 정보
     */
    @PostMapping("/user/signin")
    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인")
    public TokenDto signin(@RequestBody UsersSigninDto dto){
        return usersServiceImpl.signIn(dto);
    }

    /**
     * 회원가입
     * @param dto
     * @return 회원가입 정보
     */
    @PostMapping("/user/signup")
    @ApiOperation(value = "회원가입", notes = "가입 경로 포함시켜야됨")
    public CommonResult signup(@RequestBody UsersSignupDto dto){
        usersServiceImpl.signUpUser(dto);
        return responseService.getSuccessResult();
    }

    /**
     * 토큰 확인
     * @param token
     * @return 사용자 토큰 발급(access, refresh 토큰)
     */
    @GetMapping("/user/test")
    @ApiOperation(value = "토큰 확인")
    public String chk(String token) {
        String resultToken = jwtTokenProvider.getUserPk(token);
        // 토큰 테스트
        System.out.print("resultToken : "+resultToken);
        return jwtTokenProvider.getUserPk(token);
    }

    /**
     * 토큰 갱신
     * @param dto
     * @return refresh 토큰 발급
     */
    @PostMapping("/user/reissue")
    @ApiOperation(value = "토큰 갱신")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto dto){
        return ResponseEntity.ok(usersServiceImpl.reIssue(dto));
    }

}