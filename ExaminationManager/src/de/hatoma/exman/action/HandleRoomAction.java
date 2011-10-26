package de.hatoma.exman.action;

import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.model.room.Room;
import de.hatoma.exman.service.room.IRoomService;

public class HandleRoomAction extends ActionSupport {

	private static final long serialVersionUID = 7425704410173059148L;
	private IRoomService roomService;
	private String selectedId;
	private Room room;

	public String save() throws Exception {
		long roomId = roomService.findIdByBuildingAndRoomNumber(
				room.getBuilding(), room.getRoomNumber());
		if (roomId != 0) {
			room = roomService.updateRoom(roomId, room.getSeats(),
					room.isBeamer());
		} else {
			room = roomService.createRoom(room.getRoomNumber(),
					room.getBuilding(), room.getSeats(), room.isBeamer());
		}
		return SUCCESS;
	}

	public String show() throws Exception {
		long id = parseSelectedId();
		if (id < 0) {
			return INPUT;
		}
		room = roomService.loadRoom(id);
		return SUCCESS;
	}

	public String delete() throws Exception {
		long id = parseSelectedId();
		if (id < 0) {
			return INPUT;
		}
		roomService.deleteRoom(id);
		return SUCCESS;
	}

	private long parseSelectedId() {
		if (selectedId == null || selectedId.length() == 0) {
			addActionError("Kein Raum ausgewählt.");
			return -1;
		}
		try {
			return Long.parseLong(selectedId);
		} catch (NumberFormatException exception) {
			return -1;
		}
	}

	public String getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(String selectedId) {
		this.selectedId = selectedId;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public IRoomService getRoomService() {
		return roomService;
	}

	public void setRoomService(IRoomService roomService) {
		this.roomService = roomService;
	}

}
