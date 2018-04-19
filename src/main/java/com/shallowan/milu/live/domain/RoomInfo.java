package com.shallowan.milu.live.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author ShallowAn
 */
@Data
public class RoomInfo {

    private Integer roomId;

    @NotNull
    private String userId;

    @NotNull
    private String userName;

    @NotNull
    private String userAvatar;

    @NotNull
    private String liveCover;

    @NotNull
    private String liveTitle;

    private Integer watcherNums;

    @Override
    public String toString() {
        return "RoomInfo{" +
                "roomId=" + roomId +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", liveCover='" + liveCover + '\'' +
                ", liveTitle='" + liveTitle + '\'' +
                ", watcherNums=" + watcherNums +
                '}';
    }
}
