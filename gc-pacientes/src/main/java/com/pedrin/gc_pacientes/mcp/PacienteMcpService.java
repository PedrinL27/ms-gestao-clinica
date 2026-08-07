package com.pedrin.gc_pacientes.mcp;

import com.pedrin.gc_pacientes.controller.dto.RetornarPacienteDTO;
import com.pedrin.gc_pacientes.controller.dto.SalvarPacienteDTO;
import com.pedrin.gc_pacientes.controller.mapper.PacienteMapper;
import com.pedrin.gc_pacientes.model.Paciente;
import com.pedrin.gc_pacientes.service.PacienteService;
import com.pedrin.gc_pacientes.service.exception.PacienteNaoEncontradoException;
import com.pedrin.gc_pacientes.service.exception.RegistroDuplicadoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PacienteMcpService {

    private final PacienteService service;
    private final PacienteMapper mapper;

    @McpTool(description = "Cadastra um novo paciente no sistema. Use quando o usuário pedir para criar, adicionar ou registrar um novo paciente.")
    public String salvarPaciente(@McpToolParam(description = "Dados do paciente a ser cadastrado") SalvarPacienteDTO dto) {
        var paciente = mapper.salvarPacienteDTOtoPaciente(dto);
        try {
            service.salvar(paciente);
            return "Paciente cadastrado com sucesso.";
        } catch (RegistroDuplicadoException e) {
            return "Erro ao cadastrar paciente: " + e.getMessage() + " (campo: " + e.getField() + ")";
        }
    }

    @Tool(description = "Lista todos os pacientes cadastrados no sistema.")
    public List<RetornarPacienteDTO> listarPacientes() {
        return service.findAll().stream()
                .map(mapper::pacienteToRetornarPacienteDTO)
                .toList();
    }

    @Tool(description = "Busca um paciente específico pelo seu ID único (UUID).")
    public String acharPorId(@ToolParam(description = "ID do paciente no formato UUID") String id) {
        try {
            UUID uuid = UUID.fromString(id);
            Paciente paciente = service.findById(uuid);
            RetornarPacienteDTO dto = mapper.pacienteToRetornarPacienteDTO(paciente);
            return dto.toString();
        } catch (IllegalArgumentException e) {
            return "Erro: ID informado não é um UUID válido.";
        } catch (PacienteNaoEncontradoException e) {
            log.error(e.getMessage());
            return "Erro: paciente com ID " + id + " não encontrado.";
        }
    }

    @Tool(description = "Atualiza os dados de um paciente existente a partir do seu ID.")
    public String atualizarPaciente(
            @ToolParam(description = "ID do paciente a ser atualizado") String id,
            @ToolParam(description = "Novos dados do paciente") SalvarPacienteDTO dto) {
        try {
            UUID uuid = UUID.fromString(id);
            Paciente paciente = mapper.salvarPacienteDTOtoPaciente(dto);
            service.update(uuid, paciente);
            return "Paciente atualizado com sucesso.";
        } catch (IllegalArgumentException e) {
            return "Erro: ID informado não é um UUID válido.";
        } catch (PacienteNaoEncontradoException e) {
            log.error(e.getMessage());
            return "Erro: paciente com ID " + id + " não encontrado.";
        }
    }

    @Tool(description = "Remove um paciente permanentemente do sistema utilizando o ID único.")
    public String deletarPaciente(@ToolParam(description = "ID do paciente a ser removido") String id) {
        try {
            UUID uuid = UUID.fromString(id);
            service.delete(uuid);
            return "Paciente com ID " + id + " deletado com sucesso.";
        } catch (IllegalArgumentException e) {
            return "Erro: ID informado não é um UUID válido.";
        }
    }
}