package vidis.ui.gui;

import java.awt.Color;

import javax.media.opengl.GL;

import org.apache.log4j.Logger;

import vidis.sim.Simulator;
import vidis.ui.config.Configuration;
import vidis.ui.events.AEventHandler;
import vidis.ui.events.AMouseEvent;
import vidis.ui.events.IVidisEvent;
import vidis.ui.events.VidisEvent;
import vidis.ui.model.impl.BasicGuiContainer;
import vidis.ui.model.impl.BasicMarginLayout;
import vidis.ui.model.impl.Button;
import vidis.ui.model.impl.CheckBox;
import vidis.ui.model.impl.CheckChangeListener;
import vidis.ui.model.impl.Label;
import vidis.ui.model.impl.NodeField;
import vidis.ui.model.impl.PacketField;
import vidis.ui.model.impl.PercentMarginLayout;
import vidis.ui.model.impl.TextField;
import vidis.ui.model.impl.guielements.popupWindows.ApplyLayoutPopupWindow;
import vidis.ui.model.impl.guielements.popupWindows.OpenMSIMFilePopupWindow_tree;
import vidis.ui.model.impl.guielements.scrollpane.AScrollpane3D;
import vidis.ui.model.impl.guielements.scrollpane.ScrollPane3D;
import vidis.ui.model.structure.IGuiContainer;
import vidis.ui.model.structure.IVisObject;
import vidis.ui.mvc.api.Dispatcher;


public class Gui extends AEventHandler {

	private static Logger logger = Logger.getLogger( Gui.class );
	
	private BasicGuiContainer mainContainer;
	
	public Label fps;
	
	private AScrollpane3D slider = new ScrollPane3D();
	
	private Menu menu;
	
	
	
	public Gui() {
		logger.debug("Constructor()");
		mainContainer = new BasicGuiContainer();
		mainContainer.setOpaque( false );
		mainContainer.setName("mainContainer");
		initializeRightPanel();
		initializeControls();
		initializeMenu();
	}
	
	private MenuAction createEventRunnable( final int event ) {
		return new MenuAction() {
			@Override
			public void execute(Menu menu, MenuItem item) {
				Dispatcher.forwardEvent(event);
			}
		};
	}
	private void initializeMenu() {
		final MenuItem menu = new MenuItem( null, "Menu", null );
		MenuItem playPause = new MenuItem( menu, "Play", createEventRunnable(IVidisEvent.SimulatorPlay) );
		MenuItem layout = new MenuItem( menu, "Layout", null );
		MenuItem l1 = new MenuItem( layout, "Electric Spring Layout", createEventRunnable(IVidisEvent.LayoutApplyGraphElectricSpring) );
		MenuItem l2 = new MenuItem( layout, "Grid Layout", createEventRunnable(IVidisEvent.LayoutApplyGrid) );
		MenuItem l3 = new MenuItem( layout, "Spiral Layout", createEventRunnable(IVidisEvent.LayoutApplySpiral) );
		MenuItem l4 = new MenuItem( layout, "Random Layout", createEventRunnable(IVidisEvent.LayoutApplyRandom) );
		MenuItem reset = new MenuItem( menu, "Reset", createEventRunnable(IVidisEvent.SimulatorReload) );
		MenuItem load = new MenuItem( menu, "Load", new MenuAction() {
			@Override
			public void execute(Menu menu, MenuItem item) {
				if ( OpenMSIMFilePopupWindow_tree.getInstance().isVisible() ) {
					OpenMSIMFilePopupWindow_tree.getInstance().setVisible( false );
				}
				else {
					OpenMSIMFilePopupWindow_tree.getInstance().setVisible( true );
				}
			}
		});
		MenuItem test = new MenuItem( menu, "Test Runtime Add", new MenuAction() {
			@Override
			public void execute(Menu xmenu, MenuItem item) {
				MenuItem test = new MenuItem( menu, "Test "+xmenu.getParent().getChilds().size(), null );
				xmenu.addChild( test.content );
				test.setMenu(xmenu);
				xmenu.update();
//				xmenu.fireEvent(new VidisEvent(IVidisEvent.GuiResizeEvent, null ) );
			}
		});
		this.menu = new Menu( menu );
		this.menu.update();
		mainContainer.addChild( this.menu );
	}
	
	private void initializeRightPanel() {
		logger.debug("initializeRightPanel");
		BasicGuiContainer rightPanel = new BasicGuiContainer();
		rightPanel.setName("right Panel");
		rightPanel.setColor1( Color.gray );
		rightPanel.setColor2( Color.black );
		rightPanel.setLayout(new PercentMarginLayout(-0.7,1,1,1,-1,-0.30));
		
		
		slider.setLayout(new PercentMarginLayout(-0.0001,-0.0001,-0.0001,-0.0001,-1,-1));
		slider.setOpaque( false );
		slider.setColor1( Color.orange );
		slider.setColor2 ( Color.orange.brighter() );
		
		
//		slider.setColor1(Color.cyan);
//		slider.setColor2(Color.pink);
		rightPanel.addChild(slider);
		
		TextField tf = new TextField();
		tf.setBounds(1, 5, 2, 20);
		slider.addChild( tf );
		
		NodeField nf = new NodeField();
		nf.setBounds(1, 18, 2, 20);
		slider.addChild( nf );
		
		PacketField pf = new PacketField();
		pf.setBounds(1, 11, 2, 20);
		slider.addChild( pf );
		
		CheckBox test = new CheckBox();
		test.setName("myFirstCheckBox");
		test.setText("Wireframe?");
		test.addCheckChangeListener( new CheckChangeListener() {
			@Override
			public void onCheckCange(boolean checked) {
				Configuration.DISPLAY_WIREFRAME = checked;
			}
		});
		test.setChecked(false);
		test.setBounds(1, 1, 1.5, 20);
		
		slider.addChild(test);
		
		mainContainer.addChild(rightPanel);
		
	}
	
	private void initializeControls(){
		logger.debug("initializeControls()");
		BasicGuiContainer container1 = new BasicGuiContainer();
		container1.setLayout(new PercentMarginLayout(1,0.9,-0.9,1,-0.1,-0.1));
//		Button playButton = new Button() {
//			private boolean paused = false;
//			@Override
//			public void renderContainer(GL gl) {
//				if(Simulator.getInstance().getPlayer().isPaused() && !paused) {
//					paused = true;
//					setText("Play");
//				} else if (!Simulator.getInstance().getPlayer().isPaused() && paused) {
//					paused = false;
//					setText("Pause");
//				}
//				super.renderContainer(gl);
//			}
//			@Override
//			public void onClick() {
//				Dispatcher.forwardEvent( IVidisEvent.SimulatorPlay );
//			}
//		};
//		playButton.setName("PLAY BUTTON");
//		playButton.setLayout(new PercentMarginLayout(1,0.9,-0.9,1,-0.1,-0.1));
//		//playButton.setLayout(new PercentMarginLayout(-0.1,0.9,-0.1,-0.1,-0.8,-0.8));
//		playButton.setText("Play");

		BasicGuiContainer container2 = new BasicGuiContainer();
		container2.setLayout(new PercentMarginLayout(-0.2,0.9,-0.8,1,-0.1,-0.1));
		mainContainer.addChild( OpenMSIMFilePopupWindow_tree.getInstance() );
		OpenMSIMFilePopupWindow_tree.getInstance().setVisible( false );
//		Button loadButton = new Button() {
//			private File selectedFile = null;
//			JFileChooser x = new JFileChooser( new File( Configuration.MODULE_PATH )) {
//				private static final long serialVersionUID = 1L;
//
//				@Override
//				public void cancelSelection() {
//					super.cancelSelection();
//					selectedFile = null;
//				}
//				
//				@Override
//				public void approveSelection() {
//					super.approveSelection();
//					selectedFile = getSelectedFile();
//				}
//			};
//			@Override
//			protected void onMouseClicked(MouseClickedEvent e) {
//				x.setVisible( true );
//				x.showOpenDialog( null );
//				if ( selectedFile != null ) { 
//					VidisEvent<File> ve = new VidisEvent<File>( IVidisEvent.SimulatorLoad, x.getSelectedFile() );
//					Dispatcher.forwardEvent( ve );
//				}
//			}
//			@Override
//			public void onClick() {
//				if ( OpenMSIMFilePopupWindow_tree.getInstance().isVisible() ) {
//					OpenMSIMFilePopupWindow_tree.getInstance().setVisible( false );
//				}
//				else {
//					OpenMSIMFilePopupWindow_tree.getInstance().setVisible( true );
//				}
//			}
//		};
//		loadButton.setLayout(new PercentMarginLayout(-0.2,0.9,-0.8,1,-0.1,-0.1));
//		loadButton.setName("LOAD BUTTON");
////		loadButton.setLayout(new PercentMarginLayout(-0.1,-0.1,-0.1,-0.1,-0.8,-0.8));
//		loadButton.setText("Load");
//		
//		Button resetButton = new Button() {
//			@Override
//			public void onClick() {
//				Dispatcher.forwardEvent( IVidisEvent.SimulatorReload );
//			}
//		};
//		resetButton.setLayout(new PercentMarginLayout(-0.4,0.9,-0.6,1,-0.1,-0.1));
//		resetButton.setName("RESET BUTTON");
////		loadButton.setLayout(new PercentMarginLayout(-0.1,-0.1,-0.1,-0.1,-0.8,-0.8));
//		resetButton.setText("Reset");
		mainContainer.addChild(ApplyLayoutPopupWindow.getInstance());
		ApplyLayoutPopupWindow.getInstance().setVisible(false);
//		Button layoutButton = new Button() {
//			@Override
//			public void onClick() {
////				Dispatcher.forwardEvent( IVidisEvent.SimulatorReload );
//				if ( ApplyLayoutPopupWindow.getInstance().isVisible() ) {
//					ApplyLayoutPopupWindow.getInstance().setVisible( false );
//				}
//				else {
//					ApplyLayoutPopupWindow.getInstance().setVisible(true);
//				}
//			}
//		};
//		layoutButton.setLayout(new PercentMarginLayout(-0.6,0.9,-0.6,1,-0.1,-0.1));
//		layoutButton.setName("LAYOUT BUTTON");
////		loadButton.setLayout(new PercentMarginLayout(-0.1,-0.1,-0.1,-0.1,-0.8,-0.8));
//		layoutButton.setText("Layout");
		
		
//		mainContainer.addChild(playButton);
		//container1.addChild(playButton);
//		mainContainer.addChild(loadButton);
		//container2.addChild(loadButton);
//		mainContainer.addChild(resetButton);
//		mainContainer.addChild(layoutButton);
		
		fps = new Label();
		fps.setName("FPS");
		fps.setLayout(new BasicMarginLayout(0, -1, -1, 0, 1.5, 6 ));
		fps.setTextColor( Color.black );
		fps.setText("0fps");
		mainContainer.addChild(fps);
	}
	
	
	@Override
	protected void handleEvent(IVidisEvent e) {
		if ( e instanceof AMouseEvent ) {
			// invert
			AMouseEvent me = (AMouseEvent) e;
			if ( me.guiCoords != null ) {
				me.guiCoords.y = mainContainer.getHeight() - me.guiCoords.y;
			}
			
		}
		// workaround for clicking ( FIXME: gui should use normal mouseEvent with guiCoords set! )
//		if ( e instanceof GuiMouseEvent ) {
//			GuiMouseEvent ge = (GuiMouseEvent) e;
//			ge.where.y = mainContainer.getHeight() - ge.where.y;
//		}
		mainContainer.fireEvent(e);
	}
	
	public void updateBounds( double x, double y, double height, double width ) {
		mainContainer.setBounds(x, y, height, width);
	}
	
	public void render( GL gl ) {
		mainContainer.render(gl);
	}

	public IVisObject getMainContainer() {
		return mainContainer;
	}

	public void addContainer(IGuiContainer data) {
		slider.addChild( data );
	}
	
	public void playPressed() {
		Dispatcher.forwardEvent( IVidisEvent.SimulatorPlay );
	}
	
}

