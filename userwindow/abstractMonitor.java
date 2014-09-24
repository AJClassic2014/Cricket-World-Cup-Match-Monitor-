package userwindow;

import match.Observer;

public abstract interface abstractMonitor extends Observer
{
	public void update(String[] MatchDataFields, String[] MatchData);
	
	

	public void createWindow();
}
