package br.com.emanuelgabriel.lojawebapi.config;

import org.springdoc.webmvc.ui.SwaggerConfig;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * 
 * @author emanuel.sousa
 *
 */

@OpenAPIDefinition(
		info = @Info(description = "Documentação da API da Loja Web API - Gerenciador de Pedidos", 
		termsOfService = "Termos de uso", 
		title = "API da Loja Web", 
		version = "1.0.0", 
		contact = @Contact(name = "Emanuel Gabriel Sousa", 
		email = "emanuel.gabriel.sousa@hotmail.com")))
public class DocumentationConfig extends SwaggerConfig {

}
