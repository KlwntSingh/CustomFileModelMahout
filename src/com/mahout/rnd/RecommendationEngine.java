package com.mahout.rnd;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.math.hadoop.similarity.cooccurrence.measures.PearsonCorrelationSimilarity;

import com.mahout.rnd.customFileModel.PaxcomFileDataModel;
import com.mahout.rnd.customFileModel.retrieveDataFromIndex;


public class RecommendationEngine {
	
	public static void main(String s[]) {
		RecommendationEngine recommendationEngine = new RecommendationEngine();
		recommendationEngine.getRecommendation();
	}
	
	public void getRecommendation(){

		DataModel dataModel = null;
		
		try {
			
			File file = new File("Data/data.csv");
			
			 dataModel = new PaxcomFileDataModel(file);
			 
			 // to retrieve the string or real data from indexes which are generated by custom file model
			 retrieveDataFromIndex dataretriever;
			 
			 // the map of long to string(real data) will be in - memory you can even implement it to write the indexes in file and you read file later 
			 // if want to changes from indexes to string.
			 dataretriever = new retrieveDataFromIndex(((PaxcomFileDataModel) dataModel).getIndexedMapInstansce());
			 
//			ItemSimilarity sim = new LogLikelihoodSimilarity(dm);
			UserSimilarity us =  new EuclideanDistanceSimilarity(dataModel);
			ItemSimilarity sim = new TanimotoCoefficientSimilarity(dataModel);
			GenericUserBasedRecommender ubr = new GenericUserBasedRecommender(dataModel, null, us);
			final GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dataModel, (ItemSimilarity) sim);
			
			
			for(LongPrimitiveIterator items = dataModel.getItemIDs(); items.hasNext();) {
				long itemId = items.nextLong();
				List<RecommendedItem>recommendations = recommender.mostSimilarItems(itemId, 5);    // major step which find similiar items for itemId
				for(RecommendedItem recommendation : recommendations) {
					System.out.println(dataretriever.getDataBack(itemId) + "," + dataretriever.getDataBack(recommendation.getItemID()) + "," + recommendation.getValue());
					}		
			  }
		} catch (IOException e) { 
			System.out.println("There was an error.");
			e.printStackTrace();
		} catch (TasteException e) {
			System.out.println("There was a Taste Exception");
			e.printStackTrace();
		}
	}
}
