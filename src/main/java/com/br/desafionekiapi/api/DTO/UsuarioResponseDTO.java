package com.br.desafionekiapi.api.DTO;

public class UsuarioResponseDTO {

	private Integer id;

	private String login;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public UsuarioResponseDTO(Integer id, String login) {
		super();
		this.id = id;
		this.login = login;
	}

	public UsuarioResponseDTO() {

	}

}
