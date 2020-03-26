package org.fire.server.model1.service;

import java.util.List;

import org.fire.api.dto.DemoInput;
import org.fire.api.dto.base.core.ResDto;

public interface IDemoService {
	
	List<ResDto> returnCollection(DemoInput input);
	
	List<ResDto> returnCollection(List<DemoInput> input);
	
	ResDto returnDto(DemoInput input);
	
	ResDto returnDto(List<DemoInput> input);
	
}
