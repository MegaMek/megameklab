package megameklab.com.ui.dialog;

import megamek.client.ui.swing.util.UIUtil;
import megamek.common.Entity;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.function.Consumer;

public class QueuedUnitList extends JPanel {

    static {
        var protoTypePanel = new JPanel();
        protoTypePanel.setLayout(new BoxLayout(protoTypePanel, BoxLayout.LINE_AXIS));
        var deleteButton = new JButton("X");
        protoTypePanel.add(new JLabel("Armored Personnel Carrier (3025)"));
        protoTypePanel.add(Box.createHorizontalGlue());
        protoTypePanel.add(deleteButton);
        protoTypePanel.setVisible(true);
        rowSize = protoTypePanel.getPreferredSize();
    }

    private final int minimumListLength;
    private final Consumer<Integer> deleteCallBack;

    private static Dimension rowSize;
    private static Dimension listSize;

    QueuedUnitList(List<Entity> units, int minimumListLength, Consumer<Integer> deleteCallBack) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setOpaque(true);
        setAlignmentY(JComponent.TOP_ALIGNMENT);
        this.minimumListLength = minimumListLength;
        this.deleteCallBack = deleteCallBack;
        setUnits(units);
    }

    public void setUnits(List<Entity> units) {
        removeAll();
        int rowIndex = 0;
        for (Entity unit : units) {
            add(new RowPanel(unit, deleteCallBack, rowIndex));
            rowIndex++;
        }
//        units.forEach(unit -> add(new RowPanel(unit, deleteCallBack, )));
        add(new JPanel());
        add(Box.createVerticalGlue());
        revalidate();
    }

    @Override
    public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

//    @Override
//    public Dimension getMaximumSize() {
//        return new Dimension(listSize.width, 100000);
//    }

    private Dimension getListSize() {
        if (rowSize == null) {
            return new Dimension(0, 0);
        } else if (listSize == null) {
            listSize = new Dimension(rowSize.width, rowSize.height * Math.max(getComponentCount(),minimumListLength));
        }
        return listSize;
    }

    static class RowPanel extends UIUtil.FixedYPanel {



        private final JButton deleteButton = new JButton("X");
        private final Entity entity;
        private final int rowIndex;

        RowPanel(Entity entity, Consumer<Integer> deleteCallBack, int index) {
            this.entity = entity;
            rowIndex = index;
//            setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
            setLayout(null);
            setOpaque(true);
//            setBorder(new EmptyBorder(0, 10, 0, 10));

            JLabel nameLabel = new JLabel(entity.getShortNameRaw());
            nameLabel.setBounds(10, 0, rowSize.width - 10, rowSize.height);
            add(nameLabel);

//            add(Box.createHorizontalGlue());
            Dimension buttonSize = deleteButton.getPreferredSize();
            deleteButton.addActionListener(e -> deleteCallBack.accept(rowIndex));
            deleteButton.setForeground(UIUtil.uiLightRed());
            deleteButton.setBounds(rowSize.width - buttonSize.width - 10, 0, buttonSize.width, buttonSize.height);
            deleteButton.addMouseListener(mouseListener);
            deleteButton.setVisible(false);
            deleteButton.repaint();
            add(deleteButton);

            addMouseListener(mouseListener);
        }

        @Override
        public Dimension getPreferredSize() {
            return rowSize;
        }

        @Override
        public Dimension getMinimumSize() {
            return rowSize;
        }

        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(UIUtil.alternateTableBGColor());
                deleteButton.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(null);
                deleteButton.setVisible(false);
            }
        };

    }

}
