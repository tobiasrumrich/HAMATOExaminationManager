package de.hatoma.exman.service.room;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import de.hatoma.exman.dao.room.ICourseDAO;
import de.hatoma.exman.model.room.Course;

/**
 * The course service that manages all business functionality.
 */
public class CourseService implements ICourseService {

	/** The {@link ICourseDAO} to use for db access. */
	private ICourseDAO courseDAO;

	/** {@inheritDoc} */
	@Override
	public Course createCourse(String fieldOfStudy, int number,
			String lecturer, String title) {
		// Create course object
		Course course = new Course();
		course.setFieldOfStudy(fieldOfStudy);
		course.setNumber(number);
		course.setLecturer(lecturer);
		course.setTitle(title);
		try {
			getCourseDAO().save(course);
		} catch (ConstraintViolationException exception) {
			throw new CourseAlreadyExistsException();
		}
		return course;
	}

	/** {@inheritDoc} */
	@Override
	public List<Course> listCourses() {
		return getCourseDAO().findAll();
	}

	/** {@inheritDoc} */
	@Override
	public Course loadCourse(Long courseId) {
		Course course = getCourseDAO().load(courseId);
		if (course == null) {
			throw new CourseNotFoundException();
		}
		return course;
	}

	/** {@inheritDoc} */
	@Override
	public void updateCourse(Long courseId, String lecturer, String title) {
		Course course = loadCourse(courseId);
		course.setLecturer(lecturer);
		course.setTitle(title);
	}

	/** {@inheritDoc} */
	@Override
	public void deleteCourse(Long courseId) {
		Course course = loadCourse(courseId);
		getCourseDAO().delete(course);
	}

	public ICourseDAO getCourseDAO() {
		return courseDAO;
	}

	public void setCourseDAO(ICourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

}
