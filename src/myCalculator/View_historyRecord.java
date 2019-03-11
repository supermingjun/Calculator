package myCalculator;

import javax.swing.*;
import java.awt.*;
public class View_historyRecord extends JFrame{
	
	private static final long serialVersionUID = 8703561976266114178L;
	//定义组件
	JPanel historyspace;
	JTextArea jta_history;
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new View_historyRecord("hello");
	}

	public View_historyRecord(String history) throws HeadlessException {
		//创建组件
		Font font = new Font("Helvetica", Font.PLAIN, 22);
		historyspace = new JPanel();
		jta_history = new JTextArea();
		jta_history.setFont(new Font("宋体",Font.BOLD,20));
		jta_history.setEditable(false);
		jta_history.setVisible(true);
		jta_history.setLineWrap(true);//自动换行
		jta_history.setText(history);
		jta_history.setFont(font);
		JScrollPane jta_historyScroll = new JScrollPane(jta_history);//实现滚动
		//设置布局
		historyspace.setLayout(new BorderLayout());
		//添加组件
		historyspace.add(jta_historyScroll);
		this.add(historyspace);
		
		//设置窗体参数
		
		setTitle("历史记录");
		setSize(500,400);
		setLocationRelativeTo(null);//显示在屏幕中央
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

}
