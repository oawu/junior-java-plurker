import info.clearthought.layout.TableLayout;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Shape;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.jplurk.DateTime;
import com.google.jplurk.PlurkClient;
import com.google.jplurk.PlurkSettings;
import com.google.jplurk.Qualifier;
import com.google.jplurk.exception.PlurkException;
import com.l2fprod.common.swing.JDirectoryChooser;

public class OA_Plurker {
	
	public OA_Plurker(String title,String location){
		/*
		File file=new File(location);
		if(!file.isDirectory()) new InstallationOAPlurker(title,location);
		else new Lonin(title,location);
		*/
		
		try{
			PlurkClient client = new PlurkClient(new PlurkSettings(new File(location+"/jplurk.properties")));
			client.login("OCplurk","xxx1963");
			new MainFrame("OA_Plurker_B_1.0",location,client,client.getOwnProfile());
		}catch (PlurkException e){}
	}
	public class MouseAction implements MouseListener{
		public JLabel state;
		public MouseAction(){
			state = new JLabel();
			state.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(172, 168, 153)));
			state.setFont(new Font("Serif", Font.BOLD, 14));
			state.setForeground(new Color(127,127,127));
			state.setToolTipText("狀態欄位");
			state.setHorizontalAlignment( SwingConstants.CENTER );
			state.addMouseListener(this);
		}
		public JLabel getstate(){return this.state;}
		public void mouseClicked(MouseEvent arg0){}
		public void mouseEntered(MouseEvent arg0){state.setText(((JComponent) arg0.getSource()).getToolTipText());}
		public void mouseExited(MouseEvent arg0){state.setText("");}
		public void mousePressed(MouseEvent arg0){}
		public void mouseReleased(MouseEvent arg0){}
	}
	public class SpeakArrowLabel extends JLabel{
		private Color bordercolor=new Color(0,0,0);
		private String choice=null;
		public SpeakArrowLabel(Color c,String choice){
			bordercolor=c;
			this.choice=choice;
		}
		public SpeakArrowLabel(Color c,String choice,int w,int h){
			bordercolor=c;
			this.choice=choice;
			setIcon(new ImageIcon(new BufferedImage(w, h==0?w:h, BufferedImage.TYPE_INT_RGB)));
		}
		protected void paintComponent(Graphics g){
			g.setColor(new Color(0,0,0,0));
	        g.fillRect(0, 0, getSize().width, getSize().height);
	        
			if(choice.equalsIgnoreCase("left,top")){
		        g.setColor(bordercolor);
		     	int xValues[] = { getWidth()-1,getWidth()-4,getWidth()-6,0,getWidth()-6,getWidth()-6,getWidth()-4,getWidth()-1};
		    	int yValues[] = { 0,2,6,6,getWidth(),getHeight()-6,getHeight()-3,getHeight()-1};
		    	g.drawPolyline(xValues,yValues,xValues.length);
		    	
		    	g.setColor(Color.white);
		    	int xValues2[] = { getWidth(),getWidth()-3,getWidth()-6,1,getWidth()-5,getWidth()-5,getWidth()-4,getWidth()};
		    	int yValues2[] = { 1,2,7,7,getWidth(),getHeight()-6,getHeight()-3,getHeight()-1};
		    	g.fillPolygon(xValues2,yValues2,xValues2.length);
			}
			else if(choice.equalsIgnoreCase("left,bottom")){
				g.setColor(bordercolor);
				int xValues[] = { getWidth()-1,getWidth()-4,getWidth()-6,getWidth()-6,0,getWidth()-6,getWidth()-6,getWidth()-4,getWidth()-1}; 
				int yValues[] = { 0,2,6,getHeight()-6-getWidth(),getHeight()-6-getWidth(),getHeight()-6-6,getHeight()-6,getHeight()-3,getHeight()-1};
				g.drawPolyline( xValues, yValues, xValues.length );
				    	
				g.setColor(Color.white);
				int xValues2[] = { getWidth(),getWidth()-4,getWidth()-5,getWidth()-5,2,getWidth()-5,getWidth()-5,getWidth()-4,getWidth()}; 
				int yValues2[] = { 0,3,5,getHeight()-5-getWidth(),getHeight()-5-getWidth(),getHeight()-5-6,getHeight()-6,getHeight()-3,getHeight()-1};
				g.fillPolygon( xValues2, yValues2, xValues2.length);
			}
			else if(choice.equalsIgnoreCase("right,null")){
				g.setColor(bordercolor);
			    int xValues[] = { 0,2,5,5,3,0};     
			    int yValues[] = { 0,1,5,getHeight()-6,getHeight()-3,getHeight()-1};
			    g.drawPolyline( xValues, yValues, xValues.length );
			   
			    g.setColor(Color.white);
			    int xValues2[] = { 0,3,5,5,3,0};   
			    int yValues2[] = { 1,2,6,getHeight()-6,getHeight()-3,getHeight()-1};
			    g.fillPolygon( xValues2, yValues2, xValues2.length);
			}
			else if(choice.equalsIgnoreCase("null")){}
			else{
				g.setColor(Color.black);
				g.drawString("erreo!", 20, 20);
			}
	    }
	}
	public class WarningTextField extends JTextField{
		private int round=12;
		private Color bordercolor=new Color(0,0,0,0);
		
		public WarningTextField(String d,int r){
			super(d);
			round = r;
			setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(172, 168, 153)));
		}
		public void setbordercolor(Color c){
			bordercolor=c;
		}
		protected void paintComponent(Graphics g){
			g.setColor(this.getBackground());
			g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), round, round);
			g.setColor(this.getForeground());
			g.drawString(this.getText(), 10, (this.getHeight()+this.getFont().getSize())/2-+2);
			g.setColor(bordercolor);
			g.drawRoundRect(0, 0, this.getWidth()-1, this.getHeight()-1, round, round);
		}
	    public boolean contains(int x,int y){
			Shape shape = null;
	        if (shape == null || !shape.getBounds().equals(getBounds())){
	        	shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), round, round);
	        }
	        return shape.contains(x,y);
	    }
	}
	public class PicLabel extends JLabel{
		private int round=12,w=100,h=100;
		private ImageIcon img;
		private Color bordercolor=new Color(0,0,0);
		public PicLabel(ImageIcon img,int w,int h,int r,Color bc,MouseAction m){
			super();
			this.addMouseListener(m);
			
			this.img=img;
			this.w=(w==0?100:w);
			this.h=(h==0?100:h);
			this.round=r;
			this.bordercolor=(bc==null?new Color(0,0,0):bc);
			setIcon(new ImageIcon(new BufferedImage(this.w, this.h, BufferedImage.TYPE_INT_RGB)));
		}
		public PicLabel(ImageIcon img,int w,int h,int r,Color bc){
			super();
			this.img=img;
			this.w=(w==0?100:w);
			this.h=(h==0?100:h);
			this.round=r;
			this.bordercolor=(bc==null?new Color(0,0,0):bc);
			setIcon(new ImageIcon(new BufferedImage(this.w, this.h, BufferedImage.TYPE_INT_RGB)));
			
		}
		protected void paintComponent(Graphics g){
			g.drawImage(img.getImage(), round/4, round/4,w-round/2, h-round/2, this);
		}
	    protected void paintBorder(Graphics g){
		   
	    	Graphics2D g2d = ( Graphics2D ) g; 
	    	g2d.setStroke( new BasicStroke( round/6 ) );
	    	g.setColor(this.bordercolor);
		    g.drawRoundRect(round/6, round/6, w-round/3, h-round/3, round, round);
	    }
	    public boolean contains(int x,int y){
			Shape shape = null;
	        if (shape == null || !shape.getBounds().equals(getBounds())){
	        	shape = new RoundRectangle2D.Float(round/4, round/4,w-round/2, h-round/2, round, round);
	        }
	        return shape.contains(x,y);
	    }
	}
	public class PlurkPanelContent extends JPanel{
		private String tooltiptext="";
		public PlurkPanelContent(MouseAction m){addMouseListener(m);}
		public void setToolTipText(String tooltiptext){this.tooltiptext = tooltiptext;}
		public String getToolTipText(){return this.tooltiptext;}
	}
	public class PlurkPanel extends PlurkPanelContent implements MouseListener{
		private String tooltiptext=null,plurkstringstyle="Serif"
				,string[][]={{"刪除","編輯","喜歡","消音"}
							,{"刪除此噗","編輯此噗","喜歡此噗","消掉此音"}
							,{"編輯區域"}};
		private int head_width=60,head_height=60,round=24,plurkstringsize=10;
		private Color bordercolor = new Color(172, 168, 153);
		
		public PlurkPanel(final PlurkList p,String location,MouseAction m,String myid,final PlurkClient client,final JSONObject plurk,JSONObject plurkuser){
			super(m);
			
			try{
				this.setLayout(new TableLayout(new double[][] {{round/6,TableLayout.FILL,round/6}, {round/6,TableLayout.FILL,round/6}}));
				this.setBackground(new Color(230,230,230));
					
				String plurkname,qualifier;
				if(plurkuser.getJSONObject(plurk.getString("owner_id")).isNull("display_name")) plurkname = plurkuser.getJSONObject(plurk.getString("owner_id")).getString("nick_name");
				else plurkname = plurkuser.getJSONObject(plurk.getString("owner_id")).getString("display_name");//+" ( "+plurkuser.getJSONObject(plurk.getString("owner_id")).getString("nick_name")+" ) ";
				setToolTipText(plurkname);
				tooltiptext = plurkuser.getJSONObject(plurk.getString("owner_id")).getString("nick_name");
				
				if(plurk.isNull("qualifier_translated")) qualifier = plurk.getString("qualifier");
				else qualifier = plurk.getString("qualifier_translated");
				
				plurkname+=(" "+qualifier);
				
				JLabel namelabel = new JLabel(plurkname);
				namelabel.setToolTipText(tooltiptext);
				namelabel.setFont(new Font("Serif", Font.PLAIN, 15));
				namelabel.addMouseListener(this);
				namelabel.addMouseListener(m);
				
				String response = ""+plurk.getString("response_count");
				
				JLabel responselabel = new JLabel(response);
				responselabel.setToolTipText("回應數 : "+response);
				if(!(""+plurk.getString("is_unread")).equalsIgnoreCase("0")){
					responselabel.setForeground(Color.red);
					responselabel.setToolTipText(responselabel.getToolTipText()+"  有新回應!!");
				}
				else responselabel.setForeground(Color.black);
				
				responselabel.setHorizontalAlignment(SwingConstants.CENTER);
				responselabel.setFont(new Font("Serif", Font.BOLD, 15));
				responselabel.addMouseListener(this);
				responselabel.addMouseListener(m);
				
				PlurkPanelContent namepanel = new PlurkPanelContent(m);
				namepanel.setToolTipText(tooltiptext);
				namepanel.setBackground(new Color(0,0,0,0));
				namepanel.setLayout(new TableLayout(new double[][] {{TableLayout.FILL,50}, {TableLayout.FILL}}));
				namepanel.add(namelabel,"0,0,f,f");
				namepanel.add(responselabel,"1,0,f,f");
				namepanel.addMouseListener(this);
				
				JEditorPane poof = setHTML(plurk.getString("content"),"a{text-decoration: none;}","font-family:"+plurkstringstyle+";font-size:"+plurkstringsize+"px;text-decoration: none;",Color.white);// = //new JTextArea(plurk.getString("content"));
				poof.setToolTipText(plurkuser.getJSONObject(plurk.getString("owner_id")).getString("nick_name"));
				poof.setEditable(false);
				poof.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(172, 168, 153)));
				poof.addMouseListener(this);
				poof.addMouseListener(m);
				
				JButton button[] = new JButton[4];
				for(int i = 0 ; i < button.length ; i++){
					button[i] = new JButton(string[0][i]);
					button[i].setToolTipText(string[1][i]);
					button[i].setFont(new Font("Serif", Font.PLAIN, 13));
					button[i].setMargin(new Insets(-1,2,-1,2)); 
					button[i].addMouseListener(this);
					button[i].addMouseListener(m);
				}
				button[0].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						try{
							JSONObject ret = client.plurkDelete(plurk.getString("plurk_id"));
							
							if(ret!=null){
								try{Thread.sleep(3000);}catch (InterruptedException e){}
								p.reset(false,false);
							}
							else{
								JOptionPane.showMessageDialog(null, "系統繁忙!請稍候在試~3Q^^","警告!",1);
							}
							
						}catch (JSONException e){
							JOptionPane.showMessageDialog(null, "DeletePlurk錯誤!請聯絡OA!!","DeletePlurk錯誤!",0);
							System.exit(0);
						}
					}});
				
				
				PlurkPanelContent editpanel = new PlurkPanelContent(m);
				editpanel.addMouseListener(this);
				editpanel.setToolTipText(string[2][0]);
				editpanel.setBackground(new Color(0,0,0,0));
				editpanel.setLayout(new TableLayout(new double[][] {{TableLayout.FILL,40,12,40,12,40}, {TableLayout.FILL}}));
				if(myid.equalsIgnoreCase(plurk.getString("owner_id"))) editpanel.add(button[0],"5,0,c,c");
				/*
				if(myid.equalsIgnoreCase(plurk.getString("owner_id"))){
					editpanel.add(button[0],"1,0,c,c");
					editpanel.add(new JLabel("－"),"2,0,c,f");
					editpanel.add(button[1],"3,0,c,c");
					editpanel.add(new JLabel("－"),"4,0,c,f");
					editpanel.add(button[2],"5,0,c,c");
				}
				else{
					editpanel.add(button[3],"3,0,c,c");
					editpanel.add(new JLabel("－"),"4,0,c,f");
					editpanel.add(button[2],"5,0,c,c");
				}*/
				
				PlurkPanelContent poofpanel = new PlurkPanelContent(m);
				poofpanel.setToolTipText(tooltiptext);
				poofpanel.setBackground(new Color(0,0,0,0));
				poofpanel.setLayout(new TableLayout(new double[][] {{18,TableLayout.FILL,10}, {30,TableLayout.FILL,25}}));
				poofpanel.addMouseListener(this);
				poofpanel.add(new SpeakArrowLabel(new Color(172, 168, 153),"left,top"),"0,1,f,f");
				poofpanel.add(new SpeakArrowLabel(new Color(172, 168, 153),"right,null"),"2,1,f,f");
				poofpanel.add(namepanel,"1,0,f,f");
				poofpanel.add(poof,"1,1,f,f");
				poofpanel.add(editpanel,"1,2,f,f");
				
				PicLabel pic=null;
				//System.out.println(plurk.getString("owner_id"));
				try{
					if(plurkuser.getJSONObject(plurk.getString("owner_id")).get("avatar").toString().equalsIgnoreCase("null")){
						pic= new PicLabel(new ImageIcon(getClass().getResource("image/img.png")),head_width,head_height,12,Color.GRAY,m);
					}
					else{
						String Picname = "";
						if(plurkuser.getJSONObject(plurk.getString("owner_id")).get("avatar").toString().equalsIgnoreCase("0"))
							Picname=plurk.getString("owner_id")+"-big"+".jpg";
						else Picname=plurk.getString("owner_id")+"-big"+plurkuser.getJSONObject(plurk.getString("owner_id")).get("avatar")+".jpg";
							
						if(!new File(checkpath(location+"/pic")+"/"+Picname).exists()){
							BufferedImage i = ImageIO.read(new URL("http://avatars.plurk.com/"+Picname));
							ImageIO.write(i, Picname.substring(Picname.lastIndexOf(".")+1,Picname.length()), new File(location+"/pic/"+Picname));
						}
						pic= new PicLabel(new ImageIcon(location+"/pic/"+Picname),head_width,head_height,12,Color.GRAY,m);
					}
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, "存圖檔錯誤!請聯絡OA!!","存圖檔錯誤!",0);
					System.exit(0);
				}
				//pic= new PicLabel(new ImageIcon(getClass().getResource("image/img.png")),head_width,head_height,12,Color.GRAY,m);
				
				pic.setToolTipText(tooltiptext);
				pic.addMouseListener(this);
				
				PlurkPanelContent picpanel = new PlurkPanelContent(m);
				picpanel.setLayout(new TableLayout(new double[][] {{TableLayout.FILL}, {10,TableLayout.FILL}}));
				picpanel.setToolTipText(tooltiptext);
				picpanel.setBackground(new Color(0,0,0,0));
				picpanel.addMouseListener(this);
				picpanel.add(pic,"0,1,c,t");
				
				PlurkPanelContent showpanel = new PlurkPanelContent(m);
				showpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(172, 168, 153)));
				showpanel.setToolTipText(tooltiptext);
				showpanel.setBackground(new Color(0,0,0,0));
				showpanel.setLayout(new BorderLayout(2,0));
				showpanel.add(picpanel,BorderLayout.WEST);
				showpanel.add(poofpanel,BorderLayout.CENTER);
				showpanel.addMouseListener(this);
				
				this.add(showpanel,"1,1,f,f");
				this.addMouseListener(this);
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, "PlurkPanel錯誤!請聯絡OA!!","PlurkPanel錯誤!",0);
				System.exit(0);
			}	
		}
		public void setBorderColor(Color c){
			this.bordercolor = c;
		}
		protected void paintComponent(Graphics g){
	         g.setColor(getBackground());
	         g.fillRoundRect(0, 0, getSize().width-1, getSize().height-1,round,round);
	    }
		
	    protected void paintBorder(Graphics g){
		    g.setColor(this.bordercolor);
		    g.drawRoundRect(0, 0, getSize().width-1, getSize().height-1, round, round);
	    }
		public boolean contains(int x,int y){
			Shape shape = null;
	        if (shape == null || !shape.getBounds().equals(getBounds())){
	        	shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), round, round);
	        }
	        return shape.contains(x,y);
	    }
		public void mouseClicked(MouseEvent arg0) {
			if(arg0.getClickCount()==2 && !arg0.isMetaDown() && ! arg0.isAltDown()){
				JOptionPane.showMessageDialog(null,"點噗");
			}
		}
		public void mouseEntered(MouseEvent arg0){
			this.setBackground(new Color(245,245,245));
		}
		public void mouseExited(MouseEvent arg0){
			this.setBackground(new Color(230,230,230));
		}
		public void mousePressed(MouseEvent arg0){}
		public void mouseReleased(MouseEvent arg0){}
	}
	public class Music {
		private AudioFormat format;
		private byte[] samples;
		public Music(URL filename) {
			try{
				AudioInputStream stream = AudioSystem.getAudioInputStream(filename);
				format = stream.getFormat();
				samples = getSamples(stream);
			} catch (Exception ex) {}
		}
		public byte[] getSamples(){return samples;}
		private byte[] getSamples(AudioInputStream audioStream) {
			int length = (int) (audioStream.getFrameLength() * format.getFrameSize());

			byte[] samples = new byte[length];
			DataInputStream is = new DataInputStream(audioStream);
			try {is.readFully(samples);}catch (IOException ex){ex.printStackTrace();}
			return samples;
		}
		public void play(InputStream source) {
			int bufferSize = format.getFrameSize()* Math.round(format.getSampleRate() / 10);
			byte[] buffer = new byte[bufferSize];

			SourceDataLine line;
			try {
				DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
				line = (SourceDataLine) AudioSystem.getLine(info);
				line.open(format, bufferSize);
			}catch (LineUnavailableException ex){ex.printStackTrace();return;}
			line.start();
			try {
				int numBytesRead = 0;
				while (numBytesRead != -1) {
					numBytesRead = source.read(buffer, 0, buffer.length);
					if (numBytesRead != -1) {
						line.write(buffer, 0, numBytesRead);
					}
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			line.drain();
			line.close();
		}
	}
	
	public class PlurkList extends JPanel{
		private MouseAction m;
		private String location,myid;
		private PlurkClient client;
		private boolean enable=true,isunread;
		
		private DateTime datetime;
		private int num_plurk=0;
		private boolean ismy=true,ispri=true,isre=true;
		private String key="";
		
		public PlurkList(MouseAction m,String location, DateTime dt,int num_plurk,boolean ismy,boolean ispri,boolean isre,String myid,PlurkClient client){
			this.m=m;
			this.location=location;
			this.myid=myid;
			this.client=client;
			
			this.datetime=dt;
			this.num_plurk=num_plurk;
			this.ismy=ismy;
			this.ispri=ispri;
			this.isre=isre;
			this.isunread=false;
			this.setLayout(new GridLayout(1,1));
			this.setkey();
			
			try {
				this.add(center_panel(client.getPlurks(dt,num_plurk, ismy, ispri,isre )));
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, "PlurkList初始錯誤!請聯絡OA!!","PlurkList初始錯誤!",0);
				System.exit(0);
			}
		}
		public PlurkList(MouseAction m,String location,JSONObject ret ,String myid,PlurkClient client){
			this.m=m;
			this.location=location;
			this.myid=myid;
			this.client=client;
			this.isunread=true;
			this.setLayout(new GridLayout(1,1));
			this.setkey();
			
			try {
				this.add(center_panel(client.getUnreadPlurks()));
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, "PlurkList初始錯誤!請聯絡OA!!","PlurkList初始錯誤!",0);
				System.exit(0);
			}
		}
		public void setkey(){
			if(isunread) key="unread_load_first_time";
			else if(ismy) key="my_load_first_time";
			else if(isre) key="re_load_first_time";
			else if(ispri) key="pri_load_first_time";
			else key="all_load_first_time";
		}
		public boolean getenable(){return enable;}
		
		public void reset(final boolean ismusic,final boolean check){
			if(enable){
				new Thread(new Runnable(){public void run(){
					try {
						enable=false;
						//System.out.println("---------------------start---------------------");
						JSONObject ret;
						if(isunread){
							ret = client.getUnreadPlurks();
						}
						else{
							ret = client.getPlurks(datetime,num_plurk, ismy, ispri,isre );
						}
						JSONArray plurks = ret.getJSONArray("plurks");
						
						if(plurks.length()>0){
							if(!check || Long.parseLong(getSQL(location,"listset",key)) < new Date(plurks.getJSONObject(0).getString("posted")).getTime()){
								if(ismusic && !myid.equalsIgnoreCase(plurks.getJSONObject(0).getString("owner_id"))){
									//System.out.println(myid+"---"+plurks.getJSONObject(0).getString("owner_id")+"\n"+Long.parseLong(getSQL(location,"mainset","load_first_time"))+"-"+new Date(plurks.getJSONObject(0).getString("posted")).getTime());
									new Thread(new Runnable(){public void run(){
										try{new Music(getClass().getResource("config/Gong.wav")).play(new ByteArrayInputStream(new Music(getClass().getResource("config/Gong.wav")).getSamples()));
										}catch (Exception e) {
											JOptionPane.showMessageDialog(null, "Music迴圈 錯誤!請聯絡OA!!","Music迴圈 錯誤!",0);
											System.exit(0);
										}
									}}).start();
								}
								removeAll();
								add(center_panel(ret));
								revalidate();
							}
						}
					}catch (Exception e) {}
					enable=true;
					//System.out.println("---------------------end---------------------");
				}}).start();
				
			}
			else{
				try{
					int a = (new Random().nextInt(5)+1)*1000;
					System.out.println("---------------------sleep_start="+a+"---------------------");
					Thread.sleep(a);
				}catch (InterruptedException e) {}
				System.out.println("---------------------sleep_end---------------------");
				reset(ismusic,check);
				System.out.println("---------------------reset_end---------------------");
			}
		}
		
		public JScrollPane center_panel(JSONObject ret){
			try {
				JPanel backpanel = new JPanel();
				GroupLayout layout = new GroupLayout(backpanel);
				backpanel.setLayout(layout);
				//backpanel.setBackground(new Color(255,255,255));
				layout.setAutoCreateGaps(true);
				layout.setAutoCreateContainerGaps(true);
				
				JSONArray plurks = ret.getJSONArray("plurks");
				
				if(plurks.length()>0){
					setSQL(location,"listset","update listset SET "+key+"='"+ new Date(plurks.getJSONObject(0).getString("posted")).getTime()+"'");
					
					PlurkPanel plurk[] = new PlurkPanel[plurks.length()];
					for(int i = 0 ; i < plurk.length ; i++){
						plurk[i] = new PlurkPanel(this,location,m,myid,client,plurks.getJSONObject(i),ret.getJSONObject("plurk_users"));
					}
							
					GroupLayout.ParallelGroup h = layout.createParallelGroup();
					for(int i = 0 ; i <plurk.length ; i++)h.addComponent(plurk[i], 0, 0, Short.MAX_VALUE);
					layout.setHorizontalGroup(layout.createSequentialGroup().addGroup(h));
					SequentialGroup v = layout.createSequentialGroup();
					for(int i = 0 ; i <plurk.length ; i++)v.addGroup(layout.createSequentialGroup().addComponent(plurk[i], 0, 140, Short.MAX_VALUE));
					layout.setVerticalGroup(v);
				}
				JScrollPane scrollpane = new JScrollPane(backpanel);
				scrollpane.getVerticalScrollBar().setUnitIncrement(plurks.length());
				scrollpane.getVerticalScrollBar().setValue(0);
				scrollpane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(172, 168, 153)));
				
				
				return scrollpane;
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, "center_panel錯誤!請聯絡OA!!"+e,"center_panel錯誤!",0);
				System.exit(0);
				return  new JScrollPane(new JPanel());
			}
		}
	}
	
	
	public class MainFrame extends JFrame{
		public int mainframe_width=500,mainframe_height=700
					,head_width=115,head_height=125,cleartime = 86400000
					,loadtime=5000,num_plurk=0;
		public String location;
		public String myid=null,myfullname=null,account=null,nickname=null,picnum=null
					,place=null,birth=null,gender=null;
		public PlurkClient client;
		
		public MainFrame(String title,String location,final PlurkClient client,JSONObject ret){
			super(title);
			try{
				this.location = location;
				
				TableLayout thisLayout = new TableLayout(new double[][] {{TableLayout.FILL}, {150,TableLayout.FILL,30}});
				thisLayout.setHGap(5);
				thisLayout.setVGap(5);
				this.setLayout(thisLayout);
				
				this.client=client;
				
				if(new Date(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(new Date())).getTime()-(getSQL(location,"mainset","logintime").equalsIgnoreCase("")?0:new Date(getSQL(location,"mainset","logintime")).getTime()) > cleartime){
					deleteTree(checkpath(location+"/pic"));
					setSQL(location,"mainset","update mainset SET logintime='"+new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(new Date())+"'");
				}
				
				ret=ret.getJSONObject("user_info");
				myid=ret.getString("uid");
				myfullname=ret.getString("full_name");
				account=ret.getString("nick_name");
				nickname=ret.getString("display_name");
				picnum=(ret.get("avatar").toString().equalsIgnoreCase("null")?null:String.valueOf(ret.get("avatar")));
				place=ret.getString("location");
				birth=ret.getString("date_of_birth");
				gender=(ret.getInt("gender")==1?"男":"女");

				MouseAction m = new MouseAction();
				
				final PlurkList all_list = new PlurkList(m,location,null,num_plurk, false, false,false,myid,client);
				final PlurkList my_list = new PlurkList(m,location,null,num_plurk, true, false,false ,myid,client);
				final PlurkList re_list = new PlurkList(m,location,null,num_plurk, false, true,false ,myid,client);
				final PlurkList new_re_list = new PlurkList(m,location,client.getUnreadPlurks(),myid,client);
				
				final JTabbedPane tabbedPane = new JTabbedPane();
				//tabbedPane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(172, 168, 153)));
				tabbedPane.setFont(new Font("Serif", Font.PLAIN, 16));
				
				tabbedPane.addTab("主要時間軸",null,all_list,"顯示所有的");
				tabbedPane.addTab("我的個人噗",null,my_list,"我自己的");
				tabbedPane.addTab("回應過的噗",null,re_list,"我有回應的");
				tabbedPane.addTab("新回應的噗",null,new_re_list,"新回應的");
				
				
				/*tabbedPane.addChangeListener(new ChangeListener(){
					public void stateChanged(ChangeEvent arg0) {
						if(tabbedPane.getSelectedIndex()==0){
							all_list.reset(true);
						}
						else if(tabbedPane.getSelectedIndex()==1){
							my_list.reset(true);
						}
						
					}});*/
				
				this.add(top_panel(m,client,all_list,my_list),"0,0");
				this.add(tabbedPane,"0,1");
				this.add(bottom_panel(m),"0,2");
				
				new Thread(new Runnable(){public void run(){
					while(true){
						try{
							Thread.sleep(loadtime);
							if(all_list.getenable()){
								all_list.reset(true,true);
							}
							Thread.sleep(loadtime);
							if(new_re_list.getenable()){
								new_re_list.reset(false,true);
							}
						}catch (Exception e) {
							JOptionPane.showMessageDialog(null, "loadind迴圈 錯誤!請聯絡OA!!","loadind迴圈 錯誤!",0);
							System.exit(0);
						}
					}
				}}).start();
				
				//this.setJMenuBar( menubar() );
				
				createSysTrayIcon(this,title);
				this.setSize(mainframe_width, mainframe_height);
				this.setBounds(Math.abs(Toolkit.getDefaultToolkit().getScreenSize().width-this.getWidth())/2, Math.abs(Toolkit.getDefaultToolkit().getScreenSize().height-this.getHeight())/2, this.getWidth(), this.getHeight());
				this.setVisible(true);
				
				//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}catch (Exception e){JOptionPane.showMessageDialog(null, "MainFrame錯誤!請聯絡OA!!\n"+e,"MainFrame錯誤!",0);System.exit(0);}
		}
		/*
		public JMenuBar menubar(){
			JMenuBar a = new JMenuBar();
			a.add(new JMenu("檔案(F)"));
			a.add(new JMenu("編輯(E)"));
			a.add(new JMenu("檢視(V)"));
			a.add(new JMenu("說明(H)"));
			return a;
		}*/
		public JPanel bottom_panel(MouseAction m){
			JPanel panel[] =new JPanel[4];
			TableLayout thisLayout = new TableLayout(new double[][] {
					{TableLayout.FILL,150,mainframe_width*0.2}
					,{TableLayout.FILL}
					});
			thisLayout.setHGap(5);
			thisLayout.setVGap(5);
			
			
			for(int i = 0 ; i < panel.length ; i++){
				panel[i] =new JPanel();
				if(i==0){
					panel[i].setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(172, 168, 153)));
					panel[i].setLayout(thisLayout);
				}
				else if(i>0){
					if(i==2) panel[i].add(m.getstate());
					panel[i].setLayout(new GridLayout(1,1));
					panel[i].setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, new Color(172, 168, 153)));
					
					panel[0].add(panel[i],(i-1)+",0");
				}
			}
			return panel[0];
		}
		public JPanel top_panel(MouseAction m,final PlurkClient client,final PlurkList alllist,final PlurkList mylist){
			String qualifier_translated[] = {"愛","喜歡","分享","給","討厭"
											,"想要","期待","需要","打算","希望"
											,"問","已經","曾經","好奇","覺得"
											,"想","說","正在"};
			final Qualifier qualifier[] = {Qualifier.LOVES,Qualifier.LIKES,Qualifier.SHARES,Qualifier.GIVES,Qualifier.HATES
									,Qualifier.WANTS,Qualifier.WISHES,Qualifier.NEEDS,Qualifier.WILL,Qualifier.HOPES
									,Qualifier.ASKS,Qualifier.HAS,Qualifier.WAS,Qualifier.WONDERS,Qualifier.FEELS
									,Qualifier.THINKS,Qualifier.SAYS,Qualifier.IS};
			
			String othertext[][]={{"你的稱呼"},{"輸入你的噗"},{"送出","發噗"},{"選擇動作"},{"提示區"}};
			JPanel panel[] = new JPanel[6];
			JLabel namelabel = new JLabel();
			namelabel = new JLabel(nickname.equalsIgnoreCase("")?account:(nickname+" ( "+account+" ) "));
			namelabel.setFont(new Font("Serif", Font.PLAIN, 16));
			namelabel.setHorizontalAlignment(SwingConstants.LEADING);
			namelabel.setToolTipText(othertext[0][0]);
			namelabel.addMouseListener(m);
			
			final JButton button = new JButton(othertext[2][0]);
			
			
			final WarningTextField statelabel = new WarningTextField("",13);
			statelabel.setToolTipText(othertext[4][0]);
			statelabel.setHorizontalAlignment(SwingConstants.RIGHT);
			statelabel.setFont(new Font("Serif", Font.BOLD, 14));
			statelabel.setbordercolor(new Color(0,0,0,0));
			statelabel.setEditable(false);
			statelabel.addMouseListener(m);
			
			final JTextArea textarea = new JTextArea();
			textarea.setFont(new Font("Serif", Font.PLAIN, 18));
			textarea.setToolTipText(othertext[1][0]);
			textarea.setLineWrap (true);
			textarea.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(172, 168, 153)));
			textarea.addMouseListener(m);
			textarea.addKeyListener(new KeyListener(){
				public void keyPressed(KeyEvent arg0){setrelated(arg0);}
				public void keyReleased(KeyEvent arg0){setrelated(arg0);}
				public void keyTyped(KeyEvent arg0){}
				private void setrelated(KeyEvent arg0){
					if(textarea.getText().indexOf("  ")!=-1 || textarea.getText().indexOf("\n")!=-1){
						if(arg0.getKeyCode()==10) textarea.setText(textarea.getText().replaceAll("\n"," ").replaceAll("  "," "));
						if(arg0.getKeyCode()==32) textarea.setText(textarea.getText().replaceAll("  "," "));
					}
					if(textarea.getText().length()>140){
						textarea.setForeground(Color.red);
						button.setEnabled(false);
						statelabel.setBackground(new Color(255,212,204));
						statelabel.setForeground(Color.red);
						statelabel.setText("字數太多了。刪掉 "+(textarea.getText().length()-140)+" 個吧！");
						statelabel.setbordercolor(Color.red);
					}
					else{
						textarea.setForeground(Color.black);
						button.setEnabled(true);
						statelabel.setBackground(null);
						statelabel.setText("");
						statelabel.setbordercolor(statelabel.getBackground());
					}
					if(textarea.getText().length()==0){
						button.setEnabled(false);
					}
				}
				});
			JScrollPane scrollpane = new JScrollPane(textarea);
			scrollpane.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(172, 168, 153)));
			
			final JComboBox combobox = new JComboBox(qualifier_translated);
			combobox.setSelectedIndex(Integer.parseInt(getSQL(location,"mainset","qualifier")));
			combobox.setFont(new Font("Serif", Font.PLAIN, 15));
			combobox.setToolTipText(othertext[3][0]);
			combobox.addMouseListener(m);
			
			combobox.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent arg0) {
					if ( arg0.getStateChange() == ItemEvent.SELECTED ){
						setSQL(location,"mainset","update mainset SET qualifier='"+combobox.getSelectedIndex()+"'");
					}
				}});
			
			button.setFont(new Font("Serif", Font.PLAIN, 13));
			button.setEnabled(false);
			button.setToolTipText(othertext[2][1]);
			button.addMouseListener(m);
			button.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					JSONObject ret = client.plurkAdd(textarea.getText(),qualifier[combobox.getSelectedIndex()]);
					if(ret!=null){
						textarea.setText("");
						button.setEnabled(false);
						try{Thread.sleep(3000);}catch (InterruptedException e){}
						
						alllist.reset(false,true);
						try{Thread.sleep(5000);}catch (InterruptedException e){}
						mylist.reset(false,false);
					}
					else{
						JOptionPane.showMessageDialog(null, "你有類似重複囉^^","警告!",1);
					}
				}});
			
			for(int i= 0; i < panel.length ; i++){
				panel[i] = new JPanel();
				panel[i].setLayout(new BorderLayout(i==2?5:0,i==2?10:0));
			}
			if(picnum!=null){
				try{
					String Picname = myid+"-big"+picnum+".jpg";
					
					if(!new File(checkpath(location+"/pic")+"/"+Picname).exists()){
						BufferedImage i = ImageIO.read(new URL("http://avatars.plurk.com/"+Picname));
						ImageIO.write(i, Picname.substring(Picname.lastIndexOf(".")+1,Picname.length()), new File(location+"/pic/"+Picname));
					}
					if(getSQL(location,"mainset","avatar").equalsIgnoreCase("") || !getSQL(location,"mainset","avatar").equalsIgnoreCase(picnum))
						setSQL(location,"mainset","update mainset SET avatar='"+Picname+"'");
					
					panel[1].add(new PicLabel(new ImageIcon(location+"/pic/"+Picname),head_width,head_height,12,Color.GRAY),BorderLayout.NORTH);
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, "存圖檔(大頭貼)錯誤!請聯絡OA!!","存圖檔(大頭貼)錯誤!",0);
					System.exit(0);
				}
			}else{
				panel[1].add(new PicLabel(new ImageIcon(getClass().getResource("image/img.png")),head_width,head_height,12,Color.GRAY),BorderLayout.NORTH);
			}
			
			panel[3].add(new SpeakArrowLabel(new Color(0,0,0,0),"null",20,0),BorderLayout.WEST);
			panel[3].add(namelabel,BorderLayout.CENTER);
			panel[3].add(combobox,BorderLayout.EAST);
			
			panel[4].add(new SpeakArrowLabel(new Color(172, 168, 153),"left,bottom",25,0),BorderLayout.WEST);
			panel[4].add(scrollpane,BorderLayout.CENTER);
			panel[4].add(new SpeakArrowLabel(new Color(172, 168, 153),"right,null",15,0),BorderLayout.EAST);
			
			panel[5].add(button,BorderLayout.EAST);
			panel[5].add(statelabel,BorderLayout.CENTER);
			
			panel[2].add(panel[3],BorderLayout.NORTH);
			panel[2].add(panel[4],BorderLayout.CENTER);
			panel[2].add(panel[5],BorderLayout.SOUTH);
			
			panel[0].add(panel[1],BorderLayout.WEST);
			panel[0].add(panel[2],BorderLayout.CENTER);
			
			return panel[0];
		}
		
	}
	public class Lonin extends JFrame{
		private int longin_width=450,longin_height=600,head_width=150,width_height=150;
		private String string[][] = {{"帳號 : ","密碼 : ","Key : "}
									,{"輸入帳號","輸入密碼","輸入Key"}
									,{"記住密碼","登入"}
									,{"記住密碼","登入OA_Plurker"}};
		private JTextField textField[] = new JTextField[string[0].length];
		private JLabel label[] = new JLabel[string[0].length];
		private JButton button;
		private JCheckBox checkbox = new JCheckBox();
		private JPanel panel = new JPanel();
		//private String regstring;
		
		public Lonin(final String title,final String location){
			super(title);
			
			TableLayout thisLayout = new TableLayout(new double[][] {{TableLayout.FILL, 80.0, 150.0, TableLayout.FILL}, {10.0, 25.0, 25.0, 25.0,25.0,30.0, TableLayout.FILL}});
			thisLayout.setHGap(5);
			thisLayout.setVGap(5);
			panel.setLayout(thisLayout);
			
			try {
				for(int i= 0 ; i <string[0].length ; i++){
					textField[i] = i==1?new JPasswordField(15):new JTextField(15);
					textField[i].setText(getSQL(location,"loginset",i==0?"account":(i==1?"password":"key")));
					textField[i].setFont(new Font("Serif", Font.PLAIN, 15));
					textField[i].setBackground(new Color(250,255,189));
					textField[i].setToolTipText(string[1][i]);
					textField[i].setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, new Color(172, 168, 153)));
					panel.add(textField[i],"2,"+(i+1));
					
					label[i] = new JLabel(string[0][i]);
					label[i].setFont(new Font("Serif", Font.BOLD, 17));
					
					panel.add(label[i],"1,"+(i+1)+",r,f");
				}
				
				checkbox = new JCheckBox(string[2][0],getSQL(location,"loginset","remlogin").equalsIgnoreCase("1")?true:false);
				checkbox.setFont(new Font("Serif", Font.PLAIN, 14));
				checkbox.setToolTipText(string[3][0]);
				panel.add(checkbox,"2,4,l,f");
				button = new JButton(string[2][1]);
				button.setToolTipText(string[3][1]);
				button.setFont(new Font("Serif", Font.BOLD, 17));
				button.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						int c=0;
						for(int i =0 ; i < textField.length ; i++){
							if(textField[i].getText().equalsIgnoreCase("")){
								JOptionPane.showMessageDialog(null, "您沒有"+string[1][i]);
								c=1;
								break;
							}
						}
						if(c==0){
							String account=textField[0].getText(),password=textField[1].getText(),key=textField[2].getText(),remlogin=checkbox.isSelected()?"1":"0";
							writeconfig(location,key);
							if(!checkbox.isSelected())account=password=key="";
							setSQL(location,"loginset","update loginset SET account='"+account+"',password='"+password+"',key='"+key+"',remlogin='"+remlogin+"'");
							
							
							try {
								final JFrame logining = new JFrame("等入中...");
								logining.setLayout(new BorderLayout());
								logining.add(new JLabel(new ImageIcon(getClass().getResource("image/loader.gif"))),BorderLayout.CENTER);
								JLabel a = new JLabel("登入中，請稍候...");
								a.setHorizontalAlignment( SwingConstants.CENTER );
								a.setToolTipText(a.getText());
								a.setFont(new Font("Serif", Font.BOLD, 18));
								logining.add(a,BorderLayout.SOUTH);
								logining.setSize(300, 300);
								logining.setBounds(Math.abs(Toolkit.getDefaultToolkit().getScreenSize().width-logining.getWidth())/2, Math.abs(Toolkit.getDefaultToolkit().getScreenSize().height-logining.getHeight())/2, logining.getWidth(), logining.getHeight());
								logining.setDefaultCloseOperation(0);
								logining.setVisible(true);
								
								setVisible(false);
								
								new Thread(new Runnable(){public void run(){
									try {
										
										PlurkClient client = new PlurkClient(new PlurkSettings(new File(location+"/jplurk.properties")));
										JSONObject ret = client.login(textField[0].getText(),textField[1].getText());
										
										if(ret==null){
											logining.setVisible(false);
											setVisible(true);
											JOptionPane.showMessageDialog(null, "登入資訊錯誤!!\n請檢帳號、密碼 或 Key 是否有輸入錯誤。","錯誤!", JOptionPane.ERROR_MESSAGE);
										}else{
											new MainFrame(title,location,client,client.getOwnProfile());
											logining.setVisible(false);
											setVisible(false);
											return ;
										}
									}
									catch (PlurkException e) {
										JOptionPane.showMessageDialog(null, "錯誤!!\n"+e,"錯誤!!",0);
										System.exit(0);
									}
								}}).start();
							}
							
							catch (Exception e) {
								JOptionPane.showMessageDialog(null, "XX");
							}
						}
					}});
				
				panel.add(button,"2,5,l,f");
				panel.add(setHTML("<a href='http://www.plurk.com/Users/showRegister'>註冊</a> | <a href='http://www.plurk.com/API#key'>取得key</a>","a{font-size:12px;text-decoration: none;}","",this.getBackground()),"2,6,r,f");
				
				thisLayout = new TableLayout(new double[][] {{TableLayout.FILL}, {10,230, TableLayout.FILL}});
				this.setLayout(thisLayout);
				
				if(getSQL(location,"loginset","remlogin").equalsIgnoreCase("1")&&!getSQL(location,"mainset","avatar").equalsIgnoreCase("") && new File(checkpath(location+"/pic")+"/"+getSQL(location,"mainset","avatar")).exists() )
					this.add(new PicLabel(new ImageIcon(location+"/pic/"+getSQL(location,"mainset","avatar")),head_width,width_height,12,Color.GRAY),"0,1,c,c");
				else this.add(new PicLabel(new ImageIcon(getClass().getResource("image/img.png")),head_width,width_height,12,Color.GRAY),"0,1,c,c");
				
				this.add(panel,"0,2");
			}
			catch (Exception e){System.out.println(e);}
			
			createSysTrayIcon(this,title);
			this.setSize(longin_width, longin_height);
			this.setBounds(Math.abs(Toolkit.getDefaultToolkit().getScreenSize().width-this.getWidth())/2, Math.abs(Toolkit.getDefaultToolkit().getScreenSize().height-this.getHeight())/2, this.getWidth(), this.getHeight());
			
			this.setVisible(true);
			//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
	public class InstallationOAPlurker extends JFrame implements ActionListener{
		public int InstallationOAPlurker_width=500,InstallationOAPlurker_height=400;
		public JPanel botttompanel,centerpanel[]=new JPanel[3];
		public JLabel label[] = new JLabel[5];
		public String string[][]={{"下一步","取消","歡迎使用OA Plurker","<center><b><font size='6'>遵守條約</font></b></center><hr width='98%'/><font size='5'>&nbsp;1.要覺得OA很帥!<br/>&nbsp;2.還在想...</font>"
																,"同意,遵守!","上一步","完成","安裝位置 : ","瀏覽其他位置","失敗","恭喜!!安裝成功!!","<html>警告!!安裝失敗!!<br/>請聯絡OA查明原因!!</html>","立即啟動"}
							, {"next","cancel","歡迎使用OA","遵守條約","確定同意","befor","success","選擇路徑","瀏覽","安裝失敗","安裝成功","安裝失","立即啟動"}};
		public final JButton nextbutton = new JButton(string[0][0]);
		public final JButton cancelbutton = new JButton(string[0][1]);
		public final JButton backbutton = new JButton(string[0][5]);
		public String title,location;
		public int page=0;
		public boolean islocation=false,isstart=true;
		public JCheckBox checkbox[] = new JCheckBox[2];
		public InstallationOAPlurker(String title,String location){
			super(title);
			
			string[0][12]+=(" "+title);
			
			this.title = title;
			this.location = location;

			islocation=false;
			
			this.setLayout(new BorderLayout());
			
			nextbutton.setToolTipText(string[1][0]);
			nextbutton.setEnabled(false);
			nextbutton.addActionListener(this);
			
			cancelbutton.setToolTipText(string[1][1]);
			cancelbutton.addActionListener(this);
			
			backbutton.setToolTipText(string[1][5]);
			backbutton.setEnabled(false);
			backbutton.addActionListener(this);
			
			botttompanel = new JPanel();
			botttompanel.setLayout(new TableLayout(new double[][] {{TableLayout.FILL, 80, 80, 80,50},{5,TableLayout.FILL,5}}));
			botttompanel.add(backbutton,"1,1");
			botttompanel.add(nextbutton,"2,1");
			botttompanel.add(cancelbutton,"3,1");
			
			botttompanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(172, 168, 153)));
			
			this.add(getpanel(-1,page),BorderLayout.CENTER);
			this.add(botttompanel,BorderLayout.SOUTH);
			
			this.setSize(InstallationOAPlurker_width, InstallationOAPlurker_height);
			this.setBounds(Math.abs(Toolkit.getDefaultToolkit().getScreenSize().width-this.getWidth())/2, Math.abs(Toolkit.getDefaultToolkit().getScreenSize().height-this.getHeight())/2, this.getWidth(), this.getHeight());
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		public JPanel getpanel(int removepage,int page){
			JPanel panel = null;
			if(removepage!=-1)this.remove(centerpanel[removepage]);
			
			if(centerpanel[page]==null){
				switch(page){
					case 0:
						centerpanel[page] = new JPanel();
						label[0] = new JLabel(string[0][2]);
						label[0].setToolTipText(string[1][2]);
						label[0].setHorizontalAlignment( SwingConstants.CENTER );
						label[0].setFont(new Font("Serif", Font.BOLD, 22));
						
						JEditorPane ruledescription = setHTML(string[0][3],"","",new Color(250,250,250));
						ruledescription.setToolTipText(string[1][3]);
						ruledescription.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(172, 168, 153)));
						
						checkbox[0] = new JCheckBox(string[0][4],false);
						checkbox[0].setFont(new Font("Serif", Font.PLAIN, 14));
						checkbox[0].setToolTipText(string[1][4]);
						checkbox[0].addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent arg0) {
								nextbutton.setEnabled(checkbox[0].isSelected());
							}
						});
						centerpanel[page].setLayout(new TableLayout(new double[][]{{50,TableLayout.FILL,50},{50,TableLayout.FILL,50}}));
						centerpanel[page].add(label[0],"1,0,f,f");
						centerpanel[page].add(new JScrollPane(ruledescription),"1,1,f,f");
						centerpanel[page].add(checkbox[0],"1,2,r,c");
						centerpanel[page].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(172, 168, 153)));
						break;
					case 1:
						centerpanel[page] = new JPanel();
						centerpanel[page].setLayout(new TableLayout(new double[][]{{120,TableLayout.FILL,100,20},{50,20,30,TableLayout.FILL}}));
						
						label[1] = new JLabel(string[0][2]);
						label[1].setToolTipText(string[1][2]);
						label[1].setHorizontalAlignment( SwingConstants.CENTER );
						label[1].setFont(new Font("Serif", Font.BOLD, 22));
						
						label[2] = new JLabel(string[0][7]);
						label[2].setToolTipText(string[1][7]);
						label[2].setHorizontalAlignment( SwingConstants.CENTER );
						label[2].setFont(new Font("Serif", Font.PLAIN, 18));
						
						final JTextField textarea = new JTextField(location);
						textarea.setToolTipText(string[1][7]);
						textarea.setFont(new Font("Serif", Font.PLAIN, 18));
						textarea.setEnabled(false);
						
						JButton button = new JButton(string[0][8]);
						button.setToolTipText(string[1][8]);
						button.setEnabled(false);
						button.setFont(new Font("Serif", Font.PLAIN, 18));
						button.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent arg0) {
								String newloaction = getlocation();
								location = (newloaction==null?location:newloaction);
								location = location.replace('\\','/');
								textarea.setText(location);
							}});
						
						centerpanel[page].add(label[1],"1,0,f,f");
						centerpanel[page].add(label[2],"0,2,r,f");
						centerpanel[page].add(textarea,"1,2,f,f");
						centerpanel[page].add(button,"2,2,f,f");
						break;
					case 2:
						
						centerpanel[page] = new JPanel();
						centerpanel[page].setLayout(new TableLayout(new double[][]{{120,TableLayout.FILL,120},{50,islocation?50:100,100,TableLayout.FILL}}));
						
						label[3] = new JLabel(string[0][2]);
						label[3].setToolTipText(string[1][2]);
						label[3].setHorizontalAlignment( SwingConstants.CENTER );
						label[3].setFont(new Font("Serif", Font.BOLD, 22));
						
						label[4] = new JLabel(string[0][islocation?10:11]);
						label[4].setToolTipText(string[1][islocation?10:11]);
						label[4].setFont(new Font("Serif", Font.BOLD, islocation?18:18));
						
						centerpanel[page].add(label[3],"1,0,f,f");
						centerpanel[page].add(label[4],"1,1,f,f");
						
						if(islocation){
							checkbox[1] = new JCheckBox(string[0][12],true);
							checkbox[1].setToolTipText(string[1][12]);
							checkbox[1].setFont(new Font("Serif", Font.PLAIN, 14));
							checkbox[1].addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent arg0) {
									isstart=checkbox[1].isSelected();
								}
							});
							centerpanel[page].add(checkbox[1],"1,2,l,t");
						}
						break;
				}
			}
			return centerpanel[page];
		}
		
		public void writesqlite(String location){
			createtable(location,"loginset");
			createtable(location,"mainset");
			
		}
		public void actionPerformed(ActionEvent arg0) {
			
			if(((JComponent)arg0.getSource()).getToolTipText().equalsIgnoreCase(string[1][0])){
				if(page==1){
					File file=new File(location);
					if(!file.isDirectory()){
						file.mkdirs();
						new File(location+"/pic").mkdirs();
					}
					islocation = file.isDirectory();
					writeconfig(location,"");
					writesqlite(location);
				}
				JPanel panel = getpanel(page,page+1);
				this.add(panel,BorderLayout.CENTER);
				this.repaint();
				panel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(172, 168, 153)));
				page++;
			}
			else if(((JComponent)arg0.getSource()).getToolTipText().equalsIgnoreCase(string[1][1]) || ((JComponent)arg0.getSource()).getToolTipText().equalsIgnoreCase(string[1][9])){
				System.exit(0);
			}
			else if(((JComponent)arg0.getSource()).getToolTipText().equalsIgnoreCase(string[1][5])){
				JPanel panel = getpanel(page,page-1);
				this.add(panel,BorderLayout.CENTER);
				this.repaint();
				panel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(172, 168, 153)));
				page--;
			}
			else if(((JComponent)arg0.getSource()).getToolTipText().equalsIgnoreCase(string[1][6])){
				if(isstart){
					new Lonin(title,location);
					this.setVisible(false);
				}
				else{
					System.exit(0);
				}
			}
			if(page==centerpanel.length-1){
				if(islocation){
					nextbutton.setText(string[0][6]);
					nextbutton.setToolTipText(string[1][6]);
				}else{
					nextbutton.setText(string[0][9]);
					nextbutton.setToolTipText(string[1][9]);
				}
			}
			else{
				nextbutton.setText(string[0][0]);
				nextbutton.setToolTipText(string[1][0]);
			}
			if(page>0 && page<centerpanel.length-1)backbutton.setEnabled(true);
			else backbutton.setEnabled(false);
		}
		public String getlocation(){
			try{
				JDirectoryChooser chooser = new JDirectoryChooser();
				chooser.setShowingCreateDirectory(false);
				if(chooser.showOpenDialog(null) == JDirectoryChooser.CANCEL_OPTION) return null;
				else return chooser.getSelectedFile().getAbsolutePath();
			}catch(Exception e){return null;}
		}
	}

	private void setSQL(String location,String table,String sql){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:"+location+"/personal.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.executeUpdate(sql);
			connection.close();
		}
		catch(Exception e){
			if(e.toString().equalsIgnoreCase("java.sql.SQLException: no such table: "+table)){
				createtable(location,table);
				setSQL(location,table,sql);
			}
			else{
				JOptionPane.showMessageDialog(null, "getmainset錯誤!!請聯絡OA!!","getmainset錯誤!!",0);
			}
		}
	}
	private String getSQL(String location,String table,String key){
		String s=null;
		try{
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:"+location+"/personal.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			ResultSet rs = statement.executeQuery("select * from "+table);
			if(rs.next()) s=rs.getString(key);
			connection.close();
		}
		catch(Exception e){
			if(e.toString().equalsIgnoreCase("java.sql.SQLException: no such table: "+table)){
				createtable(location,table);
				return getSQL(location,table,key);
			}
			else{
				JOptionPane.showMessageDialog(null, "getmainset錯誤!!請聯絡OA!!","getmainset錯誤!!",0);
			}
		}
		return s;
	}
	private void createtable(String location,String tabelmane){
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:"+location+"/personal.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			
			if(tabelmane.equalsIgnoreCase("loginset")){
				statement.executeUpdate("drop table if exists "+tabelmane);
				statement.executeUpdate("create table "+tabelmane+" (account,password,key,remlogin)");
				statement.executeUpdate("insert into "+tabelmane+" values('','','','0')");
			}
			else if(tabelmane.equalsIgnoreCase("mainset")){
				statement.executeUpdate("drop table if exists "+tabelmane);
				statement.executeUpdate("create table "+tabelmane+" (avatar,qualifier,logintime)");
				statement.executeUpdate("insert into "+tabelmane+" values('','17','')");
			}
			else if(tabelmane.equalsIgnoreCase("listset")){
				statement.executeUpdate("drop table if exists "+tabelmane);
				statement.executeUpdate("create table "+tabelmane+" (all_load_first_time ,my_load_first_time ,re_load_first_time ,pri_load_first_time ,unread_load_first_time)");
				statement.executeUpdate("insert into "+tabelmane+" values('','','','','')");
			}
			else{
				JOptionPane.showMessageDialog(null, "createtable錯誤!!請聯絡OA!!","createtable錯誤!!",0);
				System.exit(0);
			}
			connection.close();
		}catch (Exception e1){}
	}
	private void writeconfig(String location,String key){
		try{
			FileWriter filew = new FileWriter(new File(location+"/jplurk.properties"));
			filew.write("api_key="+key+"\r\nlang=tr_ch\r\n");
			filew.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "wirteconfig錯誤!!請聯絡OA!!","wirteconfig錯誤!!",0);
			System.exit(0);
		}
	}
	private String checkpath(String location){
		File file = new File(location);
		if(!file.isDirectory()){
			checkpath(location.substring(0,location.lastIndexOf("/")));
			file.mkdir();
		}
		
		return location;
	}
	private JEditorPane setHTML(String content,String css,String style,Color bc){
		
		JEditorPane jep = new JEditorPane();
		jep.setBackground(new JOptionPane().getBackground());
		jep.setEditable(false);
		jep.setBackground(bc);
		jep.setContentType("text/html");
		
		jep.setText("<style type='text/css'>"+css+"</style><div style='"+style+"'>"+content+"</div>");
		jep.addHyperlinkListener(new HyperlinkListener() {
			public void hyperlinkUpdate(HyperlinkEvent arg0) {
				if (arg0.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
					try {
						//System.out.println(arg0.getURL().toURI());
						Desktop desk = Desktop.getDesktop();
						if (desk != null)
							desk.browse(arg0.getURL().toURI());
					} catch (Exception ioe) {
						JOptionPane.showMessageDialog(null, "Erreo!請將此事件告知OA!!");
					}
			}
		});
		return jep;
	}
	private void deleteTree(String location){
		File[] list = new File(location).listFiles();
		for(int i = 0 ; i < list.length ; i++){
			if(list[i].isDirectory()) deleteTree(location+"/"+list[i].getName());
			list[i].delete();
		}
	}
	private void createSysTrayIcon(final JFrame frame,String title) {
		PopupMenu popupMenu = new PopupMenu();
	    MenuItem defaultItem = new MenuItem("開啟 "+title);
	    MenuItem exitItem = new MenuItem("關閉 "+title);
	    popupMenu.add(defaultItem);
	    popupMenu.addSeparator();
	    popupMenu.add(exitItem);
	    final TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/oa_logo.png")), title, popupMenu);
	    final SystemTray systemTray = SystemTray.getSystemTray(); // create system tray
	      
	    ActionListener listener = new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		try{
	    			frame.setState(JFrame.NORMAL);
	    			frame.setVisible(true);
	    			frame.toFront();
	    			systemTray.remove(trayIcon);
	    		}
	    		catch(Exception ex) {
	    			ex.printStackTrace();
	    		}
	        }
	    };
	    defaultItem.addActionListener(listener);
	    trayIcon.addActionListener(listener);
	    
	    exitItem.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	System.exit(0);
	        }
	    });
	    frame.addWindowListener(new WindowAdapter(){
	        public void windowIconified(WindowEvent e) {
	        	frame.setVisible(false);
	        	try{systemTray.add(trayIcon);}
	        	catch(Exception ex) {ex.printStackTrace();}
	        }
	        public void windowClosing(WindowEvent e) {
	        	try{
	        		System.exit(0);
	        		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		            //systemTray.add(trayIcon);
		          }
		          catch(Exception ex) {
		            ex.printStackTrace();
		          }
		       }
	    });
	}
	public static void main(String[] args) {
		try{UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch(Exception e){e.printStackTrace();}
		
		String location = "C:/Program Files/OA/Plurker";
		new OA_Plurker("OA_Plurker",location);
	}
}