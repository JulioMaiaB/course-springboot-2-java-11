package com.educandoweb.course.entities.enums;

public enum OrderStatus {

	WAITING_PAYMENT(1), PAID(2), SHIPPED(3), DELIVERED(4), CANCELED(5);

	// Atributos do Enum.
	private int code;

	// Construtor: por padrao privado.
	private OrderStatus(int code) {
		this.code = code;
	}
	
	// Getter para deixar o atributo acessivel para as outras classes.
	public int getCode() {
		return this.code;
	}

	// Converte um valor numero para o valor Enumerado.
	public static OrderStatus valueOf(int code) {
		//OrderStatus.values() percorre todos os valores Enumerados (PAID, SHIPPED...).
		for (OrderStatus value : OrderStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		// Lanca uma excecao caso um numero inexistente seja informado.
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}

}
