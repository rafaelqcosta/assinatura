package br.mil.eb.sermil.assinatura.controller;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.mil.eb.sermil.assinatura.service.TokenService;
import br.mil.eb.sermil.assinatura.util.Util;
import br.mil.eb.sermil.assinatura.view.AssinaturaFrameDesign;

/**
 * Classe que recebe todas as acoes executadas no Frame Assinatura This class is
 * supposed to handle all the event trigered in the Assinatura Frame
 * 
 * @author Anselmo S Ribeiro <anselmo.sr@gmail.com>
 * @version 1.0
 * @since 1.0
 *
 */
@Controller
public class AssinaturaFrame extends AssinaturaFrameDesign {

  private static final long serialVersionUID = 1L;

  @Autowired
  private TokenService assinaturaService;

  /**
   * CLICAR EM ASSINAR CERTIFICADOS
   */

  @Override
  public void bntAssinarActionPerformed(ActionEvent evt) {

    if (this.jList1.getSelectedValuesList().size() == 0) {
      Util.displayErrorMsg("Escolha pelo menos um Certificado para assinar");
      return;
    }
    try {
      this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
      this.bntAssinar.setEnabled(false);

      this.assinaturaService.assinar(this.jList1.getSelectedValuesList());

    } catch (KeyStoreException e) {
      Util.displayErrorMsg("Não consegui ler sua Key Store");
    } catch (UnrecoverableKeyException e) {
      Util.displayErrorMsg("Não consegui ler sua chave privada");
    } catch (NoSuchAlgorithmException e) {
      Util.displayErrorMsg("Não reconheci o algoritmo do seu Token");
    } catch (Exception e) {
      Util.displayErrorMsg("Ocorreu um erro \n\r \n\r " + e.getMessage());
    } finally {
      this.bntAssinar.setEnabled(true);
      this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
  }

}
