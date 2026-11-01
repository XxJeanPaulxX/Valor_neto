package pe.senati.valorneto.service;

import org.junit.jupiter.api.Test;
import pe.senati.valorneto.config.ReglasNetoProperties;
import pe.senati.valorneto.model.ValorNetoRequest;
import pe.senati.valorneto.model.ValorNetoResponse;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CalcularValorNetoTest {

    private final CalcularValorNeto servicio = new CalcularValorNeto(new ReglasNetoProperties());

    private ValorNetoResponse calcular(String sueldo, int horas) {
        return servicio.calcular(new ValorNetoRequest("Test", new BigDecimal(sueldo), horas));
    }

    @Test
    void sueldo1200_noAplicaDescuento() {
        ValorNetoResponse r = calcular("1200", 8);
        assertFalse(r.isAplicaDescuento());
        assertEquals(new BigDecimal("1200.00"), r.getValorNeto());
    }

    @Test
    void sueldo2500_aplicaDescuento8Porciento() {
        ValorNetoResponse r = calcular("2500", 8);
        assertTrue(r.isAplicaDescuento());
        assertEquals(new BigDecimal("200.00"), r.getDescuento());
        assertEquals(new BigDecimal("2300.00"), r.getValorNeto());
    }

    @Test
    void masDe8Horas_sumaBonificacion300() {
        ValorNetoResponse r = calcular("1200", 9);
        assertTrue(r.isAplicaBonificacion());
        assertEquals(new BigDecimal("300.00"), r.getBonificacion());
        assertEquals(new BigDecimal("1500.00"), r.getValorNeto());
    }

    @Test
    void exactamente8Horas_noHayBonificacion() {
        ValorNetoResponse r = calcular("1200", 8);
        assertFalse(r.isAplicaBonificacion());
    }

    @Test
    void sueldo2500_con10Horas_descuentoMasBonificacion() {
        ValorNetoResponse r = calcular("2500", 10);
        // 2500 - 200 + 300
        assertEquals(new BigDecimal("2600.00"), r.getValorNeto());
    }
}
