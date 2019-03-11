package myCalculator;

import javax.swing.*;
import java.awt.*;
public class View_historyRecord extends JFrame{
	
	private static final long serialVersionUID = 8703561976266114178L;
	//�������
	JPanel historyspace;
	JTextArea jta_history;
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		new View_historyRecord("hello");
	}

	public View_historyRecord(String history) throws HeadlessException {
		//�������
		Font font = new Font("Helvetica", Font.PLAIN, 22);
		historyspace = new JPanel();
		jta_history = new JTextArea();
		jta_history.setFont(new Font("����",Font.BOLD,20));
		jta_history.setEditable(false);
		jta_history.setVisible(true);
		jta_history.setLineWrap(true);//�Զ�����
		jta_history.setText(history);
		jta_history.setFont(font);
		JScrollPane jta_historyScroll = new JScrollPane(jta_history);//ʵ�ֹ���
		//���ò���
		historyspace.setLayout(new BorderLayout());
		//������
		historyspace.add(jta_historyScroll);
		this.add(historyspace);
		
		//���ô������
		
		setTitle("��ʷ��¼");
		setSize(500,400);
		setLocationRelativeTo(null);//��ʾ����Ļ����
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

}
