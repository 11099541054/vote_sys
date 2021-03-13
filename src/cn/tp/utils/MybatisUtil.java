package cn.tp.utils;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {

	private static SqlSessionFactory sqlSessionFactory;
	private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();

	/**
	 * ����λ��src/mybatis.xml�����ļ�
	 */
	static {
		try {
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * ��ֹ���ͨ��new��������
	 */
	private MybatisUtil() {
	}

	/**
	 * ��ȡSqlSession
	 */
	public static SqlSession getSqlSession() {
		SqlSession sqlSession = threadLocal.get();
		if (sqlSession == null) {
			sqlSession = sqlSessionFactory.openSession();
			threadLocal.set(sqlSession);
		}
		return sqlSession;
	}

	/**
	 * �ر�SqlSession�뵱ǰ�̷ֿ߳�
	 */
	public static void closeSqlSession() {
		SqlSession sqlSession = threadLocal.get();
		if (sqlSession != null) {
			sqlSession.close();
			threadLocal.remove();	
		}
	}
}
