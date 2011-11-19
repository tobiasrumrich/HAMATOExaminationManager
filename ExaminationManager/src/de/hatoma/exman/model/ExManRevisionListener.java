package de.hatoma.exman.model;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.User;

public class ExManRevisionListener implements RevisionListener {
	@Override
	public void newRevision(Object revisionEntity) {
		ExManRevisionEntity revEntity = (ExManRevisionEntity) revisionEntity;
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		revEntity.setChangedBy(user.getUsername());
	}
}