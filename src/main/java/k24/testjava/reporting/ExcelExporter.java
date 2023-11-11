package k24.testjava.reporting;

import k24.testjava.model.Member;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Member> memberList;

    public ExcelExporter(List<Member> memberList){
        this.memberList = memberList;
        workbook = new XSSFWorkbook();
    }
    private void writeHeaderLine(){
        sheet = workbook.createSheet("Member");

        Row row = sheet.createRow(0);

        CellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        cellStyle.setFont(font);

        createCell(row, 0, "ID", cellStyle);
        createCell(row, 1, "Name", cellStyle);
        createCell(row, 2, "Password", cellStyle);
        createCell(row, 3, "Handphone", cellStyle);
        createCell(row, 4, "Born", cellStyle);
        createCell(row, 5, "Email", cellStyle);
        createCell(row, 6, "Gender", cellStyle);
        createCell(row, 7, "Number KTP", cellStyle);
        createCell(row, 8, "Photo", cellStyle);
    }

    private void createCell(Row row, int columnIndex, Object value, CellStyle style){
        sheet.autoSizeColumn(columnIndex);
        Cell cell = row.createCell(columnIndex);
        if (value instanceof Integer)
            cell.setCellValue((Integer)value);
        else if(value instanceof Boolean)
            cell.setCellValue((Boolean)value);
        else
            cell.setCellValue((String)value);

        cell.setCellStyle(style);
    }

    private void writeDataLine(List<Member> memberList){
        int rowIndex = 1;

        CellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        cellStyle.setFont(font);
        for (Member mem : memberList) {
            XSSFRow row = sheet.createRow(rowIndex++);
            int columnIndex = 0;
            createCell(row, columnIndex++, mem.getId(), cellStyle);
            createCell(row, columnIndex++, mem.getNama(), cellStyle);
            createCell(row, columnIndex++, mem.getPassword(), cellStyle);
            createCell(row, columnIndex++, mem.getNo_hp(), cellStyle);
            createCell(row, columnIndex++, mem.getTgl_lahir(), cellStyle);
            createCell(row, columnIndex++, mem.getEmail(), cellStyle);
            createCell(row, columnIndex++, mem.getJk(), cellStyle);
            createCell(row, columnIndex++, mem.getNo_ktp(), cellStyle);
            createCell(row, columnIndex++, mem.getFoto(), cellStyle);
        }
    }

    public void exportToEXCEL(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLine(memberList);

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
