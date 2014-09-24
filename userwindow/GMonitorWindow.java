package userwindow;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import match.Match;

import match.Observer;
import match.matchUniform;

public class GMonitorWindow extends JFrame implements abstractMonitor, WindowListener  
{
   private matchUniform GraphMInfo;
   private ControlWindow ControlWindow;
   private ArrayList<String> runList;
   private ArrayList<String> overList;
   private ArrayList<Double> runsperOver;
   public String BallDataGraph;
   private String matchID;
   private JButton Close;
   private JPanel CloseButton;
   XYDataset xydataset;
   XYSeries xyseries,xyseries1;
   Container chartFrame;
   public GMonitorWindow(matchUniform GraphMInfo, ControlWindow controlWindow)
   {   super("match "+GraphMInfo.getMatchDetails()[1]+" VS "+GraphMInfo.getMatchDetails()[1]+" run-rate monitor");
	   this.GraphMInfo=GraphMInfo;
	   this.ControlWindow = controlWindow;
	   this.matchID=GraphMInfo.getMatchID();
	   this.GraphMInfo.attach(this);
	   runList=new ArrayList<String>();
	   overList=new ArrayList<String>();
	   runsperOver=new ArrayList<Double>();
	   setTitle("Graph monitor for Match "+this.matchID);
	   
	   Close = new JButton("close");
	   CloseButton = new JPanel();
	   CloseButton.add(Close);
		Close.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				closeActionPerformed(e);
			}
		});
		
		add(CloseButton, BorderLayout.SOUTH);
	   xydataset=createDataset();
	   JFreeChart dynamicGraph=createChart(xydataset);
	   ChartPanel chartPanel=new ChartPanel(dynamicGraph);
	   chartFrame=this.getContentPane();
	   chartFrame.add(chartPanel,BorderLayout.CENTER);
	   addWindowListener(this);
	  
	  
	   
	   
	   }
   
   
   public void createWindow()
   {  System.out.println("create graph monitor window");
	    setVisible(true);
		setSize(1000, 500);
   }
   
   protected void closeActionPerformed(ActionEvent e) 
   {
	// TODO Auto-generated method stub
	   this.GraphMInfo.detach(this);
		setVisible(false);
		ControlWindow.GMatchInfoList.remove(GraphMInfo);
		ControlWindow.currentMonitorState();
	
    }




public void update(String[] MatchDataFields, String[] MatchData)
   {   String updated=this.GraphMInfo.getMatchData()[1];
       
       if(this.overList.size()==0)//initialize the overList
       {
    	   this.overList.add(updated);
       }
       
       String updatedRuns=this.GraphMInfo.getMatchData()[9];
       
       if(this.runList.size()==0)//initialize the runList
       {
    	   this.runList.add(updatedRuns);
       }
       
       String current=this.overList.get(overList.size()-1);//current over comparing with updated over
       
	   int updatedLocation=updated.indexOf(".");
	   int currentLocation=current.indexOf(".");
       String updatedOver=updated.substring(0,updatedLocation);
       String currentOver=current.substring(0,currentLocation);
       
	    if(updatedOver.equals(currentOver))//over have not changed
	    {   char updatedBallNo=updated.charAt(updatedLocation+1);
	        char currentBallNo=current.charAt(currentLocation+1);
	        System.out.println("new ball "+updatedBallNo+" and "+" old ball "+currentBallNo);
	    	if(currentBallNo!=updatedBallNo)//ball number changed,update runs for the same over
	    	{
	    		this.overList.remove(overList.size()-1);
	    		this.overList.add(updated);
	    		System.out.println("over is updated to "+this.overList.get(overList.size()-1));
	    	}
	    	
	    	
	    }
	    
	    else //over changed, calculate run-rate value
	    {   this.overList.add(updated);//add new over 
            this.runList.add(updatedRuns);//add new runs for new over
	    	double runRate = 0;
	    	
	    	
	    	
	    	this.runsperOver.add(Double.parseDouble(this.runList.get(this.runList.size()-1))-Integer.parseInt(this.runList.get(this.runList.size()-2)));
	    	
	    	
	    	//calculate run-rate
	    	if(this.runsperOver.size()>=3)
	    	{ 
	          runRate=(this.runsperOver.get(this.runsperOver.size()-1)+this.runsperOver.get(this.runsperOver.size()-2)+this.runsperOver.get(this.runsperOver.size()-3))/3;
	    	}
	    	else
	    	{
	    		for(int i=0;i<this.runsperOver.size();i++)
	    		{   
	    			runRate=runRate+this.runsperOver.get(i);
	    		}
	    		runRate=runRate/this.runsperOver.size();
	    	}
	    	 double runsforeach=this.runsperOver.get(this.runsperOver.size()-1);
	    	 System.out.println("///////////////////////over changed////////////////////////////");
	    	 System.out.println("runs for over "+this.overList.get(overList.size()-2)+" is"+runsforeach);
	    	 System.out.println("run-rate for over "+this.overList.get(overList.size()-2)+" is"+runRate);
	         xyseries.add(Double.parseDouble(overList.get(overList.size()-1)), runRate);
	         xyseries1.add(Double.parseDouble(overList.get(overList.size()-1)),runsforeach);

	    	 
	        
	        
	        
	    }
	    
   }
   
   
   
   private XYDataset createDataset()
   {   
	   xyseries = new XYSeries("run-rate of match");
	   xyseries1=new XYSeries("Runs for each over");
	   update(this.GraphMInfo.getDataFields(),this.GraphMInfo.getMatchData());
	   XYSeriesCollection xyseriescollection = new XYSeriesCollection(); 
	   xyseriescollection.addSeries(xyseries);
	   xyseriescollection.addSeries(xyseries1);
	   return xyseriescollection;

   }
   
   public JFreeChart createChart(XYDataset xydataset)
   {
	   JFreeChart jfreechart = ChartFactory.createXYLineChart(


			   "Graph moinitor for run-rate",
			   "over",
			   "run-rate",
			   xydataset,
			   PlotOrientation.VERTICAL,
			   true,
			   true,
			   false);
	   jfreechart.setBackgroundPaint(Color.white);
	   XYPlot xyplot = (XYPlot)jfreechart.getPlot(); 
	   xyplot.setBackgroundPaint(Color.lightGray); 
	   xyplot.setDomainGridlinePaint(Color.white); 
	   XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)
	   xyplot.getRenderer();
	   xylineandshaperenderer.setShapesVisible(true); 
	   xylineandshaperenderer.setShapesFilled(true); 
       NumberAxis numberaxis = (NumberAxis)xyplot.getRangeAxis();
       numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
       return jfreechart;


	   
	   
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
		this.GraphMInfo.detach(this);
		setVisible(false);
		ControlWindow.GMatchInfoList.remove(GraphMInfo);
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
