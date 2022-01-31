package com.concredito.webapi;

import com.concredito.webapi.Models.EstatusModel;
import com.concredito.webapi.Repositories.EstatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.boot.CommandLineRunner;

@Component
public class EstatusDataLoader implements CommandLineRunner{

    @Autowired
    EstatusRepository estatusRepository;

    @Override
	public void run(String... args) throws Exception {
		loadEstatusData();
	}

    private void loadEstatusData() {
		if (estatusRepository.count() == 0) {
			EstatusModel estatus1 = new EstatusModel();
			EstatusModel estatus2 = new EstatusModel();
			EstatusModel estatus3 = new EstatusModel();

			estatus1.setDescripcion("Enviado");
			estatus2.setDescripcion("Aprobado");
			estatus3.setDescripcion("Rechazado");

			estatusRepository.save(estatus1);
			estatusRepository.save(estatus2);
			estatusRepository.save(estatus3);
		}
		System.out.println(estatusRepository.count());
	}
    
}
