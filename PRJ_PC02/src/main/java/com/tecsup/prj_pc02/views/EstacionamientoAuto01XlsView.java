package com.tecsup.prj_pc02.views;

import java.util.List;
import java.util.Map;

import com.tecsup.prj_pc02.modelo.entidades.EstacionamientoAuto01;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("estacionamiento/verXls")
public class EstacionamientoAuto01XlsView extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        response.setHeader("Content-Disposition", "attachment; filename=\"estacionamientos_auto01.xls\"");
        List<EstacionamientoAuto01> estacionamientos = (List<EstacionamientoAuto01>) model.get("estacionamientos");

        Sheet sheet = workbook.createSheet("Estacionamientos Auto01");

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
        sheet.createRow(0).createCell(0).setCellValue("Lista de Estacionamientos Auto01");

        sheet.createRow(1).createCell(0).setCellValue("ID Slot");
        sheet.getRow(1).createCell(1).setCellValue("Placa");
        sheet.getRow(1).createCell(2).setCellValue("Ubicación");

        int rowNum = 2;
        for (EstacionamientoAuto01 estacionamiento : estacionamientos) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(estacionamiento.getIdslot());
            row.createCell(1).setCellValue(estacionamiento.getPlaca());
            // Acceder al nombre de la entidad Ubicacion
            row.createCell(2).setCellValue(estacionamiento.getUbicacion().getNombre());
        }
    }
}
