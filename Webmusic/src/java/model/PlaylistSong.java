/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
import java.sql.Timestamp;
public class PlaylistSong {
    private int playlistID;
    private int songID;
    private Timestamp addDate;
    public PlaylistSong(){}

    public PlaylistSong(int playlistID, int songID, Timestamp addDate) {
        this.playlistID = playlistID;
        this.songID = songID;
        this.addDate = addDate;
    }

    public int getPlayListID() {
        return playlistID;
    }

    public void setPlayListID(int playlistID) {
        this.playlistID = playlistID;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public Timestamp getAddDate() {
        return addDate;
    }

    public void setAddDate(Timestamp addDate) {
        this.addDate = addDate;
    }
    
}
