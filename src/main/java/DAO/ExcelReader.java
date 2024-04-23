package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Model.DuLieuNhanVien;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
    public List<DuLieuNhanVien> readExcel_NhanVien(String fileName) {
        List<DuLieuNhanVien> list = new ArrayList<>();
        try {
            Workbook workbook = new XSSFWorkbook(new FileInputStream(new File(fileName)));
            Sheet sheet = workbook.getSheetAt(0);
            System.out.println(sheet.getLastRowNum());
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Cell cell_hoten = row.getCell(0);
                Cell cell_ngaysinh = row.getCell(1);
                Cell cell_gioitinh = row.getCell(2);
                Cell cell_socccd = row.getCell(3);
                Cell cell_ngaycap = row.getCell(4);
                Cell cell_tinh_cap = row.getCell(5);
                Cell cell_huyen_cap = row.getCell(6);
                Cell cell_xa_cap = row.getCell(7);
                Cell cell_sonha_cap = row.getCell(8);
                Cell cell_tinh = row.getCell(9);
                Cell cell_huyen = row.getCell(10);
                Cell cell_xa = row.getCell(11);
                Cell cell_sonha = row.getCell(12);
                Cell cell_sdt = row.getCell(13);
                Cell cell_email = row.getCell(14);
                Cell cell_usernam = row.getCell(15);
                Cell cell_pass = row.getCell(16);
                Cell cell_congviec = row.getCell(17);
                Cell cell_phongban = row.getCell(18);
                Cell cell_chinhanh = row.getCell(19);
                Cell cell_bangcap = row.getCell(20);
                Cell cell_ngaybatdau = row.getCell(21);


                DuLieuNhanVien nv = new DuLieuNhanVien();
                if (cell_hoten.getCellType() == Cell.CELL_TYPE_STRING) {
                    nv.setHoten(cell_hoten.getStringCellValue());
                } else {
                    nv.setHoten(String.valueOf(cell_hoten.getNumericCellValue()));
                }
                Date date = cell_ngaysinh.getDateCellValue();
                Instant instant = date.toInstant();
                LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                nv.setNgaysinh(localDateTime.toLocalDate());

                if (cell_gioitinh.getCellType() == Cell.CELL_TYPE_STRING) {
                    nv.setGioitinh(cell_gioitinh.getStringCellValue());
                } else {
                    nv.setGioitinh(String.valueOf(cell_gioitinh.getStringCellValue()));
                }

                if (cell_socccd.getCellType() == Cell.CELL_TYPE_STRING) {
                    nv.setSocccd(cell_socccd.getStringCellValue());
                } else {
                    nv.setSocccd(String.valueOf(cell_socccd.getNumericCellValue()));
                }

                date = cell_ngaycap.getDateCellValue();
                instant = date.toInstant();
                localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                nv.setNgaycap(localDateTime.toLocalDate());

                if (cell_tinh_cap.getCellType() == Cell.CELL_TYPE_STRING) {
                    nv.setTinh_cap(cell_tinh_cap.getStringCellValue());
                } else {
                    nv.setTinh_cap(String.valueOf(cell_tinh_cap.getNumericCellValue()));
                }

                if (cell_huyen_cap.getCellType() == Cell.CELL_TYPE_STRING) {
                    nv.setHuyen_cap(cell_huyen_cap.getStringCellValue());
                } else {
                    nv.setHuyen_cap(String.valueOf(cell_huyen_cap.getNumericCellValue()));
                }

                if (cell_xa_cap.getCellType() == Cell.CELL_TYPE_STRING) {
                    nv.setXa_cap(cell_xa_cap.getStringCellValue());
                } else {
                    nv.setXa_cap(String.valueOf(cell_xa_cap.getNumericCellValue()));
                }

                if (cell_sonha_cap.getCellType() == Cell.CELL_TYPE_STRING) {
                    nv.setSonha_cap(cell_sonha_cap.getStringCellValue());
                } else {
                    nv.setSonha_cap(String.valueOf(cell_sonha_cap.getNumericCellValue()));
                }

                if (cell_tinh.getCellType() == Cell.CELL_TYPE_STRING) {
                    nv.setTinh(cell_tinh.getStringCellValue());
                } else {
                    nv.setTinh(String.valueOf(cell_tinh.getNumericCellValue()));
                }

                if (cell_huyen.getCellType() == Cell.CELL_TYPE_STRING) {
                    nv.setHuyen(cell_huyen.getStringCellValue());
                } else {
                    nv.setHuyen(String.valueOf(cell_huyen.getNumericCellValue()));
                }

                if (cell_xa.getCellType() == Cell.CELL_TYPE_STRING) {
                    nv.setXa(cell_xa.getStringCellValue());
                } else {
                    nv.setXa(String.valueOf(cell_xa.getNumericCellValue()));
                }

                if (cell_sonha.getCellType() == Cell.CELL_TYPE_STRING) {
                    nv.setSonha(cell_sonha.getStringCellValue());
                } else {
                    nv.setSonha(String.valueOf(cell_sonha.getNumericCellValue()));
                }

                if (cell_sdt.getCellType() == Cell.CELL_TYPE_STRING) {
                    nv.setSdt(cell_sdt.getStringCellValue());
                } else {
                    nv.setSdt(String.valueOf(cell_sdt.getNumericCellValue()));
                }

                if (cell_email.getCellType() == Cell.CELL_TYPE_STRING) {
                    nv.setEmail(cell_email.getStringCellValue());
                } else {
                    nv.setEmail(String.valueOf(cell_email.getNumericCellValue()));
                }

                if (cell_usernam.getCellType() == Cell.CELL_TYPE_STRING) {
                    nv.setUsernam(cell_usernam.getStringCellValue());
                } else {
                    nv.setUsernam(String.valueOf(cell_usernam.getNumericCellValue()));
                }

                if (cell_pass.getCellType() == Cell.CELL_TYPE_STRING) {
                    nv.setPass(cell_pass.getStringCellValue());
                } else {
                    nv.setPass(String.valueOf(cell_pass.getNumericCellValue()));
                }

                if (cell_congviec.getCellType() == Cell.CELL_TYPE_STRING) {
                    nv.setCongviec(cell_congviec.getStringCellValue());
                } else {
                    nv.setCongviec(String.valueOf(cell_congviec.getNumericCellValue()));
                }

                if (cell_phongban.getCellType() == Cell.CELL_TYPE_STRING) {
                    nv.setPhongban(cell_phongban.getStringCellValue());
                } else {
                    nv.setCongviec(String.valueOf(cell_phongban.getNumericCellValue()));
                }

                if (cell_chinhanh.getCellType() == Cell.CELL_TYPE_STRING) {
                    nv.setChinhanh(cell_chinhanh.getStringCellValue());
                } else {
                    nv.setChinhanh(String.valueOf(cell_chinhanh.getNumericCellValue()));
                }

                if (cell_bangcap.getCellType() == Cell.CELL_TYPE_STRING) {
                    nv.setBangcap(cell_bangcap.getStringCellValue());
                } else {
                    nv.setBangcap(String.valueOf(cell_bangcap.getNumericCellValue()));
                }

                date = cell_ngaybatdau.getDateCellValue();
                instant = date.toInstant();
                localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                nv.setNgaybatdau(localDateTime.toLocalDate());

                list.add(nv);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}