package Controller;

import DAO.ExcelReader;
import Model.DuLieuNhanVien;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ReadExcel", urlPatterns = { "/readExcelNhanVien"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class ReadExcelServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ReadExcelServlet() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ExcelReader excelReader = new ExcelReader();
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        System.out.println(fileName);
        long fileSize = filePart.getSize();
        String fileContentType = filePart.getContentType();
        filePart.write("C:/Users/Acer/Desktop/File_Web/" + fileName);
        List<DuLieuNhanVien> list = excelReader.readExcel_NhanVien("C:/Users/Acer/Desktop/File_Web/" + fileName);
        HttpSession session = request.getSession(false);
        session.setAttribute("list_dulieunhanvien", list);
        request.getRequestDispatcher("/qlnhanvien/themnhanvienexcel.jsp").forward(request, response);
    }
}