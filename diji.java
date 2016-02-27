import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;

import javax.swing.*;
import java.util.*;

public class diji extends JFrame{
	diji()
	{
		super("Shotrtest Path");
		add(new dijigui());
		setSize(600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);                  
		
	}
	
	public static void main(String []args)
	{
		EventQueue.invokeLater(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				diji shortest = new diji();
				shortest.setVisible(true);
			}
			
		});
	}
	
}
class dijigui extends JPanel
{
	JButton NRect;
	Rectangle2D.Float S1;
	Rectangle2D.Float S2;
	JButton genM;
	Rectangle2D one,two;
	int rects=0;
	boolean boo;
	int s ,s1,s2;
	JRadioButton line,FP;
	boolean findpath;
	Lineobj l;
	ArrayList<Rectangle2D.Float> rect;
	ArrayList<Integer> Path;
	
	//ArrayList<Line2D.Float> Lines;
	ArrayList<Lineobj> Lines;
	int[][] countmat;
	
	int i;
	dijigui(){
		 i=10;
		NRect = new JButton("New Vertex");
		genM = new JButton("Show New Path");
		line =  new JRadioButton("Lines");
		FP = new JRadioButton("Select Vertices");
		countmat = new int[50][50];
		for(int i=0;i<20;i++)
		 {
			 for(int j= 0;j<20;j++)
			 {
				countmat[i][j] = 0;
				countmat[i][i] = 0;
			 }
		 }
		line.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==1)
				{
					boo=true;
				}
				else{
					boo=false;
				}
				
			}
			
		});
		FP.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==1)
				{
					findpath=true;
					
				}
				else{
					findpath=false;
					
						S1=S2=null;
						Path = new ArrayList<Integer>();
						repaint();
					
				}
				
			}
			
		});
		rect = new ArrayList<Rectangle2D.Float>();
		
		Lines = new ArrayList<Lineobj>();
		NRect.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				rect.add(new Rectangle2D.Float(i, i, 30, 30));
				
				repaint();
			}
			
		});
		genM.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				genMat();
			}
			
		});
		
		add(NRect);
		
		add(line);
		add(genM);
		add(FP);
		addMouseListener(new MouseAdapter(){ 
			int i = 0;
			public void mousePressed(MouseEvent event) {
				
				
				
				if(findpath)
				{
					if(s==0)
					{
						for(int i=0;i<rect.size();i++)
						{
							Point p = event.getPoint();
							
							Rectangle2D on = (Rectangle2D.Float)rect.get(i);
							if(on.contains(p)){
								s1=i;
								S1=(Float) on;
								s++;
								
								repaint();
								
							}
							
						}
					}
					else if(s==1)
					{
						for(int i=0;i<rect.size();i++)
						{
							Point p = event.getPoint();
							
							Rectangle2D on = (Rectangle2D.Float)rect.get(i);
							if(on.contains(p)){
								s2=i;
								S2=(Float) on;
								s = 0;
								
								repaint();
								
								
							}
							
						}
						
					}
					
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				if(rects==0 && boo)
				{
					
					for(int i=0;i<rect.size();i++)
					{
						Point p = event.getPoint();
						
						Rectangle2D on = (Rectangle2D.Float)rect.get(i);
						if(on.contains(p)){
							
							l = new Lineobj();
							
							l.one=(Float)on;
							rects++;
							break;
						}
					}
				}
				else if(rects==1 && boo)
				{
					
					//System.out.println("in two");
					
					for(int i=0;i<rect.size();i++)
					{
						Point p = event.getPoint();
						Rectangle2D on = (Rectangle2D.Float)rect.get(i);
						if(on.contains(p)){
							l.two=(Float) on;
							
							rects++;
							break;
						}
					}
					
					//System.out.println("three");
					int x1=(int) l.one.getX();
					int x2=(int) l.two.getX();
					int y1=(int) l.one.getY();
					int y2=(int) l.two.getY();
					rects=0;
					if((int)(Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2))) != 0)
					Lines.add(l);
					repaint();
				}
				
				
			}
			@Override
			public void mouseReleased(MouseEvent event) {
		    	
		    }
			@Override
			 public void mouseDragged(MouseEvent event) {
				
				
			 }
			
		});
		addMouseMotionListener(new MouseAdapter(){ 
			int i = 0;
			Rectangle2D.Float temp;
			@Override
			public void mousePressed(MouseEvent event) {
				
				
			}
			@Override
			public void mouseReleased(MouseEvent event) {
		    	
		    }
			@Override
			 public void mouseDragged(MouseEvent event) {
				
				for(int i=0;i<rect.size();i++)
				{
					Point p = event.getPoint();
					temp = (Rectangle2D.Float)rect.get(i);
					if(temp.contains(p)){
						temp.x= (float) p.getX()-15;
						temp.y=(float)p.getY()-15;
						break;
						
					}
				}
				repaint();
			 }
			
		});
		}
	
	public void doDrawing(Graphics g){
	
		Graphics2D g2 = (Graphics2D)g;
		
		Integer car =0 ; 
		
		
		
		for(int i=0;i<Lines.size();i++)
		{
			
			int x1=(int) Lines.get(i).one.getX();
			int x2=(int) Lines.get(i).two.getX();
			int y1=(int) Lines.get(i).one.getY();
			int y2=(int) Lines.get(i).two.getY();
			/*if(findpath && Path != null)
			{
				for(int j=0;j<Path.size()-1;j++)
				{
					if(((rect.get(Path.get(j))== Lines.get(i).one)||(rect.get(Path.get(j+1))== Lines.get(i).one)) && (rect.get(Path.get(j))== Lines.get(i).two)||(rect.get(Path.get(j+1))== Lines.get(i).two)){
						g2.setColor(Color.green);
					}
					else
					{
						g2.setColor(Color.black);
					}
				}
			}*/
			
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(3));
			g2.draw(new Line2D.Double(x1+15,y1+15, x2+15, y2+15));
			Integer in = new Integer((int) Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2)));
			g2.setColor(Color.blue);
			g.setFont(new Font("TimesRoman", Font.BOLD, 12)); 
			g2.drawString(in.toString(), (x1+x2)/2, (y1+y2)/2);
			g2.setColor(Color.black);
		}
		if(findpath && Path != null && S1 !=null){
			for(int i = 0;i<Path.size()-1;i++)
			{
				
				int x1 = (int) (rect.get(Path.get(i)).getX()+15);
				int y1 = (int) (rect.get(Path.get(i)).getY()+15);
				int x2 = (int) (rect.get(Path.get(i+1)).getX()+15);
				int y2 = (int) (rect.get(Path.get(i+1)).getY()+15);
				g2.setColor(Color.green);
				 g2.setStroke(new BasicStroke(3));
				 g2.draw(new Line2D.Double(x1, y1, x2, y2));
				
			}
		}
		for(int i=0;i<rect.size();i++)
		{
			if(rect.get(i)==S1 || rect.get(i)==S2)
			{
				g2.setColor(Color.blue);
				//System.out.println("blue");
				
			}
			else{
			g2.setColor(Color.black);
			}
			g2.fill((Rectangle2D.Float)rect.get(i));
			g2.setColor(Color.red);
			g2.drawString(car.toString(), (int)rect.get(i).getX()+12, (int)rect.get(i).getY()+19);
			car++;
		}
		
		
		
	}
		
	 @Override
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        
	        doDrawing(g);
	    }
	 
	 public void genMat()
	 {
		 
		 
		 for(int i=0;i<Lines.size();i++)
		 {
			 int a=0;
			 int b=0;
			 for(int j=0;j<rect.size();j++)
			 {
				 if(Lines.get(i).one==rect.get(j))
				 {
					 a=j;
				 }
				 if(Lines.get(i).two==rect.get(j))
				 {
					 b=j;
				 }
				 
			 }
			 double x1=rect.get(a).getX();
			 double y1 = rect.get(a).getY();
			 double x2=rect.get(b).getX();
			 double y2 = rect.get(b).getY();
			 countmat[a][b] = (int) Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
			 countmat[b][a]= (int) Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
			 
		 }
		 	Im  findP  = new Im ();
		 	 
		 	Path = findP.path(countmat,s1,s2,rect.size());
		 	
		 	repaint();
		 /*for(int i=0;i<rect.size();i++)
		 {
			 for(int j= 0;j<rect.size();j++)
			 {
				 System.out.print(countmat[i][j] + "  ");
			 }
			 System.out.println();
		 }*/
	 }
 }



class Lineobj 
{
	Rectangle2D.Float one;
	Rectangle2D.Float two;
}
	
	

