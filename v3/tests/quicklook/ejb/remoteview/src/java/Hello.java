package remoteview;

import javax.ejb.Remote;
import java.util.concurrent.Future;

@Remote
public interface Hello {

    String hello();

    Future<String> helloAsync();

    Future<String> asyncBlock(int seconds);

    void fireAndForget();
	
    Future<String> asyncThrowException(String exceptionType);

    Future<String> asyncCancel(int seconds) throws Exception;
    
    void throwException(String exceptionType);
}