package de.hatoma.exman.action;

import java.util.HashSet;

import com.opensymphony.xwork2.Action;

import de.hatoma.exman.model.room.Room;

public class AuditTrailAction implements Action {

	private HashSet<Room> rooms;

	@Override
	public String execute() {
		return null;
	}

}
