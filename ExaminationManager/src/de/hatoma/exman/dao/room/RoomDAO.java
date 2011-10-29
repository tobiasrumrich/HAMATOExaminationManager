package de.hatoma.exman.dao.room;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.query.AuditQueryCreator;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hatoma.exman.model.exam.ExamAttendance;
import de.hatoma.exman.model.room.Room;

public class RoomDAO extends HibernateDaoSupport implements IRoomDAO {

	public void something () {
		
		AuditReader auditReader = AuditReaderFactory.get(getHibernateTemplate().getSessionFactory().getCurrentSession());
		//ExamResult currentRevision = auditReader.getCurrentRevision(ExamResult.class, true);
		//auditReader.getRevisions(ExamResult.class, );
		
		AuditQueryCreator query = auditReader.createQuery();
		
		long primaryKey = 0;
		long revision = 0;
		//Revisionierte historische Version aufrufen
		ExamAttendance oldRevision = auditReader.find(ExamAttendance.class, primaryKey, revision);
		
		//Get RevisionEntity for this revision
		RevisionEntity revisionEntity = auditReader.findRevision(RevisionEntity.class, revision);
	}
	
	/** {@inheritDoc} */
	@Override
	public Room save(Room room) {
		getHibernateTemplate().save(room);
		return room;
	}

	/** {@inheritDoc} */
	@Override
	public void delete(Room room) {
		getHibernateTemplate().delete(room);
	}

	/** {@inheritDoc} */
	@Override
	@SuppressWarnings("unchecked")
	public List<Room> findAll() {
		return getHibernateTemplate().loadAll(Room.class);
	}

	/** {@inheritDoc} */
	@Override
	public Room load(long id) {
		return (Room) getHibernateTemplate().get(Room.class, id);
	}

	/** {@inheritDoc} */
	@Override
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

}
