package match;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import cricketworldcup2011.service1dataAdaptor;
import cricketworldcup2011.service2dataAdaptor;
import cricketworldcup2011.serviceDataExtract;




public class matchUniform implements Match
{   public serviceDataExtract dataExtract;
	public ArrayList<Observer> ObserverList;//store all the match information in the list
	private String MatchID;//use to make sure which match
	public String[] MatchDataFields;//field names, such as inning,ballno. and bowler,batsman... 
	public String[] MatchData;//ball data to provide concrete information for field names
	private String[] MatchDetails;//get match further information, such as which match,location and date
	public int updateNo=1;
	public matchUniform(String MatchID) throws Exception///initialize all the attributes
	{   this.MatchID = MatchID;
	    if(this.MatchID.charAt(0)=='4')
	    {   dataExtract = new service1dataAdaptor(this.MatchID);
	    }
	    if(this.MatchID.charAt(0)=='3')
	    {
	    	dataExtract = new service2dataAdaptor(this.MatchID);
	    }
		ObserverList = new ArrayList<Observer>();
		
		System.out.println("Monitor for "+this.MatchID+" created");
		update();//the match information should be updated every 30 sec
		
		Timer refresh = new Timer();
		refresh.schedule(new TimerTask()
		{
			public void run()
			{
				try {
					update();
				} 
				catch (Exception e)
				{ 
					e.printStackTrace();
				}
			}
		}, 5000, 5000);
	}
	public void update() throws RemoteException//update related information, include all the ball data information
	{
		this.MatchDataFields = dataExtract.extractgetBallFieldLabels();
		  
		
		
		

		
		MatchData = dataExtract.extractgetLastBall();
		
		
		//to make sure which match to be updated
		this.MatchDetails = dataExtract.extractgetMatchDetails();
		if(this.MatchID.charAt(0)=='3')
		{   System.out.println("Service2 Monitor for "+this.MatchID+" Updated");
			notify(MatchDataFields, MatchData);}//notify and update all the observers interface monitoring this game
		if(this.MatchID.charAt(0)=='4')
		{   
			if(updateNo<=5)
			{
				System.out.println("Service1 update data,not update monitor "+updateNo);
				updateNo=updateNo+1;
			}
			else
			{   System.out.println("update monitor for 30sec"+updateNo);
				notify(MatchDataFields, MatchData);
				updateNo=1;
			}
		}
	}
	public String getMatchID()//get match ID for the match being monitored currently
	{
		return this.MatchID;
	}
	
	public String[] getMatchDetails()//get match details for the match being monitored currently 
	{
		return this.MatchDetails;
	}
	public String[] getMatchData() {
		// TODO Auto-generated method stub
		return this.MatchData;
	}
	public String[] getDataFields()
 	{
 	   return this.MatchDataFields;
 	}
	public void attach(Observer observer) //add new observer to observer list(inherit from subject)
	{   System.out.println("add new monitor for "+this.getMatchID()+" to oberver list ");
		ObserverList.add(observer);
	}

	public void detach(Observer observer)//delete observer from observer list(inherit from subject)  
	{   System.out.println("delete monitor for "+this.getMatchID()+" from oberver list ");
		ObserverList.remove(observer);
	}
	public void notify(String[] MatchDataFields, String[] MatchData)//notify and update all the observers match information(inherit from subject) 
	{
		for(int i=0;i<ObserverList.size();i++)
		{   System.out.println(" update observer "+i);
			Observer member=ObserverList.get(i);
			member.update(this.MatchDataFields, this.MatchData);//make a connection and synchronization through notify and update method
		}
			
	}
	
}
