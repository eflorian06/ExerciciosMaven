package br.com.mesttra.carro;


import br.com.mesttra.carro.entity.Carro;
import br.com.mesttra.carro.dao.carroDAO;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {



        carroDAO carroDAO = new carroDAO();
        Scanner teclado = new Scanner(System.in);

        System.out.println("Digite a placa do carro: ");
        String placa = teclado.nextLine();

        Carro carroInserido = new Carro(placa, "Azul", "Fiat", "Uno", 160.0);
        carroDAO.inserir(carroInserido);

        List<Carro> carros = carroDAO.listaCarros();
        for (Carro carro : carros) {
            System.out.println(carro);
        }
        System.out.println(carroDAO.consultaCarro("ABC-123"));
        carroDAO.removeCarro("ABC-123");

        carros= carroDAO.listaCarros();
    }
}
