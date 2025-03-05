package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MedicoEspecialidadService {
    @Autowired
    MedicoService medicoService;

    @Autowired
    EspecialidadService especialidadService;

    @Transactional
    public MedicoEntity addEspecialidad(Long medicoId, Long especialidadId) throws EntityNotFoundException {
        log.info("Iniciando proceso de agregar especialidad al médico con id = {}", medicoId);

        MedicoEntity medico = medicoService.getId(medicoId);
        if (medico == null) {
            throw new EntityNotFoundException("Médico no encontrado");
        }

        EspecialidadEntity especialidad = especialidadService.getEspecialidad(especialidadId);
        if (especialidad == null) {
            throw new EntityNotFoundException("Especialidad no encontrada");
        }

        medico.getEspecialidades().add(especialidad);
        log.info("Especialidad agregada exitosamente al médico con id = {}", medicoId);

        return medico;
    }
}

