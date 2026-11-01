package pe.senati.valorneto.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.senati.valorneto.model.ValorNetoRequest;
import pe.senati.valorneto.model.ValorNetoResponse;
import pe.senati.valorneto.service.CalcularValorNeto;

import java.math.BigDecimal;

/**
 * Responsabilidad unica: recibir las peticiones HTTP (request) y devolver la
 * respuesta (response). El calculo lo hace CalcularValorNeto.
 */
@RestController
@RequestMapping("/api/valor-neto")
public class ValorNetoController {

    private final CalcularValorNeto calcularValorNeto;

    public ValorNetoController(CalcularValorNeto calcularValorNeto) {
        this.calcularValorNeto = calcularValorNeto;
    }

    /** GET: los datos van en la URL. Ej: /api/valor-neto?sueldo=2500&horas=9 */
    @GetMapping
    public ValorNetoResponse obtener(@RequestParam BigDecimal sueldo,
                                     @RequestParam int horas,
                                     @RequestParam(required = false) String nombre) {
        return calcularValorNeto.calcular(new ValorNetoRequest(nombre, sueldo, horas));
    }

    /** POST: los datos van en el cuerpo (JSON). */
    @PostMapping
    public ValorNetoResponse calcular(@Valid @RequestBody ValorNetoRequest request) {
        return calcularValorNeto.calcular(request);
    }
}
