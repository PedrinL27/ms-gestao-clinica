package com.pedrin.gc_pacientes.config;

import com.pedrin.gc_pacientes.mcp.PacienteMcpService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpToolsConfig {

    @Bean
    public ToolCallbackProvider pacienteTools(PacienteMcpService pacienteMcpService) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(pacienteMcpService)
                .build();
    }
}