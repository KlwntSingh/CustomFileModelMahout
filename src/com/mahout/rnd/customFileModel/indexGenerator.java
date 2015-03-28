package com.mahout.rnd.customFileModel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.mahout.cf.taste.impl.common.FastByIDMap;

import com.google.common.base.Charsets;

public class indexGenerator {
	
	storeIndexedData storeindexeddata ;
	
	public indexGenerator(){
		storeindexeddata = new storeIndexedData();
	}
	
	 public long toLongID(String stringID) {
			long idInLong = 0; 
		    try {
				idInLong = hash(stringID);
				if(storeindexeddata.toStringID(idInLong) == null){
					storeindexeddata.storeMappingInMemory(idInLong, stringID);
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return idInLong;
		  }
	  

	/**
	   * @return most significant 8 bytes of the MD5 hash of the string, as a long
	 * @throws NoSuchAlgorithmException 
	   */
	 
  protected final long hash(String value) throws NoSuchAlgorithmException {
	MessageDigest md5Digest = MessageDigest.getInstance("MD5");
    byte[] md5hash;
    synchronized (md5Digest) {
      md5hash = md5Digest.digest(value.getBytes(Charsets.UTF_8));
      md5Digest.reset();
    }
    long hash = 0L;
    for (int i = 0; i < 8; i++) {
      hash = hash << 8 | md5hash[i] & 0x00000000000000FFL;
    }
    return hash;
  }
  
  protected FastByIDMap<String> getIndexedMapInstansce(){
	  return storeindexeddata.getIndexedMap();
  }
		  
}
