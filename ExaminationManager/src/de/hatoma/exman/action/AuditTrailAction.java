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
		return null;
	}
	


}
