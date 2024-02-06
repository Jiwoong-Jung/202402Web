package mySql.empCli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Emp {
	int empno;
	String ename;
	String job;
	int mgr;
	String hiredate;
	double sal;
	double comm;
	int deptno;

	Emp(int empno, String ename, String job, int mgr, String hiredate, double sal, double comm, int deptno) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.mgr = mgr;
		this.hiredate = hiredate;
		this.sal = sal;
		this.comm = comm;
		this.deptno = deptno;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getMgr() {
		return mgr;
	}

	public void setMgr(int mgr) {
		this.mgr = mgr;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public double getSal() {
		return sal;
	}

	public void setSal(double sal) {
		this.sal = sal;
	}

	public double getComm() {
		return comm;
	}

	public void setComm(double comm) {
		this.comm = comm;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
}

public class EmpCLI {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/firm";
		String id = "root";
		String pass = "mysql";
		String sql;

		

		Class.forName("com.mysql.cj.jdbc.Driver");

		try (Connection conn = DriverManager.getConnection(url, id, pass); Statement stmt = conn.createStatement();) {
			while (true) {

				System.out.println("\n1. 데이터보기");
				System.out.println("2. 데이터입력");
				System.out.println("3. 데이터삭제");
				System.out.println("4. 데이터검색");
				System.out.println("5. 데이터수정");
				System.out.println("6. 시스템종료");
				System.out.print("번호입력 > ");

				int num = Integer.parseInt(sc.nextLine());
				switch (num) {

				case 1:

					System.out.println("1. 데이터보기를 선택하셨습니다.\n");
					sql = "select * from emp";

					ResultSet rs = stmt.executeQuery(sql);
//					list = new ArrayList<>();
					List<Emp> list = new ArrayList<>();
					while (rs.next()) {
						try {
							Emp emp = new Emp(rs.getInt("empno"), rs.getString("ename"), rs.getString("job"),
									rs.getInt("mgr"), rs.getString("hiredate"), rs.getDouble("sal"),
									rs.getDouble("comm"), rs.getInt("deptno"));
							list.add(emp);
						} catch (NumberFormatException e) {
							System.out.println("올바른 숫자 형식이 아닙니다. 다시입력해주세요.");
						}
					}

					
					for (Emp emp : list) {
						System.out.println(emp.empno + ", " + emp.ename + ", " + emp.job + ", " + emp.mgr + ", "
								+ emp.hiredate + ", " + emp.sal + ", " + emp.comm + ", " + emp.deptno);
					}
					
					break;

				case 2:

					System.out.println("2. 데이터입력을 선택하셨습니다.");

					System.out.print("사원번호 입력: ");
					int empno = Integer.parseInt(sc.nextLine());

					System.out.print("이름 입력: ");
					String ename = sc.nextLine();

					System.out.print("직무 입력: ");
					String job = sc.nextLine();

					System.out.print("관리자 번호 입력: ");
					int mgr = Integer.parseInt(sc.nextLine());

					System.out.print("입사일 입력: ");
					String hiredate = sc.nextLine();

					System.out.print("급여 입력: ");
					double sal = Double.parseDouble(sc.nextLine());

					System.out.print("커미션 입력: ");
					double comm = Double.parseDouble(sc.nextLine());

					System.out.print("부서번호 입력(2자리): ");
					int deptno = Integer.parseInt(sc.nextLine());

					sql = "insert into emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (" + empno
							+ ", '" + ename + "', '" + job + "', " + mgr + ", '" + hiredate + "', " + sal + ", " + comm
							+ ", " + deptno + ")";

					stmt.executeUpdate(sql);
					System.out.println("정보입력이 완료되었습니다.");

					break;

				case 3:

					System.out.println("3. 데이터삭제를 선택하셨습니다.");

					System.out.print("삭제할 회원 이름 입력 : ");
					String deleteEname = sc.nextLine();

					sql = "delete from emp where ename = '" + deleteEname + "'";
					int dater = stmt.executeUpdate(sql);

					if (dater > 0) {
						System.out.println("데이터삭제에 성공했습니다.");
					} else {
						System.out.println("데이터삭제에 실패했습니다.");
					}

					break;

				case 4:

					System.out.println("4. 데이터검색을 선택하셨습니다.");

					System.out.print("검색할 회원 이름 입력 : ");
					String searchEname = sc.nextLine();

					sql = "select * from emp where ename = '" + searchEname + "'";
					ResultSet searchRs = stmt.executeQuery(sql);

					if (searchRs.next()) {
						do {
							Emp emp = new Emp(searchRs.getInt("empno"), searchRs.getString("ename"),
									searchRs.getString("job"), searchRs.getInt("mgr"), searchRs.getString("hiredate"),
									searchRs.getDouble("sal"), searchRs.getDouble("comm"), searchRs.getInt("deptno"));

							System.out.println(emp.empno + ", " + emp.ename + ", " + emp.job + ", " + emp.mgr + ", "
									+ emp.hiredate + ", " + emp.sal + ", " + emp.comm + ", " + emp.deptno);
						} while (searchRs.next());
					} else {
						System.out.println("검색 실패! \n잘못된 정보를 입력하셨습니다.");
					}
					break;

				case 5:
					System.out.println("5. 데이터수정을 선택하셨습니다.");
					System.out.print("수정할 회원의 사원번호 입력>");

					int updateEmpno = Integer.parseInt(sc.nextLine());

					sql = "select * from emp where empno = " + updateEmpno;
					ResultSet updateRs = stmt.executeQuery(sql);

					if (updateRs.next()) {
						Emp emp = new Emp(updateRs.getInt("empno"), updateRs.getString("ename"),
								updateRs.getString("job"), updateRs.getInt("mgr"), updateRs.getString("hiredate"),
								updateRs.getDouble("sal"), updateRs.getDouble("comm"), updateRs.getInt("deptno"));

						System.out.print("새로운 이름 입력 : ");
						String newEname = sc.nextLine();

						System.out.print("새로운 직무 입력 : ");
						String newJob = sc.nextLine();

						System.out.print("새로운 관리자 번호 입력 : ");
						int newMgr = Integer.parseInt(sc.nextLine());

						System.out.print("새로운 입사일 입력 : ");
						String newHiredate = sc.nextLine();

						System.out.print("새로운 급여 입력 : ");
						int newSal = Integer.parseInt(sc.nextLine());

						System.out.print("새로운 커미션 입력 : ");
						int newComm = Integer.parseInt(sc.nextLine());

						System.out.print("새로운 부서번호 입력(2자리) : ");
						int newDeptno = Integer.parseInt(sc.nextLine());

						sql = "update emp set ename ='" + newEname + "',job= '" + newJob + "', mgr = " + newMgr
								+ ", hiredate = '" + newHiredate + "', sal = " + newSal + ", comm = " + newComm
								+ ", deptno = " + newDeptno + " where empno = " + updateEmpno;

						int updateResult = stmt.executeUpdate(sql);

						if (updateResult > 0) {
							System.out.println("데이터 수정에 성공했습니다.");
						} else {
							System.out.println("데이터 수정에 실패했습니다. 올바른 값을 입력하세요.");
						}
					} else {
						System.out.println("사원번호에 해당하는 데이터가 없습니다.");
					}
					break;

				case 6:
					System.out.println("시스템을 종료합니다.");
					return;

				default:
					System.out.println("올바른 번호를 입력하시오");
					break;
				}

			}

		}
	}

}
