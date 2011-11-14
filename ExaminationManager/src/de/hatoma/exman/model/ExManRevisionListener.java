package de.hatoma.exman.model;

import org.hibernate.envers.RevisionListener;

public class ExManRevisionListener implements RevisionListener {
	@Override
	public void newRevision(Object revisionEntity) {
		ExManRevisionEntity revEntity = (ExManRevisionEntity) revisionEntity;
		revEntity.setChangedBy("MyFunkyUsername " + Math.random());
	}
}