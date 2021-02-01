package com.quangnv.uet.dto.extend;

import com.quangnv.uet.dto.BaseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ColorDto extends BaseDto{
	private String name;
}
