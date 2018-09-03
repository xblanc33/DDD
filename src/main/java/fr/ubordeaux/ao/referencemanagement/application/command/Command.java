package fr.ubordeaux.ao.referencemanagement.application.command;

import fr.ubordeaux.ao.referencemanagement.domain.model.Catalog;
import fr.ubordeaux.ao.referencemanagement.domain.model.KeyWordMap;

public interface Command {
    public void execute(Catalog rootCatalog, KeyWordMap keywordMap);
}