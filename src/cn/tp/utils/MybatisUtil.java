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
	 * 加载位于src/mybatis.xml配置文件
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
	 * 禁止外界通过new方法创建
	 */
	private MybatisUtil() {
	}

	/**
	 * 获取SqlSession
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
	 * 关闭SqlSession与当前线程分开
	 */
	public static void closeSqlSession() {
		SqlSession sqlSession = threadLocal.get();
		if (sqlSession != null) {
			sqlSession.close();
			threadLocal.remove();	
		}
	}
}
