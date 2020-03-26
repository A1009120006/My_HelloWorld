package org.fire.server.model1.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.fire.api.dto.DemoInput;
import org.fire.api.dto.DemoOutput;
import org.fire.api.dto.base.core.ResDto;
import org.fire.server.model1.service.IDemoService;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements IDemoService{

	@Override
	public List<ResDto> returnCollection(DemoInput input) {
		List<ResDto> result = new ArrayList<>();
		DemoOutput output = new DemoOutput();
		output.setName("name");
		DemoOutput output1 = new DemoOutput();
		output1.setName("name");
		result.add(output);
		result.add(output1);
		return result;
	}

	@Override
	public List<ResDto> returnCollection(List<DemoInput> input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResDto returnDto(DemoInput input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResDto returnDto(List<DemoInput> input) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
