/*
 * Copyright (C) 2026 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMekLab was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.generalUnit;

import java.awt.Desktop;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import megamek.client.ui.baseComponents.BooksIcon;
import megamek.client.ui.baseComponents.DeleteIcon;
import megamek.client.ui.baseComponents.MulLinkIcon;
import megamek.client.ui.dialogs.SourceChooserDialog;
import megamek.client.ui.util.DisplayTextField;
import megamek.common.SourceBook;
import megamek.common.SourceBooks;
import megamek.logging.MMLogger;

/**
 * A reusable source-selection widget: a read-only display field plus buttons to clear the source, pick from the list of
 * well-known sourcebooks (via {@link SourceChooserDialog}), and open the selected sourcebook's Master Unit List page.
 * This is the same "Source" control used by the standard unit editor's basic info view, packaged for reuse (for example
 * in the Battlefield Support Asset editor). It carries no "Published record sheet" concept.
 */
public class SourceView extends Box {

    private static final MMLogger LOGGER = MMLogger.create(SourceView.class);

    private final SourceBooks sourceBooks = new SourceBooks();
    private final DisplayTextField sourceField = new DisplayTextField(15);
    private final JButton mulLinkButton = new JButton(new MulLinkIcon());
    private final String defaultTooltip;

    private String sourceAbbreviation = "";
    private Consumer<String> changeListener;

    public SourceView(String tooltip) {
        super(BoxLayout.LINE_AXIS);
        this.defaultTooltip = tooltip;
        sourceField.setEditable(false);
        sourceField.setToolTipText(tooltip);
        add(sourceField);

        JButton clearButton = new JButton(new DeleteIcon());
        clearButton.setToolTipText("Clear the source.");
        clearButton.addActionListener(e -> setSource(""));
        add(clearButton);

        JButton editButton = new JButton(new BooksIcon());
        editButton.setToolTipText("Select from the list of well-known sources.");
        editButton.addActionListener(e -> {
            String result = SourceChooserDialog.showMultiChoiceDialog(getRootPane(), true, sourceAbbreviation);
            if (result != null) {
                setSource(result);
            }
        });
        add(editButton);

        mulLinkButton.setToolTipText("Open the selected sourcebook's Master Unit List page.");
        mulLinkButton.addActionListener(e -> openSourcebookMUL(sourceAbbreviation));
        add(mulLinkButton);

        updateControls();
    }

    /** Sets a listener invoked with the normalized source string whenever the source changes. */
    public void setChangeListener(Consumer<String> listener) {
        this.changeListener = listener;
    }

    public String getSource() {
        return sourceAbbreviation;
    }

    public void setSource(String source) {
        sourceAbbreviation = SourceBooks.normalizeSourceList(source == null ? "" : source);
        updateControls();
        if (changeListener != null) {
            changeListener.accept(sourceAbbreviation);
        }
    }

    private void updateControls() {
        List<String> sources = SourceBooks.splitSourceList(sourceAbbreviation);
        sourceField.setText(sourceAbbreviation);
        sourceField.setToolTipText(sources.isEmpty() ? defaultTooltip : sourceAbbreviation);
        mulLinkButton.setEnabled(!sourceBooks.loadSourceBooks(sourceAbbreviation).stream()
              .filter(SourceView::hasMulUrl).toList().isEmpty());
    }

    private void openSourcebookMUL(String sourceList) {
        List<SourceBook> withMul = sourceBooks.loadSourceBooks(sourceList).stream()
              .filter(SourceView::hasMulUrl).toList();
        if (withMul.isEmpty()) {
            return;
        }
        SourceBook sourceBook = (withMul.size() == 1) ? withMul.get(0) : chooseSourcebookMUL(withMul).orElse(null);
        if (sourceBook != null) {
            try {
                Desktop.getDesktop().browse(URI.create(sourceBook.getMul_url()));
            } catch (Exception ex) {
                LOGGER.error("", ex);
                JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private Optional<SourceBook> chooseSourcebookMUL(List<SourceBook> withMul) {
        Object[] options = withMul.stream().map(SourceView::displayName).toArray();
        Object choice = JOptionPane.showInputDialog(this, "Open Sourcebook MUL", "Open Sourcebook MUL",
              JOptionPane.PLAIN_MESSAGE, null, options, options.length > 0 ? options[0] : null);
        if (choice == null) {
            return Optional.empty();
        }
        for (SourceBook book : withMul) {
            if (displayName(book).equals(choice)) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    private static boolean hasMulUrl(SourceBook sourceBook) {
        return (sourceBook.getMul_url() != null) && !sourceBook.getMul_url().isBlank();
    }

    private static String displayName(SourceBook sourceBook) {
        String abbrev = (sourceBook.getAbbrev() == null || sourceBook.getAbbrev().isBlank())
              ? sourceBook.getMul_url()
              : sourceBook.getAbbrev();
        String title = (sourceBook.getTitle() == null || sourceBook.getTitle().isBlank()) ? abbrev
              : sourceBook.getTitle();
        return title.equals(abbrev) ? title : "%s (%s)".formatted(title, abbrev);
    }
}
