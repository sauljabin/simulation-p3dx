/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>, Jorge Parra <thejorgemylio@gmail.com>.
 * <p>
 * This file is part of SimulationP3DX.
 * <p>
 * SimulationP3DX is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.util;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;

public class JIntegerField extends JTextField implements Serializable {

    public JIntegerField() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();
                }
            }
        });
    }

    public JIntegerField(Integer value) {
        setValue(value);
    }

    @Override
    public void setText(String text) {
        if (text.matches("[0-9]+"))
            super.setText(text);
        else
            throw new NumberFormatException("Not integer");
    }

    /**
     * Return integer value in the field
     *
     * @return Integer, null if is empty
     */
    public Integer getValue() {
        if (getText() == null || getText().trim().isEmpty() || !getText().matches("[0-9]+"))
            return null;
        return new Integer(getText().trim());
    }

    public void setValue(Integer i) {
        setText(i.toString());
    }

}
