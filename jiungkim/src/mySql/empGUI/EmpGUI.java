package mySql.empGUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EmpGUI extends JFrame {

	JTextField tf1 = new JTextField(5);
	JTextField tf2 = new JTextField(5);
	JTextField tf3 = new JTextField(5);
	JTextField tf4 = new JTextField(5);
	JTextField tf5 = new JTextField(5);
	JTextField tf6 = new JTextField(5);
	JTextField tf7 = new JTextField(5);
	JTextField tf8 = new JTextField(5);

	JButton bt1 = new JButton("전체 내용");
	JButton bt2 = new JButton("입력");
	JButton bt3 = new JButton("이름 검색");
	JButton bt4 = new JButton("수정");
	JButton bt5 = new JButton("삭제");
	JButton bt6 = new JButton("초기화");
	JTextArea ta = new JTextArea(20, 60);

	Connection conn;
	Statement stmt;

	public EmpGUI() {
		String url = "jdbc:mysql://localhost:3306/firm";
		String id = "root";
		String pass = "mysql";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pass);
			stmt = conn.createStatement();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		JLabel lb1 = new JLabel("사원번호:");
		JLabel lb2 = new JLabel("이름:");
		JLabel lb3 = new JLabel("직무:");
		JLabel lb4 = new JLabel("관리자번호:");
		JLabel lb5 = new JLabel("입사일:");
		JLabel lb6 = new JLabel("급여:");
		JLabel lb7 = new JLabel("커미션:");
		JLabel lb8 = new JLabel("부서번호:");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container con = this.getContentPane();
		con.setLayout(new BorderLayout());

		////////////////////////////
		JPanel jp1 = new JPanel(new FlowLayout());
		jp1.add(bt1);
		jp1.add(bt2);
		jp1.add(bt3);
		jp1.add(bt4);
		jp1.add(bt5);
		jp1.add(bt6);

		JScrollPane scroll = new JScrollPane(ta);

		/////////////////////////////////
		JPanel jp2 = new JPanel(new FlowLayout());
		con.add(jp2, BorderLayout.CENTER);
		jp2.add(scroll);

		con.add(jp1, BorderLayout.SOUTH);

		////////////////////////////////
		this.setLocation(600, 400);
		this.setSize(900, 500);
		this.setVisible(true);
		this.setTitle("Emp 관리");

		///////////////////////////////////////

		JPanel jp3 = new JPanel(new FlowLayout());
		con.add(jp3, BorderLayout.NORTH);
		jp3.add(lb1);
		jp3.add(tf1);
		jp3.add(lb2);
		jp3.add(tf2);
		jp3.add(lb3);
		jp3.add(tf3);
		jp3.add(lb4);
		jp3.add(tf4);
		jp3.add(lb5);
		jp3.add(tf5);
		jp3.add(lb6);
		jp3.add(tf6);
		jp3.add(lb7);
		jp3.add(tf7);
		jp3.add(lb8);
		jp3.add(tf8);

		/////////////////////////////////////

		bt1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				select();
			}
		});

		bt2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insert();
			}
		});

		bt3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});

		bt4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});

		bt5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		
		bt6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			reset();
			}
		});
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// 선택
	public void select() {
		String sql = "select * from emp";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			ta.setText("");
			while (rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				int mgr = rs.getInt("mgr");
				String hiredate = rs.getString("hiredate");
				double sal = rs.getDouble("sal");
				double comm = rs.getDouble("comm");
				int deptno = rs.getInt("deptno");

				String str = String.format("%d, | %s, | %s, | %d, | %s, | %s, | %s, | %s,\n", empno, ename, job, mgr,
						hiredate, sal, comm, deptno);

				ta.append(str);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 넣기
	public void insert() {
		String sql = String.format("insert into emp values(%s,'%s','%s',%s,'%s',%s,%s,%s)", tf1.getText(),
				tf2.getText(), tf3.getText(), tf4.getText(), tf5.getText(), tf6.getText(), tf7.getText(),
				tf8.getText());

		try {
//			System.out.println(sql);
			int res = stmt.executeUpdate(sql);
			ta.setText("데이터입력 성공");
		} catch (SQLException e) {
//			e.printStackTrace();
			ta.append("오류 : 다시입력하십시오");
		}
	}

	// 이름 검색
	public void search() {
		String sql = String.format("select * from emp where ename = '%s'", tf2.getText());

		try {
			ResultSet rs = stmt.executeQuery(sql);
			ta.setText("");
			if (rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				int mgr = rs.getInt("mgr");
				String hiredate = rs.getString("hiredate");
				double sal = rs.getDouble("sal");
				double comm = rs.getDouble("comm");
				int deptno = rs.getInt("deptno");

				String str = String.format("%d, | %s, | %s, | %d, | %s, | %s, | %s, | %s,\n", empno, ename, job, mgr,
						hiredate, sal, comm, deptno);

				ta.append(str);
				tf1.setText(empno + "");
				tf3.setText(job + "");
				tf4.setText(mgr + "");
				tf5.setText(hiredate + "");
				tf6.setText(sal + "");
				tf7.setText(comm + "");
				tf8.setText(deptno + "");
			} else {
				tf1.setText("");
				tf3.setText("");
				tf4.setText("");
				tf5.setText("");
				tf6.setText("");
				tf7.setText("");
				tf8.setText("");
				ta.setText("해당 자료가 존재하지 않습니다.");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 수정

	public void update() {
		String empno = tf1.getText();

		String sql = String.format(String.format(
				"update emp set ename='%s', job='%s', mgr=%s, hiredate='%s', sal=%s, comm=%s, deptno=%s where empno=%s",
				tf2.getText(), tf3.getText(), tf4.getText(), tf5.getText(), tf6.getText(), tf7.getText(), tf8.getText(),
				empno));

		try {
			int res = stmt.executeUpdate(sql);
			if (res > 0) {
				ta.setText("데이터 수정에 성공하였습니다.");
			} else {
				ta.setText("변경사항이 존재하지않습니다."+"\n\n"+"주의 : 사원번호는 변경이 불가능합니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ta.setText("오류 : 다시 입력하십시오");

		}

	}

	// 삭제

	public void delete() {
		String sql = String.format("delete from emp where empno=%s", tf1.getText());

		try {
			int res = stmt.executeUpdate(sql);
			if (res > 0) {
				ta.setText("데이터 삭제를 성공하였습니다.");
			} else {
				ta.setText("변경사항이 존재하지않습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ta.setText("오류 : 다시 입력하십시오");
		}
	}
	
	//초기화
	
	public void reset() {
		tf1.setText("");
		tf2.setText("");
		tf3.setText("");
		tf4.setText("");
		tf5.setText("");
		tf6.setText("");
		tf7.setText("");
		tf8.setText("");
		ta.setText("검색정보 초기화");
		
	}

	////// 메인메소드
	public static void main(String[] args) {
		new EmpGUI();
	}
}