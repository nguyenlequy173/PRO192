
package model;

public abstract class Post {

    private long postId;
    private long authorId;
    private String content;
    private String createdAt;
    private String status;

    public Post() {
    }

    public Post(long postId, long authorId, String content,
                String createdAt, String status) {
        this.postId = postId;
        this.authorId = authorId;
        this.content = content;
        this.createdAt = createdAt;
        this.status = status;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public abstract String getPostType();

    @Override
    public String toString() {
        return "Post{"
                + "postId=" + postId
                + ", authorId=" + authorId
                + ", content='" + content + '\''
                + ", createdAt='" + createdAt + '\''
                + ", status='" + status + '\''
                + ", postType='" + getPostType() + '\''
                + '}';
    }
}
