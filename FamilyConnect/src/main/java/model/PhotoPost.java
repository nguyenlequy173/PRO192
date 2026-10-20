
package model;

public class PhotoPost extends Post {

    private String imagePath;

    public PhotoPost() {
        super();
    }

    public PhotoPost(long postId, long authorId, String content,
                     String createdAt, String status,
                     String imagePath) {
        super(postId, authorId, content, createdAt, status);
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String getPostType() {
        return "PHOTO";
    }

    @Override
    public String toString() {
        return "PhotoPost{"
                + "postId=" + getPostId()
                + ", authorId=" + getAuthorId()
                + ", content='" + getContent() + '\''
                + ", createdAt='" + getCreatedAt() + '\''
                + ", status='" + getStatus() + '\''
                + ", imagePath='" + imagePath + '\''
                + '}';
    }
}
