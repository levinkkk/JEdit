package com.cc.frm;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import javax.swing.border.*;

public class JEditFont extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JList fontpolics, fontstyle, fontsize;
	JTextField fontpolict, fontstylet, fontsizet;
	JButton[] ColorButton;
	String example;
	JLabel FontResolvent;
	JButton Valider, Annuler;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JTextArea ta;

	public JEditFont(JTextArea ta) {
		this.ta=ta;
		setSize(400, 300);
		setTitle("字体");
		setLocation((screenSize.width - this.getWidth()) / 2,
				(screenSize.height - this.getHeight()) / 2);
		JPanel FontSet, FontView;
		FontSet = new JPanel(new GridLayout(1, 4));
		FontView = new JPanel(new GridLayout(1, 2));
		example = "AaBbCcDdEe";
		FontResolvent = new JLabel(example, JLabel.CENTER);
		FontResolvent.setBackground(Color.WHITE);
		// the Panel for FontPolic's setting
		JPanel Fontpolic = new JPanel();
		Border border = BorderFactory.createLoweredBevelBorder();
		border = BorderFactory.createTitledBorder(border, "字体");
		Fontpolic.setBorder(border);
		// 获得系统所有字体
		Font[] ff = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getAllFonts();
		int taille = ff.length;
		String[] ab = new String[taille];
		for (int i = 0; i < taille; i++) {
			ab[i] = ff[i].getName();

		}
		fontpolics = new JList(ab);
		fontpolics.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fontpolics.setLayoutOrientation(JList.VERTICAL);
		fontpolics.setVisibleRowCount(5);

		fontpolict = new JTextField(ab[0]);
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					//int index = fontpolics.locationToIndex(e.getPoint());
					fontpolict.setText((String) fontpolics.getSelectedValue());
					Font font2 = FontResolvent.getFont();
					Font newfont2 = new Font(fontpolict.getText(), font2
							.getStyle(), font2.getSize());
					FontResolvent.setFont(newfont2);
				}
			}
		};
		fontpolics.addMouseListener(mouseListener);
		Fontpolic.setLayout(new BoxLayout(Fontpolic, BoxLayout.LINE_AXIS));
		Fontpolic.add(fontpolict);
		JScrollPane jspfontpolic = new JScrollPane(fontpolics);
		Fontpolic.setLayout(new BoxLayout(Fontpolic, BoxLayout.PAGE_AXIS));
		Fontpolic.add(jspfontpolic);
		// Font Style
		JPanel Fontstyle = new JPanel();
		Border border1 = BorderFactory.createLoweredBevelBorder();
		border1 = BorderFactory.createTitledBorder(border1, "字形");
		Fontstyle.setBorder(border1);

		String[] styles = { "常规","斜体", "粗体", "粗斜体" };
		fontstyle = new JList(styles);
		fontstyle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fontstyle.setLayoutOrientation(JList.VERTICAL);
		fontstyle.setVisibleRowCount(5);
		fontstylet = new JTextField(styles[0]);
		Fontstyle.setLayout(new BoxLayout(Fontstyle, BoxLayout.LINE_AXIS));
		Fontstyle.add(fontstylet);
		JScrollPane jspfontstyle = new JScrollPane(fontstyle);
		Fontstyle.setLayout(new BoxLayout(Fontstyle, BoxLayout.PAGE_AXIS));
		Fontstyle.add(jspfontstyle);
		MouseListener mouseListener1 = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					//int index = fontstyle.locationToIndex(e.getPoint());
					fontstylet.setText((String) fontstyle.getSelectedValue());
					Font font1 = FontResolvent.getFont();
					int styleti = font1.getStyle();
					;
					if (fontstylet.getText().equals("常规")) {
						styleti = 0;
					}
					if (fontstylet.getText().equals("粗体")) {
						styleti = 1;
					}
					if (fontstylet.getText().equals("斜体")) {
						styleti = 2;
					}
					if (fontstylet.getText().equals("粗斜体")) {
						styleti = 3;
					}
					Font newfont1 = new Font(font1.getName(), styleti, font1
							.getSize());
					FontResolvent.setFont(newfont1);
				}
			}
		};

		fontstyle.addMouseListener(mouseListener1);
		// Font Size
		JPanel Fontsize = new JPanel();

		Border border2 = BorderFactory.createLoweredBevelBorder();
		border2 = BorderFactory.createTitledBorder(border2, "大小");
		Fontsize.setBorder(border2);

		String[] sizes = { "10", "12", "15", "17", "21", "25" };
		fontsize = new JList(sizes);
		fontsize.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fontsize.setLayoutOrientation(JList.VERTICAL);
		fontsize.setVisibleRowCount(5);

		fontsizet = new JTextField(sizes[0]);
		Fontsize.setLayout(new BoxLayout(Fontsize, BoxLayout.LINE_AXIS));
		Fontsize.add(fontsizet);

		JScrollPane jspfontsize = new JScrollPane(fontsize);
		Fontsize.setLayout(new BoxLayout(Fontsize, BoxLayout.PAGE_AXIS));
		Fontsize.add(jspfontsize);
		MouseListener mouseListener2 = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					//int index = fontsize.locationToIndex(e.getPoint());
					fontsizet.setText((String) fontsize.getSelectedValue());
					Font font = FontResolvent.getFont();
					Font newfont = new Font(font.getName(), font.getStyle(),
							Integer.parseInt(fontsizet.getText()));
					FontResolvent.setFont(newfont);
				}
			}
		};
		fontsize.addMouseListener(mouseListener2);
		JPanel ButtonP = new JPanel(new GridLayout(4, 1));
		Valider = new JButton("确认");
		Valider.setSize(80, 30);
		Valider.addActionListener(this);
		Annuler = new JButton("取消");
		Annuler.addActionListener(this);
		ButtonP.add(new JLabel(""));
		ButtonP.add(Valider);
		ButtonP.add(Annuler);
		ButtonP.add(new JLabel(""));
		FontSet.add(Fontpolic);
		FontSet.add(Fontstyle);
		FontSet.add(Fontsize);
		FontSet.add(ButtonP);

		// Font View
		JPanel Fontcolor = new JPanel(new GridLayout(4, 4));
		ColorButton = new JButton[16];
		ColorButton[0] = new JButton("");
		ColorButton[0].setBackground(Color.BLACK);

		ColorButton[1] = new JButton("");
		ColorButton[1].setEnabled(false);
		ColorButton[1].setVisible(false);
		ColorButton[2] = new JButton("");
		ColorButton[2].setEnabled(false);
		ColorButton[2].setVisible(false);
		ColorButton[3] = new JButton("");
		ColorButton[3].setEnabled(false);
		ColorButton[3].setVisible(false);
		ColorButton[4] = new JButton("");
		ColorButton[4].setBackground(Color.BLUE);
		ColorButton[5] = new JButton("");
		ColorButton[5].setBackground(Color.CYAN);
		ColorButton[6] = new JButton("");
		ColorButton[6].setBackground(Color.DARK_GRAY);
		ColorButton[7] = new JButton("");
		ColorButton[7].setBackground(Color.GRAY);
		ColorButton[8] = new JButton("");
		ColorButton[8].setBackground(Color.GREEN);
		ColorButton[9] = new JButton("");
		ColorButton[9].setBackground(Color.LIGHT_GRAY);
		ColorButton[10] = new JButton("");
		ColorButton[10].setBackground(Color.MAGENTA);
		ColorButton[11] = new JButton("");
		ColorButton[11].setBackground(Color.ORANGE);
		ColorButton[12] = new JButton("");
		ColorButton[12].setBackground(Color.PINK);
		ColorButton[13] = new JButton("");
		ColorButton[13].setBackground(Color.WHITE);
		ColorButton[14] = new JButton("");
		ColorButton[14].setBackground(Color.RED);
		ColorButton[15] = new JButton("");
		ColorButton[15].setBackground(Color.YELLOW);
		for (int i = 0; i < 16; i++) {
			ColorButton[i].addActionListener(this);
			Fontcolor.add(ColorButton[i]);
		}
		FontView.add(Fontcolor);
		FontView.add(FontResolvent);
		setLayout(new GridLayout(2, 1));
		add(FontSet);
		add(FontView);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Valider) {
			Font temp = FontResolvent.getFont();
			Color temp2=FontResolvent.getForeground();
			ta.setFont(temp);
			ta.setForeground(temp2);
			this.dispose();
		}
		if (e.getSource() == Annuler) {
			this.dispose();
		}

		for (int i = 0; i < 16; i++) {
			if (e.getSource() == ColorButton[i]) {
				FontResolvent.setForeground(ColorButton[i].getBackground());
			}
		}
	}
	public static void main(String[] args) {
		new JEditFont(null);
	}
}
