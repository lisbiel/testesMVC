package com.fatec.scel.model;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Servico {
	public String obtemEndereco(String cep) {
		RestTemplate template = new RestTemplate();
		String url = "https://viacep.com.br/ws/{cep}/json/";
		Endereco endereco = template.getForObject(url, Endereco.class, cep);
		return endereco.getLogradouro();
	}

	/**
	 * Objetivo - verificar se houve atraso na devolucao
	 *
	 * @param umEmprestimo
	 * @return int < 0 se estiver atrasado e > 0 se estiver no prazo retorna nulo se
	 *         o objeto emprestimo é nulo.
	 */
	public Integer verificaAtraso(Emprestimo umEmprestimo) {
		if (umEmprestimo != null) {
			DateTimeFormatter fmt = DateTimeFormat.forPattern("YYYY/MM/dd");
			DateTime dataAtual = fmt.parseDateTime(new DateTime().toString(fmt));
			DateTime dataDevolucao = fmt.parseDateTime(umEmprestimo.getDataDevolucao());
			int dias = Days.daysBetween(dataAtual, dataDevolucao).getDays();
			return dias;
		} else {
			return null;
		}
	}
}