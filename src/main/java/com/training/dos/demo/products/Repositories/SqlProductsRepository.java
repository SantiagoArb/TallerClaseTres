package com.training.dos.demo.products.Repositories;

import com.training.dos.demo.products.domain.*;
import com.training.dos.demo.products.exceptions.ProductDoesNotExist;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class SqlProductsRepository implements ProductsRepository {

    private final JdbcTemplate jdbcTemplate;
    private ProductDoesNotExist productDoesNotExist;

    @Autowired
    public SqlProductsRepository(JdbcTemplate jdbc) {
                jdbcTemplate = jdbc;
    }

    private final RowMapper<ProductOperationRequest> rowMapper = (resultSet, i) -> {
        Name name = Name.of(resultSet.getString("NOMBRE"));
        Description descripcion = Description.of(resultSet.getString("DESCRIPCION"));
        BasePrice precioBase = BasePrice.of(resultSet.getBigDecimal("PRECIOBASE"));
        TaxRate tasaImpuesto = TaxRate.of(resultSet.getBigDecimal("TASAIMPUESTOS"));
        ProductStatus estado = ProductStatus.valueOf(resultSet.getString("ESTADO"));
        InventoryQuantity cantidadInventario = InventoryQuantity.of(resultSet.getInt("CANTINVE"));
        return ProductOperationRequest.of(name, descripcion, precioBase, tasaImpuesto, estado, cantidadInventario);
    };


    @Override
    public ProductOperation insertOne(ProductOperationRequest operationRequest) {
        String SQL = "INSERT INTO PRODUCTOS (NOMBRE,DESCRIPCION,PRECIOBASE,TASAIMPUESTOS,ESTADO,CANTINVE) VALUES (?,?,?,?,?,?)";
        System.out.println(operationRequest.toString());
        KeyHolder keyHolder = new GeneratedKeyHolder(); // inicializada {}
        PreparedStatementCreator psc = connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, operationRequest.getName().getValue());
            ps.setString(2, operationRequest.getDescription().getValue());
            ps.setBigDecimal(3, operationRequest.getBasePrice().getValue());
            ps.setBigDecimal(4, operationRequest.getTaxRate().getValue());
            ps.setString(5, operationRequest.getProductStatus().toString());
            ps.setInt(6, operationRequest.getInventoryQuantity().getValue());
            return ps;
        };

            jdbcTemplate.update(psc, keyHolder);
            return ProductOperationSuccess.of(operationRequest);
    }

    @Override
    public ProductOperation findById(Long id) {
        String SQL = "SELECT NOMBRE,DESCRIPCION,PRECIOBASE,TASAIMPUESTOS,ESTADO,CANTINVE FROM PRODUCTOS WHERE ID =?";
        Object[] objects = {id};
        RowMapper<ProductOperationRequest> rowMapper = (resultSet, i) -> {
            Name name = Name.of(resultSet.getString("NOMBRE"));
            Description descripcion = Description.of(resultSet.getString("DESCRIPCION"));
            BasePrice precioBase = BasePrice.of(resultSet.getBigDecimal("PRECIOBASE"));
            TaxRate tasaImpuesto = TaxRate.of(resultSet.getBigDecimal("TASAIMPUESTOS"));
            ProductStatus estado = ProductStatus.valueOf(resultSet.getString("ESTADO"));
            InventoryQuantity cantidadInventario = InventoryQuantity.of(resultSet.getInt("CANTINVE"));
            return ProductOperationRequest.of(name, descripcion, precioBase, tasaImpuesto, estado, cantidadInventario);
        };

       try {
            ProductOperationRequest request = jdbcTemplate.queryForObject(SQL, objects, rowMapper);
            return ProductOperationSuccess.of(request);
        } catch (EmptyResultDataAccessException e) {
            return ProductOperationFailture.of(new ProductDoesNotExist(id));
        }


    }

    @Override
    public List<Product> findAll() {
        String SQL = "SELECT ID,NOMBRE,DESCRIPCION,PRECIOBASE,TASAIMPUESTOS,ESTADO,CANTINVE FROM PRODUCTOS";
        RowMapper<Product> rowMapper = (resultSet, i) -> {
            ProductId id = ProductId.of(resultSet.getLong("ID"));
            Name name = Name.of(resultSet.getString("NOMBRE"));
            Description descripcion = Description.of(resultSet.getString("DESCRIPCION"));
            BasePrice precioBase = BasePrice.of(resultSet.getBigDecimal("PRECIOBASE"));
            TaxRate tasaImpuesto = TaxRate.of(resultSet.getBigDecimal("TASAIMPUESTOS"));
            ProductStatus estado = ProductStatus.valueOf(resultSet.getString("ESTADO"));
            InventoryQuantity cantidadInventario = InventoryQuantity.of(resultSet.getInt("CANTINVE"));
            return Product.of(id, name, descripcion, precioBase, tasaImpuesto, estado, cantidadInventario);
        };
        List<Product> productsList = jdbcTemplate.query(SQL, rowMapper);
        return productsList;
    }


    @Override
    public ProductOperation updateOne(Long id, ProductOperationRequest operationRequest) {
        String SQL = "UPDATE PRODUCTOS SET NOMBRE = ?, DESCRIPCION= ?, PRECIOBASE = ?, TASAIMPUESTOS = ?,ESTADO= ?,CANTINVE= ?  WHERE ID = ?";
        KeyHolder keyHolder = new GeneratedKeyHolder(); // inicializada {}
        PreparedStatementCreator psc = connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, operationRequest.getName().getValue());
            ps.setString(2, operationRequest.getDescription().getValue());
            ps.setBigDecimal(3, operationRequest.getBasePrice().getValue());
            ps.setBigDecimal(4, operationRequest.getTaxRate().getValue());
            ps.setString(5, operationRequest.getProductStatus().toString());
            ps.setInt(6, operationRequest.getInventoryQuantity().getValue());
            ps.setLong(7, id);
            return ps;
        };
        try {
            int resp = jdbcTemplate.update(psc, keyHolder);
            if (resp == 1) {
                return ProductOperationSuccess.of(operationRequest);
            } else throw new ProductDoesNotExist(id);
        }catch (ProductDoesNotExist e){
            System.out.println(e.toString());
            return ProductOperationFailture.of(e);
        }
    }

    @Override
    public ProductOperation deleteOne(Long id) {
        ProductOperationRequest valor = findById(id).value();
        String SQL ="DELETE FROM PRODUCTOS WHERE ID = ?";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator psc = connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, id);
            return ps;
        };
        try{
       int resp=  jdbcTemplate.update(psc,keyHolder);
        if(resp ==1){
           return ProductOperationSuccess.of(valor);
        } else {
            throw new ProductDoesNotExist(id);

        }
        }catch (ProductDoesNotExist e){
            return ProductOperationFailture.of(e);
        }


    }

    }

