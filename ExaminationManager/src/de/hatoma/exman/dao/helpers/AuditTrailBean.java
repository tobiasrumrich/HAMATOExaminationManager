package de.hatoma.exman.dao.helpers;

/**
 * Bean to store a pair of a RevisionEntity and a Revision
 * 
 * @author Tobias Rumrich, 3638
 * 
 * @param <RevisionEntityType>
 *            RevisionEntity (contains meta-information related to the revision)
 * @param <EntityType>
 *            Revision of an Entity (contains the revisioned entity)
 */
public class AuditTrailBean<RevisionEntityType, EntityType> {
	private final EntityType entity;
	private final RevisionEntityType revisionEntity;
	private final Boolean isCurrentRevision;

	/**
	 * Creates a new RevisionEntity Bean
	 * 
	 * @param revisionEntity
	 *            (contains meta-information related to the revision)
	 * @param entity
	 *            Revision of the Entity (contains the revisioned entity)
	 */
	public AuditTrailBean(RevisionEntityType revisionEntity, EntityType entity, Boolean isCurrentRevision) {
		this.revisionEntity = revisionEntity;
		this.entity = entity;
		this.isCurrentRevision = isCurrentRevision;
	}

	/**
	 * @return the entity
	 */
	public EntityType getEntity() {
		return entity;
	}

	/**
	 * @return the revisionEntity
	 */
	public RevisionEntityType getRevisionEntity() {
		return revisionEntity;
	}

	/**
	 * @return the isCurrentRevision
	 */
	public Boolean isCurrentRevision() {
		return isCurrentRevision;
	}

}
