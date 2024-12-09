package com.tecsup.prj_pc02.modelo.dto;

import javax.validation.constraints.NotNull;

public class QrUpdateDTO {

    @NotNull(message = "El valor del QR no puede ser nulo")
    private String qr;

    public QrUpdateDTO() {}

    public QrUpdateDTO(String qr) {
        this.qr = qr;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }
}
