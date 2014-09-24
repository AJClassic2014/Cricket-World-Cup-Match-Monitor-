package cricketworldcup2011;

import java.rmi.RemoteException;

public interface serviceDataExtract 
{
	public String[]  extractMatchIDs() throws RemoteException;
	public  String[] extractgetLastBall() throws RemoteException;
	public String[]  extractgetBallFieldLabels() throws RemoteException;
    public String[] extractgetMatchDetails() throws RemoteException;

           
}
  