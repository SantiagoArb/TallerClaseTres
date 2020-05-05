package com.training.dos.demo.products.Repositories;

import com.training.dos.demo.products.domain.*;
import com.training.dos.demo.products.exceptions.ProductDoesNotExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SqlProductsRepository implements ProductsRepository {

    private final JdbcTemplate jdbcTemplate;
    private ProductDoesNotExist productDoesNotExist;

    @Autowired
    public SqlProductsRepository(JdbcTemplate jdbc) {
                jdbcTemplate = jdbc;
    }

    @Override
    public ProductOperation insertOne(ProductOperationRequest operationRequest) {
        try {
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                    .withTableName("PRODUCTOS")
                    .usingGeneratedKeyColumns("id");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("nombre", operationRequest.getName().getValue());
            parameters.put("DESCRIPCION", operationRequest.getDescription().getValue());
            parameters.put("PRECIOBASE", operationRequest.getBasePrice().getValue());
            parameters.put("TASAIMPUESTOS", operationRequest.getTaxRate().getValue());
            parameters.put("ESTADO", operationRequest.getProductStatus());
            parameters.put("CANTINVE", operationRequest.getInventoryQuantity().getValue());
            Number key = simpleJdbcInsert.executeAndReturnKey(parameters);
            Product product = Product.from(key.longValue(), operationRequest.getName(), operationRequest.getDescription(),operationRequest.getBasePrice(),
                    operationRequest.getTaxRate(),operationRequest.getProductStatus(),operationRequest.getInventoryQuantity());
            return ProductOperationSuccess.of(product);

        }catch (ProductDoesNotExist e) {
            return ProductOperationFailure.of(null);        }
    }

    @Override
    public ProductOperation findById(Long id) {
        String SQL = "SELECT NOMBRE,DESCRIPCION,PRECIOBASE,TASAIMPUESTOS,ESTADO,CANTINVE FROM PRODUCTOS WHERE ID =?";
        Object[] objects = {id};
        RowMapper<Product> rowMapper = (resultSet, i) ->
            Product.from(id,
                    Name.of(resultSet.getString("NOMBRE")),
                    Description.of(resultSet.getString("DESCRIPCION")),
                    BasePrice.of(resultSet.getBigDecimal("PRECIOBASE")),
                    TaxRate.of(resultSet.getBigDecimal("TASAIMPUESTOS")),
                    ProductStatus.valueOf(resultSet.getString("ESTADO")),
                    InventoryQuantity.of(resultSet.getInt("CANTINVE")));

       try {
            Product product = jdbcTemplate.queryForObject(SQL, objects, rowMapper);
            return ProductOperationSuccess.of(product);
        } catch (EmptyResultDataAccessException e) {
            return ProductOperationFailure.of(new ProductDoesNotExist(id));
        }


    }

    @Override
    public List<Product> findAll() {
        String SQL = "SELECT ID,NOMBRE,DESCRIPCION,PRECIOBASE,TASAIMPUESTOS,ESTADO,CANTINVE FROM PRODUCTOS";
        RowMapper<Product> rowMapper = (resultSet, i) -> {
            Long id = resultSet.getLong("ID");
            Name name = Name.of(resultSet.getString("NOMBRE"));
            Description descripcion = Description.of(resultSet.getString("DESCRIPCION"));
            BasePrice precioBase = BasePrice.of(resultSet.getBigDecimal("PRECIOBASE"));
            TaxRate tasaImpuesto = TaxRate.of(resultSet.getBigDecimal("TASAIMPUESTOS"));
            ProductStatus estado = ProductStatus.valueOf(resultSet.getString("ESTADO"));
            InventoryQuantity cantidadInventario = InventoryQuantity.of(resultSet.getInt("CANTINVE"));
            return Product.from(id, name, descripcion, precioBase, tasaImpuesto, estado, cantidadInventario);
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
                return ProductOperationSuccess.of(Product.from(id,operationRequest.getName(),operationRequest.getDescription()
                        ,operationRequest.getBasePrice(),operationRequest.getTaxRate(),
                        operationRequest.getProductStatus(),operationRequest.getInventoryQuantity()));
            } else throw new ProductDoesNotExist(id);
        }catch (ProductDoesNotExist e){
            System.out.println(e.toString());
            return ProductOperationFailure.of(e);
        }
    }

    @Override
    public ProductOperation deleteOne(Long id) {
        Product valor = findById(id).value();
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
            return ProductOperationFailure.of(e);
        }


    }

    }

