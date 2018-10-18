package fr.ubordeaux.ao.referencemanagement.infrastructure.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import fr.ubordeaux.ao.referencemanagement.domain.exception.ReferenceManagementException;
import fr.ubordeaux.ao.referencemanagement.domain.model.Catalog;
import fr.ubordeaux.ao.referencemanagement.domain.model.Reference;
import fr.ubordeaux.ao.referencemanagement.domain.type.CatalogName;
import fr.ubordeaux.ao.referencemanagement.domain.type.Price;

public class CatalogImpl extends Catalog {
	private CatalogName name;
	private ConceptMapping conceptMapping;
	private Connection connection;

    public CatalogImpl(CatalogName name) {
		conceptMapping = new ConceptMapping();
		connection = conceptMapping.getConnection();
        this.setName(name);
    }

    private void setName(CatalogName name) {
        if (name == null ) throw new ReferenceManagementException("cannot create catalog with no-name");
        this.name = name;
    }

	@Override
	public CatalogName getName() {
		return name;
	}

	@Override
	public Set<Catalog> getSubCatalogs() {
		return null;
	}

	@Override
	public Catalog getSubCatalogByName(CatalogName catalogName) {
		return null;
	}

	@Override
	public Catalog createSubCatalog(CatalogName subCatalogName) {
		return null;
	}

	@Override
	public int size() {
        try {
			Statement statement = connection.createStatement();
			String query = "SELECT COUNT(1) FROM "+name.toString();
			ResultSet rs = statement.executeQuery(query);
			int rowCount = rs.last() ? rs.getRow() : 0; 
			rs.close();
			return rowCount;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReferenceManagementException("cannot get size, jdbc exception");
		}
	}

	@Override
	public Set<Reference> getReferences() {
        Set<Reference> references = new HashSet<Reference>();
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM "+name.toString();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Reference ref = new Reference(rs.getString(0), rs.getString(1), rs.getString(2), new Price(rs.getInt(3)));
                references.add(ref);
            }
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReferenceManagementException("cannot get size, jdbc exception");
		}
		return references;
	}

	@Override
	public void add(Reference reference) {
        if (reference == null) throw new ReferenceManagementException("cannot add null to ReferenceRepository");
		try {
			PreparedStatement preparedStatement = connection.prepareStatement( "INSERT INTO "+name.toString()+" (id, name, description, baseprice) VALUES (?,?,?,?)" );
			int nthPlaceholder = 1;
			preparedStatement.setString( nthPlaceholder++, reference.getId());
			preparedStatement.setString( nthPlaceholder++, reference.getName() ); 
            preparedStatement.setString( nthPlaceholder++, reference.getDescription() ); 
            preparedStatement.setInt( nthPlaceholder++, reference.getBasePrice().getPrice()); 
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new ReferenceManagementException("cannot add to ReferenceRepository (SQL Exception)");
		}
	}

}