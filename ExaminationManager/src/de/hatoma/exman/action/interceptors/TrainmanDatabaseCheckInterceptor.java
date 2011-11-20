/**
 * @author Tobias Rumrich, 3638
 */
package de.hatoma.exman.action.interceptors;

import java.io.Serializable;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import de.hatoma.exman.service.IExamSubjectService;
import de.hatoma.exman.service.IExaminerService;
import de.hatoma.exman.service.IManipleService;
import de.hatoma.exman.service.IStudentService;

/**
 * This Interceptor checks if the data on the database complies with the minimal
 * data requirements In other words: If the database does not contain at least
 * one student, one maniple an one exam subject, it does not comply with the
 * requirement. When we are in this situation (which is usually the case when
 * the database is initial/empty), the interceptor cancels the execution of the
 * current action and displays the "globalTrainmanInput" action result to the
 * user.
 * 
 * To allow the Trainman Action itself to be called there is an
 * ITrainmanInterceptor marker interface. Every action (class) implementing this
 * inteface will pass through the check of the interceptor.
 * 
 * @author tobias
 * 
 */
public class TrainmanDatabaseCheckInterceptor extends AbstractInterceptor
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IExaminerService examinerService;
	@Autowired
	private IExamSubjectService examSubjectService;
	@Autowired
	private IManipleService manipleService;
	@Autowired
	private IStudentService studentService;

	/**
	 * @return the examinerService
	 */
	public IExaminerService getExaminerService() {
		return examinerService;
	}

	@Override
	public String intercept(ActionInvocation inv) throws Exception {

		// To determine wether the mininimal requirements are complied we are
		// asking the respective services for the total number of entities that
		// they can retrieve from the database
		if (studentService.getStudentCount() == 0
				|| manipleService.getManipleCount() == 0
				|| examSubjectService.getExamSubjectCount() == 0 || examinerService.getExaminerCount() == 0) {
			// If we are at this point there is at least one service that
			// informed us, that there are no entities in our database
			Object action = inv.getAction();

			// Now we check if the current action is implementing our
			// interconnect interface
			if (action instanceof ITrainmanInterceptorInterconnect)
				// The current action is authorized to pass through our check,
				// so we will not interrupt the invocation process
				return inv.invoke();

			// The current action may not pass through so we are sending the
			// user to the trainman
			return "globalTrainmanDatabaseNotCompliant";

		}
		// If the check does not find an missing requirement on the data we
		// reach this point and do hand over processing to the other
		// interceptors that are waiting for action invocation.
		return inv.invoke();

	}

	/**
	 * @param examinerService the examinerService to set
	 */
	public void setExaminerService(IExaminerService examinerService) {
		this.examinerService = examinerService;
	}

}
