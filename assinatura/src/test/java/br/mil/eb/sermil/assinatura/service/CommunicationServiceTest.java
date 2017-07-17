package br.mil.eb.sermil.assinatura.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.mil.eb.sermil.assinatura.AppConfig;
import br.mil.eb.sermil.assinatura.JwtTokenUtil;
import br.mil.eb.sermil.assinatura.exception.AssinaturaException;

public class CommunicationServiceTest {

  private static final String CPF = "92054986704";
  private static final String SERMIL_URL = "https://localhost:8443/portal/assinatura";

  private CommunicationService communicationService;
  private JwtTokenUtil tokenUtil;

  @Before
  @SuppressWarnings("resource")
  public void init() {
    // Iniciando contexto de injecao de dependencia
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    this.communicationService = context.getBean(CommunicationService.class);
    this.tokenUtil = context.getBean(JwtTokenUtil.class);
  }

  @Test
  public void initialAsserts() {
    Assert.assertNotNull(this.communicationService);
  }

  @Test
  public void setInitialProperties$() throws AssinaturaException {
    this.communicationService.setSslProperties();
  }

  @Test
  public void getCpfFromToken() {
    String cpf = String.valueOf(CPF);
    String token = tokenUtil.generateToken(cpf);
    String _cpf = tokenUtil.getCpfFromToken(token);
    Assert.assertTrue(cpf.equals(_cpf));
  }

  @Test
  // @Ignore
  public void conecta() {
    System.out.println("*************** CONECTA ****************");
    try {
      this.communicationService.setSslProperties();
    } catch (AssinaturaException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      final HttpHeaders headers = this.communicationService.getHeaders(CPF);
      HttpEntity<String> entity = new HttpEntity<String>(null, headers);
      RestTemplate client = new RestTemplate();
      String res2 = client.postForObject(SERMIL_URL, entity, String.class);

      System.out.println(res2);
    } catch (RestClientException e) {
      System.out.println(e.getMessage());
    }
    System.out.println("*************** CONECTA FIM ****************");
  }

}
// Caused by: sun.security.provider.certpath.SunCertPathBuilderException: unable
// to find valid certification path to requested target
