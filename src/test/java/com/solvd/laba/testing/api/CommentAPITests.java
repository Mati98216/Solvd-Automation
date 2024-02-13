package com.solvd.laba.testing.api;


import com.solvd.laba.api.CommentCreationService;
import com.solvd.laba.api.CommentsRetrievalService;
import com.solvd.laba.api.SingleCommentRetrievalService;
import com.solvd.laba.api.CommentUpdateService;
import com.solvd.laba.domain.Comment;
import com.solvd.laba.domain.Post;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CommentAPITests {

    @DataProvider
    public Object[][] dataForCommentCreation() {
        return new Object[][] {
                {1, "John Doe", "john.doe@example.com", "This is a comment"},
                {1, "Jane Doe", "jane.doe@example.com", "Another insightful comment"}
        };
    }



    @Test(description = "Verify that the retrieved list of comments conforms to the schema")
    public void shouldMatchCommentListSchema() {
        CommentsRetrievalService commentsRetrieval = new CommentsRetrievalService();

        commentsRetrieval.callAPIExpectSuccess();

        commentsRetrieval.validateResponseAgainstSchema("api/comments/_get/rs.schema");
    }


    private static Comment parseCommentFromJson(JsonPath jsonPath) {
        Post associatedPost = new Post();
        associatedPost.setId(jsonPath.getInt("postId"));

        Comment parsedComment = new Comment();
        parsedComment.setId(jsonPath.getInt("id"));
        parsedComment.setPost(associatedPost);
        parsedComment.setName(jsonPath.getString("name"));
        parsedComment.setEmail(jsonPath.getString("email"));
        parsedComment.setBody(jsonPath.getString("body"));

        return parsedComment;
    }
    @Test
    public void shouldCreateAndUpdateComment() {
        Post post = new Post();
        post.setId(1);

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setName("Initial Name");
        comment.setEmail("initial.email@example.com");
        comment.setBody("Initial comment content");

        CommentCreationService commentCreation = new CommentCreationService();
        commentCreation.addProperty("comment", comment);

        Response response = commentCreation.callAPIExpectSuccess();
        commentCreation.validateResponse();

        Comment updatedComment = parseCommentFromJson(response.jsonPath());

        updatedComment.setName("Updated Name");
        updatedComment.setEmail("updated.email@example.com");
        updatedComment.setBody("Updated comment content");

        CommentUpdateService commentUpdate = new CommentUpdateService(updatedComment.getId());
        commentUpdate.addProperty("update_comment", updatedComment);

        commentUpdate.callAPIExpectSuccess();
        commentUpdate.validateResponse();
    }
    @Test(dataProvider = "dataForCommentCreation")
    public void shouldCreateCommentSuccessfully(Integer postId, String name, String email, String body) {
        Post post = new Post();
        post.setId(postId);

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setName(name);
        comment.setEmail(email);
        comment.setBody(body);

        CommentCreationService commentCreation = new CommentCreationService();
        commentCreation.addProperty("comment", comment);

        commentCreation.callAPIExpectSuccess();

        commentCreation.validateResponse();
    }

    @Test(description = "Verify retrieval of an existing comment by ID")
    public void shouldRetrieveCommentById() {
        Comment comment = new Comment();
        comment.setId(1);

        SingleCommentRetrievalService commentRetrieval = new SingleCommentRetrievalService(comment.getId());
        commentRetrieval.addProperty("comment", comment);
        commentRetrieval.callAPIExpectSuccess();

        commentRetrieval.validateResponse();
    }

}
