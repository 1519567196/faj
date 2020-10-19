/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.resjz.common.xss;
import org.apache.shiro.util.AntPathMatcher;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * XSS过滤
 */
public class XssFilter implements Filter {

	public static  List<String> exclusion;

	private AntPathMatcher matcher;
	@Override
	public void init(FilterConfig config) throws ServletException {
		String base =  config.getServletContext().getContextPath();
		matcher = new AntPathMatcher();
		exclusion = Arrays.asList(base +"/zmadmin/zmproduct/**");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		if(exclusion.stream().anyMatch(item->matcher.matches(item,req.getRequestURI()))){
			chain.doFilter(request, response);
		}else{
			XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
					(HttpServletRequest) request);
			chain.doFilter(xssRequest, response);
		}
	}

	@Override
	public void destroy() {
	}

}