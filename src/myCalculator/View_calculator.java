/**
 * 功能：标准版计算器界面
 * 作者：王铭君 221600329
 * 版本：v1.0
 * 日期：2018-5-20
 */
package myCalculator;

import javax.swing.*;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;

public class View_calculator extends JFrame {

	private static final long serialVersionUID = -6441101230779003754L;
	private boolean flag = false;

	private String s = "";//存储输入的表达式
	private String jtfTemp = "";//暂存显示框内容
	private String jtaTemp = "";//暂存历史记录内容

	// 定义组件
	/************************* 菜单 *******************************/
	private JMenuBar jmb;// 菜单条组件
	private JMenu menu1, menu2, menu3;
	private JMenuItem item1, item2, item3, item4;// menu1查看
	private JMenuItem item5, item6;// menu2编辑
	private JMenuItem item7, item8;// menu3帮助
	/************************* 文本框 *******************************/
	private JTextArea jtf;//显示
	private JTextArea jta;// 历史记录
	/************************* 按钮 *******************************/
	private JButton button_0;// 数字0~9
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JButton button_6;
	private JButton button_7;
	private JButton button_8;
	private JButton button_9;

	private JButton button_plus;// 双目运算符“+”
	private JButton button_minus;// 双目运算符“-”
	private JButton button_multiply;// 双目运算符“*”
	private JButton button_divide;// 双目运算符“/”
	private JButton button_not; // 单目运算符“±”，取反
	private JButton button_point;// 小数点
	private JButton button_equal;// 运算符“=”
	private JButton button_left_bracket;// 运算符“(”,左括号
	private JButton button_right_bracket; // 运算符“)”，右括号
	private JButton button_backspace; // 退格键，删除一个字符
	private JButton button_reciprocal; // 求倒
	private JButton button_c; // 清除所有，所有数据、算式都清除，归零复位
	private JButton button_sq; // 平方
	private JButton button_sqrt; // 开平方

	/*************************************************************/
	private JPanel jp1;// 面板1~7，对应网格布局（7,1）
	private JPanel jp2;
	private JPanel jp3;
	private JPanel jp4;
	private JPanel jp5;
	private JPanel jp6;
	private JPanel jp7;

	/*************************************************************/
	// 构造器
	public View_calculator() throws HeadlessException {

		// 统一设置字体
		Font font = new Font("Helvetica", Font.PLAIN, 18);
		Font font2 = new Font("Helvetica", Font.PLAIN, 22);
		UIManager.put("Button.font", font2);
	    UIManager.put("Menu.font", font);
	    UIManager.put("MenuItem.font", font);

		/**
		 * 创建组件
		 */
		// 菜单组件
		this.jmb = new JMenuBar();
		this.menu1 = new JMenu("查看(V)");
		menu1.setMnemonic('F');
		this.menu2 = new JMenu("编辑(E)");
		menu2.setMnemonic('E');
		this.menu3 = new JMenu("帮助(H)");
		menu3.setMnemonic('H');

		// item1~item10
		this.item1 = new JMenuItem("标准型(T)");
		item1.setMnemonic('T');
		this.item2 = new JMenuItem("科学型(S)");
		item2.setMnemonic('S');
		item2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "当前功能暂未实现","错误",JOptionPane.ERROR_MESSAGE);
			}
		});
		this.item3 = new JMenuItem("历史记录(Y)");
		item3.setMnemonic('Y');
		item3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new View_historyRecord(jtaTemp);
				
			}
		});
		this.item4 = new JMenuItem("退出(O)");
		item4.setMnemonic('O');
		item4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		this.item5 = new JMenuItem("复制(C)");
		item5.setMnemonic('C');
		item5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jtf.getText()==null||jtf.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "复制失败(当前显示区域无字符)","错误",JOptionPane.ERROR_MESSAGE);
				}
				else {
					setSysClipboardText(jtf.getText());
					JOptionPane.showMessageDialog(null, "复制成功");
				}
				
			}
		});
		this.item6 = new JMenuItem("粘贴(P)");
		item6.setMnemonic('P');
		item6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String temp = new String(getClipboardString());
				if(temp == null||temp.equals("")) {
					JOptionPane.showMessageDialog(null, "粘贴失败(当前剪贴板无字符)","错误",JOptionPane.ERROR_MESSAGE);
				}
				else {
					jtf.setText(temp);
					JOptionPane.showMessageDialog(null, "粘贴成功");
				}
				
	
			}
		});
		/*
		 * this.item5 = new JMenuItem("二进制(B)"); item1.setMnemonic('B'); this.item6 =
		 * new JMenuItem("八进制(O)"); item1.setMnemonic('O'); this.item7 = new
		 * JMenuItem("十进制(D)"); item1.setMnemonic('D'); this.item8 = new
		 * JMenuItem("十六进制(H)"); item1.setMnemonic('H');
		 */
		this.item7 = new JMenuItem("查看帮助(V)");
		item7.setMnemonic('V');
		item7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, 
						"* 本计算器实现windows自带的计算器上的大多数基本运算和复合运算\n\n"
						+ "* 关于负号的使用:负号是界面右上角的 ± ,在你输入完一个数字时,点击这个按钮,会直接生成负数,注意只限于以这种方式使用,另外‘1/x’以及‘√’也是如此\n\n"
						+ "* 关于1/x的使用：依照负号使用规则,生成一个数的倒数\n\n"
						+ "* 关于√的使用：依照负号使用规则,生成一个数的二次根\n\n"
						+ "* C按钮是清空输入区的所有内容;←按钮是删除输入的最后一个字符\n\n"
						+ "* 注意：大多数情况下，当你非法输入时,计算器会自动屏蔽你的操作(没有响应)\n\n"
						+ "* 本程序的主要操作逻辑仿照Microsoft Windows自带的计算器实现\n\n"
						+ "Copyright @2018 王铭君");
				
			}
		});
		this.item8 = new JMenuItem("关于计算器(A)");
		item8.setMnemonic('A');
		item8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new View_about();
			}
		});

		// 给菜单项添加快捷方式
		item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.ALT_MASK));//快捷键Alt + 1
		item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.ALT_MASK));//快捷键Alt + 2
		item3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));//快捷键Ctrl + H
		item4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));//快捷键Ctrl + O
		item5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));//快捷键Ctrl + C
		item6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));//快捷键Ctrl + P

		// 文本框
		this.jtf = new JTextArea();//主界面显示框
		this.jta = new JTextArea();//历史记录
		// jtf.setHorizontalAlignment(JTextField.RIGHT);
		jtf.setFont(font);//设置字体
		jtf.setEditable(false);// 设置主界面显示框不可编辑

		// 创建按钮
		button_0 = new JButton("0");
		button_1 = new JButton("1");
		button_2 = new JButton("2");
		button_3 = new JButton("3");
		button_4 = new JButton("4");
		button_5 = new JButton("5");
		button_6 = new JButton("6");
		button_7 = new JButton("7");
		button_8 = new JButton("8");
		button_9 = new JButton("9");

		button_plus = new JButton("+");
		button_minus = new JButton("-");
		button_multiply = new JButton("*");
		button_divide = new JButton("/");
		button_not = new JButton("±");
		button_point = new JButton(".");
		button_equal = new JButton("=");
		button_left_bracket = new JButton("(");
		button_right_bracket = new JButton(")");
		button_backspace = new JButton("←");
		button_reciprocal = new JButton("1/x");
		button_c = new JButton("C");
		button_sq = new JButton("x^");
		button_sqrt = new JButton("√");

		// 注册按钮监听器(可以用数组缩短代码)
		ButtonMonitor buttonMonitor = new ButtonMonitor();
		button_0.addActionListener(buttonMonitor);
		button_1.addActionListener(buttonMonitor);
		button_2.addActionListener(buttonMonitor);
		button_3.addActionListener(buttonMonitor);
		button_4.addActionListener(buttonMonitor);
		button_5.addActionListener(buttonMonitor);
		button_6.addActionListener(buttonMonitor);
		button_7.addActionListener(buttonMonitor);
		button_8.addActionListener(buttonMonitor);
		button_9.addActionListener(buttonMonitor);

		button_plus.addActionListener(buttonMonitor);
		button_minus.addActionListener(buttonMonitor);
		button_multiply.addActionListener(buttonMonitor);
		button_divide.addActionListener(buttonMonitor);
		button_not.addActionListener(buttonMonitor);
		button_point.addActionListener(buttonMonitor);
		button_equal.addActionListener(buttonMonitor);
		button_left_bracket.addActionListener(buttonMonitor);
		button_right_bracket.addActionListener(buttonMonitor);
		button_backspace.addActionListener(buttonMonitor);
		button_reciprocal.addActionListener(buttonMonitor);
		button_c.addActionListener(buttonMonitor);
		button_sq.addActionListener(buttonMonitor);
		button_sqrt.addActionListener(buttonMonitor);

		// 面板
		jp1 = new JPanel(new GridLayout(1, 4, 4, 4));
		jp2 = new JPanel(new GridLayout(1, 4, 4, 4));
		jp3 = new JPanel(new GridLayout(1, 4, 4, 4));
		jp4 = new JPanel(new GridLayout(1, 4, 4, 4));
		jp5 = new JPanel(new GridLayout(1, 4, 4, 4));
		jp6 = new JPanel(new GridLayout(1, 4, 4, 4));
		jp7 = new JPanel(new GridLayout(1, 4, 4, 4));

		// 设置布局
		this.setLayout(new GridLayout(7, 1));//网格布局，七行一列

		/**
		 * 添加组件
		 */

		// 将菜单项添加到菜单上

		menu1.add(item1);
		menu1.add(item2);
		menu1.addSeparator();// 添加分割线
		menu1.add(item3);
		menu1.addSeparator();// 添加分割线
		menu1.add(item4);

		menu2.add(item5);
		menu2.add(item6);

		// menu2.add(item7);
		// menu2.add(item8);

		menu3.add(item7);
		menu3.add(item8);

		// 将菜单添加到菜单条上
		jmb.add(menu1);
		jmb.add(menu2);
		jmb.add(menu3);

		// 将菜单条添加到窗体上
		this.setJMenuBar(jmb);

		// 将文本框添加到面板jp1
		JScrollPane jsp = new JScrollPane(jtf);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);// 设置文本可滚动显示
		jp1.add(jsp);

		// 将按钮添加到面板jp2~jp7
		jp2.add(button_reciprocal);
		jp2.add(button_c);
		jp2.add(button_backspace);
		jp2.add(button_not);

		jp3.add(button_left_bracket);
		jp3.add(button_right_bracket);
		jp3.add(button_sq);
		jp3.add(button_sqrt);

		jp4.add(button_7);
		jp4.add(button_8);
		jp4.add(button_9);
		jp4.add(button_divide);

		jp5.add(button_4);
		jp5.add(button_5);
		jp5.add(button_6);
		jp5.add(button_multiply);

		jp6.add(button_1);
		jp6.add(button_2);
		jp6.add(button_3);
		jp6.add(button_minus);

		jp7.add(button_point);
		jp7.add(button_0);
		jp7.add(button_equal);
		jp7.add(button_plus);

		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		this.add(jp6);
		this.add(jp7);

		// 设置窗体属性
		this.setTitle("计算器 by MJ");
		//this.setIconImage(new ImageIcon("icon.png").getImage());
		//this.pack();/*窗体根据内容自动调整*/
		this.setSize(450, 700);/* 窗体的大小 */
		// this.setLocation(200, 200);/* 左上角的坐标 */
		this.setLocationRelativeTo(null);// 显示在屏幕中央
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/* 设置关闭方式，退出时关闭 */

		// 显示窗体
		this.setVisible(true);
	}

	// 括号匹配函数
	boolean Match(String s) {
		CharStack S = new CharStack();
		int ptr = 0;
		while (ptr != s.length()) {
			if (s.charAt(ptr) == '(')// 返回 char指定索引处(ptr)的值。
			{
				S.Push(s.charAt(ptr));
				ptr++;
			} else if (s.charAt(ptr) == ')') {
				if (S.EmptyStack()) {
					return false;
				} else {
					S.Pop();
					ptr++;
				}
			} else {
				ptr++;
			}
		}
		if (S.EmptyStack())
			return true;
		else
			return false;
	}
	//将字符串复制到剪切板
	public void setSysClipboardText(String writeMe) {  
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();  
        Transferable tText = new StringSelection(writeMe);  
        clip.setContents(tText, null);  
    } 
	//将剪贴板的字符黏贴进显示区域
	public static String getClipboardString() {
        // 获取系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // 获取剪贴板中的内容
        Transferable trans = clipboard.getContents(null);

        if (trans != null) {
            // 判断剪贴板中的内容是否支持文本
            if (trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    // 获取剪贴板中的文本内容
                    String text = (String) trans.getTransferData(DataFlavor.stringFlavor);
                    return text;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

	// 按钮监听器
	class ButtonMonitor implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton J = (JButton) e.getSource();
			int ptr = 0;
			boolean flag3 = false;
			double t;
			String res = "";
			if (flag) {
				jtf.setText("");
				flag = false;
			}

			if (J.getText().equals("←")) // 退格键
			{
				jtfTemp = jtf.getText();
				if (jtfTemp == null || jtfTemp.equals("")) {
				} else {
					jtfTemp = jtfTemp.substring(0, jtfTemp.length() - 1);// 返回字符串的子字符串(0~(n-1))
					jtf.setText(jtfTemp);
				}
			} else if (J.getText().equals("=")) // 等号
			{
				jtfTemp = jtf.getText();
				if (jtfTemp == null || jtfTemp.equals("")) {
				} else {
					if (jtfTemp.charAt(jtfTemp.length() - 1) == '/' || jtfTemp.charAt(jtfTemp.length() - 1) == '*'
							|| jtfTemp.charAt(jtfTemp.length() - 1) == '-'
							|| jtfTemp.charAt(jtfTemp.length() - 1) == '+'
							|| jtfTemp.charAt(jtfTemp.length() - 1) == '.'
							|| jtfTemp.charAt(jtfTemp.length() - 1) == '(') {
						JOptionPane.showMessageDialog(null, "表达式不完整(逻辑错误)！", "Error", JOptionPane.WARNING_MESSAGE);
						jtaTemp = jta.getText();
						jtaTemp = jtaTemp + "\n" + jtf.getText() + " = " + "ERROR(逻辑错误)!";// 历史记录
						jtf.setText(jtf.getText() + " = " + "ERROR!");
						jta.setText(jtaTemp);
					} else {
						jtaTemp = jta.getText();
						jtaTemp = jtaTemp + "\n" + jtf.getText() + " = ";
						jta.setText(jtaTemp);
						s = jtf.getText();
						s += '=';// '='为s结尾标志
						if (!Match(s)) {
							JOptionPane.showMessageDialog(null, "表达式不完整(括号不匹配)！", "Error", JOptionPane.WARNING_MESSAGE);
							jtaTemp = jta.getText();
							jtaTemp += "ERROR(括号不匹配)!";
							jta.setText(jtaTemp);// 历史记录
							jtf.setText(jtf.getText() + " = " + "ERROR!");
						} else {
							res = new Expression(s).EvaluateExpression();// 每次按下=把计算结果显示在jtf区域并且添加一条历史记录到jta区域
							if (res == "ERROR!") {
								jtfTemp = jtf.getText();
								jtfTemp += " = " + res;
								jtf.setText(jtfTemp);
								jtaTemp = jta.getText();
								jtaTemp += res;
								jta.setText(jtaTemp);
								flag = true;
							} else {
								t = Double.valueOf(res);
								if (res.charAt(res.length() - 1) == '0' && res.charAt(res.length() - 2) == '.') {
									jtfTemp = jtf.getText();
									jtfTemp += " = " + (int) t;
									jtf.setText(jtfTemp);
									jtaTemp = jta.getText();
									jtaTemp += (int) t;
									jta.setText(jtaTemp);
								} else {
									jtfTemp = jtf.getText();
									jtfTemp += " = " + t;
									jtf.setText(jtfTemp);
									jtaTemp = jta.getText();
									jtaTemp += t;
									jta.setText(jtaTemp);
								}
								flag = true;
								s = "";
							}
						}
					}
				}
			} else if (J.getText().equals("±"))// 按下的是负号
			{
				jtfTemp = jtf.getText();
				if (jtfTemp == null || jtfTemp.equals("")) {
				} else {
					ptr = jtfTemp.length() - 1;
					if (jtfTemp.charAt(ptr) == '(' || jtfTemp.charAt(ptr) == ')' || jtfTemp.charAt(ptr) == '/'
							|| jtfTemp.charAt(ptr) == '*' || jtfTemp.charAt(ptr) == '-' || jtfTemp.charAt(ptr) == '+'
							|| jtfTemp.charAt(ptr) == '.') {
					} else {
						while ((jtfTemp.charAt(ptr) == '0' || jtfTemp.charAt(ptr) == '1' || jtfTemp.charAt(ptr) == '2'
								|| jtfTemp.charAt(ptr) == '3' || jtfTemp.charAt(ptr) == '4'
								|| jtfTemp.charAt(ptr) == '5' || jtfTemp.charAt(ptr) == '6'
								|| jtfTemp.charAt(ptr) == '7' || jtfTemp.charAt(ptr) == '8'
								|| jtfTemp.charAt(ptr) == '9' || jtfTemp.charAt(ptr) == '.') && ptr >= 0) {
							ptr--;
							if (ptr < 0) {
								break;
							}
						}
						jtfTemp = jtfTemp.substring(0, ptr + 1) + "(" + "-"
								+ jtfTemp.substring(ptr + 1, jtfTemp.length()) + ")";
						jtf.setText(jtfTemp);
					}
				}
			} else if (J.getText().equals("/") || J.getText().equals("*") || J.getText().equals("-") || J.getText().equals("+")) {
				jtfTemp = jtf.getText();
				if (jtfTemp == null || jtfTemp.equals("")) {
				} else {
					if (jtfTemp.charAt(jtfTemp.length() - 1) == '/' || jtfTemp.charAt(jtfTemp.length() - 1) == '*'
							|| jtfTemp.charAt(jtfTemp.length() - 1) == '-'
							|| jtfTemp.charAt(jtfTemp.length() - 1) == '+'
							|| jtfTemp.charAt(jtfTemp.length() - 1) == '('
							|| jtfTemp.charAt(jtfTemp.length() - 1) == '.') {} // 不能在这些符号后面输入运算符
					else {
						jtfTemp = jtf.getText();
						jtfTemp += J.getText();
						jtf.setText(jtfTemp);
					}
				}
			} else if (J.getText() == ".") {
				jtfTemp = jtf.getText();
				if (jtfTemp == null || jtfTemp.equals("")) {
				} else {
					ptr = jtfTemp.length() - 1;
					while ((jtfTemp.charAt(ptr) == '0' || jtfTemp.charAt(ptr) == '1' || jtfTemp.charAt(ptr) == '2'
							|| jtfTemp.charAt(ptr) == '3' || jtfTemp.charAt(ptr) == '4' || jtfTemp.charAt(ptr) == '5'
							|| jtfTemp.charAt(ptr) == '6' || jtfTemp.charAt(ptr) == '7' || jtfTemp.charAt(ptr) == '8'
							|| jtfTemp.charAt(ptr) == '9') && ptr >= 0) {
						ptr--;
						if (ptr >= 0) {
							if (jtfTemp.charAt(ptr) == '.') {
								flag3 = true;
								break;
							}
						} else
							break;
					}

					jtfTemp = jtf.getText();
					if (jtfTemp.charAt(jtfTemp.length() - 1) == '/' || jtfTemp.charAt(jtfTemp.length() - 1) == '*'
							|| jtfTemp.charAt(jtfTemp.length() - 1) == '-'
							|| jtfTemp.charAt(jtfTemp.length() - 1) == '+'
							|| jtfTemp.charAt(jtfTemp.length() - 1) == '('
							|| jtfTemp.charAt(jtfTemp.length() - 1) == ')'
							|| jtfTemp.charAt(jtfTemp.length() - 1) == '.' || flag3) {
					} else {
						jtfTemp = jtf.getText();
						jtfTemp += J.getText();
						jtf.setText(jtfTemp);
					}
				}
			} else if (J.getText() == "(") {
				jtfTemp = jtf.getText();
				if (jtfTemp == null || jtfTemp.equals("")) {
					jtf.setText("(");
				} else {
					if (jtfTemp.charAt(jtfTemp.length() - 1) == '0' || jtfTemp.charAt(jtfTemp.length() - 1) == '1'
							|| jtfTemp.charAt(jtfTemp.length() - 1) == '2'
							|| jtfTemp.charAt(jtfTemp.length() - 1) == '3'
							|| jtfTemp.charAt(jtfTemp.length() - 1) == '4'
							|| jtfTemp.charAt(jtfTemp.length() - 1) == '5'
							|| jtfTemp.charAt(jtfTemp.length() - 1) == '6'
							|| jtfTemp.charAt(jtfTemp.length() - 1) == '7'
							|| jtfTemp.charAt(jtfTemp.length() - 1) == '8'
							|| jtfTemp.charAt(jtfTemp.length() - 1) == '9'
							|| jtfTemp.charAt(jtfTemp.length() - 1) == '.'
							|| jtfTemp.charAt(jtfTemp.length() - 1) == ')') {
					} else {
						jtfTemp += J.getText();
						jtf.setText(jtfTemp);
					}
				}
			} else if (J.getText() == ")") {
				jtfTemp = jtf.getText();
				if (jtfTemp == null || jtfTemp.equals("")) {
				} else {
					if (jtfTemp.charAt(jtfTemp.length() - 1) == '.' || jtfTemp.charAt(jtfTemp.length() - 1) == '/'
							|| jtfTemp.charAt(jtfTemp.length() - 1) == '*'
							|| jtfTemp.charAt(jtfTemp.length() - 1) == '-'
							|| jtfTemp.charAt(jtfTemp.length() - 1) == '+'
							|| jtfTemp.charAt(jtfTemp.length() - 1) == '(' || jtfTemp == "") {
					} else {
						jtfTemp += J.getText();
						jtf.setText(jtfTemp);
					}
				}
			} else if (J.getText() == "√") {
				jtfTemp = jtf.getText();
				if (jtfTemp == null || jtfTemp.equals("")) {
				} else {
					ptr = jtfTemp.length() - 1;
					if (jtfTemp.charAt(ptr) == '(' || jtfTemp.charAt(ptr) == ')' || jtfTemp.charAt(ptr) == '/'
							|| jtfTemp.charAt(ptr) == '*' || jtfTemp.charAt(ptr) == '-' || jtfTemp.charAt(ptr) == '+'
							|| jtfTemp.charAt(ptr) == '.') {
					} else {
						while ((jtfTemp.charAt(ptr) == '0' || jtfTemp.charAt(ptr) == '1' || jtfTemp.charAt(ptr) == '2'
								|| jtfTemp.charAt(ptr) == '3' || jtfTemp.charAt(ptr) == '4'
								|| jtfTemp.charAt(ptr) == '5' || jtfTemp.charAt(ptr) == '6'
								|| jtfTemp.charAt(ptr) == '7' || jtfTemp.charAt(ptr) == '8'
								|| jtfTemp.charAt(ptr) == '9' || jtfTemp.charAt(ptr) == '.') && ptr >= 0) {
							ptr--;
							if (ptr < 0) {
								break;
							}
						}
						jtfTemp = jtfTemp.substring(0, ptr + 1) + "√" + "("
								+ jtfTemp.substring(ptr + 1, jtfTemp.length()) + ")";
						jtf.setText(jtfTemp);
					}
				}
			} else if (J.getText() == "x^") {
				jtfTemp = jtf.getText();
				if (jtfTemp == null || jtfTemp.equals("")) {
				} else {
					ptr = jtfTemp.length() - 1;
					if (jtfTemp.charAt(ptr) == '(' || jtfTemp.charAt(ptr) == ')' || jtfTemp.charAt(ptr) == '/'
							|| jtfTemp.charAt(ptr) == '*' || jtfTemp.charAt(ptr) == '-' || jtfTemp.charAt(ptr) == '+'
							|| jtfTemp.charAt(ptr) == '.') {
					} else {
						while ((jtfTemp.charAt(ptr) == '0' || jtfTemp.charAt(ptr) == '1' || jtfTemp.charAt(ptr) == '2'
								|| jtfTemp.charAt(ptr) == '3' || jtfTemp.charAt(ptr) == '4'
								|| jtfTemp.charAt(ptr) == '5' || jtfTemp.charAt(ptr) == '6'
								|| jtfTemp.charAt(ptr) == '7' || jtfTemp.charAt(ptr) == '8'
								|| jtfTemp.charAt(ptr) == '9' || jtfTemp.charAt(ptr) == '.') && ptr >= 0) {
							ptr--;
							if (ptr < 0) {
								break;
							}
						}
						jtfTemp = jtfTemp + "^";
						jtf.setText(jtfTemp);
					}
				}
			} else if (J.getText() == "1/x") {
				jtfTemp = jtf.getText();
				if (jtfTemp == null || jtfTemp.equals("")) {
				} else {
					ptr = jtfTemp.length() - 1;
					if (jtfTemp.charAt(ptr) == '(' || jtfTemp.charAt(ptr) == ')' || jtfTemp.charAt(ptr) == '/'
							|| jtfTemp.charAt(ptr) == '*' || jtfTemp.charAt(ptr) == '-' || jtfTemp.charAt(ptr) == '+'
							|| jtfTemp.charAt(ptr) == '.') {
					} else {
						while ((jtfTemp.charAt(ptr) == '0' || jtfTemp.charAt(ptr) == '1' || jtfTemp.charAt(ptr) == '2'
								|| jtfTemp.charAt(ptr) == '3' || jtfTemp.charAt(ptr) == '4'
								|| jtfTemp.charAt(ptr) == '5' || jtfTemp.charAt(ptr) == '6'
								|| jtfTemp.charAt(ptr) == '7' || jtfTemp.charAt(ptr) == '8'
								|| jtfTemp.charAt(ptr) == '9' || jtfTemp.charAt(ptr) == '.') && ptr >= 0) {
							ptr--;
							if (ptr < 0) {
								break;
							}
						}
						jtfTemp = jtfTemp.substring(0, ptr + 1) + "(1/" + jtfTemp.substring(ptr + 1, jtfTemp.length())
								+ ")";
						jtf.setText(jtfTemp);
					}
				}
			} else if (J.getText() == "C") {
				jtf.setText("");
				s = "";
			} else {
				jtfTemp = jtf.getText();
				if (jtfTemp == null || jtfTemp.equals("")) {
					jtfTemp += J.getText();
					jtf.setText(jtfTemp);
				} else {
					ptr = jtfTemp.length() - 1;
					if (jtfTemp.charAt(ptr) == ')') {
					} else {
						jtfTemp += J.getText();
						jtf.setText(jtfTemp);
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//设置主题
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
		} catch (Exception e) {}
		new View_calculator();
	}
}
