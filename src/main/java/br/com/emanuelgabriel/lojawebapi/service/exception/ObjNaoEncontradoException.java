package br.com.emanuelgabriel.lojawebapi.service.exception;

public class ObjNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjNaoEncontradoException(String msg) {
		super(msg);
	}

}
