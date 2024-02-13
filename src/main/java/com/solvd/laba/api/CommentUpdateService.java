package com.solvd.laba.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
@Endpoint(url = "${config.api_url}/comments/${comment_id}", methodType = HttpMethodType.PATCH)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
@RequestTemplatePath(path = "api/comments/_patch/rq.ftl")
@ResponseTemplatePath(path = "api/comments/_patch/rs.ftl")
public class CommentUpdateService extends AbstractApiMethodV2 {
    public CommentUpdateService(int commentId) {
        replaceUrlPlaceholder("comment_id", Integer.toString(commentId));

        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }
}