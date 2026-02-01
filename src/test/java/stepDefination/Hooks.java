package stepDefination;

import java.io.IOException;

import AIAssistance.AIAnalyzer;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hooks {
	
	@Before("@DeleteProducts")
	
	public void beforeScenario() throws IOException
	
	{
		stepDefination1 m= new stepDefination1();
		
		m.the_user_can_able_to_delete_from_server();
		m. user_select_with_http_request_and_with_given("DeleteProduct", "DELETE", 195);
		
	}
	
	@After()
	public void tearDown(Scenario scenario)
	{
		//Only if the tests fail, the AI bot will run
		if(scenario.isFailed())
		{
			System.out.println("❌ Scenario Failed: " +scenario.getName());
			System.out.println("🤖 AI is analyzing the failure... please wait.");
			
		}
		
		// 2. Call the AI utility we created earlier
        // Pass the path to your log file (make sure this matches your project)
		String logPath = "loggingAPI.txt"; 
        String aiAnalysis = AIAnalyzer.getRootCause(logPath);
	
	// 3. Attach the AI analysis to the Cucumber Report
    // This makes the AI feedback visible in your HTML/Extent reports!
    scenario.log("\n--- 🤖 AI ROOT CAUSE ANALYSIS ---\n");
    scenario.log(aiAnalysis);
    scenario.log("\n----------------------------------\n");
	
 // Also print to console for immediate visibility
    System.err.println("AI Analysis: " + aiAnalysis);
	
} 

}
