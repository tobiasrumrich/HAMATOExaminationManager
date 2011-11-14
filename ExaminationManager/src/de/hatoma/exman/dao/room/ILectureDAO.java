package de.hatoma.exman.dao.room;

import java.util.List;

import de.hatoma.exman.model.room.Lecture;

public interface ILectureDAO {

	public abstract List<Lecture> findByRoom(long roomId);

}