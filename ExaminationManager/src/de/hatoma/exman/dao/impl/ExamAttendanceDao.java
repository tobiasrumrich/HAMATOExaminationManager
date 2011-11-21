package de.hatoma.exman.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IExamAttendanceDao;
import de.hatoma.exman.dao.exceptions.NoPreviousAttemptException;
import de.hatoma.exman.dao.helpers.AuditTrailBean;
import de.hatoma.exman.model.ExManRevisionEntity;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;

/**
 * 
 * @author Tobias Rumrich, 3638
 * @author Hannes Lemberg, 3547
 * @author Marcel Schroeter, 3690
 * 
 */
@Component
public class ExamAttendanceDao extends BaseDao<ExamAttendance> implements
		IExamAttendanceDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5755726939521813215L;

	public ExamAttendanceDao() {
		super(ExamAttendance.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamAttendance> findByExam(Exam exam) {
		Criteria criteria = getCurrentSession().createCriteria(
				ExamAttendance.class).add(Restrictions.eq("exam", exam));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamAttendance> findByExamSubject(ExamSubject examSubject) {
		Criteria criteria = getCurrentSession().createCriteria(
				ExamAttendance.class).add(
				Restrictions.eq("exam.examSubject", examSubject));
		return criteria.list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamAttendance> findByExamSubjectAndStudent(
			ExamSubject examSubject, Student student) {

		Criteria criteria = getCurrentSession().createCriteria(
				ExamAttendance.class);
		criteria.add(Restrictions.eq("student", student));
		criteria.createCriteria("exam").add(
				Restrictions.eq("examSubject", examSubject));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamAttendance> findbyManipleAndGrade(Maniple maniple,
			ExamGrade examGrade) {
		return getCurrentSession().createCriteria(ExamAttendance.class)
				.add(Restrictions.eq("examGrade", examGrade))
				.add(Restrictions.isNull("supplementOralExamGrade"))
				.createCriteria("student")
				.add(Restrictions.eq("maniple", maniple)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamAttendance> findbyManipleAndGrade(Maniple maniple,
			ExamGrade examGrade, ExamGrade oralExamGrade) {
		return getCurrentSession().createCriteria(ExamAttendance.class)
				.add(Restrictions.eq("examGrade", examGrade))
				.add(Restrictions.eq("supplementOralExamGrade", oralExamGrade))
				.createCriteria("student")
				.add(Restrictions.eq("maniple", maniple)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamAttendance> findByStudentAndExamSubject(Student student,
			ExamSubject examSubject) {
		return getCurrentSession().createCriteria(ExamAttendance.class)
				.add(Restrictions.eq("student", student))
				.add(Restrictions.isNotNull("supplementOralExamGrade"))
				.createCriteria("exam")
				.add(Restrictions.eq("examSubject", examSubject)).list();
	}

	@Override
	public ExamAttendance findLatestExamAttendanceOfStudentByExamSubject(
			ExamSubject examSubject, Student student)
			throws NoPreviousAttemptException {
		Criteria criteria = getCurrentSession().createCriteria(
				ExamAttendance.class);
		criteria.createCriteria("exam")
				.add(Restrictions.eq("examSubject", examSubject))
				.addOrder(Order.asc("date"));
		criteria.add(Restrictions.eq("student", student));
		if (criteria.list().size() == 0)
			throw new NoPreviousAttemptException();
		return (ExamAttendance) criteria.list().get(criteria.list().size() - 1);
	}

	@Override
	public List<AuditTrailBean<ExManRevisionEntity, ExamAttendance>> getAuditTrail(
			long id) {
		AuditReader reader = AuditReaderFactory.get(getCurrentSession());
		List<Number> revisions = reader.getRevisions(ExamAttendance.class, id);

		List<AuditTrailBean<ExManRevisionEntity, ExamAttendance>> auditTrail = new ArrayList<AuditTrailBean<ExManRevisionEntity, ExamAttendance>>();
		for (Number revisionId : revisions) {
			ExManRevisionEntity revisionEntity = reader.findRevision(
					ExManRevisionEntity.class, revisionId);
			ExamAttendance entity = reader.find(ExamAttendance.class, id,
					revisionId);
			
			Boolean isCurrentRevision;
			if (revisionId.longValue() < revisions.size()) {
				isCurrentRevision = false;
			}
			else {
				isCurrentRevision = true;
			}

			auditTrail
					.add(new AuditTrailBean<ExManRevisionEntity, ExamAttendance>(
							revisionEntity, entity, isCurrentRevision));

		}
		return auditTrail;
	}

}