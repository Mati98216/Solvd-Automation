package com.solvd.laba.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${config.api_url}/comments/${comment_id}", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/comments/_get/rs.ftl")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class SingleCommentRetrievalService extends AbstractApiMethodV2 {
    public SingleCommentRetrievalService(int commentId) {
        replaceUrlPlaceholder("comment_id", Integer.toString(commentId));

        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }
}