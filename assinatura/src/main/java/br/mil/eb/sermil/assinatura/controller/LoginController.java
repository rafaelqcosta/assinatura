package br.mil.eb.sermil.assinatura.controller;

import java.awt.Cursor;
import java.awt.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.mil.eb.sermil.assinatura.service.CommunicationService;
import br.mil.eb.sermil.assinatura.service.TokenService;
import br.mil.eb.sermil.assinatura.util.Util;
import br.mil.eb.sermil.assinatura.view.LoginFrameDesign;

/**
 * Pelo fato de que a classe LoginFrameDesign e' editada em outra IDE, decidi
 * criar essa classe para separar a view layer: LoginFrameDesign do
 * processamento: essa classe
 * 
 * @author Anselmo S Ribeiro <anselmo.sr@gmail.com>
 *
 */
@Controller
public class LoginController extends LoginFrameDesign {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CommunicationService communicationService;

	@Autowired
	AssinaturaFrame assinaturaFrame;

	@Autowired
	TokenService tokenService;

	/**
	 * Clicar em Entrar
	 */
	@Override
	public void jButton1ActionPerformed(ActionEvent evt) {
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		try {
			// Obter cpf do token
			String cpf = this.tokenService.getCpf();

			if (cpf != null) {

				// Preencher a lista de Certificados
				assinaturaFrame.jList1.setListData(communicationService.getCertificados());

				// Mostrar proxima janela
				this.setVisible(false);
				assinaturaFrame.setVisible(true);
			}
		} catch (Exception e) {
			Util.displayErrorMsg(e.getMessage());
		} finally {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
}
