package org.fire.api.dto;

import org.fire.api.dto.base.core.ResDto;

import lombok.Data;

@Data
public class LoginOutput implements ResDto {
	String token;
}
