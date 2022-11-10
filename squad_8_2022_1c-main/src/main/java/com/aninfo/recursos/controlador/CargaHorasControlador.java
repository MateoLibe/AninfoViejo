package com.aninfo.recursos.controlador;

import com.aninfo.recursos.modelo.CargaHoras;
import com.aninfo.recursos.servicio.CargaHorasServicio;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@EnableSwagger2
public class CargaHorasControlador {

    @Autowired
    private CargaHorasServicio cargaHorasServicio;

    @PostMapping("/horas")
    @ResponseStatus(HttpStatus.CREATED)
    public CargaHoras cargarHoras(@RequestBody CargaHoras carga) throws Throwable {
        return cargaHorasServicio.createCargaHoras(carga);
    }

    @GetMapping("/horas/legajo/{legajo}")
    public Collection<CargaHoras> getCargas(@PathVariable Long legajo) {
        return cargaHorasServicio.findByLegajo(legajo);
    }

    @GetMapping("/horas/{id}")
    public ResponseEntity<CargaHoras> getCarga(@PathVariable Long id) {
        Optional<CargaHoras> cargaHorasOptional = cargaHorasServicio.findById(id);
        return ResponseEntity.of(cargaHorasOptional);
    }

    @DeleteMapping("/horas/{id}")
    public void deleteCarga(@PathVariable Long id) { cargaHorasServicio.deleteById(id); }

    @PutMapping("/horas/{id}")
    public ResponseEntity<CargaHoras> updateCarga(@RequestBody CargaHoras carga, @PathVariable Long id) throws Throwable {
        Optional<CargaHoras> cargaHorasOptional = cargaHorasServicio.findById(id);

        if (!cargaHorasOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        carga.setIdCarga(id);

        //un update comun que no se fija en las reglas de negocio
        //cargaHorasServicio.validate(carga);

        cargaHorasServicio.save(carga);
        return ResponseEntity.of(cargaHorasOptional);
    }

    @PutMapping("/horas/{id}/modificar")
    public CargaHoras modifyHorasCargadas(@PathVariable Long id, @RequestParam Double horas) throws Throwable {

        return cargaHorasServicio.modifyHoras(id, horas);

    }

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }
}