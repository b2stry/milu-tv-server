package com.shallowan.milu.live.service;

import com.shallowan.milu.live.dao.RoomInfoDao;
import com.shallowan.milu.live.domain.RoomInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author ShallowAn
 */
public class RoomListService {
    private static RoomListService manager;

    private Map<String, Long> roomMap = new HashMap<String, Long>();

    private ScheduledExecutorService service = Executors
            .newSingleThreadScheduledExecutor();

    @Autowired
    private RoomInfoService roomInfoService;

    private Runnable command = new Runnable() {
        @Override
        public void run() {
            // 10秒钟执行的任务
           // System.out.println("RoomListManager 10秒钟执行的任务");
            for (String roomId : roomMap.keySet()) {
                long lastUpdateTime = roomMap.get(roomId);
                if (lastUpdateTime + 10 * 1000 > System.currentTimeMillis()) {
                    // 说明这个房间是有效的.
                    // 无需操作

                    //System.out.println(roomId + "RoomListManager 这个房间是有效的");
                } else {
                    // 房间无效，需要从数据库中删除这个roomid
                    //System.out.println(roomId + "RoomListManager 房间无效，需要从数据库中删除这个roomid");
                    deleteRoom(roomId);
                    removeRoom(roomId);
                    WatcherListService.getInstance().removeRoom(roomId);
                }
            }

        }
    };

    private RoomListService() {
        addExistRooms();
        startMangeTimer();
    }

    private void startMangeTimer() {
        System.out.println("RoomListManager 开始10秒钟执行的任务");
        service.scheduleWithFixedDelay(command, 0, 10, TimeUnit.SECONDS);
    }

    protected void deleteRoom(String roomId) {
        // 操作数据库
        roomInfoService.deleteRoomInfoByRoomId(roomId);
    }

    public static RoomListService getInstance() {
        if (manager == null) {
            synchronized (RoomListService.class) {
                if (manager == null) {
                    manager = new RoomListService();
                }
            }
        }
        return manager;
    }

    private void addExistRooms() {
        List<RoomInfo> roomInfos = roomInfoService.getRoomList();
        for (RoomInfo roomInfo : roomInfos) {
            updateRoom(roomInfo.getRoomId() + "");
        }
    }

    public void updateRoom(String roomId) {
        roomMap.put(roomId, System.currentTimeMillis());
    }

    public void removeRoom(String roomId) {
        roomMap.remove(roomId);
    }

}
