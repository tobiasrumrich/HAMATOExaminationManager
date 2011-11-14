package de.hatoma.exman.model.room;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 * The room entity.
 */
@Entity
@Audited
@Table(name = "ROOM")
@AuditTable(value = "ADT_ROOM")
public class Room implements Serializable {

	/** The serial version uid. */
	private static final long serialVersionUID = 7664217191744579056L;

	/** The beamer information. */
	private boolean beamer;
	/** The building. */
	private String building;
	/** The identifier. */
	private long id;
	/** The set of lectures to be held in this room. */
	private Set<Lecture> lectures;
	/** The room number. */
	private int roomNumber;
	/** The seat count. */
	private int seats;

	/**
	 * Associates the given lecture to this room.
	 * 
	 * @param lecture
	 *            The lecture to associate.
	 */
	public void associateLecture(Lecture lecture) {
		if (lecture == null) {
			throw new IllegalArgumentException();
		}
		if (this.equals(lecture.getRoom())) {
			// The same room is already associated
			return;
		}
		if (lecture.getRoom() != null) {
			lecture.getRoom().getLectures().remove(lecture);
		}
		lecture.setRoom(this);
		this.lectures.add(lecture);
	}

	/**
	 * Detaches lecture from this room.
	 */
	public void detachLecture(Lecture lecture) {
		if (lecture == null) {
			throw new IllegalArgumentException();
		}
		if (this.equals(lecture.getRoom())) {
			// unlink the lecture
			lecture.setRoom(null);
		}
		getLectures().remove(lecture);
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Room other = (Room) obj;
		if (building == null) {
			if (other.building != null)
				return false;
		} else if (!building.equals(other.building))
			return false;
		if (roomNumber != other.roomNumber)
			return false;
		return true;
	}

	@NaturalId
	@Column(nullable = false)
	public String getBuilding() {
		return building;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	@OneToMany(mappedBy = "room")
	public Set<Lecture> getLectures() {
		return lectures;
	}

	@NaturalId
	@Column(name = "ROOM_NUMBER", nullable = false)
	public int getRoomNumber() {
		return roomNumber;
	}

	@Column(nullable = false)
	public int getSeats() {
		return seats;
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((building == null) ? 0 : building.hashCode());
		result = prime * result + roomNumber;
		return result;
	}

	@Column(nullable = false)
	public boolean isBeamer() {
		return beamer;
	}

	public void setBeamer(boolean beamer) {
		this.beamer = beamer;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLectures(Set<Lecture> lectures) {
		this.lectures = lectures;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}
}