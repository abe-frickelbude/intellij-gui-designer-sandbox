package gui_designer_sandbox;

import com.bulenkov.darcula.DarculaLaf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

import static org.burningwave.core.assembler.StaticComponentContainer.Modules;

public class SandboxApp {

    private static final Logger log = LoggerFactory.getLogger(SandboxApp.class);

    public static void main(String[] args) {

        // hack Java >= 9 reflection without having to pass "add-opens" JVM cli parameters
        // needed mainly by the Darcula LAF
        Modules.exportAllToAll();

        configureSwingLookAndFeel();
        createUI();
    }

    private static void createUI() {

        var frame = new JFrame();
        var layoutTestPanel = new LayoutTestPanel();

        frame.setTitle("GUI Designer Sandbox");
        frame.getContentPane().add(layoutTestPanel.mainPanel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 500, 500);
        frame.setVisible(true);

    }

    private static void configureSwingLookAndFeel() {
        try {
            UIManager.installLookAndFeel(new UIManager.LookAndFeelInfo(DarculaLaf.NAME, DarculaLaf.class.getName()));
            UIManager.setLookAndFeel(DarculaLaf.class.getName());

        } catch (Exception ex) {
            // Note: this is not an unrecoverable exception - the application will then use the default look and feel!
            log.error(ex.getMessage(), ex);
        }
    }
}
