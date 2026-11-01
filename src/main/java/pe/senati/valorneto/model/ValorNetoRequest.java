package pe.senati.valorneto.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * Datos que envia el cliente (REQUEST).
 */
public class ValorNetoRequest {

    private String nombre;

    @NotNull(message = "El sueldo es obligatorio")
    @DecimalMin(value = "0.0", message = "El sueldo no puede ser negativo")
    private BigDecimal sueldo;

    @NotNull(message = "Las horas trabajadas son obligatorias")
    @Min(value = 0, message = "Las horas trabajadas no pueden ser negativas")
    private Integer horasTrabajadas;

    public ValorNetoRequest() {
    }

    public ValorNetoRequest(String nombre, BigDecimal sueldo, Integer horasTrabajadas) {
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.horasTrabajadas = horasTrabajadas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    public Integer getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(Integer horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }
}
