package AIAssistance;

import java.nio.file.Files;
import java.nio.file.Paths;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.github.cdimascio.dotenv.Dotenv;

public class AIAnalyzer {
	
	// 1. Define the 'Contract' between Java and AI  
    interface QualityAssistant {
        @SystemMessage("""
            You are an expert Backend QA Automation Engineer. 
            Analyze the provided Rest Assured API logs. 
            Focus on Status Codes, Error Messages, and Header mismatches.
            Provide a concise 2-line Root Cause Analysis (RCA).
            """)
        String analyzeLog(@UserMessage String logContent);
    }
	
    public static String getRootCause(String logFilePath) {
        try {
            // 2. Initialize the Gemini Model
        	
        	Dotenv dotenv= Dotenv.load();
        	
         	String myApiKey = dotenv.get("GEMINI_API_KEY");
         	
         	if(myApiKey==null)
         	{
         	    // Fallback for .env file if System.getenv is empty
         		myApiKey = Dotenv.load().get("GEMINI_API_KEY");
         		
         	}
        	
            GoogleAiGeminiChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(myApiKey) // Put your key here
                .modelName("gemini-2.5-flash")
                .build();

            // 3. Create the AI Service
            QualityAssistant assistant = AiServices.create(QualityAssistant.class, model);

            // 4. Read the logs from your local file (loggingAPI.txt)
            String content = new String(Files.readAllBytes(Paths.get(logFilePath)));

            // 5. Get the magic answer
            return assistant.analyzeLog(content);
        } catch (Exception e) {
            return "AI Analysis failed: " + e.getMessage();
        }
    }
}
