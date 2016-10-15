import java.util.regex.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class TextEditor implements WindowListener,ActionListener,KeyListener
{
	Button b_prompt_save_new,b_prompt_dontsave_new,b_prompt_cancel_new,b_prompt_save_new_fpn; ///Prompt NEW buttons
	Button b_prompt_save_open,b_prompt_dontsave_open,b_prompt_cancel_open,b_prompt_save_open_fpn; ///Prompt OPEN buttons
	Button b_prompt_save_exit,b_prompt_dontsave_exit,b_prompt_cancel_exit; ///Prompt EXIT buttons
	Button b_find_cancel,b_find_next; ///Find dialog buttons
	Button b_findReplace_findnext,b_findReplace_replace,b_findReplace_replaceAll,b_findReplace_cancel; ///Find and Replace dialog buttons
	
	boolean once = false,keypressed = false,hasfilepath = false,promptgone = true;
	Frame f,f_find,f_findReplace,f_prompt;
	TextArea ta;	
	TextField tf_find,tf_replace;	
	MenuBar mb;		
	Menu m1,m2,m3;
	MenuItem nw,opn,sve,svas,ext,cpy,pst,ct,find,find_replace;		
	CheckboxMenuItem bld,itlc;
	Pattern patt;	
	Matcher matt;	
	int mat = 0,c = 0;	
	StringBuffer sb = new StringBuffer();
	String fpn,allText;
	
	public TextEditor()	
	{
		f = new Frame();
		f.setSize(600,600);
		
		ta = new TextArea();
		f.add(ta,"Center");
		
		mb = new MenuBar();
		m1 = new Menu("File");	m2 = new Menu("Edit");	//m3 = menuItem("");
		nw = new MenuItem("New");	opn = new MenuItem("Open");	sve = new MenuItem("Save"); svas = new MenuItem("Save As");	ext = new MenuItem("Exit");
		find  = new MenuItem("Find");	find_replace = new MenuItem("Find and Replace");
		//cpy = new MenuItem();	pst = new MenuItem();	ct = new MenuItem();
		//bld = new CheckboxMenuItem();	itlc = new CheckboxMenuItem();
		
		//m3.add(ct);	m3.add(cpy); m3.add(pst);
		m2.add(find);	m2.add(find_replace);
		m1.add(nw);	m1.add(opn); m1.add(sve); m1.add(svas); m1.addSeparator(); m1.add(ext);
		mb.add(m1);mb.add(m2);
		f.setMenuBar(mb);
		
		nw.addActionListener(this);	opn.addActionListener(this); sve.addActionListener(this); svas.addActionListener(this); ext.addActionListener(this);
		find.addActionListener(this); find_replace.addActionListener(this);
		f.addWindowListener(this);f.addWindowListener(this);ta	.addKeyListener(this);
		f.setVisible(true);
	}
		
	public void keyPressed(KeyEvent ke)
	{
		keypressed = true;
	}
	  
	public void keyReleased(KeyEvent ke)
	{
		keypressed = true;
	}
	
	public void keyTyped(KeyEvent ke)
	{
		keypressed = true;
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String s = ae.getActionCommand();
		allText = ta.getText();
		c=0;
	
			/////////////////////////////////////////NEW-NEW-NEW-NEW\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		if(ae.getSource() == nw)
		{
			if(hasfilepath)
			{
				if(keypressed)
				{
					f_prompt = new Frame();
						f_prompt.setSize(300,200);
					
						Label l_prompt = new Label("Do you want to save the file?");		f_prompt.add(l_prompt,"Center");
						Panel p_prompt = new Panel();										f_prompt.add(p_prompt,"South");
					
						b_prompt_save_new = new Button("Save");				p_prompt.add(b_prompt_save_new);	b_prompt_save_new.addActionListener(this);
						b_prompt_dontsave_new_fpn = new Button("Don't Save");	p_prompt.add(b_prompt_dontsave_new_fpn);	b_prompt_dontsave_new_fpn.addActionListener(this);
						b_prompt_cancel_new = new Button("Cancel");			p_prompt.add(b_prompt_cancel_new);	b_prompt_cancel_new.addActionListener(this);
						
						
						f_prompt.addWindowListener(this);
						f_prompt.setResizable(false);
						f_prompt.setVisible(true);
				}
				else
				{
					
					ta.setText("");
					hasfilepath = false;
					keypressed = false;
					fpn = "";
				}
			}
			else
			{
				if(keypressed)
				{
					allText = ta.getText();
					if(allText.equals(""))
					{
						ta.setText("");
						hasfilepath = false;
						keypressed = false;
						fpn = "";
					}
					else
					{
						f_prompt = new Frame();
						f_prompt.setSize(300,200);
					
						Label l_prompt = new Label("Do you want to save the file?");		f_prompt.add(l_prompt,"Center");
						Panel p_prompt = new Panel();										f_prompt.add(p_prompt,"South");
					
						b_prompt_save_new_fpn = new Button("Save");				p_prompt.add(b_prompt_save_new_fpn);	b_prompt_save_new_fpn.addActionListener(this);
						b_prompt_dontsave_new = new Button("Don't Save");	p_prompt.add(b_prompt_dontsave_new);	b_prompt_dontsave_new.addActionListener(this);
						b_prompt_cancel_new = new Button("Cancel");			p_prompt.add(b_prompt_cancel_new);	b_prompt_cancel_new.addActionListener(this);
						
						
						f_prompt.addWindowListener(this);
						f_prompt.setResizable(false);
						f_prompt.setVisible(true);
					}
				}
				else
				{
					ta.setText("");
					hasfilepath = false;
					keypressed = false;
					fpn = "";
				}
				
			}
		//	System.out.print(keypressed);
			
		}
		////////////////////////////////////////////////////////NEW NEW NEW NEW\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		///////////////////////////////////////////////////////New-Prompt-Save-fpn\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		if(ae.getSource() == b_prompt_save_new)
		{
			f_prompt.setVisible(false);
			
			char ch[] = ta.getText().toCharArray();
				
					File fi = new File(fpn);
			
					FileOutputStream fos ;
				
					try
					{
						if(!fi.exists())
						{
							fi.createNewFile();
						}
						fos	= new FileOutputStream(fi);
						for(int i=0;i<ch.length;i++)
						fos.write(ch[i]);
						fos.close();
					}
					catch(Exception ex)
					{
						System.out.print(ex.getMessage());
					}
			
			ta.setText("");
			hasfilepath = false;
			keypressed = false;
			fpn = "";
		}
		///////////////////////////////////////////////////////New-Prompt-Save-fpn\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		///////////////////////////////////////////////////////NEW-PROMPT-SAVE\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		if(ae.getSource() == b_prompt_save_new)
		{
			f_prompt.setVisible(false);
			filesavedialog();
			ta.setText("");
			hasfilepath = false;
			keypressed = false;
			fpn = "";
		}
		///////////////////////////////////////////////////////NEW-PROMPT-SAVE\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		//////////////////////////////////////////////////////New-Prompt-Dontsave\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		if(ae.getSource() == b_prompt_dontsave_new)
		{
			f_prompt.setVisible(false);
			ta.setText("");
			hasfilepath = false;
			keypressed = false;
			fpn = "";
		}
		//////////////////////////////////////////////////////New-Prompt-Dontsave\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		/////////////////////////////////////////////////////New-prompt-Cancel\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		if(ae.getSource() == b_prompt_cancel_new)
		{
			f_prompt.setVisible(false);
		}
		/////////////////////////////////////////////////////New-prompt-Cancel\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		/////////////////////////////////////OPEN-OPEN-OPEN-OPEN\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		if(ae.getSource() == opn)
		{
			if(hasfilepath)
			{
				if(keypressed)
				{
					f_prompt = new Frame();
					f_prompt.setSize(300,200);
					
					Label l_prompt = new Label("Do you want to save the file?");		f_prompt.add(l_prompt,"Center");
					Panel p_prompt = new Panel();										f_prompt.add(p_prompt,"South");
					
					b_prompt_save_open_fpn = new Button("Save");				p_prompt.add(b_prompt_save_open_fpn);	b_prompt_save_open_fpn.addActionListener(this);
					b_prompt_dontsave_open = new Button("Don't Save");	p_prompt.add(b_prompt_dontsave_open);	b_prompt_dontsave_open.addActionListener(this);
					b_prompt_cancel_open = new Button("Cancel");			p_prompt.add(b_prompt_cancel_open);	b_prompt_cancel_open.addActionListener(this);
						
						
					f_prompt.addWindowListener(this);
					f_prompt.setResizable(false);
					f_prompt.setVisible(true);
				
					
				}
				else
				{	
					fileopendialog();
				}
			}
			else
			{
				if(keypressed)
				{
					allText = ta.getText();
					if(!allText.equals(""))
					{
						f_prompt = new Frame();
						f_prompt.setSize(300,200);
					
						Label l_prompt = new Label("Do you want to save the file?");		f_prompt.add(l_prompt,"Center");
						Panel p_prompt = new Panel();										f_prompt.add(p_prompt,"South");
					
						b_prompt_save_open = new Button("Save");				p_prompt.add(b_prompt_save_open);	b_prompt_save_open.addActionListener(this);
						b_prompt_dontsave_open = new Button("Don't Save");	p_prompt.add(b_prompt_dontsave_open);	b_prompt_dontsave_open.addActionListener(this);
						b_prompt_cancel_open = new Button("Cancel");			p_prompt.add(b_prompt_cancel_open);	b_prompt_cancel_open.addActionListener(this);
						
						
						f_prompt.addWindowListener(this);
						f_prompt.setResizable(false);
						f_prompt.setVisible(true);
					
					}
					else
					{
						fileopendialog();
					}
					
				}
				else
				{
						fileopendialog();
				
				}
			}				
		}
		///////////////////////////////////////////////////OPEN OPEN OPEN OPEN\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		///////////////////////////////////////////////////open-prompt-save-fpn\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		if(ae.getSource() == b_prompt_save_open_fpn)
		{
			f_prompt.setVisible(false);
			char ch[] = ta.getText().toCharArray();
				
					File fi = new File(fpn);
			
					FileOutputStream fos ;
				
					try
					{
						if(!fi.exists())
						{
							fi.createNewFile();
						}
						fos	= new FileOutputStream(fi);
						for(int i=0;i<ch.length;i++)
						fos.write(ch[i]);
						fos.close();
					}
					catch(Exception ex)
					{
						System.out.print(ex.getMessage());
					}
			
			fileopendialog();
		}
		///////////////////////////////////////////////////open-prompt-save\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		///////////////////////////////////////////////////open-prompt-save\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		if(ae.getSource() == b_prompt_save_open)
		{
			f_prompt.setVisible(false);
			filesavedialog();
			fileopendialog();
		}
		///////////////////////////////////////////////////open-prompt-save\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		///////////////////////////////////////////////////open-prompt-dontsave\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		if(ae.getSource() == b_prompt_dontsave_open)
		{
			f_prompt.setVisible(false);
			fileopendialog();
		}
		///////////////////////////////////////////////////open-prompt-dontsave\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		///////////////////////////////////////////////////open-prompt-cancel\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		if(ae.getSource() == b_prompt_cancel_open)
		{
			f_prompt.setVisible(false);
		}
		///////////////////////////////////////////////////open-prompt-cancel\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		/////////////////////////////////////////SAVE-SAVE-SAVE-SAVE///////////////////////////////////////////////////////////////////
		if(ae.getSource() == sve)
		{
			if(hasfilepath)
			{
				char ch[] = ta.getText().toCharArray();
				File fi = new File(fpn);
				FileOutputStream fos ;
				try
				{
					if(!fi.exists())
					{
						fi.createNewFile();
					}
					fos	= new FileOutputStream(fi);
					for(int i=0;i<ch.length;i++)
					fos.write(ch[i]);
					fos.close();
				}
				catch(Exception ex)
				{
					System.out.print(ex.getMessage());
				}
				keypressed = false;
			}
			else
			{
				filesavedialog();
			}	
		}
		///////////////////////////////////////////////////SAVE SAVE SAVE SAVE\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
		//////////////////////////////////////////////SAVE AS-SAVE AS-SAVE AS-SAVE AS///////////////////////////////////////////////////
		if(ae.getSource() == svas)
		{
			filesavedialog();	
		}
	
		///////////////////////////////////////////////SAVE AS-SAVE AS-SAVE AS-SAVE AS/////////////////////////////////////////////////
	
		/////////////////////////////////////////FIND-FIND-FIND-FIND///////////////////////////////////////////////////////////////////
			if(ae.getSource() == find)
			{
				f_find = new Frame(); once = false;
				f_find.setSize(450,150);
				f_find.setLayout(new GridBagLayout());
				GridBagConstraints gbc = new GridBagConstraints();
    			gbc.gridx = 0;		gbc.gridy = 0;
    			gbc.weightx = 1;		gbc.weighty = 1;
				Label l_find = new Label("Find What :");	f_find.add(l_find,gbc);
				gbc.gridx = 1;	gbc.gridy = 0;
				
				tf_find = new TextField(30);		f_find.add(tf_find,gbc);
				gbc.gridx = 0;	gbc.gridy = 1;
				
				b_find_next = new Button("Find Next");	f_find.add(b_find_next,gbc);b_find_next.addActionListener(this);
			
				gbc.gridx = 1;		gbc.gridy = 1;
				
				b_find_cancel = new Button("Cancel");		f_find.add(b_find_cancel,gbc);b_find_cancel.addActionListener(this);
				
				f_find.addWindowListener(this);
				f_find.setVisible(true);
				f_find.setResizable(false);
				
			}
		///////////////////////////////////////////////////FIND-FIND-FIND-FIND///////////////////////////////////////////////////////////////////
		
		//////////////////////////////////////////////////find-dialog-next///////////////////////////////////////////////////////////////////////
		if(ae.getSource() == b_find_next || ae.getSource() == b_findReplace_findnext)
		{
			String s_find = tf_find.getText();
			
				String a = allText.replaceAll("\r","");
			//	a = a.replaceAll("\n","");
				patt = Pattern.compile(s_find);
				matt = patt.matcher(a);
			//	System.out.print(a + "/n" + s_find);
								
				if(!once)
				{
					once = true;
					mat = ta.getCaretPosition();
					System.out.print(mat + "\n\n\n");
					if(matt.find(mat))
					{
						System.out.print(mat);
					//	System.out.print(matt.start() + "\t\t" + matt.end());
						ta.select(matt.start(),matt.end());	
						mat = matt.end();
						f.toFront();					
					}
					else
					{
						Frame f_error = new Frame();
						f_error.setSize(200,200);
						Label l_error = new Label("ERROR : CANNOT FIND " + s_find); f_error.add(l_error);
						mat = 0;
						f_error.addWindowListener(this);
						f_error.setVisible(true);
						f_error.setResizable(false);
					}
				}
				else
				{
					if(matt.find(mat))
					{//System.out.print(matt.start() + "\t\t" + matt.end());
					ta.select(matt.start(),matt.end());
					mat = matt.end();
					f.toFront();	}
					else
					{
						Frame f_error = new Frame();
						f_error.setSize(200,200);
						Label l_error = new Label("No other occcurances!!"); f_error.add(l_error);
						mat = 0;
						f_error.addWindowListener(this);
						f_error.setVisible(true);
						f_error.setResizable(false);
					}
				}	
		}
		//////////////////////////////////////////////////find-dialog-next///////////////////////////////////////////////////////////////////////
		
		//////////////////////////////////////////////////find-dialog-cancel/////////////////////////////////////////////////////////////////////
		if(ae.getSource() == b_find_cancel)
		{
			f_find.setVisible(false);
			mat = 0;
			once = false;
		}
		//////////////////////////////////////////////////find-dialog-cancel/////////////////////////////////////////////////////////////////////

		/////////////////////////////////////////FIND AND REPLACE-FIND AND REPLACE///////////////////////////////////////////////////////////////////
			if(ae.getSource() == find_replace)
			{
				f_findReplace = new Frame();
				f_findReplace.setSize(450,250);
				f_findReplace.setLayout(new GridBagLayout());
				GridBagConstraints gbc = new GridBagConstraints();
    			gbc.gridx = 0;		gbc.gridy = 0;
    			gbc.weightx = 1;		gbc.weighty = 1;
				Label l_findReplace1 = new Label("Find What :");	f_findReplace.add(l_findReplace1,gbc);
				gbc.gridx = 1;	gbc.gridy = 0;
				
				tf_find = new TextField(30);		f_findReplace.add(tf_find,gbc);
				gbc.gridx = 2;	gbc.gridy = 0;
				
				b_findReplace_findnext= new Button("Find Next");	f_findReplace.add(b_findReplace_findnext,gbc);b_findReplace_findnext.addActionListener(this);
				gbc.gridx = 0;		gbc.gridy = 1;
				
				Label l_findReplace2 = new Label("Replace with :");	f_findReplace.add(l_findReplace2,gbc);
				gbc.gridx = 1;	gbc.gridy = 1;
				
				tf_replace = new TextField(30);		f_findReplace.add(tf_replace,gbc);
				gbc.gridx = 2;	gbc.gridy = 1;
				
				b_findReplace_replace = new Button("Replace");	f_findReplace.add(b_findReplace_replace,gbc);b_findReplace_replace.addActionListener(this);
				
				gbc.gridx = 0;		gbc.gridy = 2;
				
				Label l_find1 = new Label("");		f_findReplace.add(l_find1,gbc);
				gbc.gridx = 1;		gbc.gridy = 2;
				
				Label l_find2 = new Label("");		f_findReplace.add(l_find2,gbc);			
				gbc.gridx = 2;	gbc.gridy = 2;
				
				b_findReplace_replaceAll = new Button("Replace All");		f_findReplace.add(b_findReplace_replaceAll,gbc);b_findReplace_replaceAll.addActionListener(this);
				gbc.gridx = 0;		gbc.gridy = 3;
				
				Label l_find3 = new Label("");		f_findReplace.add(l_find3,gbc);
				gbc.gridx = 1;		gbc.gridy = 3;
				
				Label l_find4 = new Label("");		f_findReplace.add(l_find4,gbc);			
				gbc.gridx = 2;	gbc.gridy = 3;
				
				b_findReplace_cancel = new Button("Cancel");		f_findReplace.add(b_findReplace_cancel,gbc);b_findReplace_cancel.addActionListener(this);
			
				f_findReplace.addWindowListener(this);
				f_findReplace.setVisible(true);
				f_findReplace.setResizable(false);
				
			}
		////////////////////////////////////////////////FIND AND REPLACE-FIND AND REPLACE-FIND AND REPLACE///////////////////////////////////////////
		
		////////////////////////////////////////////////findreplace-dialog-replace///////////////////////////////////////////////////////////////////
		if(ae.getSource() == b_findReplace_replace)
		{
			String s_replace = tf_replace.getText();
			String s_find = tf_find.getText();
			String a = s_find.replaceAll("\r","");
		//	a = s_find.replaceAll("\n","");
			patt = Pattern.compile(a);
			matt = patt.matcher(allText);
							
			if(!once)
			{
				once = true;
				if(matt.find(ta.getCaretPosition()))
				{
					System.out.print(matt.start() + "\t\t" + matt.end());
					ta.select(matt.start(),matt.end());	
					mat = matt.end();
					matt.appendReplacement(sb,s_replace);
					matt.appendTail(sb);
					ta.setText("");
					ta.setText(sb + "");
					//f.tofront();
					
				}
				else
				{
					Frame f_error = new Frame();
					f_error.setSize(200,200);
					Label l_error = new Label("ERROR : CANNOT FIND " + s_find); f_error.add(l_error);
					mat = 0;
					f_error.addWindowListener(this);
					f_error.setVisible(true);
					f_error.setResizable(false);
				}
				
			}
			else
			{
				if(matt.find(mat));
				{System.out.print(matt.start() + "\t\t" + matt.end());
				ta.select(matt.start(),matt.end());
				mat = matt.end();
				matt.appendReplacement(sb,s_replace);
				matt.appendTail(sb);
				ta.setText("");
				ta.setText(sb + "");	}
			}
			sb = new StringBuffer();
		}
		////////////////////////////////////////////////findreplace-dialog-replace/////////////////////////////////////////////////////////////////////
		
		////////////////////////////////////////////////findreplace-dialog-replaceAll/////////////////////////////////////////////////////////////////////
		if(ae.getSource() == b_findReplace_replaceAll)
		{
			String s_replace = tf_replace.getText();
				String s_find = tf_find.getText();					
				String a = allText.replaceAll(s_find,s_replace);
				ta.setText("");
				//trySystem.out.print(a);
				ta.setText(a + "");
				f.toFront();		
		}
		////////////////////////////////////////////////findreplace-dialog-replaceAll/////////////////////////////////////////////////////////////////////
		
		////////////////////////////////////////////////findreplace-dialog-cancel/////////////////////////////////////////////////////////////////////
			if(ae.getSource() == b_findReplace_cancel)
			{
				f_findReplace.setVisible(false);
			}
		////////////////////////////////////////////////findreplace-dialog-cancel/////////////////////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////////EXIT - EXIT - EXIT - EXIT///////////////////////////////////////////////////////////////
		if(s == "Exit")
		{
			if(hasfilepath)
			{
				if(keypressed)
				{
					
						f_prompt = new Frame();
						f_prompt.setSize(300,200);
					
						Label l_prompt = new Label("Do you want to save the file?");		f_prompt.add(l_prompt,"Center");
						Panel p_prompt = new Panel();										f_prompt.add(p_prompt,"South");
					
						b_prompt_save_exit = new Button("Save");				p_prompt.add(b_prompt_save_exit);	b_prompt_save_exit.addActionListener(this);
						b_prompt_dontsave_exit = new Button("Don't Save");	p_prompt.add(b_prompt_dontsave_exit);	b_prompt_dontsave_exit.addActionListener(this);
						b_prompt_cancel_exit = new Button("Cancel");			p_prompt.add(b_prompt_cancel_exit);	b_prompt_cancel_exit.addActionListener(this);
						
						
						f_prompt.addWindowListener(this);
						f_prompt.setResizable(false);
						f_prompt.setVisible(true);
				}
				else
				{
					System.exit(1);
				}
			}
			else
			{
				if(keypressed)
				{
					allText = ta.getText();
					if(allText.equals(""))
					{
						System.exit(1);
					}
					else
					{
						f_prompt = new Frame();
						f_prompt.setSize(300,200);
					
						Label l_prompt = new Label("Do you want to save the file?");		f_prompt.add(l_prompt,"Center");
						Panel p_prompt = new Panel();										f_prompt.add(p_prompt,"South");
					
						b_prompt_save_exit = new Button("Save");				p_prompt.add(b_prompt_save_exit);	b_prompt_save_exit.addActionListener(this);
						b_prompt_dontsave_exit = new Button("Don't Save");	p_prompt.add(b_prompt_dontsave_exit);	b_prompt_dontsave_exit.addActionListener(this);
						b_prompt_cancel_exit = new Button("Cancel");			p_prompt.add(b_prompt_cancel_exit);	b_prompt_cancel_exit.addActionListener(this);
						
						
						f_prompt.addWindowListener(this);
						f_prompt.setResizable(false);
						f_prompt.setVisible(true);
					}
				}
				else
				{
					System.exit(1);
				}
			}
		}
		//////////////////////////////////////////////////////////EXIT-EXIT-EXIT-EXIT\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		/////////////////////////////////////////////////////////exit prompt save fpn\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		if(ae.getSource() == b_prompt_save_exit_fpn)
		{
			f_prompt.setVisible(false);
			
			char ch[] = ta.getText().toCharArray();
				
					File fi = new File(fpn);
			
					FileOutputStream fos ;
				
					try
					{
						if(!fi.exists())
						{
							fi.createNewFile();
						}
						fos	= new FileOutputStream(fi);
						for(int i=0;i<ch.length;i++)
						fos.write(ch[i]);
						fos.close();
					}
					catch(Exception ex)
					{
						System.out.print(ex.getMessage());
					}
			
			System.exit(1);
		}
		/////////////////////////////////////////////////////////exit prompt save\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		/////////////////////////////////////////////////////////exit prompt save\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		if(ae.getSource() == b_prompt_save_exit)
		{
			filesavedialog();
			f_prompt.setVisible(false);
			System.exit(1);
		}
		/////////////////////////////////////////////////////////exit prompt save\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		/////////////////////////////////////////////////////////exit prompt dontsave\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		if(ae.getSource() == b_prompt_dontsave_exit)
		{
			f_prompt.setVisible(false);
			System.exit(1);
		}
		/////////////////////////////////////////////////////////exit prompt dontsave\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		/////////////////////////////////////////////////////////exit prompt cancel\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		if(ae.getSource() == b_prompt_cancel_exit)
		{
			f_prompt.setVisible(false);
		}
		/////////////////////////////////////////////////////////exit prompt cancel\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	}
	
	public void windowOpened(WindowEvent we)
	{
	}
	
	public void windowDeiconified(WindowEvent we)
	{
	}
		
	public void windowIconified(WindowEvent we)
	{
	}
	
	public void windowClosed(WindowEvent we)
	{
		Window w = we.getWindow();
		if(we.getWindow() == f)
		{	
			if(hasfilepath)
			{
				if(keypressed)
				{
						f_prompt = new Frame();
						f_prompt.setSize(300,200);
					
						Label l_prompt = new Label("Do you want to save the file?");		f_prompt.add(l_prompt,"Center");
						Panel p_prompt = new Panel();										f_prompt.add(p_prompt,"South");
					
						b_prompt_save_exit = new Button("Save");				p_prompt.add(b_prompt_save_exit);	b_prompt_save_exit.addActionListener(this);
						b_prompt_dontsave_exit = new Button("Don't Save");	p_prompt.add(b_prompt_dontsave_exit);	b_prompt_dontsave_exit.addActionListener(this);
						b_prompt_cancel_exit = new Button("Cancel");			p_prompt.add(b_prompt_cancel_exit);	b_prompt_cancel_exit.addActionListener(this);
						
						
						f_prompt.addWindowListener(this);
						f_prompt.setResizable(false);
						f_prompt.setVisible(true);						
				}
				else
				{
					w.setVisible(false);
					w.dispose();
					System.exit(1);
				}
			}
			else
			{
				if(keypressed)
				{
					allText = ta.getText();
					if(allText.equals(""))
					{
						System.exit(1);
					}
					else
					{
						f_prompt = new Frame();
						f_prompt.setSize(300,200);
					
						Label l_prompt = new Label("Do you want to save the file?");		f_prompt.add(l_prompt,"Center");
						Panel p_prompt = new Panel();										f_prompt.add(p_prompt,"South");
					
						b_prompt_save_exit = new Button("Save");				p_prompt.add(b_prompt_save_exit);	b_prompt_save_exit.addActionListener(this);
						b_prompt_dontsave_exit = new Button("Don't Save");	p_prompt.add(b_prompt_dontsave_exit);	b_prompt_dontsave_exit.addActionListener(this);
						b_prompt_cancel_exit = new Button("Cancel");			p_prompt.add(b_prompt_cancel_exit);	b_prompt_cancel_exit.addActionListener(this);
						
						
						f_prompt.addWindowListener(this);
						f_prompt.setResizable(false);
						f_prompt.setVisible(true);
					}
				}
				else
				{
					w.setVisible(false);
					w.dispose();
					System.exit(1);
				}
			}
		}
		else
		{
			w.setVisible(false);
			w.dispose();
		}
		if(we.getWindow() == f_find)
		{
			once = false;
			f_find.setVisible(false);
		}
	}
	
	public void windowClosing(WindowEvent we)
	{
		Window w = we.getWindow();
		if(we.getWindow() == f)
		{	
			if(hasfilepath)
			{
				if(keypressed)
				{
					f_prompt = new Frame();
						f_prompt.setSize(300,200);
					
						Label l_prompt = new Label("Do you want to save the file?");		f_prompt.add(l_prompt,"Center");
						Panel p_prompt = new Panel();										f_prompt.add(p_prompt,"South");
					
						b_prompt_save_exit = new Button("Save");				p_prompt.add(b_prompt_save_exit);	b_prompt_save_exit.addActionListener(this);
						b_prompt_dontsave_exit = new Button("Don't Save");	p_prompt.add(b_prompt_dontsave_exit);	b_prompt_dontsave_exit.addActionListener(this);
						b_prompt_cancel_exit = new Button("Cancel");			p_prompt.add(b_prompt_cancel_exit);	b_prompt_cancel_exit.addActionListener(this);
						
						
						f_prompt.addWindowListener(this);
						f_prompt.setResizable(false);
						f_prompt.setVisible(true);
				}
				else
				{
					w.setVisible(false);
					w.dispose();
					System.exit(1);
				}
			}
			else
			{
				if(keypressed)
				{
					allText = ta.getText();
					if(allText.equals(""))
					{
						System.exit(1);
					}
					else
					{
					f_prompt = new Frame();
						f_prompt.setSize(300,200);
					
						Label l_prompt = new Label("Do you want to save the file?");		f_prompt.add(l_prompt,"Center");
						Panel p_prompt = new Panel();										f_prompt.add(p_prompt,"South");
					
						b_prompt_save_exit = new Button("Save");				p_prompt.add(b_prompt_save_exit);	b_prompt_save_exit.addActionListener(this);
						b_prompt_dontsave_exit = new Button("Don't Save");	p_prompt.add(b_prompt_dontsave_exit);	b_prompt_dontsave_exit.addActionListener(this);
						b_prompt_cancel_exit = new Button("Cancel");			p_prompt.add(b_prompt_cancel_exit);	b_prompt_cancel_exit.addActionListener(this);
						
						
						f_prompt.addWindowListener(this);
						f_prompt.setResizable(false);
						f_prompt.setVisible(true);
					}
				}
				else
				{
					w.setVisible(false);
					w.dispose();
					System.exit(1);
				}
			}
		}
		else
		{
			w.setVisible(false);
			w.dispose();
		}
		if(we.getWindow() == f_find)
		{
			once = false;
			f_find.setVisible(false);
		}
	}

	public void windowDeactivated(WindowEvent we)
	{
		
	}
	
	public void windowActivated(WindowEvent we)
	{
		
	}
	
	public void fileopendialog()
	{
			FileDialog fd = new FileDialog(f,"Select",FileDialog.LOAD);
					fd.setVisible(true);
					String fpn1 = fd.getFile();			String fpn2 = fd.getDirectory();
					if(fpn1 != null | fpn2 != null)
					{
						fpn = fpn2 + "/" + fpn1;
						File fi = new File(fpn);	
						ta.setText("");
						FileInputStream fis ;
						try
						{	fis	= new FileInputStream(fi);
							while((c=fis.read())!=-1)
							{
								ta.appendText((char)c + "");
							}
							fis.close();	}
						catch(Exception ex)	
						{	System.out.print(ex.getMessage());	}
						hasfilepath = true;
						keypressed = false;
					}
	}
	
	public void filesavedialog()
	{
				FileDialog fd = new FileDialog(f,"Save As",FileDialog.SAVE);
				fd.setVisible(true);
				String fpn1 = fd.getFile();
				String fpn2 = fd.getDirectory();
				if(fpn1 != null | fpn2 != null)
				{	fpn = fpn2 + "/" + fpn1;
					hasfilepath = true;
					keypressed = false;
					char ch[] = ta.getText().toCharArray();
				
					File fi = new File(fpn);
			
					FileOutputStream fos ;
				
					try
					{
						if(!fi.exists())
						{
							fi.createNewFile();
						}
						fos	= new FileOutputStream(fi);
						for(int i=0;i<ch.length;i++)
						fos.write(ch[i]);
						fos.close();
					}
					catch(Exception ex)
					{
						System.out.print(ex.getMessage());
					}
				}
	}
	
    public static void main(String args[])
    {
    	TextEditor te = new TextEditor();
    }
}