package com.ja.freeboard.model;

import java.sql.*;
import java.util.*;

public class MemberDao {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "SCOTT";
	private static final String PASSWORD = "TIGER";

	public void insert(String m_id, String m_pw, String m_nick, String m_phone) {
		String query = "INSERT INTO fb_member VALUES(fb_member_seq.nextval,?,?,?,?,SYSDATE)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
		    Class.forName("oracle.jdbc.driver.OracleDriver");
		    
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
}
