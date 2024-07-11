import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
/*
Trabalho de desenvolvido por:
Grupo: 2
Integrantes: Diego Carvalho, José Eraldo Dos Santos Junior, Guilherme Nardini, Jéfferson Tales

 */
public class ApiCadastroTest extends BaseTest{
    private Response response;

    @Test
    public void listaCompletaUsuarios(){
       response = carregaListaUsuarios();
        System.out.println(response.prettyPrint());
        Assert.assertEquals(response.getStatusCode(),200);// a api retornou sucesso?
        Assert.assertNotEquals(response.body(),null);// a mensagem retornou vazia?
    }
    @Test
    public void pesquisarUsuario(){
        boolean resultado = pesquisarUsuario("josej");
        Assert.assertEquals(resultado,true); // o usuário foi encontrado com sucesso?
    }
    @Test
    public void criarNovoUsuario(){
       response= criarNovoUsuario("josej","josej@teste.com","5199001010");
        Assert.assertEquals(response.getStatusCode(),200); // a criação do usuário aconteceu com sucesso?
    }

}
