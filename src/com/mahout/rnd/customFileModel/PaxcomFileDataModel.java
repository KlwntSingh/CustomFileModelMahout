package com.mahout.rnd.customFileModel;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.codehaus.jackson.map.ser.std.InetAddressSerializer;

import com.google.common.base.Charsets;

public class PaxcomFileDataModel extends FileDataModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	indexGenerator indexgenerator;
	

	public PaxcomFileDataModel(File dataFile) throws IOException {
		super(dataFile);
	}
	
	@Override
	protected long readItemIDFromString(String value) {
		if(indexgenerator == null){
			indexgenerator= new indexGenerator();
		}
		return Long.parseLong(indexgenerator.toLongID(value)+"");
	}
	
	@Override
	protected long readUserIDFromString(String value) {
		if(indexgenerator == null){
			indexgenerator= new indexGenerator();
		}
		return Long.parseLong(indexgenerator.toLongID(value)+"");
	}
	
	public FastByIDMap<String> getIndexedMapInstansce(){
		return indexgenerator.getIndexedMapInstansce();
	}
	
}
