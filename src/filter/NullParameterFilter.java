package filter;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class NullParameterFilter implements Filter {

	String[] parameterName;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// 필터가 수행할 기능
		System.out.println("필터 ON!");

		NullParameterRequestWrapper requestWrapper = new NullParameterRequestWrapper((HttpServletRequest) request);

		requestWrapper.checkNull(parameterName);

		chain.doFilter(requestWrapper, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String names = filterConfig.getInitParameter("parameterName");

		System.out.println(names);

		StringTokenizer st = new StringTokenizer(names, ",");

		parameterName = new String[st.countTokens()];

		for (int i = 0; i < parameterName.length; i++)
			parameterName[i] = st.nextToken();
	}

}
