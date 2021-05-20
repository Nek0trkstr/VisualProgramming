package aspects;

import java.util.ArrayList;
import java.util.HashMap;

public class MSDSPOStaticInfo {

	private static HashMap<String,HashMap<Integer,String>> interactionsLifelines=null;
	private static HashMap<String,HashMap<Integer,String>> interactionsVariables=null;


	public static HashMap<String,HashMap<Integer,String>>
		getInteractionLifelines()
	{
		if(interactionsLifelines==null){
			buildInteractionsLifelines();
		}
		return interactionsLifelines;
	}

	private static void buildInteractionsLifelines()
	{
		interactionsLifelines=new HashMap<String,HashMap<Integer,String>>();
		HashMap<Integer,String> lifelines = null;

		lifelines = new HashMap<Integer,String>();
		lifelines.put(0,"dog");
		lifelines.put(1,"owner");
		interactionsLifelines.put("LSC3", lifelines);

		lifelines = new HashMap<Integer,String>();
		lifelines.put(0,"owner");
		lifelines.put(1,"house");
		lifelines.put(2,"dog");
		interactionsLifelines.put("LSC2", lifelines);

		lifelines = new HashMap<Integer,String>();
		lifelines.put(0,"stranger");
		lifelines.put(1,"house");
		lifelines.put(2,"dog");
		interactionsLifelines.put("LSC1", lifelines);

		lifelines = new HashMap<Integer,String>();
		lifelines.put(0,"stranger");
		lifelines.put(1,"house");
		lifelines.put(2,"owner");
		lifelines.put(3,"dog");
		interactionsLifelines.put("LSC4", lifelines);
	}


	public static HashMap<String,HashMap<Integer,String>>
		getInteractionVariables()
	{
		if(interactionsVariables==null){
			buildInteractionsVariables();
		}
		return interactionsVariables;
	}

	private static void buildInteractionsVariables()
	{
		interactionsVariables=new HashMap<String,HashMap<Integer,String>>();
		HashMap<Integer,String> variables = null;
	}

}

