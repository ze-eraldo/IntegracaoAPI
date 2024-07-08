import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

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
    public void criarNovoUsuario(){
       response= criarNovoUsuario("adian","a1@teste.com");
        Assert.assertEquals(response.getStatusCode(),200); // a criação do usuário aconteceu com sucesso?
    }
    @Test
    public void pesquisarUsuario(){
        boolean resultado = pesquisarUsuario("a2@teste.com");
        Assert.assertEquals(resultado,true); // o usuário foi encontrado com sucesso?
    }
}
