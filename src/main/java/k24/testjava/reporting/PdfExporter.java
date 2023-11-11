package k24.testjava.reporting;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import k24.testjava.model.Member;

public class PdfExporter {
    private List<Member> memberList;

    public PdfExporter(List<Member> memberList){
        this.memberList = memberList;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Password", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Handphone", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Born", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("JK", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Number KTP", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Photo", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Member member : memberList) {
            table.addCell(String.valueOf(member.getId()));
            table.addCell(member.getNama());
            table.addCell(member.getPassword());
            table.addCell(member.getNo_hp());
            table.addCell(member.getTgl_lahir());
            table.addCell(member.getEmail());
            table.addCell(member.getJk());
            table.addCell(member.getNo_ktp());
            table.addCell(member.getFoto());
        }
    }

    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Members", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.5f, 4.0f, 3.0f,4.0f,1.5f,4.0f,3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
