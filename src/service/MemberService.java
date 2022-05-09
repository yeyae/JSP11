package service;

import java.util.List;

import dao.MemberDao;
import model.Member;

public class MemberService {

	// Dao 객체를 멤버로 가져오면서 사용
	MemberDao memberDao;

	public MemberService() {
		// 생성자 => 메소드 이름이 클래스 이름과 같음
		// 객체가 생성될 때 실행되는 코드
		// new MemberService() ==> 생성자 코드 실행
		memberDao = new MemberDao();
	}

	// 로그인 성공 시 true, 실패시 false
	public boolean login(String id, String pw) {
		// 1.데이터 베이스에서 입력받은 아이디와 동일한 회원정보를 조회
		Member member = memberDao.selectOne(id);

		if (member != null) {
			// member가 null이 아니면 해당 아이디를 가진 회원정보는 있다.
			if (member.getPw().equals(pw)) {
				// 로그인 성공
				return true;
			} else {
				// 비밀번호가 틀림
				// 로그인 실패
				return false;
			}
		} else {
			// member 가 null => 아이디가 존재하지 않음
			// 로그인 실패
			return false;

		}

	}

//회원 가입 성공 ==> true 리턴
//회원 가입 실패 ==> false 리턴
	public boolean join(Member member) {
		int insertResult = memberDao.insertMember(member);
		// 회원가입 성공하면 insertResult에는 1이 들어가 있다.
		if (insertResult == 1) {
			return true;
		}
		return false;
	}

//회원 정보수정 성공 => true 리턴
	// 회원 정보수정 실패 => false 리턴
	public boolean modify(Member member) {
		int updateResult = memberDao.updateMember(member);

		boolean result = false;
		if (updateResult > 0) {
			// 회원정보 수정 성공
			// updateMember() 메소드 실행 결과는 영향을 받은 행의 개수
			// 수정된 회원 정보의 개수가 0개보다 크다
			result = true;

		}
		return result;
	}

	public Member getMember(String id) {
		return memberDao.selectOne(id);
	}

	public List<Member> getAllMembers() {
		return memberDao.selectAll();

	}
}