package dao.board;

import hibernate.Hibernate;

import java.util.List;

import model.board.BoardModel;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import util.ComUtil;


/**
 * 게시판 Hibernate DAO 클래스
 * @since 2013.07.29
 * @author stoneis.pe.kr
 */
public class BoardHibernateDAO implements BoardDAOImpl {
	
	/** Hibernate SQL 팩토리 */
	private SessionFactory sessionFactory = null;
	
	public BoardHibernateDAO() {
		this.sessionFactory = Hibernate.getSessionFactory();
	}
	
	/**
	 * 게시판 목록 조회
	 * @param boardModel ....
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BoardModel> selectList(BoardModel boardModel) {
		Session session = this.sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(BoardModel.class);
			criteria.setMaxResults(boardModel.getListCount());
			criteria.setFirstResult(boardModel.getStartIndex());
			String searchText = boardModel.getSearchText();
			String searchType = boardModel.getSearchType();
			if (!"".equals(searchText)) {
				Criterion subject = Restrictions.like("subject", "%"+searchText+"%");
				Criterion writer = Restrictions.like("writer", "%"+searchText+"%");
				Criterion contents = Restrictions.like("contents", "%"+searchText+"%");
				if ("ALL".equals(searchType)) {
					criteria.add(Restrictions.or(subject, writer, contents));
				} else if ("SUBJECT".equals(searchType)) {
					criteria.add(subject);
				} else if ("WRITER".equals(searchType)) {
					criteria.add(writer);
				} else if ("CONTENTS".equals(searchType)) {
					criteria.add(contents);
				}
			}
			criteria.addOrder(Order.desc("regDate"));
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		return null;
	}
	
	/**
	 * 게시판 수 조회
	 * @param boardModel
	 * @return
	 */
	public int selectCount(BoardModel boardModel) {
		Session session = this.sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(BoardModel.class);
			String searchText = boardModel.getSearchText();
			String searchType = boardModel.getSearchType();
			if (!"".equals(searchText)) {
				Criterion subject = Restrictions.like("subject", "%"+searchText+"%");
				Criterion writer = Restrictions.like("writer", "%"+searchText+"%");
				Criterion contents = Restrictions.like("contents", "%"+searchText+"%");
				if ("ALL".equals(searchType)) {
					criteria.add(Restrictions.or(subject, writer, contents));
				} else if ("SUBJECT".equals(searchType)) {
					criteria.add(subject);
				} else if ("WRITER".equals(searchType)) {
					criteria.add(writer);
				} else if ("CONTENTS".equals(searchType)) {
					criteria.add(contents);
				}
			}
			criteria.setProjection(Projections.rowCount());
			return ((Long)criteria.uniqueResult()).intValue();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		return 0;
	}
	
	/**
	 * 게시판 상세 조회
	 * @param boardModel
	 * @return
	 */
	public BoardModel select(BoardModel boardModel) {
		Session session = this.sessionFactory.openSession();
		try {
			return (BoardModel)session.get(BoardModel.class, boardModel.getNum());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		return null;
	}
	
	/**
	 * 게시판 등록 처리
	 * @param boardModel
	 */
	public void insert(BoardModel boardModel) {
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			boardModel.setRegDate(ComUtil.getDate());
			boardModel.setModDate(ComUtil.getDate());
			session.save("BoardModel", boardModel);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
	}
	
	/**
	 * 게시판 수정 처리
	 * @param boardModel
	 */
	public void update(BoardModel boardModel) {
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			BoardModel oldBoardModel = (BoardModel)session.get(BoardModel.class, boardModel.getNum());
			oldBoardModel.setSubject(boardModel.getSubject());
			oldBoardModel.setContents(boardModel.getContents());
			oldBoardModel.setWriter(boardModel.getWriter());
			oldBoardModel.setIp(boardModel.getIp());
			oldBoardModel.setModDate(ComUtil.getDate());
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
	}
	
	/**
	 * 게시판 조회수 증가 수정 처리
	 * @param boardModel
	 */
	public void updateHit(BoardModel boardModel) {
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			boardModel.setHit(boardModel.getHit()+1);
			session.update(boardModel);
			//SQLQuery sql = session.createSQLQuery("UPDATE Board SET hit = hit +1 WHERE num = :num");
			//sql.setParameter("num", boardModel.getNum());
			//sql.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
	}
	
	/**
	 * 게시판 삭제 처리
	 * @param boardModel
	 */
	public void delete(BoardModel boardModel) {
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(boardModel);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
	}
	
}
