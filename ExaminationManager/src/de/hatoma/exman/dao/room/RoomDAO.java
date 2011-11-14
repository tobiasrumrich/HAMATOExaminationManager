package de.hatoma.exman.dao.room;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hatoma.exman.model.room.Room;

public class RoomDAO extends HibernateDaoSupport {

	/** {@inheritDoc} */

	public void delete(Room room) {
		getHibernateTemplate().delete(room);
	}

	/** {@inheritDoc} */

	@SuppressWarnings("unchecked")
	public List<Room> findAll() {
		return getHibernateTemplate().loadAll(Room.class);
	}

	/** {@inheritDoc} */

	public long findIdByBuildingAndRoomNumber(String building, int roomNumber) {
		Query query = getHibernateTemplate()
				.getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"select room.id from Room as room where room.building = :building and room.roomNumber = :roomNumber");
		query.setLong("roomNumber", roomNumber);
		query.setString("building", building);
		Long roomId = (Long) query.uniqueResult();
		if (roomId != null) {
			return roomId.longValue();
		}
		return 0;
	}

	/** {@inheritDoc} */

	public Room load(long id) {
		return (Room) getHibernateTemplate().get(Room.class, id);
	}

	/** {@inheritDoc} */

	public Room save(Room room) {
		getHibernateTemplate().save(room);
		return room;
	}

	public void update(Room entity) {
		// nothing
	}

}
