package com.tecsup.prj_pc02.views;

import java.util.List;
import java.util.Map;

import com.tecsup.prj_pc02.modelo.entidades.EstacionamientoMoto01;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("estacionamientoMoto/verXls")
public class EstacionamientoMoto01XlsView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        response.setHeader("Content-Disposition", "attachment; filename=\"estacionamientos_moto01.xlsx\"");

        List<EstacionamientoMoto01> estacionamientos = (List<EstacionamientoMoto01>) model.get("estacionamientos");

        Sheet sheet = workbook.createSheet("Estacionamientos Moto01");
        sheet.setDefaultColumnWidth(30);


        XSSFCellStyle style = ((XSSFWorkbook) workbook).createCellStyle();
        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setBold(true);
        style.setFont(font);


        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
        sheet.createRow(0).createCell(0).setCellValue("Lista de Estacionamientos Moto01");


        sheet.createRow(1).createCell(0).setCellValue("ID Slot");
        sheet.getRow(1).createCell(1).setCellValue("Placa");
        sheet.getRow(1).createCell(2).setCellValue("Ubicación");


        int rowCount = 2;
        for (EstacionamientoMoto01 estacionamiento : estacionamientos) {
            sheet.createRow(rowCount).createCell(0).setCellValue(estacionamiento.getIdslot());
            sheet.getRow(rowCount).createCell(1).setCellValue(estacionamiento.getPlaca());
            sheet.getRow(rowCount).createCell(2).setCellValue(estacionamiento.getUbicacion().getNombre()); // Mostrar el nombre de la ubicación
            rowCount++;
        }
    }
}
