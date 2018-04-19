package com.shallowan.milu.live.service;

import com.github.pagehelper.PageHelper;
import com.shallowan.milu.live.dao.RoomInfoDao;
import com.shallowan.milu.live.domain.RoomInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ShallowAn
 */
@Service
public class RoomInfoService {
    @Autowired
    private RoomInfoDao roomInfoDao;

    private final int SIZE = 20;

    public RoomInfo create(RoomInfo roomInfo) {
        deleteRoomInfoByUserId(roomInfo.getUserId());
        int result = roomInfoDao.create(roomInfo);
        if (result < 1) {
            return null;
        }
        roomInfo = roomInfoDao.getRoomInfoByUserId(roomInfo.getUserId());
        return roomInfo;
    }

    public List<RoomInfo> getRoomList(int pageIndex) {
        PageHelper.startPage(pageIndex, SIZE);
        return roomInfoDao.getRoomList();
    }

    public List<RoomInfo> getRoomList() {
        return roomInfoDao.getRoomList();
    }

    public int deleteRoomInfoByRoomId(String roomId) {
        return roomInfoDao.deleteRoomInfoByRoomId(roomId);
    }

    public int deleteRoomInfoByUserId(String userId) {
        return roomInfoDao.deleteRoomInfoByUserId(userId);
    }

    public List<String> getUsersIdByRoomId(String roomId) {
        return roomInfoDao.getUsersIdByRoomId(roomId);
    }

    public List<RoomInfo> getUserIdAndWatcherNumsByRoomId(String roomId) {
        return roomInfoDao.getUserIdAndWatcherNumsByRoomId(roomId);
    }

    public void updateWatcherNums(int finalWatchNums, String roomId) {
        roomInfoDao.updateWatcherNums(finalWatchNums, roomId);
    }
}
