package de.hatoma.exman.dao.room;

import java.util.List;

import de.hatoma.exman.dao.IDAO;
import de.hatoma.exman.model.room.Lecture;

public interface ILectureDAO extends IDAO<Lecture> {

	public abstract List<Lecture> findByRoom(long roomId);

}