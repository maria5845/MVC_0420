package com.ja.freeboard.model;

import java.sql.*;
import java.util.*;
import java.util.Date;

import com.ja.freeboard.vo.*;

public class MemberDao {
	// AWS 혹은 외부접속을 시도할때는 @localhost : 이부분을 변경해주자

	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "SCOTT";
	private static final String PASSWORD = "TIGER";

	public void insert(String m_id, String m_pw, String m_nick, String m_phone) {
		String query = "INSERT INTO fb_member VALUES(fb_member_seq.nextval,?,?,?,?,SYSDATE)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// oracle.jdbc.driver.OracleDriver d; 동적으로 로드가 가능
			// 한번 꺼내야 되기 때문에 Class.forName을 작성함
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 자체내의 프로토콜을 사용하여 오라클과 연동하는 과정 이클립스가 활용됨
			// URL이 들어가는데
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstm = conn.prepareStatement(query);
			pstm.setString(1, m_id);
			pstm.setString(2, m_pw);
			pstm.setString(3, m_nick);
			pstm.setString(4, m_phone);
			pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstm != null) {
				try {
					pstm.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

	// SELECT문 연동
	public MemberVo selectbyIdAndPw(String id, String pw) {
		MemberVo memberVo = null;
       
		String query = "SELECT * FROM fb_member WHERE m_id = ? AND m_pw = ? ";

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// 맨처음 한번 오라클을 로드한다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstm = conn.prepareStatement(query);
			pstm.setString(1, id);
			pstm.setString(2, pw);
			rs = pstm.executeQuery(); // 익스큐트 업데이트는 인서트 업데이트 구문에 사용

			// 로직

			if (rs.next()) {
				int m_no = rs.getInt("m_no");
				String m_id = rs.getString("m_id");
				String m_pw = rs.getString("m_pw");
				String m_nick = rs.getString("m_nick");
				String m_phone = rs.getString("m_phone");
				Date m_joindate = rs.getDate("m_joindate"); // Date가 sql과 유틸 두군데에 있기 때문에 선택해야함
				memberVo = new MemberVo(m_no, m_id, m_pw, m_nick, m_phone, m_joindate);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return memberVo;
	}

	public MemberVo selectByNo(int no) {
		
		MemberVo memberVo = null;

		String query = "SELECT  * FROM fb_member WHERE m_no =?";

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// 맨처음 한번 오라클을 로드한다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, no);
			rs = pstm.executeQuery(); // 익스큐트 업데이트는 인서트 업데이트 구문에 사용

			// 로직

			if (rs.next()) {
				int m_no = rs.getInt("m_no");
				String m_id = rs.getString("m_id");
				String m_pw = rs.getString("m_pw");
				String m_nick = rs.getString("m_nick");
				String m_phone = rs.getString("m_phone");
				Date m_joindate = rs.getDate("m_joindate"); // Date가 sql과 유틸 두군데에 있기 때문에 선택해야함
				memberVo = new MemberVo(m_no, m_id, m_pw, m_nick, m_phone, m_joindate);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return memberVo;	
	}	
}
