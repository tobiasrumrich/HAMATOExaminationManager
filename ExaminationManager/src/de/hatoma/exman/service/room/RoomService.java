package de.hatoma.exman.service.room;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import de.hatoma.exman.dao.room.IRoomDAO;
import de.hatoma.exman.model.room.Room;

/**
 * The room service that manages all business functionality.
 */
public class RoomService implements IRoomService {

	/** The {@link IRoomDAO} to use for db access. */
	private IRoomDAO roomDAO;

	/** {@inheritDoc} */
	@Override
	public Room createRoom(int roomNumber, String building, int seats,
			boolean beamer) {
		Room room = new Room();
		room.setBuilding(building);
		room.setRoomNumber(roomNumber);
		room.setSeats(seats);
		room.setBeamer(beamer);
		try {
			getRoomDAO().save(room);
		} catch (ConstraintViolationException exception) {
			throw new RoomAlreadyExistsException();
		}
		return room;
	}

	/** {@inheritDoc} */
	@Override
	public void deleteRoom(long roomId) {
		Room room = loadRoom(roomId);
		if (!room.getLectures().isEmpty()) {
			throw new RoomInUseException();
		}
		getRoomDAO().delete(room);
	}

	/** {@inheritDoc} */
	@Override
	public long findIdByBuildingAndRoomNumber(String building, int roomNumber) {
		return getRoomDAO().findIdByBuildingAndRoomNumber(building, roomNumber);
	}

	public IRoomDAO getRoomDAO() {
		return roomDAO;
	}

	/** {@inheritDoc} */
	@Override
	public List<Room> listRooms() {
		return getRoomDAO().findAll();
	}

	/** {@inheritDoc} */
	@Override
	public Room loadRoom(long roomId) {
		Room room = getRoomDAO().load(roomId);
		if (room == null) {
			throw new RoomNotFoundException();
		}
		return room;
	}

	public void setRoomDAO(IRoomDAO roomDAO) {
		this.roomDAO = roomDAO;
	}

	/** {@inheritDoc} */
	@Override
	public void updateRoom(long roomId, int seats, boolean beamer) {
		Room room = loadRoom(roomId);
		room.setSeats(seats);
		room.setBeamer(beamer);
		//getRoomDAO().update(room);
	}
}
