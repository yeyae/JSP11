package filter;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class NullParameterRequestWrapper extends HttpServletRequestWrapper {

	private Map<String, String[]> parameterMap;

	public NullParameterRequestWrapper(HttpServletRequest request) {
		super(request);

		parameterMap = new HashMap<String, String[]>(request.getParameterMap());
	}

	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		String[] values = getParameterValues(name);
		if (values != null && values.length > 0) {
			return values[0];
		}
		return null;
	}

	@Override
	public Enumeration<String> getParameterNames() {
		// TODO Auto-generated method stub
		return Collections.enumeration(parameterMap.keySet());
	}

	@Override
	public String[] getParameterValues(String name) {
		// 우리가 가지고 있는 map에서 key를 통해 value 가져다 줌
		return parameterMap.get(name);
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		// TODO Auto-generated method stub
		return super.getParameterMap();
	}

	public void checkNull(String[] pn) {
		// 우리가 지정한 파라미터가 null 일 경우, 파라미터 기본값 추가
		for (int i = 0; i < pn.length; i++) {
			String param = pn[i];
			if (parameterMap.containsKey(param)) {
				String[] values = new String[] { "값 없음" };
				parameterMap.put(param, values);
			}
		}
	}
}
