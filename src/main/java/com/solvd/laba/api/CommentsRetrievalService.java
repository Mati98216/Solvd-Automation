package com.solvd.laba.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

import static com.zebrunner.carina.api.http.HttpMethodType.*;

@Endpoint(url = "${config.api_url}/comments", methodType = GET)
@ResponseTemplatePath(path = "api/comments/_get/rs.schema")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class CommentsRetrievalService extends AbstractApiMethodV2 {
    public CommentsRetrievalService() {
        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }
}