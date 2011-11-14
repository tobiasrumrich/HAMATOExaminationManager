/**
 * 
 */
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
import org.hibernate.envers.Audited;

/**
 * The course entity.
 */
@Entity
@Audited
@Table(name = "COURSE")
public class Course implements Serializable {
	/** The serial version UID. */
	private static final long serialVersionUID = 1L;
	/** The field of study. */
	private String fieldOfStudy;
	/** The identifier. */
	private long id;
	/** The lecturer's name. */
	private String lecturer;
	/** The list of lectures. */
	private Set<Lecture> lectures;
	/** The course number. */
	private int number;
	/** The course title. */
	private String title;

	/**
	 * Associates the given lecture to this course.
	 * 
	 * @param lecture
	 *            The lecture to associate.
	 */
	public void associateLecture(Lecture lecture) {
		if (lecture == null) {
			throw new IllegalArgumentException();
		}
		if (this.equals(lecture.getCourse())) {
			// The same course is already associated
			return;
		}
		if (lecture.getCourse() != null) {
			lecture.getCourse().getLectures().remove(lecture);
		}
		lecture.setCourse(this);
		this.lectures.add(lecture);
	}

	/**
	 * Detaches lecture from this course.
	 */
	public void detachLecture(Lecture lecture) {
		if (lecture == null) {
			throw new IllegalArgumentException();
		}
		if (this.equals(lecture.getCourse())) {
			// unlink the lecture
			lecture.setCourse(null);
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
		final Course other = (Course) obj;
		if (fieldOfStudy == null) {
			if (other.fieldOfStudy != null)
				return false;
		} else if (!fieldOfStudy.equals(other.fieldOfStudy))
			return false;
		if (number != other.number)
			return false;
		return true;
	}

	@NaturalId
	@Column(name = "FIELD_OF_STUDY", nullable = false)
	public String getFieldOfStudy() {
		return fieldOfStudy;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	@Column(nullable = false)
	public String getLecturer() {
		return lecturer;
	}

	@OneToMany(mappedBy = "course")
	public Set<Lecture> getLectures() {
		return lectures;
	}

	@NaturalId
	@Column(nullable = false)
	public int getNumber() {
		return number;
	}

	@Column(nullable = false)
	public String getTitle() {
		return title;
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fieldOfStudy == null) ? 0 : fieldOfStudy.hashCode());
		result = prime * result + number;
		return result;
	}

	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}

	public void setLectures(Set<Lecture> lectures) {
		this.lectures = lectures;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
