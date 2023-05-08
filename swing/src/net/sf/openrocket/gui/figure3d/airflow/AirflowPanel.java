package net.sf.openrocket.gui.figure3d.airflow;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.nio.IntBuffer;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.fixedfunc.GLLightingFunc;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.glu.GLU;

public class AirflowPanel extends JPanel implements GLEventListener, MouseInputListener, MouseWheelListener, AirflowController.ControllerEventHandler {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(AirflowPanel.class);

	private AirflowController controller;
	private AirflowView view;
	
	private GLCanvas canvas;
	private float ratio = 1.0f;
	private int[] textureIds = new int[1];
	
	public AirflowPanel(AirflowController controllerIn) {
		log.info("AirflowPanel constructor");
		controller = controllerIn;
		view = new AirflowView(controller);
		
		this.setLayout(new BorderLayout());
		
		log.debug("Setting up GL capabilities...");
		final GLProfile glprofile = GLProfile.get(GLProfile.GL2);
		final GLCapabilities glcaps = new GLCapabilities(glprofile);

		canvas = new GLCanvas(glcaps);
		canvas.addGLEventListener(this);
		this.add(canvas, BorderLayout.CENTER);

		canvas.addMouseWheelListener(this);
		canvas.addMouseMotionListener(this);
		canvas.addMouseListener(this);
		controller.addRedrawListener(this);
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		log.info("display handler");
		
		
		GL2 gl = drawable.getGL().getGL2();
		GLU glu = new GLU();
		gl.glClearColor(0,0,0, 1);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glDisable(GLLightingFunc.GL_LIGHTING);

		gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(
				30.0, //vertical FoV degrees
				ratio,
				10, 10000); //front and back clips

		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		gl.glLoadMatrixd(controller.settings.viewMatrix.matrix, 0);

		//gl.glEnable(GL.GL_CULL_FACE);
		//gl.glCullFace(GL.GL_BACK);
		//gl.glFrontFace(GL.GL_CCW);
		gl.glDepthMask(true);
		gl.glDepthFunc(GL.GL_LESS);
		gl.glEnable(GL.GL_DEPTH_TEST);

		gl.glBindTexture(GL.GL_TEXTURE_2D, textureIds[0]);

		view.draw(gl);

		int error = gl.glGetError();
		log.info("error = "+error);
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		log.info("dispose handler");
		GL2 gl = drawable.getGL().getGL2();
		IntBuffer buf = IntBuffer.wrap(textureIds);
		gl.glDeleteTextures(1, buf);
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		log.info("init handler");
		GL2 gl = drawable.getGL().getGL2();

		IntBuffer buf = IntBuffer.wrap(textureIds);
		gl.glGenTextures(1,buf);
		log.info("texture id = "+textureIds[0]);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
		log.info("reshape handler");
		ratio = (float)w/(float)h;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		log.info("mouse clicked");
		canvas.repaint(); // FIXME do this on change
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		log.info("mouse pressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		log.info("mouse released");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		log.info("mouse entered");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		log.info("mouse exited");
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		log.info("mouse dragged");
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		log.info("mouse moved");
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		log.info("mouse wheel");
	}

	@Override
	public void onControllerChange() {
		canvas.repaint();
	}
}
