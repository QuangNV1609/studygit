package com.quangnv.uet.dto.extend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
	private String customerId;
	private String name;
	private Integer age;
	private String address;
	private String email;
	private String phone;
	private Boolean gender;
	private ImageDto image;
}
