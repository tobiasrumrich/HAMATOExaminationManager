package de.hatoma.exman.dao.room;

import de.hatoma.exman.dao.IDaoTTT;
import de.hatoma.exman.model.room.Room;

public interface IRoomDAO extends IDaoTTT<Room> {

	/**
	 * finds a room id by it's building and number combination. If none was
	 * found 0 is returned.
	 */
	long findIdByBuildingAndRoomNumber(String building, int roomNumber);

}