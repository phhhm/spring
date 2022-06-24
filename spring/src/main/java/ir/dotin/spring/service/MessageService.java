package ir.dotin.spring.service;

import com.github.javafaker.Faker;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Service
public class MessageService {

    private final ResourceBundleMessageSource messageSource;

    public MessageService(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String key) {
        return messageSource.getMessage(key, null, Locale.US);
    }

    public static void main(String[] args) {

        Set<String> mySet = new HashSet<>();
      /// List<String> mySet =new ArrayList<>();
        Faker faker =new Faker();

        for (int i = 0; i < 20000000; i++) {
            mySet.add(faker.name().firstName()+" "+i+"  "+faker.name().lastName());
            if (i == 17055400) {
                    mySet.add("mahdi shojaie");
            }
        }

        System.out.println(mySet.size());
        long start =System.currentTimeMillis();
        System.out.println(mySet.contains("mahdi shojaie"));
        long end =System.currentTimeMillis();
        System.out.println(end-start);

    }

}