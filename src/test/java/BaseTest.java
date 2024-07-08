import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;

import static io.restassured.RestAssured.given;

public class BaseTest {
    private String chave = "bad3b3cb3565c9ae477c0ef5a08daa5dc79260c5";
    private RequestSpecification httpRequest;
    private Response response;

    @BeforeTest
    public void beforeTest(){
        RestAssured.baseURI = "https://api.pipedrive.com/v1"; // setando o endereço URL padrão
        httpRequest = RestAssured.given().queryParam("api_token",chave); // setando a chave de acesso. OBS: poderia ficar num properties
    }

    public Response carregaListaUsuarios(){
        response = httpRequest.get("/users");
        return response;
    }
    public Response criarNovoUsuario(String nome,String email){
        JSONObject novoUsuario = new JSONObject();
        //criando array de acessos
        JSONObject acessos = new JSONObject();
        acessos.put("app","sales");
        JSONArray acessoArray = new JSONArray();
        acessoArray.put(acessos);

        // cria o json completo
        novoUsuario.put("email", email);
        novoUsuario.put("access",acessoArray);
        novoUsuario.put("active_flag",true);
        // exibe o json que será enviado
        System.out.println(novoUsuario.toString());

        Response response = httpRequest
                .relaxedHTTPSValidation()
                .header("Content-Type","application/json")
                .body(novoUsuario.toString())
                .post("/users")
                .then()
                .extract().response();
        return response;

    }

    public boolean pesquisarUsuario(String email){
       List<String> valores = httpRequest.get("/users").then().extract().jsonPath().getList("data.email");
       for(int i=0;i< valores.size();i++ ){
           if(valores.get(i).matches(email)) {
               return true;
           }
       }
       return false;
    }
}
