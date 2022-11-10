package com.aninfo.recursos.servicio;


import com.aninfo.recursos.excepciones.*;
import com.aninfo.recursos.modelo.CargaHoras;
import com.aninfo.recursos.repositorio.CargaHorasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import java.util.*;

@Service
public class CargaHorasServicio {

	@Autowired
	private CargaHorasRepositorio cargaHorasRepositorio;

	public boolean validateHoras(CargaHoras carga) throws Throwable {
		//Una carga no puede realizarse por menos de 1 hora o mas de 8.
		if (carga.getHoras() <= 0 || carga.getHoras() > 8) {
			throw new CantidadDeHorasInvalidaException();
		}

		return true;
	}

	public boolean validateFecha(CargaHoras carga) throws Throwable{

		//Una carga no puede realizarse a una fecha de mas de 30 dias antes.
		LocalDate fechaHoy   = LocalDate.now();
		LocalDate fechaCarga = LocalDate.ofInstant(carga.getFecha().toInstant(), ZoneId.systemDefault());

		if (fechaCarga.until(fechaHoy, ChronoUnit.DAYS) > 30 || fechaCarga.until(fechaHoy, ChronoUnit.DAYS) <= -1) {
			throw new CantidadDeDiasInvalidaException();
		}

		//TODO: Se pueden realizar cargas de dias en el futuro?
		return true;

	}

	@Transactional
	public CargaHoras createCargaHoras(CargaHoras carga) throws Throwable {

		if(validateHoras(carga) && validateFecha(carga)){
			cargaHorasRepositorio.save(carga);
		}

		return carga;
	}

	public Collection<CargaHoras> getCargasDeHoras() {
		Collection<CargaHoras> cargas = new ArrayList<>();
		cargaHorasRepositorio.findAll().forEach(cargas::add);
		return cargas;
	}

	public Optional<CargaHoras> findById(Long id) {
		return cargaHorasRepositorio.findById(id);
	}

	public void save(CargaHoras carga) { cargaHorasRepositorio.save(carga); }

	public void deleteById(Long id) {
		cargaHorasRepositorio.deleteById(id);
	}
	public Collection<CargaHoras> findByLegajo(Long legajo) { return cargaHorasRepositorio.findByLegajoPersona(legajo); }

	@Transactional
	public CargaHoras modifyHoras(Long id, Double horas) throws Throwable {

		Optional<CargaHoras> horaGuardadaEnRepo = cargaHorasRepositorio.findById(id);
		CargaHoras horaAModificar = null;

		if(horaGuardadaEnRepo.isPresent()){

			horaAModificar = horaGuardadaEnRepo.get();
			horaAModificar.setHoras(horas);
			validateHoras(horaAModificar);
			cargaHorasRepositorio.save(horaAModificar);

		}

		return horaAModificar;

	}


}
