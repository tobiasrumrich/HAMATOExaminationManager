package de.hatoma.exman.action;

import java.util.HashSet;
import java.util.Set;

import com.opensymphony.xwork2.Action;

import de.hatoma.exman.model.room.Room;
import de.hatoma.exman.service.room.IRoomService;

public class ShowRoomListAction implements Action {
	private IRoomService roomService;
	private Set<Room> rooms;

	@Override
	public String execute() throws Exception {
		rooms = new HashSet<Room>(roomService.listRooms());
		return SUCCESS;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	public IRoomService getRoomService() {
		return roomService;
	}

	public void setRoomService(IRoomService roomService) {
		this.roomService = roomService;
	}

}
