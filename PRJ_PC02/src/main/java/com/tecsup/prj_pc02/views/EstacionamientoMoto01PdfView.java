package com.tecsup.prj_pc02.views;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import com.tecsup.prj_pc02.modelo.entidades.EstacionamientoMoto01;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("estacionamientoMoto/verPdf")
public class EstacionamientoMoto01PdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<EstacionamientoMoto01> estacionamientos = (List<EstacionamientoMoto01>) model.get("estacionamientos");

        PdfPTable tabla = new PdfPTable(1);
        tabla.setSpacingAfter(20);

        PdfPCell cell = null;

        cell = new PdfPCell(new Phrase("Lista de Estacionamientos Moto01"));
        cell.setBackgroundColor(new Color(184, 218, 255));
        cell.setPadding(8f);
        tabla.addCell(cell);

        PdfPTable tabla2 = new PdfPTable(3);
        tabla2.setWidths(new float[]{3.5f, 1, 1});

        tabla2.addCell("ID Slot");
        tabla2.addCell("Placa");
        tabla2.addCell("Ubicación");

        for (EstacionamientoMoto01 estacionamiento : estacionamientos) {

            tabla2.addCell(estacionamiento.getIdslot());
            tabla2.addCell(estacionamiento.getPlaca());
            tabla2.addCell(estacionamiento.getUbicacion().getNombre()); // Mostrar el nombre de la ubicación
        }

        document.add(tabla);
        document.add(tabla2);
    }
}
