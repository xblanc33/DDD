package fr.ubordeaux.ao.referencemanagement.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Set;

import fr.ubordeaux.ao.referencemanagement.application.command.AddReferenceToCatalog;
import fr.ubordeaux.ao.referencemanagement.application.command.Gateway;
import fr.ubordeaux.ao.referencemanagement.application.command.MapKeyWord;
import fr.ubordeaux.ao.referencemanagement.domain.model.Catalog;
import fr.ubordeaux.ao.referencemanagement.domain.model.KeyWord;
import fr.ubordeaux.ao.referencemanagement.domain.model.KeyWordMap;
import fr.ubordeaux.ao.referencemanagement.domain.model.Reference;
import fr.ubordeaux.ao.referencemanagement.domain.service.SearchEngine;
import fr.ubordeaux.ao.referencemanagement.domain.type.CatalogName;
import fr.ubordeaux.ao.referencemanagement.domain.type.Price;
import fr.ubordeaux.ao.referencemanagement.infrastructure.command.GatewayImpl;
import fr.ubordeaux.ao.referencemanagement.infrastructure.command.HandlerImpl;
import fr.ubordeaux.ao.referencemanagement.infrastructure.persistence.inmemory.KeyWordMapImpl;
import fr.ubordeaux.ao.referencemanagement.infrastructure.persistence.inmemory.CatalogImpl;

public class TextualMenu {
    private BufferedReader in;
    private PrintWriter out;
    private Gateway gateway;
    private Catalog rootCatalog;
    private KeyWordMap keywordMap;

    protected TextualMenu(BufferedReader in , PrintWriter out) {
        this.in = in;
        this.out = out;
        initCollectionManager();
        createCommandGatewayAndHandler();
    }

    protected TextualMenu(InputStream in, PrintStream out) {
        this.in = new BufferedReader(new InputStreamReader(in));
        this.out = new PrintWriter(out, true);
        initCollectionManager();
        createCommandGatewayAndHandler();
    }

    private void initCollectionManager() {
        rootCatalog = new CatalogImpl(new CatalogName("root"));
        keywordMap = new KeyWordMapImpl();
    }

    private void createCommandGatewayAndHandler() {
        gateway = new GatewayImpl();
        gateway.addCommandHandler(new HandlerImpl(rootCatalog, keywordMap));
    }

    protected void handleUserInstructions() throws IOException {
        boolean end = false;
        while (!end) {
            out.println("(1) Add new Reference to Catalog, (2) map keyword to reference (3) exit");
            out.println("Your choice 1-3:");
            String choice = in.readLine();
            switch (choice) {
                case "1" : createReferenceAndAddItToCatalog();
                            break;
                case "2" : mapKeyWordToReference();
                            break;
                case "3" : end = true;
                default : 
            }
        }
    }

    private void createReferenceAndAddItToCatalog() throws IOException {
        out.println("You ask to create a new reference and add it to the root catalog!");
        out.println("Reference id (any string, must be unique) : ");
        String refId = in.readLine();
        out.println("Reference name : ");
        String refName = in.readLine();
        out.println("Reference description : ");
        String refDescription = in.readLine();
        out.println("Price : ");
        String price = in.readLine();
        Price refPrice = new Price(Integer.parseInt(price));
        Reference reference = new Reference(refId, refName, refDescription, refPrice);

        out.println("Catalog Name (root) : ");
        String name = in.readLine();
        CatalogName catalogName = new CatalogName("root");
        if (name.compareTo("")!=0) catalogName = new CatalogName(name);
        gateway.pushCommand(new AddReferenceToCatalog(reference, catalogName));
        out.println("Reference ("+refId+") should be created soon !");
    }

    private void mapKeyWordToReference() throws IOException {
        out.println("You ask to create link a (new) KeyWord !");
        out.println("KeyWord name : ");
        String keyWord = in.readLine();
        out.println("Reference name : ");
        String refName = in.readLine();
        
        SearchEngine searchEngine = new SearchEngine(rootCatalog, keywordMap);
        Set<Reference> foundReferences = searchEngine.searchReferencesByName(refName);

        if (foundReferences.size() == 0) {
            out.println("Sorry reference not found ...");
            return;
        } 
        
        for (Reference reference : foundReferences) {
            gateway.pushCommand(new MapKeyWord(new KeyWord(keyWord), reference));
            out.println("Reference ("+reference.getId()+") should be linked soon with the keyword "+keyWord+"!");
        }
    }
}