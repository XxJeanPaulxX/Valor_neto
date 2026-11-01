package pe.senati.valorneto.model;

import java.math.BigDecimal;

/**
 * Resultado que devuelve la API (RESPONSE).
 */
public class ValorNetoResponse {

    private String nombre;
    private BigDecimal sueldo;
    private Integer horasTrabajadas;
    private boolean aplicaDescuento;
    private BigDecimal descuento;
    private boolean aplicaBonificacion;
    private BigDecimal bonificacion;
    private BigDecimal valorNeto;

    public ValorNetoResponse() {
    }

    public ValorNetoResponse(String nombre, BigDecimal sueldo, Integer horasTrabajadas,
                             boolean aplicaDescuento, BigDecimal descuento,
                             boolean aplicaBonificacion, BigDecimal bonificacion,
                             BigDecimal valorNeto) {
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.horasTrabajadas = horasTrabajadas;
        this.aplicaDescuento = aplicaDescuento;
        this.descuento = descuento;
        this.aplicaBonificacion = aplicaBonificacion;
        this.bonificacion = bonificacion;
        this.valorNeto = valorNeto;
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public Integer getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public boolean isAplicaDescuento() {
        return aplicaDescuento;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public boolean isAplicaBonificacion() {
        return aplicaBonificacion;
    }

    public BigDecimal getBonificacion() {
        return bonificacion;
    }

    public BigDecimal getValorNeto() {
        return valorNeto;
    }
}
