package com.solvd.laba.api;


import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${config.api_url}/comments", methodType = HttpMethodType.POST)
@ResponseTemplatePath(path = "api/comments/_post/rs.ftl")
@RequestTemplatePath(path = "api/comments/_post/rq.ftl")
@SuccessfulHttpStatus(status = HttpResponseStatusType.CREATED_201)
public class PostComment extends AbstractApiMethodV2 {
    public PostComment() {
        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }
}