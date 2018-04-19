package com.shallowan.milu.live.dao;

import com.shallowan.milu.live.domain.RoomInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ShallowAn
 */
@Mapper
public interface RoomInfoDao {
    @Insert("insert into room_info(room_id, user_id, user_name, user_avatar, live_cover, live_title, watcher_nums) values(0,#{userId},#{userName},#{userAvatar},#{liveCover},#{liveTitle},0)")
    int create(RoomInfo roomInfo);

    @Select("select * from room_info where user_id = #{userId}")
    RoomInfo getRoomInfoByUserId(String userId);

    @Delete("delete from room_info where user_id = #{userId}")
    int deleteRoomInfoByUserId(String userId);

    @Select("select * from room_info")
    List<RoomInfo> getRoomList();

    @Delete("delete from room_info where room_id = #{roomId}")
    int deleteRoomInfoByRoomId(String roomId);

    @Select("select user_id from room_info where room_id = #{roomId}")
    List<String> getUsersIdByRoomId(String roomId);

    @Select("select user_id, watcher_nums from room_info where room_id = #{roomId}")
    List<RoomInfo> getUserIdAndWatcherNumsByRoomId(String roomId);

    @Update("update room_info set watcher_nums = #{finalWatchNums} where room_id = #{roomId}")
    void updateWatcherNums(int finalWatchNums, String roomId);
}
