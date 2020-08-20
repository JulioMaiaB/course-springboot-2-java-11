package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.educandoweb.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant momemnt;
	private Integer orderStatus;
	//O tratamento como Integer (orderstatus) e apenas para o mundo interno (classe e DB). Para o mundo externo e um OrderStatus

	@ManyToOne
	@JoinColumn(name = "client_id")
	private User client;

	public Order(Long id, Instant momemnt, OrderStatus orderStatus, User client) {
		this.id = id;
		this.momemnt = momemnt;
		setOrderStatus(orderStatus);
		this.client = client;
	}
	
	// Getters e Setters personalizados para o tipo Enum. 
	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}
	
	public void setOrderStatus(OrderStatus orderStatus) {
		if(orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}
}
