package de.hatoma.exman.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;

public class TrainmanService implements ITrainmanService {

	private SessionFactory sessionFactory;

	@Override
	public void transportSQLDumpToSystem(List<String> transportStatements)
			throws Exception {
		/*
		 * Transaction transaction = sessionFactory.getCurrentSession()
		 * .beginTransaction(); transaction.begin();
		 */

		for (String currentStatement : transportStatements) {

			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
					currentStatement);
			//query.executeUpdate();
		}
		/*
		 * final List<String> workerTransportStatements = transportStatements;
		 * Work transportWorker = new Work() {
		 * 
		 * @Override public void execute(Connection arg0) throws SQLException {
		 * for (String currentStatement : workerTransportStatements) {
		 * 
		 * arg0.nativeSQL(currentStatement);
		 * System.out.println("WORKER EXECUTED: "+currentStatement); }
		 * arg0.commit();
		 * 
		 * } }; sessionFactory.getCurrentSession().doWork(transportWorker);
		 */

		// SCHRITT 1: Datenbank leerfegen
		SQLQuery tableListQuery =
		sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='PUBLIC' AND TABLE_TYPE='TABLE'");

		@SuppressWarnings("unchecked")
		List<String> list = tableListQuery.list();

		for (final String tableName : list) {

			Work work = new Work() {

				@Override
				public void execute(Connection arg0) throws SQLException {

					String sql;

					sql = "ALTER TABLE " + tableName
							+ " SET REFERENTIAL_INTEGRITY FALSE;";
					arg0.nativeSQL(sql);
					System.out.println(sql);

					sql = "DELETE FROM " + tableName + ";";
					System.out.println(arg0.nativeSQL(sql));
					System.out.println(sql);

					sql = "ALTER TABLE " + tableName
							+ " SET REFERENTIAL_INTEGRITY TRUE;";
					arg0.nativeSQL(sql);
					System.out.println(sql);
					arg0.commit();

				}
			};
			sessionFactory.getCurrentSession().doWork(work);
		}

		/*
		 * Query query = session.createSQLQuery(
		 * "select * from stock s where s.stock_code = :stockCode")
		 * .addEntity(Stock.class) .setParameter("stockCode", "7277"); List
		 * result = query.list();
		 */

	}

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
