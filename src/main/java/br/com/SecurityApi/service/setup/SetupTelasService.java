package br.com.SecurityApi.service.setup;

import br.com.SecurityApi.Models.Telas;
import br.com.SecurityApi.Models.TelasPermitidas;
import br.com.SecurityApi.repository.TelasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SetupTelasService {

    @Autowired
    private TelasRepository telasRepository;

    public List create(){
        HashMap<String, List<Telas>> telas = new HashMap<>();
        List<Telas> telasList = new ArrayList<>(){{
            add(new Telas("Produtos","/produtos"));
            add(new Telas("Estoque","/estoque"));
            add(new Telas("Pedidos","/pedidos"));
            add(new Telas("Boletos","/boletos"));
            add(new Telas("Cobrancas","/cobrancas"));
            add(new Telas("Transportadoras","/transportadoras"));
            add(new Telas("Clientes","/clientes"));
            add(new Telas("Unidades","/unidades"));
            add(new Telas("Contas a pagar","/contasapagar"));
            add(new Telas("Ordem de produção","/ordemdeproducao"));
            add(new Telas("Criar Pedido","/criarpedido"));
            add(new Telas("Funcionario","/funcionario"));
            add(new Telas("Atividades","/atividades"));
            add(new Telas("Compras","/compras"));
            add(new Telas("Projetos","/projetos"));
            add(new Telas("Nota fiscal - NFE","/nfe"));
            add(new Telas("Veiculo","/veiculo"));
            add(new Telas("Cor","/cor"));
            add(new Telas("Imposto","/imposto"));
            add(new Telas("Medida","/medida"));
            add(new Telas("Assinatura","/assinatura"));
            add(new Telas("Loja","/loja"));
            add(new Telas("Materia Prima","/materiaprima"));
            add(new Telas("Saida de produto","/saidadeproduto"));
            add(new Telas("Entrada de produto","/entradadeproduto"));
            add(new Telas("Fornecedores","/fornecedor"));
            add(new Telas("Motivos da devolução","/motivodevolucao"));
            add(new Telas("Devolução","/devolucao"));
            add(new Telas("Mapeamento Do Estoque","/mapaestoque"));
            add(new Telas("Tipo De Lote","/tipolote"));
            add(new Telas("Estoque externo","/estoqueexterno"));
            add(new Telas("Itens externo","/itensexternos"));
            add(new Telas("Montagem de Carga","/montagemdecarga"));
            add(new Telas("Motorista","/motorista"));
        }};


        telas.put("ERP",telasList);

        return insertNoBanco(telas);
    }

    private List insertNoBanco(HashMap<String, List<Telas>> telas) {
        List<Telas> telasList = telas.get("ERP");
        List<Telas> telasAdicionadas = new ArrayList<>();
        if(telasList != null && !telasList.isEmpty()){
            for (Telas tela : telasList) {
                Telas telaBD = telasRepository.findTelasByNomeTela(tela.getNomeTela());
                if(telaBD == null){
                    telasRepository.save(tela);
                    telasAdicionadas.add(tela);
                }
            }
        }
        return telasAdicionadas;
    }
}
