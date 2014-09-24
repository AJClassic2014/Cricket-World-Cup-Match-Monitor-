package userwindow;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import match.Observer;
import match.matchUniform;
/*
 * Class is a monitor window for one match to be selected and monitored by user
 * and activated when user press the monitor button from the control window interface
 *  monitor window will show all the related information about one match according to
 *  field names and match information from match details
 */
//construct monitor window interface 
public class MonitorWindow extends JFrame implements abstractMonitor, WindowListener 
{

	private matchUniform MatchInfo;
	private ControlWindow ControlWindow;
	private JPanel MatchDetails, CloseButton;
	private JButton Close;
	private JTable MatchDataTable;
	private Object CellData[][];
	private Object Column[] = {"Fields", "BallData"};
	
	public MonitorWindow(matchUniform matchInfo2, ControlWindow controlWindow)
	{
		setTitle(matchInfo2.getMatchDetails()[1] + "vs" + matchInfo2.getMatchDetails()[2]);
		
		this.MatchInfo = matchInfo2;
		System.out.println("create monitor for match");
		matchInfo2.attach(this);
		
		CellData = new Object[11][2];
		
		for(int i=0; i < this.MatchInfo.MatchDataFields.length; i++)//put all the information according to the field names
		{
			CellData[i][0] = this.MatchInfo.MatchDataFields[i];
			CellData[i][1] = this.MatchInfo.MatchData[i];
		}
		
		this.ControlWindow = controlWindow;
		
		Close = new JButton("close");
		MatchDetails = new JPanel();
		CloseButton = new JPanel();
		
		MatchDataTable = new JTable(CellData, Column);
		MatchDataTable.setRowHeight(20);
		
		for(int i=0; i < this.MatchInfo.getMatchDetails().length; i++)
		{
			MatchDetails.add(new JLabel(this.MatchInfo.getMatchDetails()[i]));
		}
		
		CloseButton.add(Close);
		Close.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				closeActionPerformed(e);
			}
		});
		
		add(CloseButton, BorderLayout.SOUTH);
		add(MatchDetails, BorderLayout.NORTH);
		add(new JScrollPane(MatchDataTable), BorderLayout.CENTER);
		addWindowListener(this);
		
	}
	
	public void update(String[] MatchDataFields, String[] MatchData)  //inherit from observer,to update all the information according to the field names 
	{
		for(int i=0; i < this.MatchInfo.MatchDataFields.length; i++)
		{
			CellData[i][0] = this.MatchInfo.MatchDataFields[i];
			CellData[i][1] = this.MatchInfo.MatchData[i];
			MatchDataTable.repaint();
		}
		System.out.println("finish update ");
	}
	
	public void createWindow()
	{ System.out.println("create monitor window");
		for(int i=0; i < this.MatchInfo.MatchDataFields.length; i++)
		{
			CellData[i][0] = this.MatchInfo.MatchDataFields[i];
			CellData[i][1] = this.MatchInfo.MatchData[i];
			MatchDataTable.repaint();//use to update the table contents according to the field names information 
		}
		
		setVisible(true);
		setSize(1000, 500);
	}
	
	private void closeActionPerformed(ActionEvent e) //activated by pressing the close button
	{
		MatchInfo.detach(this);
		setVisible(false);
		ControlWindow.MatchInfoList.remove(MatchInfo);
		ControlWindow.currentMonitorState();
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		//use to further extension
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) //equal to press the close button
	{
		MatchInfo.detach(this);
		setVisible(false);
		ControlWindow.MatchInfoList.remove(MatchInfo);
		ControlWindow.currentMonitorState();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	

}
