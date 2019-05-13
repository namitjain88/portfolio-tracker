package com.springboot.mongodb.utility;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.springboot.mongodb.document.CustomSequences;

@Service
public class NextSequenceService {
	@Autowired
	private MongoOperations mongo;

	public Long getNextSequence(String seqName) {
		CustomSequences counter = mongo.findAndModify(query(where("_id").is(seqName)), new Update().inc("value", 1),
				options().returnNew(true).upsert(true), CustomSequences.class);
		return counter.getValue();
	}
}
