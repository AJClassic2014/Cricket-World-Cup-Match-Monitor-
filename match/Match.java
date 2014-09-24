package match;
//(subject in observer pattern, implemented by MatchInformation)
public interface Match {

	public void attach(Observer o);
	
	public void detach(Observer o);
	
	public void notify(String[] MatchDataFields, String[] MatchData);
	
}
