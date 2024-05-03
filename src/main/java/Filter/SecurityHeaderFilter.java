package Filter;


import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;


public class SecurityHeaderFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo filter, nếu cần
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResp = (HttpServletResponse) response;
        // Thêm header X-Content-Type-Options vào mọi HTTP response
        httpResp.setHeader("X-Frame-Options", "SAMEORIGIN");
        httpResp.setHeader("X-Content-Type-Options", "nosniff");
        chain.doFilter(request, response);
    }


    @Override
    public void destroy() {
        // Dọn dẹp tài nguyên, nếu cần
    }
}
