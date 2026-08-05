package com.pedrin.gc_pacientes.controller.mapper;

import com.pedrin.gc_pacientes.controller.dto.RetornarPacienteDTO;
import com.pedrin.gc_pacientes.controller.dto.SalvarPacienteDTO;
import com.pedrin.gc_pacientes.model.Paciente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
        Paciente salvarPacienteDTOtoPaciente(SalvarPacienteDTO dto);

        RetornarPacienteDTO pacienteToRetornarPacienteDTO(Paciente paciente);
}
