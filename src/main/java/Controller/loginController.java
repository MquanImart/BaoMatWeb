package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.mail.*;

import DAO.*;

import Model.*;
import DAO.chucvuDAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@WebServlet(name = "login", urlPatterns = { "/login", "/forgot", "/change","/sendmail","/login_post","/forgot_post","/change_post"})
public class loginController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(loginController.class);
    private static final long serialVersionUID = 1L;
    private loginDAO loginDao;
    private changeDAO changeDao = new changeDAO();
    private forgotDAO forgotDao = new forgotDAO();
    private static final int MIN_PASSWORD_LENGTH = 8;
    public void init() {
        loginDao = new loginDAO();
    }

    // Chỉnh sửa
    private static final int TIME_STEP_SECONDS = 120;
    private static final int TOTP_LENGTH = 6;
    // Chỉnh sửa

    // Tạo một số ngẫu nhiên gồm 6 chữ số
    Random rand = new Random();
    private String maOtp = "";
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action){
            case  "/login_post":
                authenticate(request, response);
                break;
            case "/forgot_post":
                try {
                    NewPass(request, response);
                } catch (NoSuchAlgorithmException | InvalidKeyException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/change_post":
                ChangePass(request, response);
                break;
        }
        response.setHeader("X-Content-Type-Options", "nosniff");
        doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try{
            switch (action){
                case  "/login":
                    FromLogin(request, response);
                    break;
                case "/forgot":
                    FromForgot(request, response);
                    break;
                case "/change":
                    FromChange(request, response);
                    break;
                case "/sendmail":
                    Forgotpass(request, response);
                    break;
            }
        }catch (SQLException | NoSuchAlgorithmException | InvalidKeyException ex)
        {throw new ServletException(ex);}
    }
    private void authenticate(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            HttpSession session = request.getSession();
            String csrfToken = request.getParameter("csrfToken");
            String sessionToken = (String) session.getAttribute("csrfToken");
            if (csrfToken == null || !csrfToken.equals(sessionToken)) {
                return;
            } else {
                taikhoan tk = loginDao.findByUsername(username); // Tìm tài khoản dựa trên tên người dùng
                if (tk != null) {
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                    if(encoder.matches(password, tk.getPass())) { // So sánh mật khẩu đã nhập với mật khẩu đã mã hóa từ CSDL
                        // Mật khẩu khớp, tiến hành đăng nhập
                        session.setAttribute("user", tk);
                        // Kiểm tra tình trạng tài khoản
                        if (loginDAO.layTinhTrang(tk.getMatk())) { // Kiểm tra tài khoản có bị khóa hay không
                            // Lấy thông tin cấp bậc
                            int capbac = chucvuDAO.CapBacQuyenHan(tk.getMatk());
                            session.setAttribute("capbac", capbac);

                            // Lấy thông tin nhân viên
                            nhanvien thongtinnv = qlnhanvienDAO.LayThongTinNhanVien(tk.getMatk());
                            session.setAttribute("thongtinnv", thongtinnv);

                            // Lấy tên chức vụ
                            String tenchucvu = chucvuDAO.TenCapBac(tk.getMatk());
                            session.setAttribute("tencapbac_header", tenchucvu);

                            // Lấy thông tin phòng ban
                            phongban ttphongban = phongbanDAO.selectPhongBan(thongtinnv.getMapb());
                            session.setAttribute("phongban_header", ttphongban);

                            // Lấy thông tin chi nhánh
                            chinhanh inf_chinhanh = chinhanhDAO.selectChiNhanh(thongtinnv.getMacn());
                            session.setAttribute("chinhanh_header", inf_chinhanh);

                            // Lấy thông tin cá nhân
                            thongtincanhan tennv = thongtincanhanDAO.layThongTinCaNhan(tk.getMatk());
                            session.setAttribute("tennhanvien_menu", tennv);

                            RequestDispatcher dispatcher = request.getRequestDispatcher("/trangchu");
                            dispatcher.forward(request, response);
                        } else {
                            // Tài khoản bị khóa
                            request.setAttribute("error", "Tài khoản đã bị khóa");
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
                            dispatcher.forward(request, response);
                        }
                    } else {
                        // Mật khẩu không khớp
                        request.setAttribute("error", "Mật khẩu không chính xác");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
                        dispatcher.forward(request, response);
                    }
                } else {
                    // Tài khoản không tồn tại
                    request.setAttribute("error", "Tài khoản không tồn tại");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
                    dispatcher.forward(request, response);
                }
            }
            throw new Exception("Một lỗi đã xảy ra trong ExampleServlet.");
        } catch (Exception ex) {
            // Log lỗi vào hệ thống ghi log (khuyến khích)
            // logError(ex);

            // Đặt thông báo lỗi tùy chỉnh vào thuộc tính "errorMessage"
            request.setAttribute("errorMessage", "Xin lỗi, đã xảy ra một lỗi không mong muốn.");

            // Forward đến trang hiển thị lỗi
            request.getRequestDispatcher("/header_menu/error.jsp").forward(request, response);
        }
    }

    // Chỉnh sửa
    private String generateSecretKey() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            sb.append((char) (rand.nextInt(26) + 'a'));
        }
        return sb.toString();
    }
    private String generateTotp(String secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] keyBytes = secretKey.getBytes();
        byte[] data = new byte[8];
        long value = System.currentTimeMillis() / 1000 / TIME_STEP_SECONDS;
        for (int i = 7; value > 0; i--) {
            data[i] = (byte) (value & 0xff);
            value >>= 8;
        }

        SecretKeySpec key = new SecretKeySpec(keyBytes, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(key);

        byte[] hash = mac.doFinal(data);

        int offset = hash[hash.length - 1] & 0xF;
        int binary = ((hash[offset] & 0x7f) << 24)
                | ((hash[offset + 1] & 0xff) << 16)
                | ((hash[offset + 2] & 0xff) << 8)
                | (hash[offset + 3] & 0xff);
        int otp = binary % (int) Math.pow(10, TOTP_LENGTH);

        return String.format("%0" + TOTP_LENGTH + "d", otp);
    }
    // Chỉnh sửa

    private void Forgotpass(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, NoSuchAlgorithmException, InvalidKeyException {

        String host;
        String port;
        String user;
        String pass;

        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");

        String csrfToken = request.getParameter("csrfToken");
        HttpSession session = request.getSession();
        String sessionToken = (String) session.getAttribute("csrfToken");

        // Chỉnh sửa
        String secretKey = generateSecretKey();
        session.setAttribute("secretKey", secretKey);

        if (csrfToken == null || !csrfToken.equals(sessionToken)) {
            request.setAttribute("error", "Token không hợp lệ!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
            dispatcher.forward(request, response);
        } else {
            maOtp = generateTotp(secretKey);
            // Chỉnh sửa

            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String subject = "Mã OTP xác nhận của bạn là:";

            taikhoan usernameModel = new taikhoan();
            usernameModel.setUsername(username);
            thongtincanhan emailModel = new thongtincanhan();
            emailModel.setEmail(email);

            try {
                boolean kt = forgotDao.kiemtratk(usernameModel,emailModel);
                if(kt){
                    forgotDao.sendEmail(host, port, user, pass, email, subject, maOtp);
                    request.setAttribute("inputUsername", username);
                    request.setAttribute("inputEmail", email);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/login/forgot.jsp");
                    dispatcher.forward(request, response);
                }
                else{
                    request.setAttribute("error", "Tài khoản hoặc email không đúng!");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/login/forgot.jsp");
                    dispatcher.forward(request, response);
                }

            } catch (ClassNotFoundException | MessagingException e) {
                throw new RuntimeException(e);
            }
        }

    }
    private boolean verifyTotp(String secretKey, String otp) throws NoSuchAlgorithmException, InvalidKeyException {
        String generatedTotp = generateTotp(secretKey);
        return otp.equals(generatedTotp);
    }
    private void NewPass(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, NoSuchAlgorithmException, InvalidKeyException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String otp = request.getParameter("otp");
        String newpassword = request.getParameter("newpassword");

        if (username == null || username.isEmpty() ||
                email == null || email.isEmpty() ||
                newpassword == null || newpassword.length() < MIN_PASSWORD_LENGTH || !isValidPassword(newpassword)) {
            request.setAttribute("error", "Vui lòng điền đầy đủ thông tin và mật khẩu phải đủ độ dài và định dạng!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login/forgot.jsp");
            dispatcher.forward(request, response);
            return;
        }
        HttpSession session = request.getSession();
        String secretKey = (String) session.getAttribute("secretKey");
        String csrfToken = request.getParameter("csrfToken");
        String sessionToken = (String) session.getAttribute("csrfToken");

        if (csrfToken == null || !csrfToken.equals(sessionToken)) {
            request.setAttribute("error", "Token không hợp lệ!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
            dispatcher.forward(request, response);
        } else {
            if (!verifyTotp(secretKey, otp)) {
                request.setAttribute("error", "Mã OTP không trùng khớp!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
                dispatcher.forward(request, response);
                return;
            }
            taikhoan usernameModel = new taikhoan();
            usernameModel.setUsername(username);
            thongtincanhan emailModel = new thongtincanhan();
            emailModel.setEmail(email);

            try {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String hashedPassword = encoder.encode(newpassword);
                boolean ischanged = forgotDao.changePass(usernameModel, emailModel, hashedPassword);
                if(ischanged){
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
                    dispatcher.forward(request, response);
                }
                else{
                    request.setAttribute("error", "Không thể thay đổi mật khẩu!");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/login/forgot.jsp");
                    dispatcher.forward(request, response);
                }

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void ChangePass(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        String username = request.getParameter("username");
        String oldpassword = request.getParameter("oldpassword");
        String newpassword = request.getParameter("newpassword");
        String confirmnewpass = request.getParameter("confirmnewpass");

        if (username == null || username.isEmpty() ||
                oldpassword == null || oldpassword.isEmpty() ||
                newpassword == null || newpassword.isEmpty() ||
                confirmnewpass == null || confirmnewpass.isEmpty() ||
                newpassword.length() < MIN_PASSWORD_LENGTH || !isValidPassword(newpassword)) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin và mật khẩu phải đủ độ dài và định dạng!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login/change.jsp");
            dispatcher.forward(request, response);
            return;
        }

        String csrfToken = request.getParameter("csrfToken");
        HttpSession session = request.getSession();
        String sessionToken = (String) session.getAttribute("csrfToken");

        if (csrfToken == null || !csrfToken.equals(sessionToken)) {
            request.setAttribute("error", "Token không hợp lệ!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
            dispatcher.forward(request, response);
        } else {
            if (!newpassword.equals(confirmnewpass)) {
                request.setAttribute("error", "Mật khẩu mới không trùng khớp!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login/change.jsp");
                dispatcher.forward(request, response);
                return;
            }
            try {
                taikhoan tk = loginDao.findByUsername(username);
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                if (tk != null) {
                    if(encoder.matches(oldpassword, tk.getPass())) { // So sánh mật khẩu đã nhập với mật khẩu đã mã hóa từ CSDL
                        String hashedPassword = encoder.encode(newpassword);
                        boolean isChanged = changeDao.changePassword(tk, hashedPassword);
                        if (isChanged) {
                            request.setAttribute("message", "Thay đổi mật khẩu thành công");
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
                            dispatcher.forward(request, response);
                        } else {
                            request.setAttribute("error", "Không thể thay đổi mật khẩu!");
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/login/change.jsp");
                            dispatcher.forward(request, response);
                        }
                    } else {
                        // Mật khẩu không khớp
                        request.setAttribute("error", "Mật khẩu không chính xác");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/login/change.jsp");
                        dispatcher.forward(request, response);
                    }
                } else {
                    request.setAttribute("error", "Tài khoản không tồn tại!");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/login/change.jsp");
                    dispatcher.forward(request, response);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
    private boolean isValidPassword(String password) {
        // Kiểm tra độ dài của mật khẩu
        if (password.length() < MIN_PASSWORD_LENGTH) {
            return false;
        }

        // Kiểm tra xem mật khẩu có chứa ít nhất một chữ cái hoa
        boolean hasUpperCase = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
                break;
            }
        }
        if (!hasUpperCase) {
            return false;
        }

        // Kiểm tra xem mật khẩu có chứa ít nhất một chữ cái thường
        boolean hasLowerCase = false;
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                hasLowerCase = true;
                break;
            }
        }
        if (!hasLowerCase) {
            return false;
        }

        // Kiểm tra xem mật khẩu có chứa ít nhất một số
        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
                break;
            }
        }
        if (!hasDigit) {
            return false;
        }

        boolean hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) { // Kiểm tra xem ký tự không phải là chữ hoặc số
                hasSpecial = true;
                break;
            }
        }
        if (!hasSpecial) {
            return false;
        }
        for (char c : password.toCharArray()) {
            if (c == '"' || c == '-') {
                return false;
            }
        }

        return true;
    }
    private void FromLogin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(true);
        String csrfToken = UUID.randomUUID().toString();
        session.setAttribute("csrfToken", csrfToken);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
        dispatcher.forward(request, response);
    }
    private void FromForgot(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(true);
        String csrfToken = UUID.randomUUID().toString();
        session.setAttribute("csrfToken", csrfToken);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login/forgot.jsp");
        dispatcher.forward(request, response);
    }
    private void FromChange(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(true);
        String csrfToken = UUID.randomUUID().toString();
        session.setAttribute("csrfToken", csrfToken);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login/change.jsp");
        dispatcher.forward(request, response);
    }
}