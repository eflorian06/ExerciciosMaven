package br.com.mesttra.carro.dao;

import br.com.mesttra.carro.connectionfactory.ConnectionFactory;
import br.com.mesttra.carro.entity.Carro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class carroDAO {

    //private Connection conn;
    private EntityManager manager;

    public carroDAO() {
        //this.conn = ConnectionFactory.getConnection();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("carros");
        this.manager = entityManagerFactory.createEntityManager();
    }

    public boolean inserir(Carro carro) {
//        String sql = "INSERT INTO carro (placa, cor, marca, modelo) VALUES (?, ?, ?, ?)";
//        try {
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            stmt.setString(1, carro.getPlaca());
//            stmt.setString(2, carro.getCor());
//            stmt.setString(3, carro.getMarca());
//            stmt.setString(4, carro.getModelo());
//            stmt.execute();
//            stmt.close();
//            return true;
//        } catch (SQLException e) {
//            System.err.println("Erro ao inserir carro");
//
//        }
//        return false;

        this.manager.getTransaction().begin();
        this.manager.persist(carro);
        this.manager.getTransaction().commit();

        return true;
    }

    public List<Carro> listaCarros() {
//        List<Carro> carros = new ArrayList<Carro>();
//
//        String sql = "SELECT * FROM carros.carro";
//
//        try {
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                Carro carro = new Carro();
//                carro.setPlaca(rs.getString("placa"));
//                carro.setCor(rs.getString("cor"));
//                carro.setMarca(rs.getString("marca"));
//                carro.setModelo(rs.getString("modelo"));
//                carros.add(carro);
//            }
//            stmt.close();
//        } catch (SQLException e) {
//            System.err.println("Erro ao listar carros");
//        }
//        return carros;

        Query query = this.manager.createQuery("SELECT c FROM Carro as c");
        return query.getResultList();
    }

    public Carro consultaCarro(String placa) {
//        String sql = "SELECT * FROM carros.carro WHERE placa = ?";
//
//        try {
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            stmt.setString(1, placa);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                Carro carroRetornado = new Carro();
//                carroRetornado.setPlaca(rs.getString("placa"));
//                carroRetornado.setCor(rs.getString("cor"));
//                carroRetornado.setMarca(rs.getString("marca"));
//                carroRetornado.setModelo(rs.getString("modelo"));
//                return carroRetornado;
//            }
//        } catch (SQLException e) {
//            System.err.println("Erro ao consultar carro");
//        }
//        return null;
        return this.manager.find(Carro.class, placa);
    }

    public boolean removeCarro(String placa) {
//        String sql = "DELETE FROM carros.carro WHERE placa = ?";
//
//        try {
//            PreparedStatement stmt = this.conn.prepareStatement(sql);
//            stmt.setString(1, placa);
//            stmt.execute();
//            stmt.close();
//            return true;
//        } catch (SQLException e) {
//            System.err.println("Erro ao remover carro");
//        }
//        return false;
//    }

        Carro carro = this.manager.find(Carro.class, placa);

        if (carro != null) {
            this.manager.getTransaction().begin();
            this.manager.remove(carro);
            this.manager.getTransaction().commit();
            return true;
        }

        return false;
    }

    public List<Carro> listaCarrosPorMarca(String marca) {
        Query query = this.manager.createQuery("SELECT c FROM Carro as c WHERE c.marca = :marca");
        query.setParameter("marca", marca);
        return query.getResultList();
    }
}
