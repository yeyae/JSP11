package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import service.MemberService;

public class ServiceTest {
	MemberService service;

	@Test
	public void loginTest() {
		service = new MemberService();

		boolean result = service.login("iddd", "1234");
		assertEquals(true, result);
	}
}
