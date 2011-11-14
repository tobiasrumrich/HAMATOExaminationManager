package de.hatoma.exman.model.room;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

/**
 * The lecture entity.
 */
@Entity
@Audited
@Table(name = "LECTURE")
public class Lecture {
	/** The begin date. */
	private Date beginDate;
	/** The course this lecture is attached to. */
	private Course course;
	/** The end date. */
	private Date endDate;
	/** The identifier. */
	private long id;
	/** The room this lecture will be held in. */
	private Room room;

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Lecture other = (Lecture) obj;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (beginDate == null) {
			if (other.beginDate != null)
				return false;
		} else if (!beginDate.equals(other.beginDate))
			return false;
		return true;
	}

	@Column(nullable = false)
	public Date getBeginDate() {
		return beginDate;
	}

	@ManyToOne
	@JoinColumn(name = "COURSE_ID")
	public Course getCourse() {
		return course;
	}

	@Column(nullable = false)
	public Date getEndDate() {
		return endDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	@ManyToOne
	@JoinColumn(name = "ROOM_ID")
	public Room getRoom() {
		return room;
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result
				+ ((beginDate == null) ? 0 : beginDate.hashCode());
		return result;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}
