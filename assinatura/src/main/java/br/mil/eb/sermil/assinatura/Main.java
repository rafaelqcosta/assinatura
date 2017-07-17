package br.mil.eb.sermil.assinatura;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import br.mil.eb.sermil.assinatura.controller.LoginController;
import br.mil.eb.sermil.assinatura.util.Util;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Classe inicial para comecar a aplicacao
 * 
 * Initial class where the whole process begins
 * 
 * @author Anselmo S Ribeiro <anselmo.sr@gmail.com>
 * @version 1.0
 * @since 1.0
 *
 */
@Configuration
public class Main extends Application {

	/*
	 * Metodo usado para compatibilizar a utilizacao em ambiente de
	 * desenvolvimento
	 */
	static {
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
			public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
				if (hostname.equals("localhost")) {
					return true;
				}
				return false;
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings("resource")
	@Override
	public void start(Stage arg0) {

		// Iniciando contexto de injecao de dependencia
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		try {
			// Metal Nimbus CDE/Motif Windows Windows Classic
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						/*
						 * ************************** Aqui a aplicacao inicia:
						 ****************************/
						context.getBean(LoginController.class).setVisible(true);
						context.close();
					} catch (Exception e) {
						java.util.logging.Logger.getLogger(LoginController.class.getName())
								.log(java.util.logging.Level.SEVERE, null, e);
						Util.displayErrorMsg(e.getMessage());
						System.exit(0);
					}
				}
			});
		} catch (Exception ex) {
			Util.displayErrorMsg(ex.getMessage());
			System.exit(0);
		}
	}
}