package br.mil.eb.sermil.assinatura.service;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.mil.eb.sermil.assinatura.JwtTokenUtil;
import br.mil.eb.sermil.assinatura.exception.AssinaturaErrorHandler;
import br.mil.eb.sermil.assinatura.exception.AssinaturaException;
import br.mil.eb.sermil.assinatura.model.Certificado;

/**
 * Classe que recebe, manipula e entrega dados vindos do backend. This class
 * receives, manipulates and delivers data that came from the backend.
 * 
 * @author Anselmo S Ribeiro <anselmo.sr@gmail.com>
 * @version 1.0
 * @since 1.0
 *
 */
@Service
public class CommunicationService extends SermilService {

	protected static final Logger logger = LoggerFactory.getLogger(CommunicationService.class);

	public static final Logger log = LoggerFactory.getLogger(CommunicationService.class);

	public static final String SERMIL_REST_SERVER_ROOT = "https://localhost:8443/portal";

	@Autowired
	TokenService tokenService;

	@Autowired
	AssinaturaErrorHandler errorHandler;
	
	@Autowired
	JwtTokenUtil tokenUtil;

	private Map<String, byte[]> hashes;

	public Map<String, byte[]> getHashes() {
		return hashes;
	}

	public String[] getCertificados() throws AssinaturaException {
		return this.getCertificadosFromBackEnd().stream().map(c -> c.toString()).collect(Collectors.toList())
				.toArray(new String[0]);
	}

	private List<Certificado> getCertificadosFromBackEnd() throws AssinaturaException {
		List<Certificado> res = this.get("/assinatura", Certificado.class);
		return res;
	}

	private <T> List<Certificado> get(String url, Class<T> clazz) throws AssinaturaException {

		try {

			setSslProperties();


			final String cpf = this.tokenService.getCpf();
			final HttpHeaders headers = getHeaders(cpf);
			HttpEntity<String> entity = new HttpEntity<String>(null, headers);
			RestTemplate client = new RestTemplate();

			client.setErrorHandler(errorHandler);

			Certificado[] res2 = client.postForObject(SERMIL_REST_SERVER_ROOT, entity, Certificado[].class);

			/*
			 * List<T> res = client.exchange(SERMIL_REST_SERVER_ROOT + url,
			 * HttpMethod.GET, entity, new ParameterizedTypeReference<List<T>>()
			 * { }).getBody();
			 */
			return Arrays.asList(res2);
		} catch (Exception e) {
			// throw new AssinaturaException("Não consegui comunicação com o
			// Sermil");
			throw new AssinaturaException(e.getMessage());
		}

	}

	public void setSslProperties() throws AssinaturaException {
		final URL trustStoreUrl = this.getClass().getResource("/ssl/store/aol-keystore.jks");

		if (trustStoreUrl == null) {
			String msg = "Arquivo de certificados (keyStoreUrl/trustStoreUrl) não foram encontrados, CpfInfoconvServico não irá funcionar.";
			logger.error(msg);
			throw new AssinaturaException(msg);
		} else {
			final Properties props = new Properties();
			props.setProperty("javax.net.ssl.trustStore", trustStoreUrl.getPath());
			props.setProperty("javax.net.ssl.trustStorePassword", "Mestre.10");
			props.setProperty("javax.net.ssl.trustStoreType", "JKS");
			System.getProperties().putAll(props);
			logger.debug("TrustStore = {}", trustStoreUrl.getPath());
		}
	}

	public HttpHeaders getHeaders(String cpf) {
		final String token = tokenUtil.generateToken(cpf);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add(JwtTokenUtil.JWT_TOKEN_KEY, token);
		return headers;
	}

}
