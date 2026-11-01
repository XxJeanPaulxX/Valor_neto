package pe.senati.valorneto.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

/**
 * Responsabilidad unica: guardar las reglas de negocio configurables
 * (se leen desde application.properties con el prefijo "reglas").
 */
@ConfigurationProperties(prefix = "reglas")
public class ReglasNetoProperties {

    /** Sueldo minimo (inclusive) a partir del cual se aplica el descuento. */
    private BigDecimal sueldoMinimoDescuento = new BigDecimal("2000");

    /** Porcentaje de descuento (8 = 8%). */
    private BigDecimal porcentajeDescuento = new BigDecimal("8");

    /** Horas por encima de las cuales se paga bonificacion. */
    private int horasLimite = 8;

    /** Bonificacion fija en soles por trabajar mas de las horas limite. */
    private BigDecimal bonificacionHorasExtras = new BigDecimal("300");

    public BigDecimal getSueldoMinimoDescuento() {
        return sueldoMinimoDescuento;
    }

    public void setSueldoMinimoDescuento(BigDecimal sueldoMinimoDescuento) {
        this.sueldoMinimoDescuento = sueldoMinimoDescuento;
    }

    public BigDecimal getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public int getHorasLimite() {
        return horasLimite;
    }

    public void setHorasLimite(int horasLimite) {
        this.horasLimite = horasLimite;
    }

    public BigDecimal getBonificacionHorasExtras() {
        return bonificacionHorasExtras;
    }

    public void setBonificacionHorasExtras(BigDecimal bonificacionHorasExtras) {
        this.bonificacionHorasExtras = bonificacionHorasExtras;
    }
}
