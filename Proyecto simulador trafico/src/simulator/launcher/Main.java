/*package simulator.launcher;
import simulator.control.Controller;
import simulator.factories.*;
import simulator.model.DequeuingStrategy;
import simulator.model.Event;
import simulator.model.LightSwitchingStrategy;
import simulator.model.TrafficSimulator;
import simulator.view.MainWindow;

import org.apache.commons.cli.*;
import java.io.*;

public class Main {
	 private static final int DEFAULT_TICKS = 10; // Valor por defecto para los ticks
	 private static String _inFile = null;          // Archivo de entrada
	 private static String _outFile = null;         // Archivo de salida (opcional)
	 private static int _timeLimit;     // Límite de tiempo (ticks), valor por defecto 10
	 private static Factory<Event> _eventsFactory = null; // Factoría de eventos

		private static void parseArgs(String[] args) {

			// define the valid command line options
			//
			Options cmdLineOptions = buildOptions();

			// parse the command line as provided in args
			//
			CommandLineParser parser = new DefaultParser();
			try {
				CommandLine line = parser.parse(cmdLineOptions, args);
				parseHelpOption(line, cmdLineOptions);
				parseInFileOption(line);
				parseOutFileOption(line);
				parseTimeOption(line);

				// if there are some remaining arguments, then something wrong is
				// provided in the command line!
				//
				String[] remaining = line.getArgs();
				if (remaining.length > 0) {
					String error = "Illegal arguments:";
					for (String o : remaining)
						error += (" " + o);
					throw new ParseException(error);
				}

			} catch (ParseException e) {
				System.err.println(e.getLocalizedMessage());
				System.exit(1);
			}

		}

		private static Options buildOptions() {
			Options cmdLineOptions = new Options();

			cmdLineOptions.addOption(Option.builder("i").longOpt("input").hasArg().desc("Events input file").build());
			cmdLineOptions.addOption(
					Option.builder("o").longOpt("output").hasArg().desc("Output file, where reports are written.").build());
			cmdLineOptions.addOption(Option.builder("h").longOpt("help").desc("Print this message").build());
			cmdLineOptions.addOption(Option.builder("t").longOpt("time").hasArg().desc("Get the time").build());

			return cmdLineOptions;
		}

		private static void parseHelpOption(CommandLine line, Options cmdLineOptions) {
			if (line.hasOption("h")) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp(Main.class.getCanonicalName(), cmdLineOptions, true);
				System.exit(0);
			}
		}
		
		private static void parseTimeOption(CommandLine line) {
			try {
				if (line.hasOption("t")) _timeLimit = Integer.parseInt(line.getOptionValue("t"));	
				else _timeLimit = DEFAULT_TICKS;
			}
			catch (NumberFormatException e){
				 _timeLimit = DEFAULT_TICKS;
			}
		}

		private static void parseInFileOption(CommandLine line) throws ParseException {
			_inFile = line.getOptionValue("i");
			if (_inFile == null) {
				throw new ParseException("An events file is missing");
			}
		}

		private static void parseOutFileOption(CommandLine line) throws ParseException {
			_outFile = line.getOptionValue("o");
		}


		private static void initFactories() {
			// Aquí se inicializa la factoría de eventos
	        // Por ejemplo, podrías devolver una instancia de una clase que implemente Factory<Event>
	    	Factory<LightSwitchingStrategy> lssFactory = new SwitchingStrategyFactory();
	    	Factory<DequeuingStrategy> dqsFactory = new DequeuingStrategyFactory();
	    	_eventsFactory = new EventFactory(lssFactory, dqsFactory); // Asume que existe una clase EventFactory
		}

		private static void startBatchMode() throws IOException {
			try (InputStream in = new FileInputStream(_inFile)) {
	            // Crear el OutputStream (archivo o consola)
	            OutputStream out;
	            if (_outFile != null) out = new FileOutputStream(_outFile);
	            else out = System.out;

	            // Crear el simulador y el controlador
	            TrafficSimulator simulator = new TrafficSimulator();
	            Controller controller = new Controller(simulator, _eventsFactory);
	            MainWindow mw = new MainWindow(controller);

	            // Cargar eventos desde el archivo de entrada
	            controller.loadEvents(in);

	            // Ejecutar la simulación
	            controller.run(_timeLimit, out);

	            // Cerrar el OutputStream si es un archivo
	            if (_outFile != null) {
	                ((FileOutputStream) out).close();
	            }
	        } catch (IOException e) {
	            System.err.println("Error during batch mode execution: " + e.getMessage());
	            System.exit(1);
	        }
		}

		private static void start(String[] args) throws IOException {
			initFactories();
			parseArgs(args);
			startBatchMode();
		}

		// example command lines:
		//
		// -i resources/examples/ex1.json
		// -i resources/examples/ex1.json -t 300
		// -i resources/examples/ex1.json -o resources/tmp/ex1.out.json
		// --help

		public static void main(String[] args) {
			try {
				start(args);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
}*/

package simulator.launcher;

import simulator.control.Controller;
import simulator.factories.*;
import simulator.model.DequeuingStrategy;
import simulator.model.Event;
import simulator.model.LightSwitchingStrategy;
import simulator.model.TrafficSimulator;
import simulator.view.MainWindow;

import org.apache.commons.cli.*;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    private static final int DEFAULT_TICKS = 10;
    private static String _inFile = null;
    private static String _outFile = null;
    private static int _timeLimit;
    private static Factory<Event> _eventsFactory = null;
    private static String _mode = "gui"; // Valor por defecto: gui

    private static void parseArgs(String[] args) {
        Options cmdLineOptions = buildOptions();

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(cmdLineOptions, args);
            parseHelpOption(line, cmdLineOptions);
            parseModeOption(line);
            parseInFileOption(line);
            parseOutFileOption(line);
            parseTimeOption(line);

            String[] remaining = line.getArgs();
            if (remaining.length > 0) {
                String error = "Illegal arguments:";
                for (String o : remaining)
                    error += (" " + o);
                throw new ParseException(error);
            }

        } catch (ParseException e) {
            System.err.println(e.getLocalizedMessage());
            System.exit(1);
        }
    }

    private static Options buildOptions() {
        Options cmdLineOptions = new Options();

        cmdLineOptions.addOption(Option.builder("i").longOpt("input").hasArg().desc("Events input file").build());
        cmdLineOptions.addOption(Option.builder("o").longOpt("output").hasArg().desc("Output file, where reports are written.").build());
        cmdLineOptions.addOption(Option.builder("h").longOpt("help").desc("Print this message").build());
        cmdLineOptions.addOption(Option.builder("t").longOpt("time").hasArg().desc("Ticks limit (default value is 10)").build());
        // Nueva opción para el modo de ejecución
        cmdLineOptions.addOption(Option.builder("m").longOpt("mode").hasArg().desc("Execution mode: gui (default) or console").build());

        return cmdLineOptions;
    }

    private static void parseHelpOption(CommandLine line, Options cmdLineOptions) {
        if (line.hasOption("h")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(Main.class.getCanonicalName(), cmdLineOptions, true);
            System.exit(0);
        }
    }

    private static void parseModeOption(CommandLine line) throws ParseException {
        if (line.hasOption("m")) {
            String mode = line.getOptionValue("m");
            if (!mode.equals("gui") && !mode.equals("console")) {
                throw new ParseException("Invalid mode: " + mode + ". Valid values are 'gui' or 'console'");
            }
            _mode = mode;
        }
    }

    private static void parseTimeOption(CommandLine line) {
        try {
            if (line.hasOption("t")) _timeLimit = Integer.parseInt(line.getOptionValue("t"));    
            else _timeLimit = DEFAULT_TICKS;
        }
        catch (NumberFormatException e){
             _timeLimit = DEFAULT_TICKS;
        }
    }

    private static void parseInFileOption(CommandLine line) throws ParseException {
        _inFile = line.getOptionValue("i");
        // En modo GUI, el archivo de entrada es opcional
        if (_inFile == null && _mode.equals("console")) {
            throw new ParseException("An events file is missing");
        }
    }

    private static void parseOutFileOption(CommandLine line) throws ParseException {
        // En modo GUI, ignoramos la opción de salida
        if (!_mode.equals("gui")) {
            _outFile = line.getOptionValue("o");
        }
    }

    private static void initFactories() {
        Factory<LightSwitchingStrategy> lssFactory = new SwitchingStrategyFactory();
        Factory<DequeuingStrategy> dqsFactory = new DequeuingStrategyFactory();
        _eventsFactory = new EventFactory(lssFactory, dqsFactory);
    }

    private static void startBatchMode() throws IOException {
        try (InputStream in = new FileInputStream(_inFile)) {
            OutputStream out;
            if (_outFile != null) out = new FileOutputStream(_outFile);
            else out = System.out;

            TrafficSimulator simulator = new TrafficSimulator();
            Controller controller = new Controller(simulator, _eventsFactory);

            controller.loadEvents(in);
            controller.run(_timeLimit, out);

            if (_outFile != null) {
                ((FileOutputStream) out).close();
            }
        } catch (IOException e) {
            System.err.println("Error during batch mode execution: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void startGUIMode() throws IOException {
        TrafficSimulator simulator = new TrafficSimulator();
        Controller controller = new Controller(simulator, _eventsFactory);

        // Cargar eventos solo si se proporcionó un archivo de entrada
        if (_inFile != null) {
            try (InputStream in = new FileInputStream(_inFile)) {
                controller.loadEvents(in);
            } catch (IOException e) {
                System.err.println("Error loading events file: " + e.getMessage());
                System.exit(1);
            }
        }

        // Iniciar la interfaz gráfica en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            try {
                MainWindow mainWindow = new MainWindow(controller);
                mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainWindow.setVisible(true);
            } catch (Exception e) {
                System.err.println("Error creating GUI: " + e.getMessage());
                System.exit(1);
            }
        });
    }

    private static void start(String[] args) throws IOException {
        initFactories();
        parseArgs(args);
        
        if (_mode.equals("gui")) {
            startGUIMode();
        } else {
            startBatchMode();
        }
    }

    public static void main(String[] args) {
        try {
            start(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}