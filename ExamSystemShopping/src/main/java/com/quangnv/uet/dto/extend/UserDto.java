package com.quangnv.uet.dto.extend;

import java.util.Set;

import com.quangnv.uet.dto.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class UserDto extends BaseDto {
	private String userId;
	private String username;
	private String password;
	
	private CustomerDto customer;
	private Set<RoleDto> roleDtos;
}
