package org.fire.api.dto;

import org.fire.api.dto.base.core.ReqDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DemoInput implements ReqDto {
	String id;
	String name;
}
