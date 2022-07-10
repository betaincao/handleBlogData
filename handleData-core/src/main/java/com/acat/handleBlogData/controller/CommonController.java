package com.acat.handleBlogData.controller;

import com.acat.handleBlogData.aop.Auth;
import com.acat.handleBlogData.constants.RestResult;
import com.acat.handleBlogData.constants.UrlConstants;
import com.acat.handleBlogData.controller.req.TranReq;
import com.acat.handleBlogData.controller.resp.*;
import com.acat.handleBlogData.enums.BatchSearchFieldEnum;
import com.acat.handleBlogData.enums.MediaSourceEnum;
import com.acat.handleBlogData.enums.RestEnum;
import com.acat.handleBlogData.outerService.outerInterface.TranslateOuterServiceImpl;
import com.acat.handleBlogData.service.esService.EsServiceImpl;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(UrlConstants.BLOG_SYSTEM_COMMON)
public class CommonController {

    @Resource
    private EsServiceImpl esService;
    @Resource
    private TranslateOuterServiceImpl translateOuterService;

    private static final String ZH = "zh";

    @Auth(required = false)
    @GetMapping("/getCountryList")
    public RestResult<SearchCountryResp> getCountryList() {

        try {
            return esService.getCountryList();
        }catch (Exception e) {
            log.error("CommonController.getCountryList has error:{}",e.getMessage());
            return new RestResult<>(RestEnum.FAILED.getCode(), e.getMessage(), null);
        }
    }

    @Auth(required = false)
    @GetMapping("/getCityList")
    public RestResult<SearchCityResp> getCityList() {

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
                            MediaTypeResp
                                    .builder()
                                    .code(e.getCode())
                                    .desc(e.getDesc())
                                    .totalSize(esService.getMediaIndexSize(e))
                                    .build())
                            .collect(Collectors.toList()));
        }catch (Exception e) {
            log.error("CommonController.getMediaTypeList has error:{}",e.getMessage());
            return new RestResult<>(RestEnum.FAILED.getCode(), e.getMessage(), null);
        }
    }

    @Auth(required = false)
    @GetMapping("/getBatchQueryField")
    public RestResult<BatchQueryResp> getBatchQueryField() {
        try {
            return new RestResult(RestEnum.SUCCESS, BatchQueryResp
                    .builder()
                    .queryFieldList(
                            Arrays.stream(BatchSearchFieldEnum.values()).map(e ->
                                            BatchQueryResp.Field
                                                    .builder()
                                                    .fieldName(e.getFieldName())
                                                    .fieldValue(e.getFieldValue())
                                                    .build())
                                    .collect(Collectors.toList()))
                    .build());
        }catch (Exception e) {
            log.error("CommonController.getBatchQueryField has error:{}",e.getMessage());
            return new RestResult<>(RestEnum.FAILED.getCode(), e.getMessage(), null);
        }
    }

    @Auth(required = false)
//    @GetMapping("/getTranResult")
    @PostMapping("/getTranResult")
    public RestResult<TranResp> getTranResult(@RequestBody Map<String, String> tranMap) {
        try {
            Map<String, Object> tranResultMap = Maps.newHashMap();
            for (String key : tranMap.keySet()) {
                String tranValue = tranMap.get(key);
                if (StringUtils.isBlank(tranValue)) {
                    //tranList.add(ImmutableMap.of(key, ""));
                    tranResultMap.put(key, "");
                    continue;
                }

                String languageType = translateOuterService.getLanguageDelectResult(tranValue);
                if (StringUtils.isBlank(languageType)) {
//                    tranList.add(ImmutableMap.of(key, ""));
                    tranResultMap.put(key, "");
                    continue;
                }

                if (ZH.equals(languageType)) {
//                    tranList.add(ImmutableMap.of(key, tranValue));
                    tranResultMap.put(key, "");
                    continue;
                }
//                tranList.add(ImmutableMap.of(key, translateOuterService.getTranslateValue(languageType, tranValue)));
                tranResultMap.put(key, translateOuterService.getTranslateValue(languageType, tranValue));
            }
            return new RestResult(RestEnum.SUCCESS, TranResp.builder().tranMap(tranResultMap).build());
        }catch (Exception e) {
            log.error("CommonController.getBatchQueryField has error:{}",e.getMessage());
            return new RestResult<>(RestEnum.FAILED.getCode(), e.getMessage(), null);
        }
    }
}
