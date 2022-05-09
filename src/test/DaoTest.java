package test;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import dao.BoardDao;
import model.Board;

public class DaoTest {
	// 테스트 수행
	BoardDao dao;

	@Test
	public void daoTest() throws ClassNotFoundException, SQLException {
		dao = new BoardDao();

		for (int i = 0; i < 120; i++) {
			Board board = new Board();
			board.setName("손오공");
			board.setTitle("테스트 글" + i);
			board.setContent("테스트 글입니당!!");
			int result = dao.insertMessage(board);
		}
//		
//		member.setName(name);
		// int result = dao.updateMember(member);
		// assertEquals(2, result);
		// 우리가 기대한 값: 1
		// 실제값 : result
	}

}
