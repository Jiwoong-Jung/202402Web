package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class Dept {
	int deptno;
	String dname;
	String loc;
	
	public Dept(int deptno, String dname, String loc) {
		super();
		this.deptno = deptno;
		this.dname = dname;
		this.loc = loc;
	}
	
	
}

public class JdbcArrayEx {

	public static void main(String[] args) {
		List<Dept> list = new ArrayList<>();
		String url = "jdbc:mysql://localhost:3306/firm";
		String id = "root";
		String pass = "mysql";
		Statement stmt = null;
		Connection conn = null;

		String sql = "select deptno, dname, loc from dept";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pass);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
//				int deptno = rs.getInt(1);
				int deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");
//				String str = deptno+", "+dname+", "+loc;
//				System.out.println(deptno+", "+dname+", "+loc);
				Dept dept = new Dept(deptno, dname, loc);
				list.add(dept);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// list에 있는 자료를 모두 출력하세요. (향상된 반복문 사용)
		for (Dept s : list) {
			System.out.println(s);
		}
		
	}

}
