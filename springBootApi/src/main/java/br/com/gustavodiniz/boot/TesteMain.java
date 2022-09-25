package br.com.gustavodiniz.boot;

import java.util.Collection;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import br.com.gustavodiniz.boot.to.file.Agente;
import br.com.gustavodiniz.boot.to.file.Agentes;
import br.com.gustavodiniz.boot.to.file.Compra;
import br.com.gustavodiniz.boot.to.file.Geracao;
import br.com.gustavodiniz.boot.to.file.Regiao;

public class TesteMain {

	public static void main(String[] args) {
		
		XStream xstream = new XStream();
		xstream.addPermission(AnyTypePermission.ANY);
		// allow some basics
		xstream.addPermission(NullPermission.NULL);
		xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
		xstream.ignoreUnknownElements();
		
		xstream.allowTypeHierarchy(Collection.class);
		// allow any type from the same package
		xstream.allowTypesByWildcard(new String[] {
		    "br.com.gustavodiniz.to.file.**"
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
//		xstream.addImplicitCollection(Regiao.class, "geracao");
//		xstream.addImplicitCollection(Regiao.class, "compra");
		
//		Agentes agentes = new Agentes();
//		List<Agente> agente = new ArrayList<Agente>();
//		Agente ag = new Agente();
//		ag.setCodigo("1");
//		
//		agente.add(ag);
//		agentes.setAgente(agente); 
//		
//		String str = xstream.toXML(agentes);
//		System.out.println(str);
		
		
		Agentes agnt =  (Agentes) xstream.fromXML("<agentes>    <agente>        <codigo>1</codigo>        <data>2000-03-14T00:00:00-03:00</data>        <regiao sigla=\"SE\">            <geracao>                <valor>1.864</valor>                <valor>1.864</valor>                <valor>1.864</valor>                <valor>1.864</valor>                <valor>1.864</valor>                <valor>1.864</valor>                <valor>1.864</valor>            </geracao>            <compra>                <valor>1.19</valor>                <valor>1.19</valor>                <valor>1.19</valor>                <valor>1.19</valor>                <valor>1.19</valor>                <valor>1.19</valor>                <valor>1.19</valor>            </compra>                    </regiao>        <regiao sigla=\"S\">            <geracao>                <valor>1.513</valor>                <valor>1.513</valor>                <valor>1.513</valor>                <valor>1.513</valor>                <valor>1.513</valor>                <valor>1.513</valor>                <valor>1.513</valor>            </geracao>            <compra>                <valor>1.27</valor>                <valor>1.27</valor>                <valor>1.27</valor>                <valor>1.27</valor>                <valor>1.27</valor>                <valor>1.27</valor>                <valor>1.27</valor>            </compra>                    </regiao>        <regiao sigla=\"NE\">            <geracao>                <valor>1.463</valor>                <valor>1.463</valor>                <valor>1.463</valor>                <valor>1.463</valor>                <valor>1.463</valor>                <valor>1.463</valor>                <valor>1.463</valor>            </geracao>            <compra>                <valor>1.936</valor>                <valor>1.936</valor>                <valor>1.936</valor>                <valor>1.936</valor>                <valor>1.936</valor>                <valor>1.936</valor>                <valor>1.936</valor>            </compra>                    </regiao>        <regiao sigla=\"N\">            <geracao>                <valor>1.707</valor>                <valor>1.707</valor>                <valor>1.707</valor>                <valor>1.707</valor>                <valor>1.707</valor>                <valor>1.707</valor>                <valor>1.707</valor>            </geracao>            <compra>                <valor>1.141</valor>                <valor>1.141</valor>                <valor>1.141</valor>                <valor>1.141</valor>                <valor>1.141</valor>                <valor>1.141</valor>                <valor>1.141</valor>            </compra>                    </regiao>    </agente></agentes>");
		System.out.println(agnt.getAgente().size());
	}
	
}
