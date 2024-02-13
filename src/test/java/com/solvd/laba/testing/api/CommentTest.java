package com.solvd.laba.testing.api;

import com.solvd.laba.api.CreateComments;
import com.solvd.laba.api.GetAllComments;
import com.solvd.laba.api.GetCommentsById;
import com.solvd.laba.api.UpdateComments;
import com.solvd.laba.domain.Comment;
import com.solvd.laba.domain.Post;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class CommentTest {

    @DataProvider
    public Object[][] provideDataForCreatingComments() {
        return new Object[][]{
                {1, "John Doe", "john.doe@example.com", "This is a comment"},
                {1, "Jane Doe", "jane.doe@example.com", "Another insightful comment"}
        };
    }

    @Test(description = "verify getting existing comment by id")
    public void verifyGetCommentByValidId() {
        Comment comment = new Comment();
        comment.setId(1);

        GetCommentsById getCommentsById = new GetCommentsById(comment.getId());
        getCommentsById.addProperty("comment",comment);
        getCommentsById.callAPIExpectSuccess();

        getCommentsById.validateResponse();
    }

    @Test(description = "verify that list of comments matches schema")
    public void verifyGetAllCommentsMatchesSchemaTest() {
        GetAllComments getAllComments = new GetAllComments();

        getAllComments.callAPIExpectSuccess();

        getAllComments.validateResponseAgainstSchema("api/comments/_get/rs.schema");
    }

    @Test(dataProvider = "provideDataForCreatingComments")
    public void verifyCreateCommentTest(Integer postId, String name, String email, String body) {
        Post post = new Post();
        post.setId(postId);

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setName(name);
        comment.setEmail(email);
        comment.setBody(body);

        CreateComments createComments = new CreateComments();
        createComments.addProperty("comment", comment);

        createComments.callAPIExpectSuccess();

        createComments.validateResponse();
    }

    @Test
    public void verifyCreateAndUpdateCommentTest() {
        Post post = new Post();
        post.setId(1);

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setName("Initial Name");
        comment.setEmail("initial.email@example.com");
        comment.setBody("Initial comment body");

        CreateComments createComments = new CreateComments();
        createComments.addProperty("comment", comment);

        Response createCommentResponse = createComments.callAPIExpectSuccess();
        createComments.validateResponse();

        Comment receivedComment = createCommentFromJson(createCommentResponse.jsonPath());

        receivedComment.setName("Updated Name");
        receivedComment.setEmail("updated.email@example.com");
        receivedComment.setBody("Updated comment body");

        UpdateComments updateComments = new UpdateComments(receivedComment.getId());
        updateComments.addProperty("update_comment", receivedComment);

        updateComments.callAPIExpectSuccess();
        updateComments.validateResponse();
    }

    private static Comment createCommentFromJson(JsonPath jsonPath) {
        Post readPost = new Post();
        readPost.setId(jsonPath.getInt("postId"));

        Comment readComment = new Comment();
        readComment.setId(jsonPath.getInt("id"));
        readComment.setPost(readPost);
        readComment.setName(jsonPath.getString("name"));
        readComment.setEmail(jsonPath.getString("email"));
        readComment.setBody(jsonPath.getString("body"));

        return readComment;
    }
}
