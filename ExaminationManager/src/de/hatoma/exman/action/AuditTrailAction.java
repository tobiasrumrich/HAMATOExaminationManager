package de.hatoma.exman.action;

import java.util.HashSet;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

import com.opensymphony.xwork2.Action;

import de.hatoma.exman.model.room.Room;
import de.hatoma.exman.service.room.IRoomService;

public class AuditTrailAction implements Action {
	
	private HashSet<Room> rooms;
	
	@Override
	public String execute(){
		rooms = new HashSet<Room>(roomService.listRooms());
		return null;
		
	}
	

	public HashSet<Room> getRooms() {
		return rooms;
	}

	public void setRooms(HashSet<Room> rooms) {
		this.rooms = rooms;
	}

	public IRoomService getRoomService() {
		return roomService;
	}

	public void setRoomService(IRoomService roomService) {
		this.roomService = roomService;
	}

	private IRoomService roomService;



}
