package Gradle_Payroll.gui;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class AutoSelectingTextField extends JTextField implements FocusListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 261601773088924898L;
	private String initialText;

    AutoSelectingTextField(String text) {
        super(text);
        this.initialText = text;
        addFocusListener(this);
    }
    
    public AutoSelectingTextField() {
    	super();
		addFocusListener(this);
	}

    public void focusLost(FocusEvent fe) {
        select(0,0);
    }

    public void focusGained(FocusEvent fe) {
            selectAll();
    }
}
