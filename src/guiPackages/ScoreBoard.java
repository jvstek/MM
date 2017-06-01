package guiPackages;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.swing.JLabel;
import javax.swing.JPanel;

import HelpingClasses.ScoreBoardRow;

public class ScoreBoard extends JPanel {
// name // score //difficultie //time (when there is time) 
	JLabel label; 
	ScoreBoardRow SBR; 
	public ScoreBoard() {
		 JLabel lab1 = new JLabel("User Name", JLabel.LEFT);
		 add(lab1);
		  SBR = new ScoreBoardRow("teste", 1, 1, "t2");
		 SBR.getClass().getFields();
//		 for (Field test : SBR.getClass().getDeclaredFields()) {
//		
//			 
//			 System.out.println(test.get);
//			
//		} 
//		 for(Method method : SBR.getClass().getMethods()){
//			 Object value = method;
//			 value.toString();
//			 if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3)))
//		        {
//		            if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase()))
//		            {
//		                // MZ: Method found, run it
//		                try
//		                {
//		                    return method.invoke(SBR.getClass());
//		                }
//		                catch (IllegalAccessException e)
//		                {
//		                   System.out.println("Could not determine method: " + method.getName());
//		                }
//		                
//
//		            }
//		        }
//			 
//		 }
		 //baselineResult.GetType().GetProperties())
		// TODO Auto-generated constructor stub
		// file reader read all information
		// Organize the information
		// display the information
	}
}
