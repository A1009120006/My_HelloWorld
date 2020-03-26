package org.fire.server.util;

import java.util.List;

import org.fire.api.dto.base.MultiRes;
import org.fire.api.dto.base.SingleRes;
import org.fire.api.dto.base.core.ResDto;

import com.nanning.commonLib.constant.RtnEnum;

public class ApiUtil {
	
	public static MultiRes<ResDto> buildRes(List<ResDto> result){
		MultiRes<ResDto> succMultiResult = new MultiRes<ResDto>(RtnEnum.RTN_000000.getRetCode(), RtnEnum.RTN_000000.getRetMsg());
		succMultiResult.setData(result);
		return succMultiResult;
	}
	
	public static SingleRes<ResDto> buildRes(ResDto result){
		SingleRes<ResDto> succSingleRes = new SingleRes<ResDto>(RtnEnum.RTN_000000.getRetCode(), RtnEnum.RTN_000000.getRetMsg());
		succSingleRes.setData(result);
		return succSingleRes;
	}
	
}
