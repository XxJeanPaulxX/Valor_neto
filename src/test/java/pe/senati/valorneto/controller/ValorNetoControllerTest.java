package pe.senati.valorneto.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ValorNetoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void get_calculaValorNeto() throws Exception {
        mockMvc.perform(get("/api/valor-neto")
                        .param("sueldo", "2500")
                        .param("horas", "9"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valorNeto").value(2600.00));
    }

    @Test
    void post_calculaValorNeto() throws Exception {
        String json = "{\"nombre\":\"Pedro\",\"sueldo\":1200,\"horasTrabajadas\":10}";
        mockMvc.perform(post("/api/valor-neto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.aplicaDescuento").value(false))
                .andExpect(jsonPath("$.valorNeto").value(1500.00));
    }

    @Test
    void post_conDatosInvalidos_devuelve400() throws Exception {
        String json = "{\"nombre\":\"Pedro\",\"sueldo\":-5,\"horasTrabajadas\":8}";
        mockMvc.perform(post("/api/valor-neto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Datos invalidos"));
    }
}
