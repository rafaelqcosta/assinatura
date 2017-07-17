package br.mil.eb.sermil.assinatura.exception;

import java.io.IOException;

/**
 * Exceção Web Service CPFSippes.
 * 
 * @author Abreu Lopes
 * @since 5.1.0
 * @version 5.4.6
 */
public class AssinaturaIOException extends IOException {

	private static final long serialVersionUID = 1L;

	public AssinaturaIOException() {
		super();
	}

	public AssinaturaIOException(String message, Throwable cause) {
		super(message, cause);
	}

	public AssinaturaIOException(String message) {
		super(message);
	}

	public AssinaturaIOException(Throwable cause) {
		super(cause);
	}
	
	

}
