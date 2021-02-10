package com.oday.aerospike;

import org.json.simple.JSONObject;

import com.aerospike.client.Record;

/**
 * JSONRecord is used to convert an Aerospike Record
 * returned from the cluster to JSON format
 * @author peter
 *
 */
@SuppressWarnings("serial")
public class JSONRecord extends JSONObject {
	@SuppressWarnings("unchecked")
	public JSONRecord(Record record){
		put("generation", record.generation);
		put("expiration", record.expiration);
		put("bins", new JSONObject(record.bins));
	}

}
