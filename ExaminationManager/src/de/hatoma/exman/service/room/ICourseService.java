package de.hatoma.exman.service.room;

import java.util.List;

import de.hatoma.exman.model.room.Course;

public interface ICourseService {

	/**
	 * Creates a new course in the database.
	 * 
	 * @param fieldOfStudy
	 *            The field of study.
	 * @param number
	 *            The course number.
	 * @param lecturer
	 *            The lecturer's name.
	 * @param title
	 *            The title.
	 * @throws CourseAlreadyPresentException
	 *             if the course already exists in the database.
	 */
	Course createCourse(String fieldOfStudy, int number,
			String lecturer, String title);

	/**
	 * List all courses currently stored in the database.
	 * 
	 * @return a list of course entities. If no course was found an empty list
	 *         is returned.
	 */
	List<Course> listCourses();

	/**
	 * Returns the course identified by the given id.
	 * 
	 * @param courseId
	 *            The identifier.
	 * @return the found entity.
	 * @throws CourseNotFoundException
	 *             if no room could be found for the given id.
	 */
	Course loadCourse(Long courseId);

	/**
	 * Updates a course entity and stores the changes into the database.
	 * 
	 * @param courseId
	 *            The identifier.
	 * @param lecturer
	 *            The lecturer.
	 * @param title
	 *            The title.
	 * @throws CourseNotFoundException
	 *             if no course could be found for the given id.
	 */
	void updateCourse(Long courseId, String lecturer,
			String title);

	/**
	 * Deletes the course with the given id.
	 * 
	 * @param courseId
	 *            The identifier.
	 * @throws CourseNotFoundException
	 *             if no course could be fount for the given id.
	 */
	void deleteCourse(Long courseId);

}