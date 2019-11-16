To run this project, you can directly run a docker instance. Follow the steps below and the dockerize container will start running along with a mongodb instance. No need to build the project.

1> Install docker and create an id in hub.docker.com
2> In the current directory you would have checked out the docker-compose.yml file.
3> Run the command->  docker-compose up
4> Spring boot applicaton will be running in 8082 port. http://localhost:8082
5> Now read the following part and try to access the URLs mentioned below

The SWAGGER UI plugin does not work with Spring boot  HATEOAS 1.0.+. (We need to use HATEOAS 1+ as Spring first time standardise the HATEOAS implementation. The have renamed all their old classes). If it is used, the the LinkProvider conflicts with Multiple LinkProvider bean. Spring people are telling, Swagger need to fix this issue from their side. However, any solid fix has not come. Some workaround are there, but finally they also did not work 100%. The final answer is, either you have to use HATEOAS or Swagger. I choose HATEOAS as thats the target. I have mentioned all the API and sample data below. However, during the project start, the project will insert some dummy data. So any get call will provide all possible sample values. Like, if any field can have multiple options in it, deriving all the sample data will show all possible values of that field.
The ID of each document is not autoincremented value. Which may be duplicated. Later point of time Unique ID generation will be implemented using another distributed system.

API for quesiton:
GET :
	/assay/questionbank/{questionBankId}/question - Return array of questions. All questions under the question bank
	/assay/questionbank/{questionBankId}/question/{questionId} - Return quesiton by Id
		{
		  "_id": "string",
		  "_links": {
		    "empty": true
		  },
		  "answerText": "string",
		  "options": [
		    "string"
		  ],
		  "questionBankId": "string",
		  "questionText": "string",
		  "questionType": "MULTI"
		}

POST:
	/assay/questionbank/{questionBankId}/question
	Request Body:
		{ 
		  "answerText": "Delhi",
		  "options": [
		    "Kolkata","Chennai", "Delhi", "Mumbai"
		  ],
		  "questionText": "What is the capital of India",
		  "questionType": "MULTI"
		}
	Another example:
		{ 
		  "answerText": "Delhi",
		  "options": [
		    "Kolkata","Chennai", "Delhi", "Mumbai"
		  ],
		  "questionText": "What is the capital of India",
		  "questionType": "BOOLEAN"
		}
	
PUT:
	/assay/questionbank/{questionBankId}/question
		Request body:
			{
			  "_id": "string",
			  "answerText": "string",
			  "options": [
			    "string"
			  ],
			  "questionBankId": "string",
			  "questionText": "string",
			  "questionType": "MULTI"
			}

DELETE:
	/assay/questionbank/{questionBankId}/question - Delete all quesiton from question bank
	/assay/questionbank/{questionBankId}/question/{questionId} - Delete the quesiton by quesiton id
	
	


API for Question Bank :

GET:
	/assay/questionbank/{questionBankId} - Returns info about question bank
	Sample value
	{
	  "_id": "string",
	  "_links": {
	    "empty": true
	  },
	  "companyId": "string",
	  "description": "string",
	  "name": "string",
	  
	}
	
	/assay/questionbank/company/{companyId} - Returns all question bank for the company


POST:
	/assay/questionbank
		Sample:
			{
			  "_id": "string",
			  
			  "companyId": "string",
			  "description": "string",
			  "name": "string",
			}
PUT:
	/assay/questionbank
		{
		  "_id": "string",
		  "_links": {
		    "empty": true
		  },
		  "companyId": "string",
		  "description": "string",
		  "name": "string",
		  "questionIds": [
		    "string"
		  ]
		}
		
DELETE:
	/assay/questionbank/{questionBankId}




Test Paper API:

GET:
	/assay/testpaper/{testPaperId}
	Sample Data:
	{
	  "_id": "string",
	  "_links": {
	    "empty": true
	  },
	  "appearance": {
	    "displayAllQuestion": true,
	    "questionSequence": "random",
	    "showPreviousAllowed": true
	  },
	  "attempt": {
	    "attemptTimesAllowed": 0,
	    "delayAfterEachAttempt": 0
	  },
	  "companyId": "string",
	  "description": "string",
	  "evaluation": {
	    "negativeMarksPerQuestion": 0,
	    "passMarks": 0,
	    "totalMarks": 0,
	    "totalQuestion": 0
	  },
	  "name": "string",
	  "publicTestPaper": true,
	  "questions": [
	    {
	      "_id": "string",
	      "answerText": "string",
	      "options": [
	        "string"
	      ],
	      "questionBankId": "string",
	      "questionText": "string",
	      "questionType": "MULTI"
	    }
	  ],
	  "resultDeclare": {
	    "resultAppearance": "AfterQuestion",
	    "resultFormat": "PASS"
	  },
	  "resultFormat": "PASS"
	}
	
	/assay/testpaper/company/{companyId} - Return all the test papers for a company (Pagination is not applied)
	
	
POST:
	/assay/testpaper
		Sample data:
			{
			  
			  "appearance": {
			    "displayAllQuestion": true,
			    "questionSequence": "random",
			    "showPreviousAllowed": true
			  },
			  "attempt": {
			    "attemptTimesAllowed": 0,
			    "delayAfterEachAttempt": 0
			  },
			  "companyId": "string",
			  "description": "string",
			  "evaluation": {
			    "negativeMarksPerQuestion": 0,
			    "passMarks": 0,
			    "totalMarks": 0,
			    "totalQuestion": 0
			  },
			  "name": "string",
			  "publicTestPaper": true,
			  "questions": [
			    {
			      "_id": "string",
			      "answerText": "string",
			      "options": [
			        "string"
			      ],
			      "questionBankId": "string",
			      "questionText": "string",
			      "questionType": "MULTI"
			    }
			  ],
			  "resultDeclare": {
			    "resultAppearance": "AfterQuestion",
			    "resultFormat": "PASS"
			  },
			  "resultFormat": "PASS"
			}

PUT: 
	/assay/testpaper
		Sample data:
			{
			  "_id": "string",
			  "_links": {
			    "empty": true
			  },
			  "appearance": {
			    "displayAllQuestion": true,
			    "questionSequence": "random",
			    "showPreviousAllowed": true
			  },
			  "attempt": {
			    "attemptTimesAllowed": 0,
			    "delayAfterEachAttempt": 0
			  },
			  "companyId": "string",
			  "description": "string",
			  "evaluation": {
			    "negativeMarksPerQuestion": 0,
			    "passMarks": 0,
			    "totalMarks": 0,
			    "totalQuestion": 0
			  },
			  "name": "string",
			  "publicTestPaper": true,
			  "questions": [
			    {
			      "_id": "string",
			      "answerText": "string",
			      "options": [
			        "string"
			      ],
			      "questionBankId": "string",
			      "questionText": "string",
			      "questionType": "MULTI"
			    }
			  ],
			  "resultDeclare": {
			    "resultAppearance": "AfterQuestion",
			    "resultFormat": "PASS"
			  },
			  "resultFormat": "PASS"
			}

DELETE:
	/assay/testpaper/{testPaperId}