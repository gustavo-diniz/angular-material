package br.com.gustavodiniz.boot.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import br.com.gustavodiniz.boot.to.file.Agente;
import br.com.gustavodiniz.boot.to.file.Agentes;
import br.com.gustavodiniz.boot.to.file.Compra;
import br.com.gustavodiniz.boot.to.file.Geracao;
import br.com.gustavodiniz.boot.to.file.Regiao;

@Service
public class FileService {

	public Agentes recuperaDadosAgente(String body) {
		try {
			XStream xstream = new XStream();
			xstream.setClassLoader(Thread.currentThread().getContextClassLoader());
			xstream.addPermission(AnyTypePermission.ANY);
			xstream.addPermission(NullPermission.NULL);
			xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
			xstream.ignoreUnknownElements();
			
			xstream.allowTypeHierarchy(Collection.class);
			
			xstream.allowTypesByWildcard(new String[] {
			    "br.com.gustavodiniz.boot.to.file.**"
			});
			xstream.alias("agentes", Agentes.class);
			xstream.alias("agente", Agente.class);
			xstream.alias("regiao", Regiao.class);
			xstream.alias("geracao", Geracao.class);
			xstream.alias("compra", Geracao.class);
			xstream.addImplicitCollection(Agentes.class, "agentes");
			xstream.addImplicitCollection(Geracao.class,"valor", String.class);
			xstream.addImplicitCollection(Compra.class,"valor", String.class);
			xstream.addImplicitCollection(Agente.class,"regiao", Regiao.class);
			
			
			Agentes agnt =  (Agentes)xstream.fromXML(body);
			return agnt;
			
		}catch(Exception e) {
			return null;
		}
	}
	
}
