/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import utils.DbUtils;
import java.sql.PreparedStatement;

/**
 *
 * @author admin
 */
public class PlaylistSongDao {

    public boolean addSongtoPlaylist(int playlistID, int songID) throws Exception {
        String sql = "INSERT INTO    PlaylistSong(playlistID, songID, addDate) VALUES (?, ?, GETDATE())";
        try ( Connection conn = DbUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playlistID);
            ps.setInt(2, songID);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean removeSongfromPlayList(int playlistID, int songID) throws Exception {
        String sql = "  DELETE FROM PlaylistSong  WHERE playlistID=? AND songID=?";
        try ( Connection conn = DbUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, playlistID);
            ps.setInt(2, songID);
            return ps.executeUpdate() > 0;
        }
    }
}
