package Lin.Warn;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.TimerTask;


	public class DateTask extends TimerTask {
        private int numA;
        
        public DateTask(int numA) {
        	this.numA=numA;
        }
		public String toString() {
	      LocalDateTime localDateTime = LocalDateTime.now();
    	  return "任務時間:" + localDateTime.plusSeconds(numA);
		
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
    	
}
