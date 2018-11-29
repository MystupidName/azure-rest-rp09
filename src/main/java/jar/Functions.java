package jar;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import lombok.Data;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Functions {
    
    public ArrayList<Funcionario> f = new ArrayList<>();
    
    @FunctionName("listarFuncionarios")
    public HttpResponseMessage listarFuncionarios(
            @HttpTrigger(name="list", 
                methods = {HttpMethod.GET},
                authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<ArrayList<Funcionario>>> requisicao, final ExecutionContext context){
        
        return requisicao.createResponseBuilder(HttpStatus.OK).body(this.f).build();
    }
    
    @FunctionName("criarFuncionario")
    public Funcionario criarFuncionario(
    @HttpTrigger(
            name = "create",
            methods = {HttpMethod.POST},
            authLevel = AuthorizationLevel.ANONYMOUS) Funcionario func){
        func.setId(UUID.randomUUID());
        f.add(func);
        return func;
    }
    
    @FunctionName("alterarFuncionario")
    public Funcionario alterarFuncionario(
    @HttpTrigger(
            name = "update",
            methods = {HttpMethod.PUT},
            authLevel = AuthorizationLevel.ANONYMOUS) Funcionario func){
        for (Funcionario aux : f){
            if(aux.getId() == func.getId()){
                f.add(f.indexOf(aux), func);
            }
        }
        return func;
    }
    
    @FunctionName("excluirFuncionario")
    public Funcionario excluirFuncionario(
    @HttpTrigger(
            name = "remove",
            methods = {HttpMethod.DELETE},
            authLevel = AuthorizationLevel.ANONYMOUS) Funcionario func){
        for (Funcionario aux : f){
            if(aux.getId() == func.getId()){
                f.remove(f.indexOf(aux));
            }
        }
        return func;
    }
    
    @Data
    class Funcionario {
    
        private UUID id;
        private String nome;
        private int idade;
        private double salario;
    
    }
}


