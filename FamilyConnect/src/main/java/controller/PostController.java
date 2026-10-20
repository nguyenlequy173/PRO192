
package controller;

import java.util.List;
import model.NewsPost;
import model.PhotoPost;
import model.Post;
import service.PostService;

public class PostController {

    private final PostService postService;

    public PostController() {
        postService = new PostService();
    }

    // Get all posts
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    // Find a post by ID
    public Post findPostById(long postId) {
        return postService.findPostById(postId);
    }

    // Add a news post
    public boolean addNewsPost(NewsPost post) {
        return postService.addNewsPost(post);
    }

    // Add a photo post
    public boolean addPhotoPost(PhotoPost post) {
        return postService.addPhotoPost(post);
    }
}
