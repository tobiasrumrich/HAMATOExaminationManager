package de.hatoma.exman.service.room;

import java.util.List;

import de.hatoma.exman.model.room.Room;

public interface IRoomService {

	/**
	 * List all rooms currently stored in the database.
	 * 
	 * @return a list of room entities. If no room was found an empty list is
	 *         returned.
	 */
	List<Room> listRooms();

	/**
	 * Creates a new room in the database.
	 * 
	 * @param roomNumber
	 *            The room number
	 * @param building
	 *            The building
	 * @param seats
	 *            The seat count
	 * @param beamer
	 *            The beamer information
	 * @throws RoomAlreadyExistsException
	 *             if the room already exists in the database.
	 */
	Room createRoom(int roomNumber, String building, int seats, boolean beamer);

	/**
	 * Returns the room identified by the given id.
	 * 
	 * @param roomId
	 *            The identifier.
	 * @return the found entity.
	 * @throws RoomNotFoundException
	 *             if no room could be found for the given id.
	 */
	Room loadRoom(long roomId);

	/**
	 * Deletes the room with the given id.
	 * 
	 * @param roomId
	 *            The identifier.
	 * @throws RoomNotFoundException
	 *             if no room could be fount for the given id.
	 * @throws RoomInUseException
	 *             if the room is still in use by lectures.
	 */
	void deleteRoom(long roomId);

	/**
	 * Updates a room entity and stores the changes into the database.
	 * 
	 * @param roomId
	 *            The identifier.
	 * @param seats
	 *            The seat count.
	 * @param beamer
	 *            The beamer information.
	 * @throws RoomNotFoundException
	 *             if no room could be found for the given id.
	 */
	void updateRoom(long roomId, int seats, boolean beamer);

	/**
	 * Finds a room id by room number and building.
	 * 
	 * @param building
	 *            The building.
	 * @param roomNumber
	 *            The seat roomNumber.
	 * @return The room id or 0 if nothing found
	 */
	long findIdByBuildingAndRoomNumber(String building, int roomNumber);

}