
package model;

public class NewsPost extends Post {

    public NewsPost() {
        super();
    }

    public NewsPost(long postId, long authorId, String content,
                    String createdAt, String status) {
        super(postId, authorId, content, createdAt, status);
    }

    @Override
    public String getPostType() {
        return "NEWS";
    }
}
