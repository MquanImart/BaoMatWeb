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
        httpResp.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
        //httpResp.setHeader("Content-Security-Policy", "default-src 'none'; script-src 'self' https://cdnjs.cloudflare.com  https://cdn.jsdelivr.net https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.js; style-src 'self' https://cdn.jsdelivr.net https://stackpath.bootstrapcdn.com https://fonts.googleapis.com https://cdnjs.cloudflare.com ; font-src 'self' https://cdn.jsdelivr.net https://fonts.gstatic.com https://cdnjs.cloudflare.com ; connect-src 'self'; img-src 'self' https://www.evn.com.vn/userfile/VH/User/huyent_tcdl/images/2021/6/hrmscuatapdoan24621(1).jpeg; frame-ancestors 'none'; form-action 'self';");
        httpResp.setHeader("X-Content-Type-Options", "nosniff");
        httpResp.setHeader("X-Frame-Options", "SAMEORIGIN");
        httpResp.setHeader("Access-Control-Allow-Origin", "https://fonts.googleapis.com https://fonts.gstatic.com https://stackpath.bootstrapcdn.com https://use.fontawesome.com https://cdnjs.cloudflare.com https://localhost:8443");
        chain.doFilter(request, response);
    }


    @Override
    public void destroy() {
        // Dọn dẹp tài nguyên, nếu cần
    }
}
