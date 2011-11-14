package de.hatoma.exman.dao;

import org.springframework.stereotype.Component;

import de.hatoma.exman.model.Maniple;

@Component
public class ManipleDAO extends BaseDAO<Maniple> implements IManipleDAO {

	public ManipleDAO() {
		super(Maniple.class);
		// TODO Auto-generated constructor stub
	}

}
