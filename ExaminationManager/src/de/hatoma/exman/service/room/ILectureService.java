package de.hatoma.exman.service.room;

import java.sql.Timestamp;
import java.util.List;

import de.hatoma.exman.model.room.Lecture;

public interface ILectureService {

	/**
	 * Creates and stores a new lecture entity.
	 * 
	 * @param beginDate
	 *            The begin of the lecture.
	 * @param endDate
	 *            The end of the lecture.
	 * @param courseId
	 *            The id of the course entity.
	 * @param roomId
	 *            The id of the room entity.
	 * @throws CourseNotFoundException
	 *             if the given course is not found.
	 * @throws RoomNotFoundException
	 *             if the given room is not found.
	 */
	Lecture createLecture(Timestamp beginDate, Timestamp endDate, long roomId,
			long courseId);

	/**
	 * Deletes the lecture with the given id.
	 * 
	 * @param lectureId
	 *            The identifier.
	 * @throws LectureNotFoundException
	 *             if no lecture could be fount for the given id.
	 */
	void deleteLecture(int lectureId);

	List<Lecture> findLectures(long roomId);

	/**
	 * List all lectures currently stored in the database.
	 * 
	 * @return a list of lecture entities. If no lecture was found an empty list
	 *         is returned.
	 */
	List<Lecture> listLectures();

	/**
	 * Returns the lecture identified by the given id.
	 * 
	 * @param lectureId
	 *            The identifier.
	 * @return the found entity.
	 * @throws LectureNotFoundException
	 *             if no lecture was found using the given identifier.
	 */
	Lecture loadLecture(long lectureId);

	/**
	 * Updates a lecture entity and stores the changes into the database.
	 * 
	 * @param lectureId
	 *            The identifier.
	 * @param roomId
	 *            The new room id.
	 * @throws LectureNotFoundException
	 *             if no lecture could be found for the given id.
	 * @throws RoomNotFoundException
	 *             if no room could be found for the given id.
	 */
	void updateLecture(int lectureId, int roomId);

}