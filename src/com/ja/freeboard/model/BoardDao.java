package com.ja.freeboard.model;

import java.util.*;
import java.util.Date;
import java.sql.*;
import com.ja.freeboard.vo.*;

public class BoardDao {

	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "SCOTT";
	private static final String PASSWORD = "TIGER";

	public ArrayList<BoardVo> selectAll() {
		// 결과가 있던 없던 만들어야함
		ArrayList<BoardVo> list = new ArrayList<BoardVo>();

		String query = "SELECT * FROM fb_board ORDER BY b_no DESC";

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// 맨처음 한번 오라클을 로드한다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery(); // 익스큐트 업데이트는 인서트 업데이트 구문에 사용

			// 로직

			while (rs.next()) {
				int b_no = rs.getInt("b_no");
				int m_no = rs.getInt("m_no");
				String b_title = rs.getString("b_title");
				String b_content = rs.getString("b_content");
				Date b_writedate = rs.getDate("b_writedate");
				BoardVo boardVo = new BoardVo(b_no, m_no, b_title, b_content, b_writedate);
				list.add(boardVo);

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

		return list;

	}

	public void delete(int no) {
		String query = "DELETE FROM fb_board WHERE b_no = ? ";
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
			pstm.setInt(1, no);
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

	public void update(int no, String title, String content) {

		String query = "UPDATE fb_board SET b_title = ? , b_content = ? WHERE b_no= ? ";
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
			pstm.setString(1, title);
			pstm.setString(2, content);
			pstm.setInt(3, no);
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

	public void insert(int memberNo, String title, String content) {

		String query = "INSERT INTO fb_board VALUES (fb_board_seq.nextval,?,?,?,SYSDATE) ";
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
			pstm.setInt(1, memberNo);
			pstm.setString(2, title);
			pstm.setString(3, content);
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

	public BoardVo selectByNo(int no) {
		BoardVo boardVo = null;
		
		String query = "SELECT * FROM fb_board WHERE b_no = ?";

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// 맨처음 한번 오라클을 로드한다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			pstm = conn.prepareStatement(query);
			pstm.setInt(1,no);
			rs = pstm.executeQuery(); // 익스큐트 업데이트는 인서트 업데이트 구문에 사용

			// 로직
            if(rs.next()) {
            	int b_no = rs.getInt("b_no");
            	int m_no = rs.getInt("m_no");
                String b_title = rs.getString("b_title");
                String b_content = rs.getString("b_content");
                Date b_writedate = rs.getDate("b_writedate");
        		boardVo = new BoardVo(b_no,m_no,b_title,b_content,b_writedate);
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
		return boardVo;
	}
}
