package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConn {

	public static void main(String[] args) {
		final String driver = "org.mariadb.jdbc.Driver";
		final String DB_IP = "localhost";
		final String DB_PORT = "3306";
		final String DB_NAME = "boot_db";
		final String DB_URL = 
				"jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			//1.Driver Class Loading
			Class.forName(driver);
			System.out.println("DB_URL = " + DB_URL);
			//2. DB���� ������ ����ϴ� Connection ��ü ����
			conn = DriverManager.getConnection(DB_URL, "boot", "boot");
			System.out.println("Connection ClassName ="+ conn.getClass().getName());
			if (conn != null) {
				System.out.println("DB ���� ����");
			}

		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB ���� ����");
			e.printStackTrace();
		}

		try {
			String sql = "select * from users where userid=?";
			//3. SQL���� DB���� �����ϴ� ������ �ϴ� Statement
			pstmt = conn.prepareStatement(sql);
			System.out.println("Statement ClassName :"+pstmt.getClass().getName());
			pstmt.setString(1, "dooly");
			//4. SQL�� ���� ����� ��� ������ �ϴ� ResultSet
			rs = pstmt.executeQuery();
			System.out.println("ResultSet ClassName :"+ rs.getClass().getName());
			String userId = null;
			String name = null;
			String gender = null;
			String city = null;
			
//			System.out.println(rs.next()); Ŀ���� �̵��� �� �������� ����/ true������ ����
			while (rs.next()) {
				//�ش� �÷��� ���� ��������
				userId = rs.getString(2); //2 or "userid"
				name = rs.getString("name");
				gender = rs.getString("gender");
				city = rs.getString("city");
				
				System.out.print(userId);
				System.out.print(name);
				System.out.print(gender);
				System.out.print(city);
				System.out.println();
			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}