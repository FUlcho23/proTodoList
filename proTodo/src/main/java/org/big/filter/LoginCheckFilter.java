package org.big.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
public class LoginCheckFilter implements Filter{
	//로그인 체크 안하는 페이지들(목록)
	private static final Set<String> EXCLUDE_URLS = Set.of(
		    "/main/login",
		    "/home",
		    "/signup",
		    "/about",
		    "/contact",
		    "/help",
		    "/public",
		    "/css/",
		    "/js/",
		    "/images/",
		    "/login/memberadd"
		);

	@Override
	public void doFilter(ServletRequest requ, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) requ;
        HttpServletResponse res = (HttpServletResponse) resp;
        HttpSession session = req.getSession(false);

        String requestURI = req.getRequestURI(); // 요청 URL

        //로그인 체크 안하는 페이지들
        if (isExcluded(requestURI)|| requestURI.startsWith("/home")) {
            chain.doFilter(requ, resp);
            return;
        }

        // ✅ 세션에 userId가 없으면 로그인 페이지로 리다이렉트
        if (session == null || session.getAttribute("memberId") == null) {
            res.sendRedirect("/main/login");
            System.out.println("세션에 저장된 memberId: " + session.getAttribute("memberId"));
            return;
        }

        chain.doFilter(req, resp); // 필터를 통과하여 컨트롤러로 이동
    }
	
     //로그인 체크가 필요 없는 URL인지 확인하는 메서드
    private boolean isExcluded(String requestURI) {
        return EXCLUDE_URLS.stream().anyMatch(requestURI::startsWith);
    }
	
}
