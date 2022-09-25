package br.com.gustavodiniz.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gustavodiniz.boot.service.FileService;
import br.com.gustavodiniz.boot.to.ArquivoTO;
import br.com.gustavodiniz.boot.to.file.Agente;
import br.com.gustavodiniz.boot.to.file.Agentes;

@RestController
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private FileService fs;

	@PostMapping(path = "/arquivo")
	public ResponseEntity<ArquivoTO> mensagem(@RequestBody String body) {

		ArquivoTO to = new ArquivoTO();
		to.setStatusRetorno("");

		Agentes agnt = fs.recuperaDadosAgente(body);

		if (agnt == null) {
			to.setStatusRetorno("Não foi possivel executar o arquivo !");
			return new ResponseEntity<ArquivoTO>(to, HttpStatus.BAD_REQUEST);

		} else {
			for (Agente agt : agnt.getAgente()) {
				System.out.println(agt.getCodigo());
			}
			to.setStatusRetorno("Arquivo gerado com sucesso !");
			return new ResponseEntity<ArquivoTO>(to, HttpStatus.OK);
		}

	}

}
