package pe.senati.valorneto.service;

import org.springframework.stereotype.Service;
import pe.senati.valorneto.config.ReglasNetoProperties;
import pe.senati.valorneto.model.ValorNetoRequest;
import pe.senati.valorneto.model.ValorNetoResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Responsabilidad unica (la "S" de SOLID): calcular el valor neto.
 *
 * Reglas (ver pizarra):
 *  - Si el sueldo es mayor o igual al minimo (S/ 2000): se descuenta el 8%.
 *    Ej: S/ 1200 -> no aplica (X) | S/ 2500 -> si aplica (check).
 *  - Si trabaja mas de 8 horas: se suma una bonificacion de S/ 300.
 *
 * valorNeto = sueldo - descuento + bonificacion
 */
@Service
public class CalcularValorNeto {

    private static final BigDecimal CIEN = new BigDecimal("100");

    private final ReglasNetoProperties reglas;

    public CalcularValorNeto(ReglasNetoProperties reglas) {
        this.reglas = reglas;
    }

    public ValorNetoResponse calcular(ValorNetoRequest request) {
        BigDecimal sueldo = request.getSueldo().setScale(2, RoundingMode.HALF_UP);
        int horas = request.getHorasTrabajadas();

        boolean aplicaDescuento = sueldo.compareTo(reglas.getSueldoMinimoDescuento()) >= 0;
        BigDecimal descuento = aplicaDescuento
                ? sueldo.multiply(reglas.getPorcentajeDescuento())
                        .divide(CIEN, 2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO.setScale(2);

        boolean aplicaBonificacion = horas > reglas.getHorasLimite();
        BigDecimal bonificacion = aplicaBonificacion
                ? reglas.getBonificacionHorasExtras().setScale(2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO.setScale(2);

        BigDecimal valorNeto = sueldo.subtract(descuento).add(bonificacion);

        return new ValorNetoResponse(
                request.getNombre(), sueldo, horas,
                aplicaDescuento, descuento,
                aplicaBonificacion, bonificacion,
                valorNeto);
    }
}
