package myCalculator;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class View_about extends JDialog {
	
    private static final long serialVersionUID = 4693799019369193520L;
    
    //定义组件
    private JPanel jp;
    private Font f1 = new Font("Helvetica",Font.PLAIN,15);
	private Font f2 = new Font("Helvetica",Font.PLAIN,20);
	private JTextArea jta;//文本域
	private JLabel MyWelcome_Label;//欢迎语
	private ShadePanel shadePanel;//渐变背景面板
	private JPanel jp2;//黑色分割线
	private JLabel MyGithub_Label;//Github名称
	private final JLabel MyGithub;//Github地址
	private JLabel MyWeibo_Label;//Weibo名称
	final JLabel MyWeibo;//Weibo地址
	private JTextArea Copyright;//版权信息
	
	
    public View_about() {
    	//创建组件
    	jp = new JPanel();// 创建内容面板
    	jp.setLayout(new BorderLayout());
        setContentPane(jp);//将jp容器置为JFrame的内容面板
    	
    	shadePanel = new ShadePanel();// 创建渐变背景面板
    	shadePanel.setLayout(null);
  
        jta = new JTextArea("版本：2018_05_20_1.0.0\n开发者：王铭君 221600329\n开发语言：Java\n"
    			+ "Email:paranoidboy@wangmingjun.cc");
        jta.setFocusable(false);
    	jta.setFont(f2);
    	jta.setEditable(false);
    	jta.setOpaque(false);//背景透明
    	jta.setBounds(10, 10, 400, 180);
    	
    	jp2 = new JPanel();
    	jp2.setBounds(5, 130, 395, 1);
	    jp2.setBorder(BorderFactory.createLineBorder(Color.black));
	    
	    MyWelcome_Label = new JLabel("欢迎访问我的主页:");
	    MyWelcome_Label.setBounds(10, 145, 200, 20);
	    MyWelcome_Label.setFont(f2);
	    
    	MyGithub_Label = new JLabel("Github:");
    	MyGithub_Label.setFont(f2);
    	MyGithub = new JLabel("https://github.com/supermingjun");
    	MyGithub.setFont(f2);
    	MyGithub.setBackground(Color.white);
    	MyGithub.addMouseListener(new InternetMonitor());//设置点击打开网页
    	MyWeibo_Label = new JLabel("Weibo:");
    	MyWeibo_Label.setFont(f2);
    	MyWeibo = new JLabel("https://weibo.com/supermingjun");
    	MyWeibo.setFont(f2);
    	MyWeibo.setBackground(Color.white);
    	MyWeibo.addMouseListener(new InternetMonitor());//设置点击打开网页
    	//MyCnBlog.addMouseListener(new InternetMonitor());
    	Copyright = new JTextArea("     	Copyright @王铭君2018.\n   	  All rights reserved.");
    	Copyright.setFocusable(false);
    	Copyright.setOpaque(false);
    	Copyright.setFont(f1);
    	Copyright.setEditable(false);
    	
    	//添加组件
        jp.add(shadePanel, BorderLayout.CENTER);// 添加面板到窗体内容面板
        
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
    	
    	//设置窗体参数
        setTitle("关于计算器");//设置窗体标题
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//DISPOSE_ON_CLOSE在窗口被关闭的时候会dispose这个窗口
        setSize(450,380);
        setResizable(false);//大小不可调
        setLocationRelativeTo(null);//居中显示
        
    	//显示窗体
    	setVisible(true);
    }
    
    public static void main(String[] args) {
    	// TODO 自动生成的方法存根
    	//设置主题
    	try {
    		org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
    		UIManager.put("RootPane.setupButtonVisible", false);
    	} catch (Exception e) {}
		new View_about();
	}
}
//监听器
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
//背景面板
class ShadePanel extends JPanel { 
    
    private static final long serialVersionUID = -2644424271663261406L;
    
    public ShadePanel() {
        super();
        setLayout(null);
    }
    
   @Override
    protected void paintComponent(Graphics g1) {// 重写绘制组件外观
        Graphics2D g = (Graphics2D) g1;
        super.paintComponent(g);// 执行超类方法
        int width = getWidth();// 获取组件大小
        int height = getHeight();
        // 创建填充模式对象
        GradientPaint paint = new GradientPaint(0, 0, Color.pink, 0, height,Color.yellow);//实现颜色渐变
        g.setPaint(paint);// 设置绘图对象的填充模式
        g.fillRect(0, 0, width, height);// 绘制矩形填充控件界面
    }
}