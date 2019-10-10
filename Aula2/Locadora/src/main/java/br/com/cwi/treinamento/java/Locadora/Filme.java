package br.com.cwi.treinamento.java.locadora;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;


@Builder
@Data
public class Filme {

   private String titulo;
   private Categoria categoria;
   private int prazoDeEntrega;
   private LocalDate dataEntrega;
    private List<Fita> fitas;


    public void criarPrazoDeEntrega(Filme filme){
        if(filme.getCategoria().equals(Categoria.DOURADA)){
            prazoDeEntrega = 2;
        }else if(filme.getCategoria().equals(Categoria.PRATA)){
            prazoDeEntrega = 3;
        }else if(filme.getCategoria().equals(Categoria.BRONZE)){
            prazoDeEntrega =  5;
        }
    }

    public void criarDataDeEntrega(){
        LocalDate now = LocalDate.now();
        dataEntrega = now.plusDays(prazoDeEntrega);
    }
}
