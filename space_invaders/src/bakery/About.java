package bakery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the About dialog.
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-15
 */
class About implements ActionListener
{
    private JFrame frame;

    /**
     * Construct a new About dialog
     *
     * @param frame
     *     the parent frame
     */
    About(JFrame frame)
    {
        this.frame = frame;
    }

    /**
     * Launch the About dialog (triggered by ActionEvent)
     *
     * @param actionEvent
     *     the ActionEvent
     */
    @Override public void actionPerformed(ActionEvent actionEvent)
    {
        JOptionPane.showMessageDialog(this.frame,
                                      "Bakery Manager v.1.0\n\u00a92014 Brian" +
                                          " " +
                                          "Desnoyers and Lucas Magee", "About",
                                      JOptionPane.PLAIN_MESSAGE);
    }
}
