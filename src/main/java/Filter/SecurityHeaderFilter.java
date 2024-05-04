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
        httpResp.setHeader("Access-Control-Allow-Origin", "https://fonts.googleapis.com https://fonts.gstatic.com https://stackpath.bootstrapcdn.com https://use.fontawesome.com https://cdnjs.cloudflare.com https://localhost:8443");
        httpResp.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
        httpResp.setHeader("Content-Security-Policy", "default-src *; script-src 'self'; style-src *; font-src 'self'; connect-src 'self'; * data; frame-src 'none'; frame-ancestors 'none'; media-src 'none'; object-src 'none'; manifest-src 'none'; worker-src 'none'; form-action 'self'");
        chain.doFilter(request, response);
    }


    @Override
    public void destroy() {
        // Dọn dẹp tài nguyên, nếu cần
    }
}
