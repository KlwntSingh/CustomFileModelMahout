package com.mahout.rnd.customFileModel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.*;
import org.apache.mahout.cf.taste.model.IDMigrator;

import com.google.common.base.Charsets;

public  class storeIndexedData  {
	
	 private final FastByIDMap<String> longToString;
		
	  protected storeIndexedData() {
	    this.longToString = new FastByIDMap<String>(100);
	  }
    
	  public void storeMappingInMemory(long longID, String stringID) {
	    synchronized (longToString) {
	      longToString.put(longID, stringID);
	    }
	  }
	  
	  public String toStringID(long longID) {
	    synchronized (longToString) {
	      return longToString.get(longID);
	    }
	  }
	  
	  public FastByIDMap<String> getIndexedMap(){
		 
		  return longToString;
	  }
 
}