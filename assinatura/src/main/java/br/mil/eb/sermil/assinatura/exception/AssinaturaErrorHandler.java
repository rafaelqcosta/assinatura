package br.mil.eb.sermil.assinatura.exception;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

@Component
public class AssinaturaErrorHandler implements ResponseErrorHandler{

	private boolean error = false;

	@Override
	public void handleError(ClientHttpResponse resp) throws IOException {
		this.error = true;
		throw new IOException(resp.getStatusText());
	}

	@Override
	public boolean hasError(ClientHttpResponse resp) throws IOException {
		return this.error;
	}

}