package com.guava.test.controller;

import lombok.Data;

import java.util.List;

/**
 * Created Time 2023-05-24
 *
 * @author LittleSun
 * @version 1.0
 */
public class HttpClientUtils {
    @Data
    public class Request {
        private String reqHeader;
        private ReqBody reqBody;
    }

    @Data
    public class ReqBody {
        private String appReqHeader;
        private AppReqBody appReqBody;
        private String reqAttach;
        private String reqMsgAuth;
    }

    @Data
    public class AppReqBody {
        private String filePath;
        private String zpk;
        private BizDTO bizDTO;
    }

    @Data
    public class BizDTO {
        private String bizMetadata;
        private String achrSec;
        private List<DocInfo> docInfoList;
    }

    @Data
    public class DocInfo {
        private String pkuuid;
        private String dataType;
        private String uniqMetadata;
        private String custNo;
        private String custName;
        private String certType;
        private String certNo;
        private String security;
        private String expireDate;
        private String imageStorageMech;
        private String isCompress;
        private String isCustInfo;
        private List<PageInfo> pageInfoList;
    }

    @Data
    public class PageInfo {
        private PageDTO pageDTO;
        private PostilDTO postilDTO;
    }

    @Data
    public class PageDTO {
        private String docIndex;
        private String pageFlag;
        private String pageUuid;
        private String fileName;
        private String isHavePostil;
    }

    @Data
    public class PostilDTO {
        private String fileName;
        private String remark;
    }
}




