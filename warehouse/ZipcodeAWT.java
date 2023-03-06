package warehouse;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class ZipcodeAWT {

	private JFrame frame;
	private JTextField textField;
	ZipcodeMgr mgr;
	private JList searchedList;
	ReleaseAWT2 awt;

	public ZipcodeAWT(ReleaseAWT2 awt) {
		this.awt=awt;
		initialize();
		this.frame.setVisible(true);
	}
	public void setDisable()
	{
		this.frame.setVisible(false);
	}

	private void initialize() {
		mgr=new ZipcodeMgr();
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 500);
		frame.setLocationRelativeTo(null);// â�� ��� ������
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ZipcodeAWT.class.getResource("/warehouse/images/searchAddress.png")));
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(searchedList.isSelectionEmpty())
				{
					JOptionPane.showMessageDialog(null,"�ּҸ� �����Ͻʽÿ�.","���",JOptionPane.WARNING_MESSAGE);
					return;
				}
				awt.setAddress(searchedList.getSelectedValue().toString());
				setDisable();
			}
		});
		btnNewButton.setIcon(new ImageIcon(ZipcodeAWT.class.getResource("/warehouse/images/selectBtn.png")));
		btnNewButton.setPreferredSize(new Dimension(70,48));
		frame.getContentPane().add(btnNewButton, BorderLayout.SOUTH);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setContentAreaFilled(false);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);

		JLabel lblNewLabel_1 = new JLabel("���θ�: ");
		panel_1.add(lblNewLabel_1);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword=textField.getText();
				if(keyword.length()==0)
				{
					JOptionPane.showMessageDialog(null,"�˻��� Ű���带 �Է��Ͻʽÿ�.","���",JOptionPane.WARNING_MESSAGE);
					return;
				}
				searchedList.removeAll();
				Vector<String> serachResult=mgr.getSearchedResult(keyword);
				if(serachResult.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "�˻� ����� �����ϴ�.\n��Ȯ�� ���θ��� �Է��Ͽ����� Ȯ���� �ֽʽÿ�.","�˸�",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			
				searchedList.setListData(serachResult);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(ZipcodeAWT.class.getResource("/warehouse/images/addressSearchBtn.png")));
		panel_1.add(btnNewButton_1);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
		
		searchedList = new JList();
		searchedList.setPreferredSize(new Dimension(270,250));
		panel.add(searchedList);
		searchedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
}
