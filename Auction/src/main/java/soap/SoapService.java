package soap;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService()
@Stateless
public class SoapService {
	
    @WebMethod(operationName="test")
    public String sayHello() {
        return "test";
    }
}
