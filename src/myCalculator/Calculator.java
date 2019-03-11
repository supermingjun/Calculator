package myCalculator;

import javax.swing.UIManager;

public class Calculator {

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
