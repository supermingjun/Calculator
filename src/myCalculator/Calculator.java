package myCalculator;

import javax.swing.UIManager;

public class Calculator {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
				//��������
				try {
					org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
					UIManager.put("RootPane.setupButtonVisible", false);
				} catch (Exception e) {}
				new View_calculator();
	}

}
