package com.acat.handleBlogData.service.tokenService;

import com.acat.handleBlogData.controller.resp.LoginRespVo;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class TokenServiceImpl {

    private static final Long TOKEN_EXPIRE_TIME = 10 * 60 * 1000L;

    public String getToken(LoginRespVo vo) {
        try {
            Date expireTime = new Date(System.currentTimeMillis() + TOKEN_EXPIRE_TIME);
            return JWT.create().withAudience(String.valueOf(vo.getId()))
                    .withExpiresAt(expireTime)
                    .sign(Algorithm.HMAC256(vo.getPassWord()));
        }catch (Exception e) {
            log.error("TokenService.getToken has error",e.getMessage());
        }
        return "";
    }
}
