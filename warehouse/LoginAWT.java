package warehouse;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

// ---------------------------------�α��� �г�------------------------------------------------------------------------

class LogIn extends JPanel{ 
	public LoginAWT win;
	JPanel imgBack;
	JTextField idTextField;
	JPasswordField passTextField;
	JButton btnNewButton, signUpBtn;
	JLabel title;
	JLabel msgl, passLabel, idLabel;
	LoginMgr mgr = new LoginMgr();
	
	// �α��� ȭ�� ����
	public LogIn(LoginAWT win) {
		this.win = win;	
		setBackground(new Color(239, 239, 239));
		setLayout(null);
		setBounds(0, 0, 390, 570);
		
		//// �ΰ��̹��� ��� �޹��
		imgBack = new JPanel();
		imgBack.setBounds(0, 0, 440, 130);
		imgBack.setLayout(null);
		imgBack.setBackground(Color.white);
			
		title = new JLabel();
		title.setIcon(new ImageIcon(".\\images\\loginTitle.png"));
		title.setBounds(30, 10, 350, 100);
		imgBack.add(title);
		add(imgBack);
		//// �α��� ��ư
		btnNewButton = new JButton(new ImageIcon(".\\images\\loginBtn.png"));
		btnNewButton.setBackground(Color.white);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.addActionListener(new MyActionListener2());
		btnNewButton.setBounds(265, 240, 75, 75);
		add(btnNewButton);
		
		///// �̸� �Է�ĭ
		idTextField = new JTextField();
		idTextField.setBounds(125, 243, 130, 21);
		add(idTextField);
		idTextField.setColumns(10);
		
		//// ��й�ȣ �Է�ĭ
		passTextField = new JPasswordField();
		passTextField.setBounds(125, 290, 130, 21);
		add(passTextField);
		passTextField.setColumns(40);
		
		//// ID ��
		passLabel = new JLabel("�̸�");
		passLabel.setBounds(60, 247, 72, 15);
		add(passLabel);
		
		//// Password ��
		idLabel = new JLabel("��ȭ��ȣ");
		idLabel.setBounds(60, 294, 72, 15);
		add(idLabel);
		
		//// ȸ������ ��ư
		signUpBtn = new JButton(new ImageIcon("C:/Java2/myJava/warehouse/images/signUpBtn.png"));
		signUpBtn = new JButton(new ImageIcon(".\\images\\signUpBtn.png"));
		signUpBtn.setBackground(new Color(255, 255, 255));
		signUpBtn.setBounds(280, 420, 97, 23);
		signUpBtn.setBorderPainted(false);
		signUpBtn.setContentAreaFilled(false);
		signUpBtn.setFocusPainted(false);
		add(signUpBtn);
		
		signUpBtn.addActionListener(new MyActionListener());	
	}
	///// ȸ�������������� �̵�
	class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("SignUp");
		}
	}
	///// �α��α��
	class MyActionListener2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(idTextField.getText().equals("")) {
				new MDialog3(win,"�˸�",true);
			}else {
				if(passTextField.getText().equals("")) { 				///// ��ĭ �Է��ϸ�
					new MDialog2(win,"�˸�",true); 				///// �Է��϶�� �˸�
				}else {
					if(mgr.loginChk(passTextField.getText(),idTextField.getText() )) { ///// DBȮ��
						win.dispose(); 											///// LoginAWT �������
						new MainAWT(); 									///// ����ȭ������ �̵�
					}else {
						System.out.println("����");					
						new MDialog(win, "�˸�", true);			/////  DBȮ�� �� ������ �˸�
					}
				}
			}
		}
	}
	///// �α��� ���� �˸�
	class MDialog extends Dialog implements ActionListener{
		
		Button b;
		Label label;

		public MDialog(Frame owner, String title, boolean modal) {
			super(owner, title, modal);
			addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent e) {
			     dispose();
			    }
		 });
			b = new Button("Ȯ��");
			b.setBounds(120, 110, 50, 20);
			b.addActionListener(this);
			add(b);
			
			label = new Label("��ġ�ϴ� ��ȣ�� �����ϴ�.", label.CENTER);
			add(label);
			
			layset();
		}
		
		public void layset() {
			int width = 300;
			int height = 150;
			setSize(width, height);
			setLocationRelativeTo(null);
			setVisible(true);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
		
	}
	
	///// �Է°� ���� �˸� ( ��й�ȣ )
	class MDialog2 extends Dialog implements ActionListener{
		
		Button b;
		Label label;

		public MDialog2(Frame owner, String title, boolean modal) {
			super(owner, title, modal);
			addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent e) {
			     dispose();
			    }
		 });
			b = new Button("Ȯ��");
			b.setBounds(120, 110, 50, 20);
			b.addActionListener(this);
			add(b);
			
			label = new Label("��ȭ��ȣ�� �Է����ּ���", label.CENTER);
			add(label);
			
			layset();
		}
		
		public void layset() {
			int width = 300;
			int height = 150;
			setSize(width, height);
			setLocationRelativeTo(null);
			setVisible(true);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
		
	}
    ///// �Է°� ���� �˸� ( ID )
	class MDialog3 extends Dialog implements ActionListener{
		
		Button b;
		Label label;

		public MDialog3(Frame owner, String title, boolean modal) {
			super(owner, title, modal);
			addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent e) {
			     dispose();
			    }
		 });
			b = new Button("Ȯ��");
			b.setBounds(120, 110, 50, 20);
			b.addActionListener(this);
			add(b);
			
			label = new Label("�̸��� �Է����ּ���", label.CENTER);
			add(label);
			
			layset();
		}
		
		public void layset() {
			int width = 300;
			int height = 150;
			setSize(width, height);
			setLocationRelativeTo(null);
			setVisible(true);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}
////----------------------------------2 ��° �г� ( ȸ������ )-------------------------------------------------------

class SignUp extends JPanel implements ActionListener {

	LoginAWT win;
	Image img;
	Container contentPane;
	JLabel lblNewLabel, lblNewLabel4, lblNewLabel5;
	JLabel title2;
	JTextField pwdTexxtField;
	JTextField textfield, textfield2;
	JComboBox cbx;
	LoginMgr mgr = new LoginMgr();
	String[] company = { "LG", "SAMSUNG", "SKT" };

	///// ȸ�� �ΰ��̹���
	ImageIcon[] images = { new ImageIcon(".\\images\\lg.png"), new ImageIcon(".\\images\\samsung.png"),
			new ImageIcon(".\\images\\skt.png") };
	JLabel imgLabel = new JLabel(images[0]);

	public SignUp(LoginAWT win) {
		this.win = win;
		setBackground(new Color(239, 239, 239));
		setLayout(null);
		setBounds(0, 0, 383, 514);

		JPanel imgBack = new JPanel();
		imgBack.setBounds(0, 0, 383, 100);
		imgBack.setBackground(Color.white);

		title2 = new JLabel();
		title2.setIcon(new ImageIcon(".\\images\\signUpTitle.png"));
		title2.setBounds(30, 10, 350, 100);
		imgBack.add(title2);
		add(imgBack); ///// �̹��� �޹�� �׸���

		///// ��ȭ��ȣ �Է��ʵ� ( ���ڸ� �Է� )
		pwdTexxtField = new JTextField();
		pwdTexxtField.setBounds(146, 253, 130, 21);
		add(pwdTexxtField);
		pwdTexxtField.setColumns(40);

		///// ��ȭ��ȣ ��
		lblNewLabel = new JLabel("��ȭ��ȣ");
		lblNewLabel.setBounds(62, 256, 72, 15);
		add(lblNewLabel);

		///// �̸� ��
		lblNewLabel4 = new JLabel("�̸�");
		lblNewLabel4.setBounds(62, 206, 72, 15);
		add(lblNewLabel4);

		///// �̸� �Է�ĭ
		textfield = new JTextField();
		textfield.setBounds(146, 203, 130, 21);
		add(textfield);

		///// �ּ� ��
		lblNewLabel5 = new JLabel("�ּ�");
		lblNewLabel5.setBounds(62, 306, 72, 15);
		add(lblNewLabel5);

		///// �ּ� �Է�ĭ
		textfield2 = new JTextField();
		textfield2.setBounds(146, 303, 130, 21);
		add(textfield2);

		///// ȸ������ ��ư
		JButton joinBtn = new JButton(new ImageIcon(".\\images\\joinBtn.png"));
		joinBtn.setBackground(new Color(255, 255, 255));
		joinBtn.setBounds(280, 425, 97, 23);
		joinBtn.setBorderPainted(false);
		joinBtn.setContentAreaFilled(false);
		joinBtn.setFocusPainted(false);
		joinBtn.addActionListener(this);
		add(joinBtn);

		///// �ڷΰ��� ��ư
		JButton backBtn = new JButton(new ImageIcon(".\\images\\backBtn.png"));
		backBtn.setBackground(new Color(255, 255, 255));
		backBtn.setBounds(20, 425, 25, 25);
		backBtn.setBorderPainted(false);
		backBtn.setContentAreaFilled(false);
		backBtn.setFocusPainted(false);
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				win.change("LogIn");
			}
		});
		add(backBtn);

	}

	///// ����̹��� �׸���
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 56, 0, this);
	}

	///// ȸ������ ��ɱ���
	@Override
	public void actionPerformed(ActionEvent e) {

		MemberBean bean = new MemberBean();
		bean.setTel(pwdTexxtField.getText());
		bean.setAddress(textfield2.getText());
		bean.setName(textfield.getText());

		if (textfield.getText().equals("")) { // �ʵ尪�� �ƹ��͵� ������
			new MDialog(win, "�˸�", true); // �Է��϶�� �˸�
		} else {
			if (mgr.signUpChk(bean)) { // DB �ߺ�üũ
				mgr.insert(bean); // DB�� ȸ������ ����
				JOptionPane.showMessageDialog(null, "ȸ������ �Ϸ�!", "����", JOptionPane.PLAIN_MESSAGE);
				win.change("LogIn");
			} else
				new MDialog2(win, "�˸�", true); // �ߺ�Ȯ�ξ˸�
		}
	}
}

///// �Է°� ���� Ȯ�ξ˸�
class MDialog extends Dialog implements ActionListener {

	Button b;
	Label label;

	public MDialog(Frame owner, String title, boolean bool) {

		super(owner, title, bool);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		b = new Button("Ȯ��");
		b.setBounds(120, 110, 50, 20);
		b.addActionListener(this);
		add(b);

		label = new Label("��й�ȣ�� �Է����ּ���", label.CENTER);
		add(label);

		layset();
	}

	public void layset() {
		int width = 300;
		int height = 150;
		setSize(width, height);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
}

///// DB�ߺ� Ȯ�ξ˸�
class MDialog2 extends Dialog implements ActionListener {
	Button b;
	Label label;

	public MDialog2(Frame owner, String title, boolean bool) {

		super(owner, title, bool);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		b = new Button("Ȯ��");
		b.setBounds(120, 110, 50, 20);
		b.addActionListener(this);
		add(b);

		label = new Label("�̹� ��ϵ� ��ȣ�Դϴ�.", label.CENTER);
		add(label);

		layset();
	}

	public void layset() {
		int width = 300;
		int height = 150;
		setSize(width, height);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
}

////////////////////
////// ������///////
////////////////////
public class LoginAWT extends JFrame {
	public LogIn jpanel01 = null; // �α����г�
	public SignUp jpanel02 = null; // ȸ������ �г�

	public LoginAWT() {
		setTitle("");// â�� Ÿ��Ʋ
		setSize(400, 500);// �������� ũ��
		setResizable(false);// â�� ũ�⸦ �������� ���ϰ�
		setLocationRelativeTo(null);// â�� ��� ������
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

	}

	///// �г� ��ȯ
	public void change(String panelName) {
		if (panelName.equals("LogIn")) {
			getContentPane().removeAll();
			getContentPane().add(jpanel01);
			revalidate();
			repaint();
		} else {
			getContentPane().removeAll();
			getContentPane().add(jpanel02);
			revalidate();
			repaint();
		}
	}

	public static void main(String[] args) {
		LoginAWT win = new LoginAWT();
		win.setTitle("");
		win.jpanel01 = new LogIn(win);
		win.jpanel02 = new SignUp(win);
		win.getContentPane().add(win.jpanel01);
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		win.setVisible(true);
	}

}
