package com.solvd.laba.testing.api;


import com.solvd.laba.api.PostComment;
import com.solvd.laba.api.GetAllComments;
import com.solvd.laba.api.GetCommeentById;
import com.solvd.laba.api.PatchComment;
import com.solvd.laba.domain.Comment;
import com.solvd.laba.domain.Post;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

public class CommentAPITests {

    private Post post;

    @BeforeMethod
    public void setup() {
        post = new Post();
        post.setId(1);
    }

    @DataProvider
    public Object[][] dataForCommentCreation() {
        return new Object[][]{
                {1, "John Doe", "john.doe@example.com", "This is a comment"},
                {1, null, "jane.doe@example.com", "Another insightful comment"}
        };
    }

    @Test(description = "Verify that the retrieved list of comments conforms to the schema")
    public void shouldMatchCommentListSchema() {
        GetAllComments commentsRetrieval = new GetAllComments();
        commentsRetrieval.callAPIExpectSuccess();
        commentsRetrieval.validateResponseAgainstSchema("api/comments/_get/rs.schema");
    }

    @Test(dataProvider = "dataForCommentCreation")
    public void shouldCreateCommentSuccessfully(Integer postId, String name, String email, String body) {
        Comment comment = createComment(postId, name, email, body);
        PostComment commentCreation = new PostComment();
        commentCreation.addProperty("comment", comment);
        commentCreation.callAPIExpectSuccess();
        commentCreation.validateResponse();
    }
@Test
    public void shouldCreateAndUpdateComment() {

        Post post= new Post();
        post.setId(1);
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setName("Initial Name");
        comment.setEmail("initial.email@example.com");
        comment.setBody("Initial comment body");

        PostComment commentCreation = new PostComment();
        commentCreation.addProperty("comment", comment);
        Response response = commentCreation.callAPIExpectSuccess();
        commentCreation.validateResponse();


        Comment receivedComment = parseCommentFromJson(response.jsonPath());


        receivedComment.setName("Updated Name");
        receivedComment.setEmail("updated.email@example.com");
        receivedComment.setBody("Updated comment body");


        PatchComment modifyComment = new PatchComment(receivedComment.getId());
        modifyComment.addProperty("update_comment", receivedComment);

        Response modifyCommentResponse = modifyComment.callAPIExpectSuccess();
        modifyComment.validateResponse();
    }


    @Test(description = "Verify retrieval of an existing comment by ID")
    public void shouldRetrieveCommentById() {
        Comment comment = new Comment();
        comment.setId(1);

        GetCommeentById commentRetrieval = new GetCommeentById(comment.getId());
        commentRetrieval.addProperty("comment", comment);
        commentRetrieval.callAPIExpectSuccess();

        commentRetrieval.validateResponse();
    }

    private Comment createComment(Integer postId, String name, String email, String body) {
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setName(name);
        comment.setEmail(email);
        comment.setBody(body);
        return comment;
    }

    private void updateComment(Comment comment, String newName, String newEmail, String newBody) {
        comment.setName(newName);
        comment.setEmail(newEmail);
        comment.setBody(newBody);

        PatchComment commentUpdate = new PatchComment(comment.getId());
        commentUpdate.addProperty("update_comment", comment);
        commentUpdate.callAPIExpectSuccess();
        commentUpdate.validateResponse();
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
}
