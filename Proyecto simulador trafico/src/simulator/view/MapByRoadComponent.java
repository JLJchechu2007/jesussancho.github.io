package simulator.view;

	import java.awt.Color;
	import java.awt.Dimension;
	import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.awt.Image;
	import java.awt.RenderingHints;
	import java.io.File;
	import java.io.IOException;
	import java.util.Collection;

	import javax.imageio.ImageIO;
	import javax.swing.JPanel;
	import javax.swing.SwingUtilities;

	import simulator.control.Controller;
	import simulator.model.Event;
	import simulator.model.Junction;
	import simulator.model.Road;
	import simulator.model.RoadMap;
	import simulator.model.TrafficSimObserver;
	import simulator.model.Vehicle;
	import simulator.model.VehicleStatus;

public class MapByRoadComponent extends JPanel implements TrafficSimObserver{
	private static final long serialVersionUID = 1L;

	private static final int _JRADIUS = 10;

	private static final Color _BG_COLOR = Color.WHITE;
	private static final Color _JUNCTION_COLOR = Color.BLUE;
	private static final Color _JUNCTION_LABEL_COLOR = new Color(200, 100, 0);
	private static final Color _GREEN_LIGHT_COLOR = Color.GREEN;
	private static final Color _RED_LIGHT_COLOR = Color.RED;

	private RoadMap _map;

	private Image _car;

	MapByRoadComponent(Controller ctrl) {
		setPreferredSize(new Dimension(300,200));
		initGUI();
		ctrl.addObserver(this);
	}

	private void initGUI() {
		_car = loadImage("car.png");
	}

	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		// clear with a background color
		g.setColor(_BG_COLOR);
		g.clearRect(0, 0, getWidth(), getHeight());

		if (_map == null || _map.getJunctions().size() == 0) {
			g.setColor(Color.red);
			g.drawString("No map yet!", getWidth() / 2 - 50, getHeight() / 2);
		} else {
			updatePrefferedSize();
			drawMap(g);
		}
	}

	private void drawMap(Graphics g) {
		drawRoads(g);
		drawVehicles(g);
	}

	private void drawRoads(Graphics g) {
		int i = 0;
		for (Road r : _map.getRoads()) {

			// the road goes from (x1,y1) to (x2,y2)
			int x1 = 50;
			int y = (i+1)*50;
			int x2 = getWidth()-100;
			
			Junction jSrc=r.getSrc();
			Junction jDest=r.getDest();
			
			//Pintamos junction inicio
			
			drawJunctioninicio(g, jSrc, x1, y);
			
			//Pintamos junction salida
			drawJunctionFin(g, jDest, x2, y,r);
			// choose a color for the arrow depending on the traffic light of the road
			
			
			int A,B,C;
			A = r.getTotalCO2();
			B = r.getContLimit();
			C = (int) Math.floor(Math.min((double) A/(1.0 + (double) B),1.0) / 0.19);
			
			String img = "cont_"+C+".png";
			Image cont = loadImage(img);
			g.drawImage(cont, x2 + 50, y - 22, 32, 32, this);
			
			String w = r.getWeather().toStringView();
			String wimg = w + ".png";
			Image weath = loadImage(wimg);
			g.drawImage(weath, x2 + 12, y - 22, 32, 32, this);
			
			g.setColor(Color.BLACK);
			g.drawLine(x1, y, x2, y);
			i++;
		}

	}
	
	private void drawJunctionFin(Graphics g, Junction jDest, int x2, int y,Road r) {
		Color jColor = _RED_LIGHT_COLOR;
		int idx = jDest.getGreenLightIndex();
		if (idx != -1 && r.equals(jDest.getInRoads().get(idx))) {
			jColor = _GREEN_LIGHT_COLOR;
		}
		
		g.setColor(jColor);
		g.fillOval(x2 - _JRADIUS / 2, y - _JRADIUS / 2, _JRADIUS, _JRADIUS);

		g.setColor(_JUNCTION_LABEL_COLOR);
		g.drawString(jDest.getId(), x2, y);
	}
	
	private void drawJunctioninicio(Graphics g, Junction j1, int x1, int y) {
		g.setColor(_JUNCTION_COLOR);
		g.fillOval(x1 - _JRADIUS / 2, y - _JRADIUS / 2, _JRADIUS, _JRADIUS);

		g.setColor(_JUNCTION_LABEL_COLOR);
		g.drawString(j1.getId(), x1, y);
	}

	private void drawVehicles(Graphics g) {
		for (Vehicle v : _map.getVehicles()) {
			if (v.getStatus() != VehicleStatus.ARRIVED) {

				Road r = v.getRoad();
				
				int i=0;
				for(Road ro: _map.getRoads()) {
					if(ro==r) break;
					else i++;
				}
	
				// The calculation below compute the coordinate (vX,vY) of the vehicle on the
				// corresponding road. It is calculated relatively to the length of the road, and
				// the location on the vehicle.
				
				int x1 = 50;
				int y = (i+1)*50;
				int x2 = getWidth()-100;
				
				 double posicion = (double) v.getLocation() / r.getLength();
		         posicion = Math.max(0, Math.min(1, posicion));  // Asegurar que esté en [0,1]

		        int posX = (int) (x1 + (x2 - x1) * posicion);  // Escalar correctamente
		            
				if(v.getLocation()>r.getLength()) posicion=(x2-x1);

				// Choose a color for the vehcile's label and background, depending on its
				// contamination class
				int vLabelColor = (int) (25.0 * (10.0 - (double) v.getContClass()));
				g.setColor(new Color(0, vLabelColor, 0));
				
				g.drawImage(_car, (int) (posX), y - 10, 18, 18, this);
				g.drawString(v.getId(), (int) (posX), y - 16);
			}
		}
	}

	// this method is used to update the preffered and actual size of the component,
	// so when we draw outside the visible area the scrollbars show up
	private void updatePrefferedSize() {
		int maxW = 200;
		int maxH = 200;
		for (Junction j : _map.getJunctions()) {
			maxW = Math.max(maxW, j.getX());
			maxH = Math.max(maxH, j.getY());
		}
		maxW += 20;
		maxH += 20;
		if (maxW > getWidth() || maxH > getHeight()) {
			setPreferredSize(new Dimension(maxW, maxH));
			setSize(new Dimension(maxW, maxH));
		}
	}

	// loads an image from a file
	private Image loadImage(String img) {
		Image i = null;
		try {
			return ImageIO.read(new File("resources/icons/" + img));
		} catch (IOException e) {
		}
		return i;
	}

	public void update(RoadMap map) {
		SwingUtilities.invokeLater(() -> {
			_map = map;
			repaint();
		});
	}

	@Override
	public void onAdvance(RoadMap map, Collection<Event> events, int time) {
		update(map);
	}

	@Override
	public void onEventAdded(RoadMap map, Collection<Event> events, Event e, int time) {
		update(map);
	}

	@Override
	public void onReset(RoadMap map, Collection<Event> events, int time) {
		update(map);
	}

	@Override
	public void onRegister(RoadMap map, Collection<Event> events, int time) {
		update(map);
	}
}
