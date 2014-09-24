package userwindow;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cricketworldcup2011.CricketWorldCup2011ServiceStub;
import cricketworldcup2011.CricketWorldCup2011ServiceTwoStub;



import match.matchUniform;
/*
 * Class is used to construct the application user interface for client
 * (want to monitor match dynamically),user can choose the match and related information
 * through user interface
 */
public class ControlWindow extends JFrame implements ItemListener, ActionListener
{ //construct the user interface
	ArrayList<matchUniform> MatchInfoList;
	ArrayList<matchUniform> GMatchInfoList;
	private JComboBox MatchList;
	private JLabel Title;
	private JTextArea TextArea;
	private JButton Monitor,graphMonitor,Instruction;
	private String[] MatchIDs;
	private String[] GMatchIDs;
	private String MatchID;
	private static CricketWorldCup2011ServiceStub CWCService;
	public static CricketWorldCup2011ServiceTwoStub CWCService2;
	ControlWindow() throws RemoteException
	{   
	    
		setTitle("Cricket World Cup 2011 Monitor");
		setLayout(new FlowLayout());
		Title = new JLabel("Cricket World Cup 2011 Monitor");
		TextArea = new JTextArea(25, 25);
		TextArea.setText("user should read instruction information at first " +
				      "\n application treat two sets of matches" +
				      "\n(433XXX and 310XXX) as two separate tournaments");
		
		MatchInfoList = new ArrayList<matchUniform>();
		GMatchInfoList = new ArrayList<matchUniform>();
		CWCService = new CricketWorldCup2011ServiceStub();
		CWCService2 = new CricketWorldCup2011ServiceTwoStub();
		MatchIDs = CWCService.getMatchIDS().get_return();
		GMatchIDs = CWCService2.getMatchIDs().get_return();
		
		MatchList = new JComboBox();
		
		for(int i=0; i < MatchIDs.length; i++)
		{
			CricketWorldCup2011ServiceStub.GetMatchDetails getMatchDetails = new CricketWorldCup2011ServiceStub.GetMatchDetails();
			getMatchDetails.setMatchID(MatchIDs[i]);
			String[] matchDetails = CWCService.getMatchDetails(getMatchDetails).get_return();
			MatchList.addItem(MatchIDs[i] + " " + matchDetails[1] + " vs " + matchDetails[2]);
		}
		for(int i=0; i < GMatchIDs.length; i++)
		{
			CricketWorldCup2011ServiceTwoStub.GetMatchDetails getGMatchDetails = new CricketWorldCup2011ServiceTwoStub.GetMatchDetails();
			getGMatchDetails.setMatchID(GMatchIDs[i]);
			String[] matchDetails = CWCService2.getMatchDetails(getGMatchDetails).get_return();
			MatchList.addItem(GMatchIDs[i] + " " + matchDetails[1] + " vs " + matchDetails[2]);
		}
		Monitor = new JButton("Monitor");
		graphMonitor = new JButton("Run-Rate Monitor");
		Instruction = new JButton("Instruction");
		
		Monitor.addActionListener(this);
		graphMonitor.addActionListener(this);
		Instruction.addActionListener(this);
		MatchList.addItemListener(this);
		
		add(Title, BorderLayout.CENTER);
		add(new JScrollPane(TextArea), BorderLayout.NORTH);
		add(MatchList);
		add(Monitor);
		add(graphMonitor);
		add(Instruction, BorderLayout.EAST);
		
		setSize(400, 700);
		setVisible(true);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		validate();
	}
	//user actions include
	//1.press monitor button
	//2.press instruction button
	//3.default close
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == Monitor)//press monitor button, add the match monitor in the list
		{
			boolean exist = false;
			matchUniform matchInfo = null;
			
			if(MatchID == null)//press monitor button before choose one match
			{
				TextArea.setText("\n Please select a match " +
						"\n input should not be null");
			}
			
			if(MatchID != null)//user selected one match
			{
				TextArea.setText("\n Monitor window for " + MatchID + " is loading");
				
				for(int i=0;i<this.MatchInfoList.size();i++)
				{ matchUniform m=this.MatchInfoList.get(i);
					if (m.getMatchID() == MatchID)
					{
						matchInfo = m;
						exist = true;
					}
				}
				
				if(!exist)
				{
					try
					{
						matchInfo = new matchUniform(MatchID);
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
					
					MatchInfoList.add(matchInfo);
					
					this.currentMonitorState();
				}			
				abstractMonitor monitorWindow=new MonitorWindow(matchInfo,this);
				monitorWindow.createWindow();
			}
		}
		else if(e.getSource() == graphMonitor)
		{
			boolean graphExist = false;
			matchUniform gmatchInfo = null;
			
			if(MatchID == null)//press monitor button before choose one match
			{
				TextArea.setText("\n Please select a match for monitoring Run-Rate" +
						"\n input should not be null");
			}
			
			if(MatchID != null)//user selected one match
			{
				TextArea.setText("\nRun-Rate Monitor window for " + MatchID + " is loading");
				
				for(int i=0;i<this.GMatchInfoList.size();i++)
				{ matchUniform gm=this.GMatchInfoList.get(i);
					if (gm.getMatchID() == MatchID)
					{
						gmatchInfo = gm;
						graphExist = true;
					}
				}
				
				if(!graphExist)
				{
					try
					{
						gmatchInfo = new matchUniform(MatchID);
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
					
					GMatchInfoList.add(gmatchInfo);
					
					this.currentMonitorState();
				}			
				abstractMonitor gmonitorWindow=new GMonitorWindow(gmatchInfo,this);
				gmonitorWindow.createWindow();
			}
		}
		else if(e.getSource() == Instruction)//press instruction button,user should read at first
		{
			TextArea.append("\nFor match(310XXX or 433XXX)" +
					        "\n1. Please select a match at first before pressing monitor button\n" +
							"2. Press Monitor button for(310XXX or 433XXX),monitor window will be activated" +
							"\n and you can get related match information  \n" +
							"3. Monitor will be updated every 30 seconds \n" +
							"4. you can monitor more than one match(open more than one window at the same time)\n"+
							"5. Press Close button for closing one match monitor\n"+
							"\nFor match(310XXX or 433XXX)\n"+
							"1. Please select a match at first before pressing monitor button"+
							"\n2. Press Monitor button for(310XXX or 433XXX),graph monitor window will be activated"+
							"\n3. Graph Monitor will be updated every 5 seconds\n"+
							"4. you can monitor more than one match(open more than one window at the same time)"+
							"\n5. Press Close button for closing one match monitor\n");
		}
	}
	
	public void itemStateChanged(ItemEvent e)//activated when user select the match from the match list
	{
		String name = MatchList.getSelectedItem().toString();
		MatchID = name.substring(0, 6);
		TextArea.setText("\n want to monitor match " + MatchID+" : "+
				"\n please press the monitor button" +
				"\n for match with the starting number as 3(for graph monitor)," +
				"\n please press the run-rate monitor button");
		
	}
	
	public void currentMonitorState()
	{
		TextArea.append("\n Current Match list: \n");
	
		for(int i=0;i<this.MatchInfoList.size();i++)
		{matchUniform m=this.MatchInfoList.get(i);
			TextArea.append(m.getMatchID() + "\n");
		}
		for(int i=0;i<this.GMatchInfoList.size();i++)
		{
			matchUniform m=this.GMatchInfoList.get(i);
			TextArea.append(m.getMatchID() + "\n");
		}
		
	}
	
	public static void main(String args[]) throws RemoteException
	{
		new ControlWindow();
	}
	
	
	
	
}
