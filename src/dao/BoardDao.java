package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.ConnectionProvider;
import model.Board;

public class BoardDao {

	// �޼��� ����(����)
	public int insertMessage(Board message) {
		int result = 0;
		// ������ : create sequence message_seq start with 1 increment by 1 maxvalue
		// 9999999 cycle;
		String sql = "insert into board2 values(project_seq.nextval, ?, ?, ?, sysdate)";

		// ���� ��ü
		Connection conn = null;
		// sql ���� ��ü
		PreparedStatement pstmt = null;

		try {
			// ���� ��������
			conn = ConnectionProvider.getConnection();
			// ���ῡ�� sql ���� ��ü�� pstmt ��������
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, message.getName());
			pstmt.setString(2, message.getTitle());
			pstmt.setString(3, message.getContent());

			// System.out.println(message.getContent());
			result = pstmt.executeUpdate();
			// executeUpdate() : ��� ó���� �ʿ���� sql�� ����
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	// �޼��� ����
	public int deleteMessage(int id) {
		int result = 0;
		String sql = "delete from board2 where id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate(); // �� sql ���๮�� ���� ��������Ѵ�.

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	// �޼��� �ϳ� ��������
	public Board selectOne(String name) {
		Board result = null;
		String sql = "select * from board2 where name = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		// ó���� ����� ���� ���
		ResultSet rs = null;

		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			// ��� ó���� �ʿ��� ���� executeQuery()

			// id �� primary key�ϱ� ����� ������ 1��
			if (rs.next()) {
				result = new Board(); // �̰� �츮�� ��ȯ�� Message ��ü
				result.setId(rs.getInt("id"));
				result.setName(rs.getString("name"));
				result.setTitle(rs.getString("title"));
				result.setContent(rs.getString("content"));
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	public Board selectOne(int id) {
		Board result = null;
		String sql = "select * from board2 where id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		// ó���� ����� ���� ���
		ResultSet rs = null;

		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			// ��� ó���� �ʿ��� ���� executeQuery()

			// id �� primary key�ϱ� ����� ������ 1��
			if (rs.next()) {
				result = new Board(); // �̰� �츮�� ��ȯ�� Message ��ü
				result.setId(rs.getInt("id"));
				result.setName(rs.getString("name"));
				result.setTitle(rs.getString("title"));
				result.setContent(rs.getString("content"));
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	// �޼��� ���� ��������
	public List<Board> selectAll() {
		List<Board> result = new ArrayList<Board>();
		String sql = "select * from Board2 order by id";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// ����� ���� ���ϼ��� �ִ�.
			while (rs.next()) {
				Board message = new Board();
				message.setId(rs.getInt("id"));
				message.setName(rs.getString("name"));
				message.setTitle(rs.getString("title"));
				message.setContent(rs.getString("content"));
				// �츮�� ��ȯ�� list�� �־���� �˴ϴ�.
				result.add(message);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	// �������� ����ؼ� ������ ��������
	public List<Board> selectList(int firstRow, int endRow) {
		// firstRow ==> ��ü ���� �������� �츮�� �������� ������ ���� ��ȣ
		// endRow ==> �������� ������ ��
		// firstRow : 1, endRow :10 ==> 1����� 10���� �����´�.
		// �����͸� �������� ����? �ֽŲ����� ���������� ���ı����� ���� �ؾ��ұ�?
		// id���� ==> ���߿� �����ɼ��� ū��
		// ������������ �ϸ� ū������ �����´�. ===> desc�� ����ؼ� �����´�.
		String sql = "select * \r\n" + "from (select rownum as rnum, m.id, m.title, m.name, m.content\r\n"
				+ "        from (select id, title, name, content \r\n"
				+ "                from board2 order by id desc) m\r\n" + "                )\r\n"
				+ "where rnum between ? and ?";
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		List<Board> result = new ArrayList<>();

		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, firstRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Board message = new Board();
				message.setId(rs.getInt("id"));
				message.setName(rs.getString("name"));
				message.setTitle(rs.getString("title"));
				message.setContent(rs.getString("content"));
				// ����Ʈ�� �߰� �� ���ּ���.
				result.add(message);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	public List<Board> searchSelectList(String name, int firstRow, int endRow) {

		String sql = "select * \r\n" + "from (select rownum as rnum, m.id, m.title, m.name, m.content\r\n"
				+ "        from (select id, title, name, content \r\n"
				+ "                from board2 where instr(title, ?) > 0 order by id desc) m\r\n"
				+ "                )\r\n" + "where rnum between ? and ?";
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		List<Board> result = new ArrayList<>();

		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, firstRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Board message = new Board();
				message.setId(rs.getInt("id"));
				message.setName(rs.getString("name"));
				message.setTitle(rs.getString("title"));
				message.setContent(rs.getString("content"));
				// ����Ʈ�� �߰� �� ���ּ���.
				result.add(message);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	// �� �޼����� ������ ���ϴ� �޼ҵ�
	public int selectCount() {
		int result = 0;
		String sql = "select count(*) from board2";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
				// ����� ù��° �÷� �� ��������
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	public int searchSelectCount(String name) {
		int result = 0;
		String sql = "select count(*) from board2 where instr(title, ?) > 0";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
				// ����� ù��° �÷� �� ��������
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}
}
