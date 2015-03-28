package com.mahout.rnd.customFileModel;

import org.apache.mahout.cf.taste.impl.common.FastByIDMap;

public class retrieveDataFromIndex {
	
	FastByIDMap<String> map = null;
	
	public retrieveDataFromIndex(FastByIDMap<String> dataMap) {
		map = dataMap;
	}
	
	public String getDataBack(Long id){
		return map.get(id);
	}

}
