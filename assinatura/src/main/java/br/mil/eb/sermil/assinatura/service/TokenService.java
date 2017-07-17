package br.mil.eb.sermil.assinatura.service;

import java.awt.TrayIcon.MessageType;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JOptionPane;

import org.demoiselle.signer.core.keystore.loader.KeyStoreLoader;
import org.demoiselle.signer.core.keystore.loader.factory.KeyStoreLoaderFactory;
import org.demoiselle.signer.signature.signer.factory.PKCS7Factory;
import org.demoiselle.signer.signature.signer.pkcs7.PKCS7Signer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Destinada a implementar todas as acoes relativas a assinatura digital em si.
 * 
 * @author ANSLEMO S RIBEIRO <anselmo.sr@gmail.com>
 *
 */
@Service
public class TokenService extends SermilService {

	public static final Logger log = LoggerFactory.getLogger(CommunicationService.class);

	private String cpf;
	private String jwtToken;

	public void assinar(List<String> indices)
			throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException {
		PKCS7Signer signer = PKCS7Factory.getInstance().factoryDefault();
		KeyStoreLoader ksLoader = KeyStoreLoaderFactory.factoryKeyStoreLoader();
		KeyStore ks = ksLoader.getKeyStore();

		Enumeration<String> aliases;
		aliases = ks.aliases();

		String alias = (String) aliases.nextElement();
		alias = (String) aliases.nextElement();
		System.out.println(alias);

		Certificate res = ks.getCertificate(alias);
		PrivateKey privateKey = (PrivateKey) ks.getKey(alias, null);

		Certificate[] certificateChain = ks.getCertificateChain(alias);

		signer.setCertificates(certificateChain);
		signer.setPrivateKey(privateKey);
		byte[] assinatura = signer.doSign("".getBytes());
	}

	private String getCpfFromToken() {
		// TODO implementar pegar cpf do token
		return JOptionPane.showInputDialog(JOptionPane.getRootFrame(), "Insira CPF para TESTE", "TESTE TESTE TESTE",
				MessageType.WARNING.ordinal());
	}

	public String getCpf() {
		if (this.cpf == null)
			this.cpf = this.getCpfFromToken();
		return cpf;
	}

}
