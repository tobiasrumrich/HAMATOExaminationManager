package de.hatoma.exman.service.room;

import java.sql.Timestamp;
import java.util.List;

import de.hatoma.exman.dao.room.ILectureDAO;
import de.hatoma.exman.model.room.Course;
import de.hatoma.exman.model.room.Lecture;
import de.hatoma.exman.model.room.Room;

/**
 * The lecture service that manages all business functionality.
 */
public class LectureService implements ILectureService {

	/** The {@link ICourseService} to use. */
	private ICourseService courseService;

	/** The {@link ILectureDAO} to use for db access. */
	private ILectureDAO lectureDAO;

	/** The {@link IRoomService} to use. */
	private IRoomService roomService;

	/** {@inheritDoc} */
	@Override
	public Lecture createLecture(Timestamp beginDate, Timestamp endDate,
			long roomId, long courseId) {
		// create the lecture
		Lecture lecture = new Lecture();
		lecture.setBeginDate(beginDate);
		lecture.setEndDate(endDate);

		// find the room and course
		Room room = getRoomService().loadRoom(roomId);
		Course course = getCourseService().loadCourse(courseId);

		// associate the lecture to the room and course
		room.associateLecture(lecture);
		course.associateLecture(lecture);

		// save the lecture
		// getLectureDAO().save(lecture);
		return lecture;
	}

	/** {@inheritDoc} */
	@Override
	public void deleteLecture(int lectureId) {
		Lecture lecture = loadLecture(lectureId);
		if (lecture.getRoom() != null) {
			lecture.getRoom().detachLecture(lecture);
		}
		if (lecture.getCourse() != null) {
			lecture.getCourse().detachLecture(lecture);
		}
		// getLectureDAO().delete(lecture);
	}

	/** {@inheritDoc} */
	@Override
	public List<Lecture> findLectures(long roomId) {
		return getLectureDAO().findByRoom(roomId);
	}

	public ICourseService getCourseService() {
		return courseService;
	}

	public ILectureDAO getLectureDAO() {
		return lectureDAO;
	}

	public IRoomService getRoomService() {
		return roomService;
	}

	/** {@inheritDoc} */
	@Override
	public List<Lecture> listLectures() {
		return null; // getLectureDAO().findAll();
	}

	/** {@inheritDoc} */
	@Override
	public Lecture loadLecture(long lectureId) {
		// Lecture lecture = null; // getLectureDAO().load(lectureId);
		// if (lecture == null) {
		// throw new LectureNotFoundException();
		// }
		return null;
	}

	public void setCourseService(ICourseService courseService) {
		this.courseService = courseService;
	}

	public void setLectureDAO(ILectureDAO lectureDAO) {
		this.lectureDAO = lectureDAO;
	}

	public void setRoomService(IRoomService roomService) {
		this.roomService = roomService;
	}

	/** {@inheritDoc} */
	@Override
	public void updateLecture(int lectureId, int roomId) {
		Lecture lecture = loadLecture(lectureId);
		Room room = getRoomService().loadRoom(roomId);
		room.associateLecture(lecture);
	}

}
