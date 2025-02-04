package com.acat.handleBlogData.controller;

import com.acat.handleBlogData.aop.Auth;
import com.acat.handleBlogData.constants.RestResult;
import com.acat.handleBlogData.constants.UrlConstants;
import com.acat.handleBlogData.controller.req.LoginReqBo;
import com.acat.handleBlogData.controller.req.UserReq;
import com.acat.handleBlogData.controller.resp.LoginRespVo;
import com.acat.handleBlogData.domain.entity.BlogSystemUserEntity;
import com.acat.handleBlogData.enums.RestEnum;
import com.acat.handleBlogData.service.tokenService.TokenServiceImpl;
import com.acat.handleBlogData.service.UserService;
import com.acat.handleBlogData.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(UrlConstants.BLOG_SYSTEM_USER_URL)
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private TokenServiceImpl tokenServiceImpl;

    @Auth(required = false)
    @GetMapping("/getAllUser")
    public RestResult<List<BlogSystemUserEntity>> getAllUser(HttpServletRequest httpServletRequest) {
        try {
            List<BlogSystemUserEntity> blogSystemUserEntityList = userService.getAllUser();
            return new RestResult<>(RestEnum.SUCCESS, blogSystemUserEntityList);
        }catch (Exception e) {
            return new RestResult<>(RestEnum.FAILED.getCode(), e.getMessage(), null);
        }
    }

    @Auth(required = false)
    @PostMapping("/addOrUpdate")
    public RestResult addOrUpdate(HttpServletRequest httpServletRequest,
                                  @RequestBody UserReq userReq) {
        try {
            if (StringUtils.isBlank(userReq.getUserName())) {
                return new RestResult<>(RestEnum.USERNAME_EMPTY_PARAM);
            }
            if (StringUtils.isBlank(userReq.getPassWord())) {
                return new RestResult<>(RestEnum.PASSWORD_EMPTY_PARAM);
            }
            if (StringUtils.isBlank(userReq.getUserNickname())) {
                return new RestResult<>(RestEnum.NICK_NAME_EMPTY);
            }
            userService.addOrUpdate(userReq);
            return new RestResult<>(RestEnum.SUCCESS);
        }catch (Exception e) {
            return new RestResult<>(RestEnum.FAILED.getCode(), e.getMessage(), null);
        }
    }

    @Auth(required = false)
    @PostMapping("/login")
    public RestResult<LoginRespVo> login(HttpServletRequest httpServletRequest,
                                         @RequestBody LoginReqBo loginReqBo) {
        try {
            if (StringUtils.isBlank(loginReqBo.getUsername())) {
                return new RestResult<>(RestEnum.USERNAME_EMPTY_PARAM);
            }
            if (StringUtils.isBlank(loginReqBo.getPassword())) {
                return new RestResult<>(RestEnum.PASSWORD_EMPTY_PARAM);
            }

            LoginRespVo loginRespVo = userService.login(loginReqBo.getUsername(), loginReqBo.getPassword());
            if (Objects.isNull(loginRespVo)) {
                return new RestResult<>(RestEnum.USER_NOT_EXISTS);
            }

//            String token = tokenServiceImpl.getToken(loginRespVo);
            String token = JwtUtils.sign(loginRespVo);
            loginRespVo.setToken(token);
//            httpServletRequest.getSession().setAttribute("token", token);
            return new RestResult<>(RestEnum.SUCCESS, loginRespVo);
        } catch (Exception e) {
            return new RestResult<>(RestEnum.FAILED.getCode(), e.getMessage(), null);
        }
    }

    @Auth(required = false)
    @PostMapping("/logout")
    public RestResult logout(HttpServletRequest httpServletRequest) {
        try {
            httpServletRequest.getSession().removeAttribute("token");
            httpServletRequest.getSession().invalidate();
            return new RestResult<>(RestEnum.SUCCESS);
        } catch (Exception e) {
            return new RestResult<>(RestEnum.FAILED.getCode(), e.getMessage(), null);
        }
    }
}
