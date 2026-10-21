package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;

public class NotificationDAO {

    // 1) Lấy danh sách thông báo theo user (mới nhất trước)
    public List<Notification> getNotificationsByUser(int userID) throws Exception {
        List<Notification> list = new ArrayList<>();

        String sql = "SELECT notificationID, userID, title, content, type, isRead, createdAt "
                   + "FROM Notification "
                   + "WHERE userID = ? "
                   + "ORDER BY createdAt DESC";

        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userID);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Notification n = new Notification();
                    n.setNotificationID(rs.getInt("notificationID"));
                    n.setUserID(rs.getInt("userID"));
                    n.setTitle(rs.getString("title"));
                    n.setContent(rs.getString("content"));
                    n.setType(rs.getString("type"));

                    // isRead có thể là BIT/BOOLEAN -> dùng getBoolean là ổn
                    n.setIsRead(rs.getBoolean("isRead"));

                    n.setCreatedAt(rs.getTimestamp("createdAt"));
                    list.add(n);
                }
            }
        }

        return list;
    }

    // 2) Đánh dấu đã đọc theo notificationID
    public boolean markAsRead(int notificationID) throws Exception {
        String sql = "UPDATE Notification SET isRead = 1 WHERE notificationID = ?";

        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, notificationID);

            return ps.executeUpdate() > 0;
        }
    }
}