package com.aninfo.recursos.repositorio;

import com.aninfo.recursos.modelo.CargaHoras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource
public interface CargaHorasRepositorio extends JpaRepository<CargaHoras, Long> {
    List<CargaHoras> findByLegajoPersona(Long legajo);

}
