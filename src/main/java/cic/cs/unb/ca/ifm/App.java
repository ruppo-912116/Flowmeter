package cic.cs.unb.ca.ifm;

import cic.cs.unb.ca.flow.FlowMgr;
import cic.cs.unb.ca.flow.ui.FlowMonitorPane;
import cic.cs.unb.ca.guava.GuavaMgr;
import cic.cs.unb.ca.ifm.ui.MainFrame;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;

public class App {
	public static final Logger logger = LoggerFactory.getLogger(App.class);
	public static FlowMonitorPane flow;
	public static MainFrame frame;
	public static void init() {
		FlowMgr.getInstance().init();
		GuavaMgr.getInstance().init();
	}

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		BasicConfigurator.configure();

		/*try {
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}*/
		
		EventQueue.invokeLater(() -> {
            try {
                init();
                new MainFrame();
				Server server = ServerBuilder.forPort(50052).build();

				try{
					server.start();
					server.awaitTermination();
				} catch (IOException | InterruptedException e){
					System.out.println(e);
				}

//				logger.info("run");
//				flow = new FlowMonitorPane();
//				flow.startTrafficFlow();
            } catch (Exception e) {
				logger.debug(e.getMessage());
            }
        });

	}
}
