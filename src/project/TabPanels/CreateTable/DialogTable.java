//github.com/oliverwatkins/swing_library
package project.TabPanels.CreateTable;

import org.apache.poi.ss.usermodel.Cell;
import project.FileManipulator;
import project.Professor_Constants;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class DialogTable extends JTable {

	public DialogTable(TableModel model) {

		super(model);

		FileManipulator rf = new FileManipulator();
		ArrayList<JTextArea> ProfessorInfo = new ArrayList<>();


		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {

					int i = rowAtPoint(e.getPoint());

					final JDialog dialog = new JDialog();

					// m.setMaximumSize(new Dimension(400,200));
					// dialog.setPreferredSize(new
					// Dimension(400,dialog.getPreferredSize().height));
					dialog.setLayout(new GridBagLayout());

					GridBagConstraints gbc = new GridBagConstraints();
					gbc.insets = new Insets(2, 2, 2, 2);

					Cell cell;
					Object professorID = getValueAt(i, 0);
					for (int j = 0; j < rf.professorSheet.getRow(0).getPhysicalNumberOfCells(); j++) {

						gbc.gridx = 0;
						gbc.gridy = j;
						gbc.anchor = GridBagConstraints.WEST;
						gbc.fill = GridBagConstraints.HORIZONTAL;


						int row = rf.getMatchedCellFromProfessorSheet(Professor_Constants.ID, professorID.toString());


						//Object valueInTable = getValueAt(i, j);
						Object valueInTable = rf.getCellFromProfessorSheet(j,row);
						//System.out.println("valueInTable=" + valueInTable);

						TableCellRenderer renderer = getCellRenderer(i, 0);
						//System.out.println("TableCellRenderer=" + renderer);

						Object valueInModel = rf.getCellFromProfessorSheet(j,row);
						//System.out.println("valueInModel=" + valueInModel + "\n");

						//Object valueInTable = getValueAt(i, j);

						//TableCellRenderer renderer = getCellRenderer(i, j);

						//Object valueInModel = getModel().getValueAt(i, j);

						Component rendererComponent = renderer
								.getTableCellRendererComponent(getThisTable(),
										valueInModel, false, false, i, j);

						dialog.add(new JLabel("" + rf.getCellFromProfessorSheet(j,0)), gbc);
						gbc.gridx = 1;

						// Rendering with DefaultTableCellRenderer does not seem
						// to work outside
						// of the table. Need to create new component
						if (rendererComponent.getClass().equals(
								DefaultTableCellRenderer.UIResource.class)) {

							JTextArea ta = new JTextArea("" + valueInTable);
							ProfessorInfo.add(ta);
							//ta.setEditable(false);
							dialog.add(new JScrollPane(ta), gbc);
						} else {
							
							dialog.add(rendererComponent, gbc);
						}

					}
					gbc.gridx = 1;
					gbc.gridy = getColumnCount() + 1;
					gbc.fill = GridBagConstraints.NONE;

					JButton button = new JButton("OK");
					button.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							dialog.setVisible(false);
							for (JTextArea jta : ProfessorInfo) {
							    
								System.out.println(jta.getText());
							}
						}
					});
					gbc.anchor = GridBagConstraints.EAST;
					dialog.add(button, gbc);

//					dialog.setLocationRelativeTo(mainFrame);
					dialog.setModal(true);
					dialog.pack();
					dialog.setVisible(true);
				}
			}
		});
	}

	private JTable getThisTable() {
		return this;
	}
}
