package elevator;


import elevator.Elevator;

import org.apache.commons.cli.*;

public class Application {
    public static void main(String[] args) {

        Options options = new Options();

        Option start_floor = new Option("s", "start", true, "start floor");
        start_floor.setRequired(true);
        start_floor.setType(Number.class);
        options.addOption(start_floor);

        options.addOption(OptionBuilder.withLongOpt("floor")
        .withArgName("f")
        .withDescription("list of floors")
        .hasArgs()
        .isRequired(true)
        .withValueSeparator(',')
        .withArgName("floors")
        .withType(Number.class)
        .create());

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch ( ParseException e ) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }

        int start = 0;
        try {
            start = ((Number)cmd.getParsedOptionValue("start")).intValue();
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }

        Elevator elevator = new Elevator(cmd.getOptionValues("floor"), start);
        elevator.travel();
        
        
        
    }

}
