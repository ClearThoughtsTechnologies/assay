package com.clearthoughts.clearcampus.assay.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.clearthoughts.clearcampus.assay.exception.AssayException;
import com.clearthoughts.clearcampus.assay.model.Question;
import com.clearthoughts.clearcampus.assay.repository.TestPaperRepositoryImpl;

public class QuestionFileUtil {
	private static Logger logger = LoggerFactory.getLogger(TestPaperRepositoryImpl.class);

	public static final int NO_OF_COLUMN_IN_EXCEL = 10;

	public static List<Question> generateQuestionFromFile(MultipartFile file) {
		if (isExcelType(file)) {

		}
		return null;
	}

	private static List<Question> extractQuesitonFromStream(FileInputStream stream) throws IOException {

		HSSFWorkbook workbook = new HSSFWorkbook(stream);

		// Get first/desired sheet from the workbook
		HSSFSheet sheet = workbook.getSheetAt(0);

		// Iterate through each rows one by one
		Iterator<Row> rowIterator = sheet.iterator();
		List<Question> questionList = new ArrayList<>();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Question q = extractQuesitonFromRow(row);
			if (q == null) {
				continue;
			}
			questionList.add(q);
		}
		
		return questionList;

	}

	private static Question extractQuesitonFromRow(Row row) {
		// If questiojn text == blank, then do not consider the text
		if (row.getCell(1) == null) {
			return null;
		}
		Question q = new Question();

		q.setQuestionText(getStringValueOfCell(row.getCell(1)));

		String questionTypeText = getStringValueOfCell(row.getCell(2));
		if (StringUtils.isEmpty(questionTypeText)) {
			return null;
		} else {
			if (questionTypeText.equalsIgnoreCase("TRUE/FALSE")) {
				q.addOptionText("TRUE");
				q.addOptionText("FALSE");
			} else {
				for (int i = 3; i <= 8; i++) {
					Cell c = row.getCell(i);
					if (c != null) {
						String cellValue = getStringValueOfCell(c);
						if (!StringUtils.isEmpty(cellValue)) {
							q.addOptionText(cellValue);
						}
					}
				}
			}
		}

		String answerText = getStringValueOfCell(row.getCell(9));
		if (StringUtils.isEmpty(answerText)) {
			return null;
		} else {
			q.setAnswerText(answerText);
		}

		return q;
	}

	private static String getStringValueOfCell(Cell cell) {

		if (cell == null) {
			return null;
		}
		CellType cellType = cell.getCellType();
		switch (cellType) {
		case STRING:
			return cell.getStringCellValue();

		case BOOLEAN:
			return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
		default:
			return cell.getStringCellValue();
		}
	}

	private static boolean isExcelType(MultipartFile file) {
		Tika tika = new Tika();
		try {
			String mimeType = tika.detect(file.getInputStream());
			if (mimeType.contains("excel") || mimeType.contains("EXCEL")) {
				return true;
			}
		} catch (IOException e) {
			logger.error("Unable to detect type of uploaded file", e);
			throw new AssayException(
					"Unable to detect mime type of uploaded file. Nested error message is : " + e.getMessage(), e);
		}
		return false;
	}

	/*
	 * public static void main(String[] args) throws IOException { Tika tika = new
	 * Tika(); String detect = tika.detect(new
	 * File("/Users/mriganka/Downloads/QuestionSheet.xls"));
	 * System.out.println(detect); extractQuesitonFromStream(new FileInputStream(new
	 * File("/Users/mriganka/Downloads/QuestionSheet.xls"))); }
	 */
}
