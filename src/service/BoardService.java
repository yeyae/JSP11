package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BoardDao;
import model.Board;

public class BoardService {
	// �������� ���� Dao �� ����ϰ� ���� �ʰ�
	// service�� ���ļ� ����ϵ���
	// �� �߰� ������ �и�
	// dao : �����ͺ��̽��� ������ ������ �ϸ� ��
	// servlet : ������ ���� ó�� ( ����ڿ��� ���� ��û ó�� )
	// service : servlet���� �����ͺ��̽� ���� �ʿ��� ����� �޼ҵ�� ����

	private BoardDao boardDao;

	// �� �������� ǥ���� �޼��� ����
	private static final int NUM_OF_MESSAGE_PER_PAGE = 10;
	// �ѹ��� ǥ���� �׺���̼��� ���� ( ������ ��ư�� ���� )
	private static final int NUM_OF_NAVI_PAGE = 10;

	public BoardService() {
		boardDao = new BoardDao();
	}

	// ��� �޼��� ��� ��������
	public List<Board> getAllMessages() {
		return boardDao.selectAll();
	}

	// �޼��� �ϳ� ��������
	public Board getMessage(int id) {
		return boardDao.selectOne(id);
	}

	public Board getMessage(String name) {
		return boardDao.selectOne(name);
	}

	// �޼��� ����
	public boolean writeMessage(Board message) {
		// �޼��� ���� (����) �� �����ϸ� ? true ����

		// �޼��� ������ �����ϸ� false ����
		int result = boardDao.insertMessage(message);

		if (result == 1) {
			return true;
		} else {
			return false;
		}

		// ������ ���� ���δ� insertMessage() �޼ҵ��� ���� ����
		// 0�̳� �ƴϳ�
		// ���ϰ��� 0�̴�. ==> �����ͺ��̽��� ���Ե�(������ ����) ����� 0
		// 0 �� �ƴϴ� ==> �����ͺ��̽��� ���Ե� ����� 0�� �ƴϴ�. ==> ������ ���.
	}

	// �޼��� �����ϱ�
	public boolean deleteMessage(int id, String name) {
		// �޼��� �Ѱ��� �����ɴϴ�. ���? id��
		Board message = boardDao.selectOne(id);

		// �ش��ϴ� id �� �޼����� ������ �׶� ��й�ȣ�� �������� ��
		// ������ ������ ����� ����
		if (message != null && message.getName().equals(name)) {
			int result = boardDao.deleteMessage(id);

			return true;
		}
		// ��й�ȣ�� �ٸ��ٰų�, �ش��ϴ� id�� �޼����� ���� ==> return false

		return false;
	}

	// �� ������ ���� ���ϱ�
	private int calPageTotalCount(int totalCount) {
		int pageCount = 0;
		// �� �������� ���� �� ��ȯ
		/*
		 * �޼��� �� : 1~10 �������� : 1 �޼��� �� : 11~20 �信���� : 2 100 10 200 20 333
		 * 33.33
		 * 
		 * Math.ceil() õ���Լ� , �ø�
		 */
		// totalCount : �� �޼��� ����
		if (totalCount != 0) {
			pageCount = (int) Math.ceil((double) totalCount / NUM_OF_MESSAGE_PER_PAGE);
		}

		return pageCount;
	}

	public int getStartPage(int pageNum) {
		int startPage = 0;
		// pageNum : ���� ������ ��ȣ
		// ���� ��������ȣ�� 6
		// ���� ������ ��ư�� ������� ������� ��Ÿ���� �ɱ�??
		// 1 ~ 10 ������
		// ���� ������ ��ȣ�� 16 ���̸� 11 ~ 20
		// ���⼭ �츮�� ���ϴ°Ŵ� 11
		startPage = ((pageNum - 1) / NUM_OF_NAVI_PAGE) * NUM_OF_NAVI_PAGE + 1;
		// 20
		// (19/10) * 10 + 1 = 11
		// 21
		// (20/10) * 10 + 1 = 21
		//

		return startPage;
	}

	public int getEndPage(int pageNum) {
		int endPage = 0;
		endPage = (((pageNum - 1) / NUM_OF_NAVI_PAGE) + 1) * NUM_OF_NAVI_PAGE;
		// 6
		// (5/10) = 0 + 1 * 10 = 10
		// 16
		// (15/10) = 1 + 1 * 10 = 20
		// 20
		// (19/10) = 1 + 1 * 10 = 20
		// 21
		// (20/20) = 2 + 1 * 10 = 30

		return endPage;
	}

	public Map<String, Object> getMessageList(int pageNumber) {
		// ���� �������� ǥ�õ� �޼��� ��� ��������

		// ȭ���� ǥ���ϱ� ���� �����͵��� ����
		Map<String, Object> viewData = new HashMap<>();

		int totalCount = boardDao.selectCount(); // �޼��� ��ü ���� ��������
		int firstRow = 0;
		int endRow = 0;
		/*
		 * firstRow endRow 1 ������ : 1 10 2 ������ : 11 20 3 ������ : 21 30
		 * 
		 */
		firstRow = (pageNumber - 1) * NUM_OF_MESSAGE_PER_PAGE + 1;
		endRow = pageNumber * NUM_OF_MESSAGE_PER_PAGE;

		List<Board> messageList = boardDao.selectList(firstRow, endRow);

		// Map�� ȭ�鿡 �ʿ��� �����͵� ���� �ֱ�
		viewData.put("messageList", messageList);

		// �� ������ ����
		viewData.put("pageTotalCount", calPageTotalCount(totalCount));

		// ���� ������
		viewData.put("startPage", getStartPage(pageNumber));

		// ������ ������
		viewData.put("endPage", getEndPage(pageNumber));

		// ���� ������ ��ȣ
		viewData.put("currentPage", pageNumber);

		return viewData;
	}

	public Map<String, Object> getSearchMessageList(String name, int pageNumber) {
		// ���� �������� ǥ�õ� �޼��� ��� ��������

		// ȭ���� ǥ���ϱ� ���� �����͵��� ����
		Map<String, Object> viewData = new HashMap<>();

		int totalCount = boardDao.searchSelectCount(name);
		int firstRow = 0;
		int endRow = 0;
		/*
		 * firstRow endRow 1 ������ : 1 10 2 ������ : 11 20 3 ������ : 21 30
		 * 
		 */
		firstRow = (pageNumber - 1) * NUM_OF_MESSAGE_PER_PAGE + 1;
		endRow = pageNumber * NUM_OF_MESSAGE_PER_PAGE;

		List<Board> messageList = boardDao.searchSelectList(name, firstRow, endRow);

		// Map�� ȭ�鿡 �ʿ��� �����͵� ���� �ֱ�
		viewData.put("messageList", messageList);

		viewData.put("searchName", name);
		// �� ������ ����
		viewData.put("pageTotalCount", calPageTotalCount(totalCount));

		// ���� ������
		viewData.put("startPage", getStartPage(pageNumber));

		// ������ ������
		viewData.put("endPage", getEndPage(pageNumber));

		// ���� ������ ��ȣ
		viewData.put("currentPage", pageNumber);

		return viewData;
	}
}
