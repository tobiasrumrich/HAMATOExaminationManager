package de.hatoma.exman.model.envers;

import org.hibernate.envers.RevisionListener;

public class ExManRevisionListener implements RevisionListener {
	public void newRevision(Object revisionEntity) {
		ExManRevisionEntity revEntity = (ExManRevisionEntity) revisionEntity;
		revEntity.setChangedBy("MyFunkyUsername " + Math.random());
	}
}