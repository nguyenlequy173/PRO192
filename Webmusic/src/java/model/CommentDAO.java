/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

    private Connection conn;

    public CommentDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean addComment(Comment comment) {
        String sql = "INSERT INTO Comment (userID, songID, content, createdAt, status)"
                + "VALUES (?, ?, ?, NOW(), ?)";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, comment.getUserID());
            ps.setInt(2, comment.getSongID());
            ps.setString(3, comment.getContent());

            ps.setString(4, comment.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Comment> getCommentsBySong(int songID) {
        List<Comment> list = new ArrayList<>();

        String sql = "SELECT * FROM Comment "
                + "WHERE songID = ? AND status = 'APPROVED' "
                + "ORDER BY createdAt DESC";

        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, songID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Comment c = new Comment();
                c.setCommentID(rs.getInt("commentID"));
                c.setUserID(rs.getInt("userID"));
                c.setSongID(rs.getInt("songID"));
                c.setContent(rs.getString("content"));
                c.setCreatedAt(rs.getTimestamp("createdAt"));
                c.setStatus(rs.getString("status"));

                list.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // 3. Update status (duyệt hoặc ẩn comment)
    public boolean updateStatus(int commentID, String status) {
        String sql = "UPDATE Comment SET status = ? WHERE commentID = ?";

        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, commentID);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // 4. Delete comment
    public boolean deleteComment(int commentID) {
        String sql = "DELETE FROM Comment WHERE commentID = ?";

        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, commentID);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
