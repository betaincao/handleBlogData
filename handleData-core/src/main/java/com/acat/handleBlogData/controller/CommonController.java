package com.acat.handleBlogData.controller;

import com.acat.handleBlogData.aop.Auth;
import com.acat.handleBlogData.constants.RestResult;
import com.acat.handleBlogData.constants.UrlConstants;
import com.acat.handleBlogData.controller.resp.MediaTypeResp;
import com.acat.handleBlogData.enums.MediaSourceEnum;
import com.acat.handleBlogData.enums.RestEnum;
import com.acat.handleBlogData.service.esService.EsServiceImpl;
import com.acat.handleBlogData.controller.resp.SearchCityResp;
import com.acat.handleBlogData.controller.resp.SearchCountryResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(UrlConstants.BLOG_SYSTEM_COMMON)
public class CommonController {

    @Resource
    private EsServiceImpl esService;

    @Auth
    @GetMapping("/getCountryList")
    public RestResult<List<SearchCountryResp>> getCountryList() {

        try {
            return esService.getCountryList();
        }catch (Exception e) {
            log.error("CommonController.getCountryList has error:{}",e.getMessage());
            return new RestResult<>(RestEnum.FAILED.getCode(), e.getMessage(), null);
        }
    }

    @Auth
    @GetMapping("/getCityList")
    public RestResult<List<SearchCityResp>> getCityList() {

        try {
            return esService.getCityList();
        }catch (Exception e) {
            log.error("CommonController.getCityList has error:{}",e.getMessage());
            return new RestResult<>(RestEnum.FAILED.getCode(), e.getMessage(), null);
        }
    }

    @Auth(required = false)
    @GetMapping("/getMediaTypeList")
    public RestResult<List<MediaTypeResp>> getMediaTypeList() {

        try {
            return new RestResult(RestEnum.SUCCESS,
                    Arrays.stream(MediaSourceEnum.values()).map(e ->
                            MediaTypeResp.builder().code(e.getCode()).desc(e.getDesc()).build())
                            .collect(Collectors.toList()));
        }catch (Exception e) {
            log.error("CommonController.getMediaTypeList has error:{}",e.getMessage());
            return new RestResult<>(RestEnum.FAILED.getCode(), e.getMessage(), null);
        }
    }
}
