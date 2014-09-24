package cricketworldcup2011;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import cricketworldcup2011.CricketWorldCup2011ServiceStub.GetLastBall;
import cricketworldcup2011.CricketWorldCup2011ServiceStub.GetMatchDetails;

public class service1dataAdaptor implements serviceDataExtract
{
	public static CricketWorldCup2011ServiceStub Service1;
	String[] extractMatchIDs;
	String[] extractFieldNames;
	String inputID;
	String[] extractBallData;
	String[] extractMatchDetails;
	public service1dataAdaptor(String inputID) throws AxisFault
	{   this.inputID=inputID;
	    extractFieldNames=new String[11];
	    extractBallData=new String[11];
		Service1 = new CricketWorldCup2011ServiceStub();
		
	}
	
	@Override
	public String[] extractMatchIDs() throws RemoteException
	{
		// TODO Auto-generated method stub
		 extractMatchIDs = Service1.getMatchIDS().get_return();

		return extractMatchIDs;
	}

	@Override
	public String[] extractgetBallFieldLabels() throws RemoteException {
		// TODO Auto-generated method stub
		String[] tempFieldNames = Service1.getBallFields().get_return();
		extractFieldNames[0]="innings";//innings
		extractFieldNames[1]="over_and_ball";//over_and_ball
		extractFieldNames[2]="bowler";
		extractFieldNames[3]="batsman";//
		extractFieldNames[4]="runs";//
		extractFieldNames[5]="extra";
		extractFieldNames[6]="isBoundary";//
		extractFieldNames[7]="isWicket";//
		extractFieldNames[8]="comment";
		extractFieldNames[9]="runs";//
		extractFieldNames[10]="wickets";//
		
		return extractFieldNames;
	}

	@Override
	public String[] extractgetLastBall() throws RemoteException {
		// TODO Auto-generated method stub
		 System.out.println("change format ");
		GetLastBall LastBallextract = new GetLastBall();
		LastBallextract.setMatchID(this.inputID);
		String[] tempBallData = Service1.getLastBall(LastBallextract).get_return();
		extractBallData[0]=tempBallData[0];//innings
		System.out.println("temp balldata is "+tempBallData[0]);
		System.out.println("extractBallData[0] is "+extractBallData[0]);
		String over_and_ball=tempBallData[1]+"."+tempBallData[2];
		extractBallData[1]=over_and_ball;//over_and_ball
		extractBallData[2]=tempBallData[3];//bowler
		extractBallData[3]=tempBallData[4];//batsman
		extractBallData[4]=tempBallData[5];//runs
		extractBallData[5]=tempBallData[6];//extra
		extractBallData[6]=tempBallData[7];//isB
		extractBallData[7]=tempBallData[8];//isW
		extractBallData[8]=tempBallData[9];//comment
		int Location=tempBallData[10].indexOf("/");
		
		
		System.out.println("inning is "+extractBallData[0]);
	    String currentruns=tempBallData[10].substring(Location+1,tempBallData[10].length());
		extractBallData[9]=currentruns;//runs
		
		extractBallData[10]="null";//wickets
			
			
		return extractBallData;
	}

	@Override
	public String[] extractgetMatchDetails() throws RemoteException {
		// TODO Auto-generated method stub
		GetMatchDetails MatchDetailsextract = new GetMatchDetails();
		MatchDetailsextract.setMatchID(this.inputID);
		extractMatchDetails = Service1.getMatchDetails(MatchDetailsextract).get_return();

		return extractMatchDetails;
	}

}
