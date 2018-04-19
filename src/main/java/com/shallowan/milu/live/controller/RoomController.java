package com.shallowan.milu.live.controller;

import com.shallowan.milu.live.domain.RoomInfo;
import com.shallowan.milu.live.result.CodeMsg;
import com.shallowan.milu.live.result.Result;
import com.shallowan.milu.live.service.RoomInfoService;
import com.shallowan.milu.live.service.RoomListService;
import com.shallowan.milu.live.service.WatcherListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * @author ShallowAn
 */
@RestController
public class RoomController {

    @Autowired
    private RoomInfoService roomInfoService;

    @GetMapping("/create")
    public Result<RoomInfo> create(@Valid RoomInfo roomInfo) {
        RoomInfo result = roomInfoService.create(roomInfo);
        if (result == null) {
            return Result.error(CodeMsg.CREATE_ERROR);
        }
        return Result.success(result);
    }

    @GetMapping("/getList")
    public Result<List<RoomInfo>> getRoomList(@RequestParam(defaultValue = "0") int pageIndex) {
        List<RoomInfo> roomInfos = roomInfoService.getRoomList(pageIndex);
        return Result.success(roomInfos);
    }

    @GetMapping("/getWatcher")
    public Result<Set<String>> getWatchers(@RequestParam String roomId) {
        if (Integer.valueOf(roomId) < 1) {
            return Result.error(CodeMsg.REQUEST_ILLEGAL);
        }

        Set<String> watcherIdSet = WatcherListService.getInstance().getWatchers(roomId);
        return Result.success(watcherIdSet);
    }

    @GetMapping("/heartBeat")
    public Result<Object> heartBeat(@RequestParam String roomId, @RequestParam String userId) {
        if (Integer.valueOf(roomId) < 1) {
            return Result.error(CodeMsg.REQUEST_ILLEGAL);
        }

        List<String> list = roomInfoService.getUsersIdByRoomId(roomId);
        if (list == null && !list.isEmpty()) {
            for (String s : list) {
                if (s != null && s.equals(userId)) {
                    RoomListService.getInstance().updateRoom(roomId + "");
                } else {
                    WatcherListService.getInstance().updateRoomUser(roomId + "", s);
                }
            }
        }
        return Result.success(null);
    }

    @GetMapping("/quit")
    public Result<Object> quitRoom(@RequestParam String roomId, @RequestParam String userId) {
        if (Integer.valueOf(roomId) < 1) {
            return Result.error(CodeMsg.REQUEST_ILLEGAL);
        }
        List<RoomInfo> list = roomInfoService.getUserIdAndWatcherNumsByRoomId(roomId);
        if (list != null && !list.isEmpty()) {
            for (RoomInfo info : list) {
                int watchNums = info.getWatcherNums();
                String uid = info.getUserId();
                if (uid != null && uid.equals(userId)) {
                    roomInfoService.deleteRoomInfoByUserId(uid);
                    RoomListService.getInstance().removeRoom("" + roomId);
                    WatcherListService.getInstance().removeRoom("" + roomId);
                } else {
                    int finalWatchNums = (watchNums - 1);
                    roomInfoService.updateWatcherNums(finalWatchNums, roomId);
                    WatcherListService.getInstance().removeWatcher("" + roomId, userId);
                }
            }
        }
        return Result.success(null);
    }
}
