package com.clearthoughts.clearcampus.assay.util;

import java.util.concurrent.atomic.AtomicLong;

public class UniqueIdGenerator {
	/* This long variable need to be deleted */
	private static AtomicLong counter = new AtomicLong(1L);

	public static enum IdType {
		QUESTION, QUESTION_BANK, TEST_PAPER;
	}

	public static String generateUniqueId(IdType type) {
		if (type == null) {
			return null;
		}
		switch (type) {
		case QUESTION:
			return generateUniqueIdForQuestion();

		case QUESTION_BANK:
			return generateUniqueIdForQuestionBank();

		case TEST_PAPER:
			return generateUniqueIdForTestPaper();

		default:
			return null;
		}
	}

	private static String generateUniqueIdForQuestion() {
		return String.valueOf(counter.getAndIncrement());
	}

	private static String generateUniqueIdForQuestionBank() {
		return String.valueOf(counter.getAndIncrement());
	}

	private static String generateUniqueIdForTestPaper() {
		return String.valueOf(counter.getAndIncrement());
	}

}
