package be.degreyt.mmdoc.interfaces.importers;

import be.degreyt.mmdoc.interfaces.importers.data.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CardsXmlParser {

    private XStream xStream;
    private String rootPath;
    private Pattern fileNamePattern = Pattern.compile(".*\\.xml");

    public CardsXmlParser() {
        this(".\\mmdoc-datafiles\\src\\main\\resources\\gamedata"); // TODO separate parser logic from knowledge where to find files
    }

    public CardsXmlParser(String rootPath) {
        this.rootPath = rootPath;
    }

    public List<XCard> parse() {
        try {
            XStream xStream = new CardsXmlParser().getXStream();
            File rootDirectory = new File(rootPath); // TODO read through URL loaded through classloader
            if (!rootDirectory.isDirectory()) {
                throw new RuntimeException("Invalid path " + rootDirectory.getAbsolutePath());
            }
            FilenameFilter filenameFilter = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name != null && fileNamePattern.matcher(name).matches();
                }
            };
            File[] files = rootDirectory.listFiles(filenameFilter);
            List<XCard> cards = new ArrayList<XCard>();
            ExecutorService executorService = Executors.newCachedThreadPool();

            List<Future<List<XCard>>> futures = new ArrayList<>(files.length);
            for (File file : files) {
                Future<List<XCard>> future = executorService.submit(new FileLoader(file));
                futures.add(future);
            }

            for (Future<List<XCard>> future : futures) {
                cards.addAll(future.get());
            }
            return cards;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return Collections.emptyList();
        } catch (ExecutionException e) {
            throw (RuntimeException) e.getCause();
        }
    }

    private class FileLoader implements Callable<List<XCard>> {

        private final File file;

        private FileLoader(File file) {
            this.file = file;
        }

        @Override
        public List<XCard> call() throws Exception {
            Cards parsedFile = (Cards) getXStream().fromXML(file);
            List<XCard> cardsInFile = parsedFile.getCards();

            if (cardsInFile != null) {
                cardsInFile.forEach((c) -> c.setFileName(file.getName()));
                return cardsInFile;
            }
            return Collections.emptyList();
        }

    }

    public static void main(String[] args) {
        List<XCard> cards = new CardsXmlParser().parse();
        System.out.println(cards.size());
        cards.stream().map(XCard::getType).forEach(System.out::println);
    }

    private XStream getXStream() {
        if (xStream == null) {
            initXStream();
        }
        return xStream;
    }

    private void initXStream() {
        xStream = new XStream(new StaxDriver());
        xStream.alias("Cards", Cards.class);
        xStream.addImplicitCollection(Cards.class, "cards", XCard.class);
        xStream.addImplicitCollection(Cards.class, "templateEffects", TemplateEffect.class);
        xStream.addImplicitCollection(XCard.class, "schools");
        xStream.addImplicitCollection(XCard.class, "variables");
        xStream.addImplicitCollection(Subgroup.class, "targets");
        xStream.addImplicitCollection(Effect.class, "abilities");
        xStream.alias("Card", XCard.class);
        xStream.alias("TemplateEffect", TemplateEffect.class);
        xStream.alias("School", School.class);
        xStream.alias("Ability", XAbility.class);
        xStream.alias("Target", Target.class);
        xStream.addImplicitCollection(Target.class, "variables");
        xStream.alias("Trigger", Trigger.class);
        xStream.alias("Variable", Variable.class);
        xStream.alias("Variable2", Variable.class);
        xStream.alias("Variable3", Variable.class);
        xStream.alias("Variable4", Variable.class);
        xStream.alias("Variable5", Variable.class);
        xStream.alias("Variable6", Variable.class);
        xStream.alias("Variable7", Variable.class);

        xStream.addImplicitCollection(Trigger.class, "targets");
        xStream.alias("Effect", Effect.class);
        xStream.alias("Condition", Condition.class);
        xStream.addImplicitCollection(Condition.class, "conditions");
        xStream.useAttributeFor(XCard.class, "Rarity");
        xStream.useAttributeFor(XCard.class, "Type");
        xStream.useAttributeFor(XCard.class, "SubType");
        xStream.useAttributeFor(XCard.class, "DisplayName");
        xStream.useAttributeFor(XCard.class, "Name");
        xStream.useAttributeFor(XCard.class, "Faction");
        xStream.useAttributeFor(XCard.class, "Cost");
        xStream.useAttributeFor(XCard.class, "DestinyLevel");
        xStream.useAttributeFor(XCard.class, "MagicLevel");
        xStream.useAttributeFor(XCard.class, "MightLevel");
        xStream.useAttributeFor(XCard.class, "Attack");
        xStream.useAttributeFor(XCard.class, "Retaliate");
        xStream.useAttributeFor(XCard.class, "HP");
        xStream.useAttributeFor(XCard.class, "Unique");
    }
}
