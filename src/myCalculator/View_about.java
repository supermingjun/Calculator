package myCalculator;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class View_about extends JDialog {
	
    private static final long serialVersionUID = 4693799019369193520L;
    
    //�������
    private JPanel jp;
    private Font f1 = new Font("Helvetica",Font.PLAIN,15);
	private Font f2 = new Font("Helvetica",Font.PLAIN,20);
	private JTextArea jta;//�ı���
	private JLabel MyWelcome_Label;//��ӭ��
	private ShadePanel shadePanel;//���䱳�����
	private JPanel jp2;//��ɫ�ָ���
	private JLabel MyGithub_Label;//Github����
	private final JLabel MyGithub;//Github��ַ
	private JLabel MyWeibo_Label;//Weibo����
	final JLabel MyWeibo;//Weibo��ַ
	private JTextArea Copyright;//��Ȩ��Ϣ
	
	
    public View_about() {
    	//�������
    	jp = new JPanel();// �����������
    	jp.setLayout(new BorderLayout());
        setContentPane(jp);//��jp������ΪJFrame���������
    	
    	shadePanel = new ShadePanel();// �������䱳�����
    	shadePanel.setLayout(null);
  
        jta = new JTextArea("�汾��2018_05_20_1.0.0\n�����ߣ������� 221600329\n�������ԣ�Java\n"
    			+ "Email:paranoidboy@wangmingjun.cc");
        jta.setFocusable(false);
    	jta.setFont(f2);
    	jta.setEditable(false);
    	jta.setOpaque(false);//����͸��
    	jta.setBounds(10, 10, 400, 180);
    	
    	jp2 = new JPanel();
    	jp2.setBounds(5, 130, 395, 1);
	    jp2.setBorder(BorderFactory.createLineBorder(Color.black));
	    
	    MyWelcome_Label = new JLabel("��ӭ�����ҵ���ҳ:");
	    MyWelcome_Label.setBounds(10, 145, 200, 20);
	    MyWelcome_Label.setFont(f2);
	    
    	MyGithub_Label = new JLabel("Github:");
    	MyGithub_Label.setFont(f2);
    	MyGithub = new JLabel("https://github.com/supermingjun");
    	MyGithub.setFont(f2);
    	MyGithub.setBackground(Color.white);
    	MyGithub.addMouseListener(new InternetMonitor());//���õ������ҳ
    	MyWeibo_Label = new JLabel("Weibo:");
    	MyWeibo_Label.setFont(f2);
    	MyWeibo = new JLabel("https://weibo.com/supermingjun");
    	MyWeibo.setFont(f2);
    	MyWeibo.setBackground(Color.white);
    	MyWeibo.addMouseListener(new InternetMonitor());//���õ������ҳ
    	//MyCnBlog.addMouseListener(new InternetMonitor());
    	Copyright = new JTextArea("     	Copyright @������2018.\n   	  All rights reserved.");
    	Copyright.setFocusable(false);
    	Copyright.setOpaque(false);
    	Copyright.setFont(f1);
    	Copyright.setEditable(false);
    	
    	//������
        jp.add(shadePanel, BorderLayout.CENTER);// �����嵽�����������
        
    	shadePanel.add(jta);
    	shadePanel.add(jp2);
    	shadePanel.add(MyWelcome_Label);
    	shadePanel.add(MyGithub_Label);
    	MyGithub_Label.setBounds(10, 180, 400, 20);
    	shadePanel.add(MyGithub);
    	MyGithub.setBounds(10, 200, 400, 30);
    	shadePanel.add(MyWeibo_Label);
    	MyWeibo_Label.setBounds(10, 240, 400, 20);
    	shadePanel.add(MyWeibo);
    	MyWeibo.setBounds(10, 260, 400, 30);
    	shadePanel.add(Copyright);
    	Copyright.setBounds(10, 300, 400, 50);
    	
    	//���ô������
        setTitle("���ڼ�����");//���ô������
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//DISPOSE_ON_CLOSE�ڴ��ڱ��رյ�ʱ���dispose�������
        setSize(450,380);
        setResizable(false);//��С���ɵ�
        setLocationRelativeTo(null);//������ʾ
        
    	//��ʾ����
    	setVisible(true);
    }
    
    public static void main(String[] args) {
    	// TODO �Զ����ɵķ������
    	//��������
    	try {
    		org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
    		UIManager.put("RootPane.setupButtonVisible", false);
    	} catch (Exception e) {}
		new View_about();
	}
}
//������
class InternetMonitor extends MouseAdapter{
	public void mouseClicked(MouseEvent e){
		JLabel JLabel_temp = (JLabel)e.getSource();
		String J_temp = JLabel_temp.getText();
		System.out.println(J_temp);
		URI uri ;
			try {
				uri = new URI(J_temp);
				Desktop desk=Desktop.getDesktop();
				if(Desktop.isDesktopSupported() && desk.isSupported(Desktop.Action.BROWSE)){
					try {
						desk.browse(uri);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
	}
	public void mouseEntered(MouseEvent e){
		JLabel JLabel_temp = (JLabel)e.getSource();
		JLabel_temp.setForeground(Color.red);
	}
	public void mouseExited(MouseEvent e){
		JLabel JLabel_temp = (JLabel)e.getSource();
		JLabel_temp.setForeground(Color.blue);
	}
}
//�������
class ShadePanel extends JPanel { 
    
    private static final long serialVersionUID = -2644424271663261406L;
    
    public ShadePanel() {
        super();
        setLayout(null);
    }
    
   @Override
    protected void paintComponent(Graphics g1) {// ��д����������
        Graphics2D g = (Graphics2D) g1;
        super.paintComponent(g);// ִ�г��෽��
        int width = getWidth();// ��ȡ�����С
        int height = getHeight();
        // �������ģʽ����
        GradientPaint paint = new GradientPaint(0, 0, Color.pink, 0, height,Color.yellow);//ʵ����ɫ����
        g.setPaint(paint);// ���û�ͼ��������ģʽ
        g.fillRect(0, 0, width, height);// ���ƾ������ؼ�����
    }
}