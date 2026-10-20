
package service;

import java.util.ArrayList;
import java.util.List;
import model.NewsPost;
import model.PhotoPost;
import model.Post;
import util.FileService;

public class PostService {

    private final FileService fileService;
    private final String POST_FILE = "data/posts.txt";

    public PostService() {
        fileService = new FileService();
    }

    // Get all posts
    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        List<String> lines = fileService.readFile(POST_FILE);

        for (String line : lines) {
            try {
                String[] parts = line.split("\\|", -1);

                if (parts.length != 7) {
                    continue;
                }

                long postId = Long.parseLong(parts[0]);
                String postType = parts[1];
                long authorId = Long.parseLong(parts[2]);
                String content = parts[3];
                String createdAt = parts[4];
                String status = parts[5];
                String imagePath = parts[6];

                Post post;

                if (postType.equalsIgnoreCase("NEWS")) {
                    post = new NewsPost(
                            postId, authorId, content, createdAt, status
                    );
                } else if (postType.equalsIgnoreCase("PHOTO")) {
                    post = new PhotoPost(
                            postId, authorId, content,
                            createdAt, status, imagePath
                    );
                } else {
                    continue;
                }

                posts.add(post);

            } catch (NumberFormatException e) {
                System.out.println("Invalid post data: " + line);
            }
        }

        return posts;
    }

    // Find a post by ID
    public Post findPostById(long postId) {
        for (Post post : getAllPosts()) {
            if (post.getPostId() == postId) {
                return post;
            }
        }

        return null;
    }

    // Add a news post
    public boolean addNewsPost(NewsPost post) {
        if (post == null) {
            return false;
        }

        if (post.getContent() == null
                || post.getContent().trim().isEmpty()) {
            System.out.println("Post content cannot be empty.");
            return false;
        }

        post.setPostId(generateNextPostId());

        String line = post.getPostId() + "|"
                + "NEWS" + "|"
                + post.getAuthorId() + "|"
                + safe(post.getContent()) + "|"
                + safe(post.getCreatedAt()) + "|"
                + safe(post.getStatus()) + "|";

        return savePostLine(line);
    }

    // Add a photo post
    public boolean addPhotoPost(PhotoPost post) {
        if (post == null) {
            return false;
        }

        if (post.getContent() == null
                || post.getContent().trim().isEmpty()) {
            System.out.println("Post content cannot be empty.");
            return false;
        }

        if (post.getImagePath() == null
                || post.getImagePath().trim().isEmpty()) {
            System.out.println("Image path cannot be empty.");
            return false;
        }

        post.setPostId(generateNextPostId());

        String line = post.getPostId() + "|"
                + "PHOTO" + "|"
                + post.getAuthorId() + "|"
                + safe(post.getContent()) + "|"
                + safe(post.getCreatedAt()) + "|"
                + safe(post.getStatus()) + "|"
                + safe(post.getImagePath());

        return savePostLine(line);
    }

    // Generate the next post ID
    private long generateNextPostId() {
        long newId = 1;

        for (Post post : getAllPosts()) {
            if (post.getPostId() >= newId) {
                newId = post.getPostId() + 1;
            }
        }

        return newId;
    }

    // Save one post line
    private boolean savePostLine(String line) {
        List<String> lines = fileService.readFile(POST_FILE);
        lines.add(line);

        return fileService.writeFile(POST_FILE, lines);
    }

    // Prevent "|" from breaking the file format
    private String safe(String value) {
        if (value == null) {
            return "";
        }

        return value.replace("|", "/");
    }
}
