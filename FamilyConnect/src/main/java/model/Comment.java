
package model;

public class Comment {

    private long commentId;
    private long postId;
    private long authorId;
    private String content;

    public Comment() {
    }

    public Comment(long commentId, long postId,
                   long authorId, String content) {
        this.commentId = commentId;
        this.postId = postId;
        this.authorId = authorId;
        this.content = content;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
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

    @Override
    public String toString() {
        return "Comment{"
                + "commentId=" + commentId
                + ", postId=" + postId
                + ", authorId=" + authorId
                + ", content='" + content + '\''
                + '}';
    }
}
