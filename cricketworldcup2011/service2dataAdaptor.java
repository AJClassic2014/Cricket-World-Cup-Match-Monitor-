package cricketworldcup2011;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import cricketworldcup2011.CricketWorldCup2011ServiceTwoStub.GetLastBall;
import cricketworldcup2011.CricketWorldCup2011ServiceTwoStub.GetMatchDetails;

public class service2dataAdaptor implements serviceDataExtract
{
	public static CricketWorldCup2011ServiceTwoStub Service2;
	String[] extractMatchIDs;
	String[] extractFieldNames;
	String inputID;
	String[] extractBallData;
	String[] extractMatchDetails;
	public service2dataAdaptor(String inputID) throws AxisFault
	{   this.inputID=inputID;
	   
		Service2 = new CricketWorldCup2011ServiceTwoStub();
		
	}
	@Override
	public String[] extractMatchIDs() throws RemoteException {
		// TODO Auto-generated method stub
		 extractMatchIDs = Service2.getMatchIDs().get_return();
		
		return extractMatchIDs;
	}

	@Override
	public String[] extractgetBallFieldLabels() throws RemoteException {
		// TODO Auto-generated method stub
		extractFieldNames = Service2.getBallFieldLabels().get_return();
		return extractFieldNames;
	}

	@Override
	public String[] extractgetLastBall() throws RemoteException {
		// TODO Auto-generated method stub
		GetLastBall LastBallextract2 = new GetLastBall();
		LastBallextract2.setMatchID(this.inputID);
		extractBallData = Service2.getLastBall(LastBallextract2).get_return();
		return extractBallData;
	}

	@Override
	public String[] extractgetMatchDetails() throws RemoteException {
		// TODO Auto-generated method stub
		GetMatchDetails MatchDetailsextract2 = new GetMatchDetails();
		MatchDetailsextract2.setMatchID(this.inputID);
		extractMatchDetails = Service2.getMatchDetails(MatchDetailsextract2).get_return();
		return extractMatchDetails;
	}

}
